package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.util.EncryptionUserPassword;
import by.varaksa.cardealer.model.connection.ConnectionPool;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.model.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code UserRepositoryImpl} designed for communication between repository
 * and database for actions related to user
 *
 * @author Pavel Varaksa
 */
public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger();

    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String BIRTH_DATE = "birth_date";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String IS_BLOCKED = "is_blocked";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";

    private static final String SAVE_USER = "insert into users (firstname, lastname, birth_date, phone_number, login, password, " +
            "email, role, is_blocked, created, changed) " +
            "values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String FIND_ALL_USERS = "select * from users";
    private static final String FIND_ONE_BY_LOGIN = "select firstname, lastname, birth_date, phone_number, login, " +
            "email, created, changed from users where login = ?";
    private static final String FIND_PASSWORD_BY_LOGIN = "select password from users where login = ?";
    private static final String FIND_USER_BY_ID = "select * from users where id = ?";
    private static final String FIND_USER_BY_LOGIN = "select * from users where login = ?";
    private static final String UPDATE_USER_BY_ID = "update users " +
            "set " +
            "firstname = ?,  " +
            "lastname = ?,  " +
            "birth_date = ?,  " +
            "phone_number = ?,  " +
            "role = ?,  " +
            "is_blocked = ?,  " +
            "changed = ?  " +
            "where id = ?";
    private static final String DELETE_USER_BY_ID = "delete from users where id = ?";
    private static final String CONFIRM_AUTHENTICATE = "select login, password from users where login = ? and password = ?";

    private User parseResultSet(ResultSet resultSet) throws SQLException {
        Date date = resultSet.getDate(BIRTH_DATE);
        LocalDate birthDate = date != null ? date.toLocalDate() : null;

        User user = new User();
        user.setId(resultSet.getLong(ID));
        user.setFirstName(resultSet.getString(FIRSTNAME));
        user.setLastName(resultSet.getString(LASTNAME));
        user.setBirthDate(birthDate);
        user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setRole(Role.valueOf(resultSet.getString(ROLE)));
        user.setBlocked(resultSet.getBoolean(IS_BLOCKED));
        user.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        user.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        return user;
    }

    private User parseResultSetForOne(ResultSet resultSet) throws SQLException {
        Date date = resultSet.getDate(BIRTH_DATE);
        LocalDate birthDate = date != null ? date.toLocalDate() : null;

        User user = new User();
        user.setFirstName(resultSet.getString(FIRSTNAME));
        user.setLastName(resultSet.getString(LASTNAME));
        user.setBirthDate(birthDate);
        user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
        user.setLogin(resultSet.getString(LOGIN));
        user.setEmail(resultSet.getString(EMAIL));
        user.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        user.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        return user;
    }

    @Override
    public User save(User user) {
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        Role defaultSavedUserRole = Role.USER;
        LocalDate birthDate = user.getBirthDate();
        Date date = birthDate != null ? Date.valueOf(birthDate) : null;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, date);
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getLogin());
            statement.setString(6, EncryptionUserPassword.encodePassword(user.getPassword()));
            statement.setString(7, user.getEmail());
            statement.setString(8, String.valueOf(defaultSavedUserRole));
            statement.setBoolean(9, user.isBlocked());
            statement.setTimestamp(10, creationTimestamp);
            statement.setTimestamp(11, creationTimestamp);
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
        List<User> userList = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);

            while (resultSet.next()) {
                userList.add(parseResultSet(resultSet));
            }

            return userList;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User find(Long id) {
        User user = new User();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = parseResultSet(resultSet);
            }

            return user;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User findByLogin(String login) {
        User user = new User();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSet(resultSet);
            }

            return user;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User update(User user) {
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        LocalDate birthDate = user.getBirthDate();
        Date date = birthDate != null ? Date.valueOf(birthDate) : null;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, date);
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, String.valueOf(user.getRole()));
            statement.setBoolean(6, user.isBlocked());
            statement.setTimestamp(7, updateTimestamp);
            statement.setLong(8, user.getId());
            statement.executeUpdate();

            return user;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public User delete(Long id) {
        User user = new User();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
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
        boolean isStatus = false;
        String encryptedPassword;
        String decryptedPassword;
        String enteredPassword = user.getPassword();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement prepareStatementByLogin = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
            prepareStatementByLogin.setString(1, user.getLogin());
            ResultSet resultSet = prepareStatementByLogin.executeQuery();

            while (resultSet.next()) {
                User newUser = new User();
                newUser.setPassword(resultSet.getString("password"));
                encryptedPassword = newUser.getPassword();
                decryptedPassword = EncryptionUserPassword.decodePassword(encryptedPassword);

                if (decryptedPassword.equals(enteredPassword)) {
                    try (PreparedStatement prepareStatementByConfirm = connection.prepareStatement(CONFIRM_AUTHENTICATE)) {

                        prepareStatementByConfirm.setString(1, user.getLogin());
                        prepareStatementByConfirm.setString(2, encryptedPassword);
                        logger.info("Login and password were correct");
                        ResultSet rs = prepareStatementByConfirm.executeQuery();
                        isStatus = rs.next();
                    }
                } else {
                    logger.error("Password wasn't correct");
                }
            }

            return isStatus;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public boolean isUserExist(String login) {
        boolean isStatus = false;
        List<User> allUsers = new ArrayList<>(findAll());

        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                isStatus = true;
                break;
            }
        }

        return isStatus;
    }

    @Override
    public Role findRoleByLogin(String login) {
        Role role = Role.GUEST;
        List<User> allUsers = new ArrayList<>(findAll());

        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                role = user.getRole();
            }
        }

        return role;
    }

    @Override
    public Long findIdByLogin(String login) {
        Long id = null;
        List<User> allUsers = new ArrayList<>(findAll());

        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                id = user.getId();
            }
        }

        return id;
    }

    @Override
    public List<User> findOneByLogin(String login) {
        List<User> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ONE_BY_LOGIN)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(parseResultSetForOne(resultSet));
            }

            return list;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public boolean isBlocking(String login) {
        boolean isBlocked = false;
        List<User> allUsers = new ArrayList<>(findAll());

        for (User user : allUsers) {
            if (user.getLogin().equals(login) && user.isBlocked()) {
                isBlocked = true;
                break;
            }
        }

        return isBlocked;
    }
}

