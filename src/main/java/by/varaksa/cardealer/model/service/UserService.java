package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;

import java.util.List;

/**
 * Interface {@code UserService} designed for communication between service
 * and repository for actions related to user
 *
 * @author Pavel Varaksa
 */
public interface UserService {

    /**
     * Save new user to the database
     *
     * @param user {@code User} user
     * @throws ServiceException if service exception happened
     */
    User save(User user) throws ServiceException;

    /**
     * Find all users from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<User> findAll() throws ServiceException;

    /**
     * Find user from the database by id
     *
     * @param id {@code Long} user id
     * @throws ServiceException if service exception happened
     */
    User find(Long id) throws ServiceException;

    /**
     * Update user from the database by user
     *
     * @param user {@code User} user
     * @throws ServiceException if service exception happened
     */
    User update(User user) throws ServiceException;

    /**
     * Delete user from the database by id
     *
     * @param id {@code Long} user id
     * @throws ServiceException if service exception happened
     */
    User delete(Long id) throws ServiceException;

    /**
     * Check user before saving
     *
     * @param user {@code User} user
     * @throws ServiceException if service exception happened
     */
    User checkBeforeSave(User user) throws ServiceException;

    /**
     * Authenticating user
     *
     * @param user {@code User} authenticating user
     * @return {@code true} if user login and password were correct {@code false} if user login and password weren't correct
     * @throws ServiceException if service exception happened
     */
    boolean isAuthenticate(User user) throws ServiceException;

    /**
     * Find existing user
     *
     * @param login {@code String} existing user
     * @return {@code true} if user login was found {@code false} if user login wasn't found
     * @throws ServiceException if service exception happened
     */
    boolean isUserExist(String login) throws ServiceException;

    /**
     * Find user role by login
     *
     * @param login {@code String} user role by login
     * @return {@code Role} if user role was user or admin
     * @throws ServiceException if service exception happened
     */
    Role findRoleByLogin(String login) throws ServiceException;

    /**
     * Find user id by login
     *
     * @param login {@code String} user id by login
     * @throws ServiceException if service exception happened
     */
    Long findIdByLogin(String login) throws ServiceException;

    /**
     * Find user by login
     *
     * @param login {@code String} user by login
     * @throws ServiceException if service exception happened
     */
    List<User> findOneByLogin(String login) throws ServiceException;

    /**
     * Find user by login
     *
     * @param login {@code String} user by login
     * @throws ServiceException if service exception happened
     */
    User findByLogin(String login) throws ServiceException;

    /**
     * Find user blocking by login
     *
     * @param login {@code String} user blocking by login
     * @throws ServiceException if service exception happened
     */
    boolean isBlocking(String login) throws ServiceException;
}
