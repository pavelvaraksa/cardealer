package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Role;
import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.repository.UserRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;

public class UserRepositoryImpl implements UserRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String BIRTH_DATE = "birth_date";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String IS_BLOCKED = "is_blocked";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";

    private User parseResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setBirthDate(resultSet.getDate(BIRTH_DATE).toLocalDate());
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setRole(Role.valueOf(resultSet.getString(ROLE)));
        user.setBlocked(resultSet.getBoolean(IS_BLOCKED));
        user.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        user.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        return user;
    }

    @Override
    public User save(User user) {
        final String saveUser = "insert into users (name, surname, birth_date, login, password, " +
                "role, is_blocked, created, changed) " +
                "values (?,?,?,?,?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(saveUser);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDate(3, Date.valueOf(user.getBirthDate()));
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, String.valueOf(user.getRole()));
            statement.setBoolean(7, user.isBlocked());
            statement.setTimestamp(8, creationTimestamp);
            statement.setTimestamp(9, creationTimestamp);

            statement.executeUpdate();

            logger.info("User with login " + user.getLogin() + " was saved");
            return user;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<User> findAll() {
        final String findAllUsers = "select * from users";

        List<User> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllUsers);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All users: " + result);
            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User find(Long id) {
        final String findUserById = "select * from users where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findUserById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            return parseResultSet(resultSet);
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User update(User user) {
        final String updateUserById = "update users " +
                "set " +
                "name = ?,  " +
                "surname = ?,  " +
                "birth_date = ?,  " +
                "login = ?,  " +
                "password = ?,  " +
                "role = ?,  " +
                "is_blocked = ?,  " +
                "changed = ?  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(updateUserById);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDate(3, Date.valueOf(user.getBirthDate()));
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, String.valueOf(user.getRole()));
            statement.setBoolean(7, user.isBlocked());
            statement.setTimestamp(8, updateTimestamp);
            statement.setLong(9, user.getId());
            statement.executeUpdate();

            logger.info("User with id " + user.getId() + " was updated");
            return find(user.getId());
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Long delete(Long id) {
        final String deleteUserById = "delete from users where id = ?";

        Connection connection;
        PreparedStatement statement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(deleteUserById);
            statement.setLong(1, id);

            int deletedRows = statement.executeUpdate();
            logger.info("User with id " + id + " was deleted");
            return (long) deletedRows;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public boolean isLoginValidate(User user) {
        final String login = "select login, password from users where login = ? and password = ?";
        boolean isStatus;

        Connection connection;
        PreparedStatement preparedStatement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            preparedStatement = connection.prepareStatement(login);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            isStatus = rs.next();
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        return isStatus;
    }

    private void connect() {

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver be loaded");
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
