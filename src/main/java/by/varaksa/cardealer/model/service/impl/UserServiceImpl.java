package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.model.repository.UserRepository;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.StringValidator;
import by.varaksa.cardealer.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class {@code UserServiceImpl} designed for communication between service
 * and repository for actions related to user
 *
 * @author Pavel Varaksa
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_FIRSTNAME = RegexpPropertiesReader.getRegexp("firstname.regexp");
    private static final String REGEXP_LASTNAME = RegexpPropertiesReader.getRegexp("lastname.regexp");
    private static final String REGEXP_PHONE_NUMBER = RegexpPropertiesReader.getRegexp("phone.number.regexp");
    private static final String REGEXP_LOGIN = RegexpPropertiesReader.getRegexp("login.regexp");
    private static final String REGEXP_PASSWORD = RegexpPropertiesReader.getRegexp("password.regexp");
    private static final String REGEXP_EMAIL = RegexpPropertiesReader.getRegexp("email.regexp");
    private static final boolean isCheckStringFromUi = true;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkBeforeSave(User user) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_FIRSTNAME, user.getFirstName()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_FIRSTNAME, user.getFirstName()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_LASTNAME, user.getLastName()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_PHONE_NUMBER, String.valueOf(user.getPhoneNumber())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_LOGIN, user.getLogin()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_PASSWORD, user.getPassword()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_EMAIL, user.getEmail()) == isCheckStringFromUi) {

                logger.info("User " + user.getFirstName() + " " + user.getLastName() + " entered correct data");

                List<User> existingUsers = userRepository.findAll();

                for (User existingUser : existingUsers) {
                    boolean hasSameUser = existingUser.getLogin().equals(user.getLogin());

                    if (hasSameUser) {
                        String errorMessage = "User with login " + user.getLogin() + " already exists";
                        logger.error(errorMessage);
                        throw new ServiceException(errorMessage);
                    }
                }

                return user;
            }

            String errorMessage = "Wasn't correct input format for register user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to save user." + exception);
        }
    }

    @Override
    public User save(User user) throws ServiceException {
        try {
            User savedUser = userRepository.save(user);
            logger.info("User " + user.getFirstName() + " " + user.getLastName() + " was saved");
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
            String errorMessage = "Can't find user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_FIRSTNAME, user.getFirstName()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_LASTNAME, user.getLastName()) == isCheckStringFromUi) {
                logger.info("User " + user.getFirstName() + " " + user.getLastName() + " was updated");
                return userRepository.update(user);
            }

            String errorMessage = "Wasn't correct input format for update user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to update user." + exception);
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
            String errorMessage = "Can't delete user";
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
                logger.error("Login " + login + " wasn't found in database");
                return Boolean.parseBoolean(login);
            }

            logger.info("Login " + login + " was found in database");
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

    @Override
    public Long findIdByLogin(String login) throws ServiceException {
        try {
            logger.info("Id = " + userRepository.findIdByLogin(login));
            return userRepository.findIdByLogin(login);
        } catch (RepositoryException exception) {
            String errorMessage = "Id is wasn't found";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
