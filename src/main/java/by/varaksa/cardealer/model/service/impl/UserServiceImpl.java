package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.model.repository.UserRepository;
import by.varaksa.cardealer.model.service.UserService;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_FIRSTNAME = RegexpPropertiesReader.getRegexp("firstname.regexp");
    private static final String REGEXP_LASTNAME = RegexpPropertiesReader.getRegexp("lastname.regexp");
    private static final boolean isCheckStringFromUi = true;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) throws ServiceException {
        try {
            User savedUser = userRepository.save(user);
            logger.info("User with login " + user.getLogin() + " was saved");
            return savedUser;
        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to save user." + exception);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> existingUsers;

        try {
            existingUsers = userRepository.findAll();
            existingUsers = existingUsers.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());

            if (existingUsers.isEmpty()) {
                String errorMessage = "Users list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("Users exist");
                return existingUsers;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to find all users." + exception);
        }
        return existingUsers;
    }

    @Override
    public User find(Long id) throws ServiceException {
        User userToFindById;

        try {
            userToFindById = userRepository.find(id);

            if (userToFindById == null) {
                String errorMessage = "User id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to find user." + exception);
        }

        try {
            logger.info("User with id " + id + " exists");
            return userRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public User update(User user) throws ServiceException {

        try {

            if (UserValidator.isUserValidate(REGEXP_FIRSTNAME, user.getFirstName()) == isCheckStringFromUi &&
                    UserValidator.isUserValidate(REGEXP_LASTNAME, user.getLastName()) == isCheckStringFromUi) {
                logger.info("User " + user.getFirstName() + " " + user.getLastName() + " was updated");
                return userRepository.update(user);
            } else {
                logger.error("User " + user.getFirstName() + " " + user.getLastName() + " wasn't updated");
                return user;
            }
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public User delete(Long id) throws ServiceException {
        User userToFindById;

        try {
            userToFindById = userRepository.find(id);

            if (userToFindById == null) {
                String errorMessage = "User id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to delete user." + exception);
        }

        try {
            logger.info("User " + userToFindById.getFirstName() + " " + userToFindById.getLastName() + " was deleted");
            return userRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public boolean isAuthenticate(User user) throws ServiceException {
        try {
            return userRepository.isAuthenticate(user);
        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to authenticate user." + exception);
        }
    }

    @Override
    public boolean isUserExist(String login) throws ServiceException {
        try {

            if (!userRepository.isUserExist(login)) {
                logger.error("Login " + login + " wasn't correct");
                return Boolean.parseBoolean(login);
            }

            logger.info("Login " + login + " was correct");
            return userRepository.isUserExist(login);
        } catch (RepositoryException exception) {
            String errorMessage = "Login wasn't found";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Role findRoleByLogin(String login) throws ServiceException {
        try {
            logger.info("Role is " + userRepository.findRoleByLogin(login));
            return userRepository.findRoleByLogin(login);
        } catch (RepositoryException exception) {
            String errorMessage = "Role is " + Role.GUEST;
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}