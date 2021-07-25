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
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

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

    private static final String SAVE_USER_ROLE = "insert into roles (role_name, user_id) " +
            "values (?,?)";
    private static final String FIND_ALL_USER_ROLES = "select * from roles";
    private static final String FIND_USER_ROLE_BY_ID = "select * from userRoles where id = ?";
    private static final String UPDATE_USER_ROLE_BY_ID = "update roles " +
            "set " +
            "role_name = ?,  " +
            "user_id = ?  " +
            "where id = ?";
    private static final String DELETE_USER_ROLE_BY_ID = "delete from roles where id = ?";

    @Override
    public UserRole save(UserRole userRole) throws RepositoryException {
        Connection connection;
        PreparedStatement statement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(SAVE_USER_ROLE);

            statement.setString(1, String.valueOf(userRole.getRoleName()));
            statement.setLong(2, userRole.getUserId());
            statement.executeUpdate();

            return userRole;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<UserRole> findAll() {
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
            resultSet = statement.executeQuery(FIND_ALL_USER_ROLES);

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
    public UserRole find(Long id) throws RepositoryException {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(FIND_USER_ROLE_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
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
        Connection connection;
        PreparedStatement statement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(UPDATE_USER_ROLE_BY_ID);

            statement.setString(1, String.valueOf(userRole.getRoleName()));
            statement.setLong(2, userRole.getUserId());
            statement.setLong(3, userRole.getId());
            statement.executeUpdate();

            return userRole;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserRole delete(Long id) {
        Connection connection;
        PreparedStatement statement;
        UserRole userRole = new UserRole();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(DELETE_USER_ROLE_BY_ID);
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
            logger.info("JDBC driver was loaded from user role repository class");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded from user role repository class." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
