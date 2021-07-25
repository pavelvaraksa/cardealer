package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Role;
import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.RepositoryException;
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
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

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
//    private static boolean check;
//    private static String checkString;

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
        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        Role defaultSavedUserRole = Role.USER;
//        checkString = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
//        check = BCrypt.checkpw(user.getPassword(), checkString);

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(SAVE_USER);

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
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_USERS);

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
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(FIND_USER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

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
        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(UPDATE_USER_BY_ID);

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
        Connection connection;
        PreparedStatement statement;
        User user = new User();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(DELETE_USER_BY_ID);
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
        Connection connection;
        PreparedStatement preparedStatement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            preparedStatement = connection.prepareStatement(CONFIRM_AUTHENTICATE);
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
        Connection connection;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));

            logger.info(connection);
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver was loaded from user repository class");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded from user repository class." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}

