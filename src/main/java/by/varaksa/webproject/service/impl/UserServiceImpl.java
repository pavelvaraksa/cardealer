package by.varaksa.webproject.service.impl;

import by.varaksa.webproject.entity.User;
import by.varaksa.webproject.exception.RepositoryException;
import by.varaksa.webproject.exception.ServiceException;
import by.varaksa.webproject.repository.UserRepository;
import by.varaksa.webproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User service exception while trying to find all users." + stackTrace.getMessage());
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User service exception while trying to find an user." + stackTrace.getMessage());
        }

        try {
            logger.info("User with id " + id + " exists");
            return userRepository.find(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public User save(User user) throws ServiceException {
        List<User> existingUsers;

        try {
            existingUsers = userRepository.findAll();
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get all users";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (User existingUser : existingUsers) {
            boolean hasSameUser = existingUser.getId().equals(user.getId());

            if (hasSameUser) {
                String errorMessage = "User with id " + user.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            User savedUser = userRepository.save(user);
            logger.info("User " + user + " was saved");
            return savedUser;
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User service exception while trying to save an user." + stackTrace.getMessage());
        }
    }

    @Override
    public User update(Long id) throws ServiceException {

        try {
            logger.info("User with id " + id + " was updated");
            return userRepository.update(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user";
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User service exception while trying to delete an user." + stackTrace.getMessage());
        }

        try {
            logger.info("User with id " + id + " was deleted");
            return userRepository.delete(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
