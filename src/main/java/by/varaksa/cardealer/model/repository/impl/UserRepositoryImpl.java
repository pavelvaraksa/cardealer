package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.model.connection.PoolConnection;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger();

    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String BIRTH_DATE = "birth_date";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String IS_BLOCKED = "is_blocked";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";

    private static final String SAVE_USER = "insert into users (firstname, lastname, birth_date, login, password, " +
            "email, role, is_blocked, created, changed) " +
            "values (?,?,?,?,?,?,?,?,?,?)";
    private static final String FIND_ALL_USERS = "select * from users";
    private static final String FIND_USER_BY_ID = "select * from users where id = ?";
    private static final String UPDATE_USER_BY_ID = "update users " +
            "set " +
            "firstname = ?,  " +
            "lastname = ?,  " +
            "birth_date = ?,  " +
            "login = ?,  " +
            "password = ?,  " +
            "email = ?,  " +
            "role = ?,  " +
            "is_blocked = ?,  " +
            "changed = ?  " +
            "where id = ?";
    private static final String DELETE_USER_BY_ID = "delete from users where id = ?";
    private static final String CONFIRM_AUTHENTICATE = "select login, password from users where login = ? and password = ?";

    private User parseResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID));
        user.setFirstName(resultSet.getString(FIRSTNAME));
        user.setLastName(resultSet.getString(LASTNAME));
        user.setBirthDate(resultSet.getDate(BIRTH_DATE).toLocalDate());
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setRole(Role.valueOf(resultSet.getString(ROLE)));
        user.setBlocked(resultSet.getBoolean(IS_BLOCKED));
        user.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        user.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        return user;
    }

    @Override
    public User save(User user) {
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        Role defaultSavedUserRole = Role.USER;

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_USER)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, Date.valueOf(user.getBirthDate()));
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getEmail());
            statement.setString(7, String.valueOf(defaultSavedUserRole));
            statement.setBoolean(8, user.isBlocked());
            statement.setTimestamp(9, creationTimestamp);
            statement.setTimestamp(10, creationTimestamp);
            statement.executeUpdate();

            return user;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();

        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            return result;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User find(Long id) {
        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("User with id " + id + " wasn't found");
            }

        } catch (SQLException | RepositoryException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User update(User user) {
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, Date.valueOf(user.getBirthDate()));
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getEmail());
            statement.setString(7, String.valueOf(user.getRole()));
            statement.setBoolean(8, user.isBlocked());
            statement.setTimestamp(9, updateTimestamp);
            statement.setLong(10, user.getId());
            statement.executeUpdate();

            return find(user.getId());
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User delete(Long id) {
        User user = new User();

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            return user;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public boolean isAuthenticate(User user) {
        boolean isStatus;

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CONFIRM_AUTHENTICATE)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            logger.info(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            isStatus = rs.next();
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        return isStatus;
    }

    @Override
    public void logOut(User user) {
        try (Connection connection = PoolConnection.getInstance().getConnection()) {
            logger.info(connection);
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}

