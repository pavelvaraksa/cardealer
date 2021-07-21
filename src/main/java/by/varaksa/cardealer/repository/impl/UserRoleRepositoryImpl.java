package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Role;
import by.varaksa.cardealer.entity.UserRole;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.UserRoleRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;

public class UserRoleRepositoryImpl implements UserRoleRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String ROLE_NAME = "role_name";
    private static final String USER_ID = "user_id";

    private UserRole parseResultSet(ResultSet resultSet) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getLong(ID));
        userRole.setRoleName(Role.valueOf(resultSet.getString(ROLE_NAME)));
        userRole.setUserId(resultSet.getLong(USER_ID));
        return userRole;
    }

    @Override
    public UserRole save(UserRole userRole) throws RepositoryException {
        final String saveUserRole = "insert into roles (role_name, user_id) " +
                "values (?,?)";

        Connection connection;
        PreparedStatement statement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(saveUserRole);

            statement.setString(1, String.valueOf(userRole.getRoleName()));
            statement.setLong(2, userRole.getUserId());
            statement.executeUpdate();

            logger.info("User role with id " + userRole.getId() + " was saved");
            return userRole;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<UserRole> findAll() {
        final String findAllUserRoles = "select * from roles";

        List<UserRole> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllUserRoles);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All user roles: " + result);
            return result;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserRole find(Long id) throws RepositoryException {
        final String findUserRoleById = "select * from userRoles where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findUserRoleById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("User role with id " + id + " was found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("User role with id " + id + " wasn't found");
            }

        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserRole update(UserRole userRole) {
        final String updateUserRoleById = "update roles " +
                "set " +
                "role_name = ?,  " +
                "user_id = ?  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(updateUserRoleById);

            statement.setString(1, String.valueOf(userRole.getRoleName()));
            statement.setLong(2, userRole.getUserId());
            statement.setLong(3, userRole.getId());
            statement.executeUpdate();

            logger.info("User role with id " + userRole.getId() + " was updated");
            return userRole;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserRole delete(Long id) {
        final String deleteUserRoleById = "delete from roles where id = ?";

        Connection connection;
        PreparedStatement statement;
        UserRole userRole = new UserRole();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(deleteUserRoleById);
            statement.setLong(1, id);
            statement.executeUpdate();

            return userRole;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver be loaded");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver can't be loaded." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
