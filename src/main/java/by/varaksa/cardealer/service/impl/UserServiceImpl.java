package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.UserRepository;
import by.varaksa.cardealer.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) throws ServiceException {
        List<User> existingUsers;

        try {
            existingUsers = userRepository.findAll();
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get all users";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (User existingUser : existingUsers) {
            boolean hasSameUser = existingUser.getLogin().equals(user.getLogin());

            if (hasSameUser) {
                String errorMessage = "User with login " + user.getLogin() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

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

            if (existingUsers.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("Users exist");
                return existingUsers;
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("User service exception while trying to find all users." + exception);
        }
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
            logger.info("User with id " + user.getId() + " was updated");
            return userRepository.update(user);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Long delete(Long id) throws ServiceException {
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
            logger.info("User with id " + id + " was deleted");
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
}