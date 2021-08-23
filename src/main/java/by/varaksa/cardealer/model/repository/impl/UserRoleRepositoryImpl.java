package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.model.connection.ConnectionPool;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.UserRole;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.repository.UserRoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleRepositoryImpl implements UserRoleRepository {
    private static final Logger logger = LogManager.getLogger();

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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_USER_ROLE)) {

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

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USER_ROLES);

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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_ROLE_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE_BY_ID)) {

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
        UserRole userRole = new UserRole();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_ROLE_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            return userRole;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
