package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;

/**
 * Interface {@code UserRepository} designed for communication between repository
 * and database for actions related to user
 *
 * @author Pavel Varaksa
 */
public interface UserRepository extends CrudRepository<Long, User> {

    /**
     * Authenticating user
     *
     * @param user {@code User} authenticating user
     * @return {@code true} if user login and password were correct {@code false} if user login and password weren't correct
     * @throws RepositoryException if repository exception happened
     */
    boolean isAuthenticate(User user) throws RepositoryException;

    /**
     * Find existing user
     *
     * @param login {@code String} existing user
     * @return {@code true} if user login was found {@code false} if user login wasn't found
     * @throws RepositoryException if repository exception happened
     */
    boolean isUserExist(String login) throws RepositoryException;

    /**
     * Find user role by login
     *
     * @param login {@code String} user role by login
     * @return {@code Role} if user role was user or admin
     * @throws RepositoryException if repository exception happened
     */
    Role findRoleByLogin(String login) throws RepositoryException;

    /**
     * Find user id by login
     *
     * @param login {@code String} user id by login
     * @throws RepositoryException if repository exception happened
     */
    Long findIdByLogin(String login) throws RepositoryException;
}
