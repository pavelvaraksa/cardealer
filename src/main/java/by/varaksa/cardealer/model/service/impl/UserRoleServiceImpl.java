package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.UserRole;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.UserRoleRepository;
import by.varaksa.cardealer.model.service.UserRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {
    private static final Logger logger = LogManager.getLogger();
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole save(UserRole userRole) throws ServiceException {
        try {
            UserRole savedUserRole = userRoleRepository.save(userRole);
            logger.info("User role " + userRole + " was saved");
            return savedUserRole;
        } catch (RepositoryException exception) {
            throw new ServiceException("User role service exception while trying to save user role." + exception);
        }
    }

    @Override
    public List<UserRole> findAll() throws ServiceException {
        List<UserRole> existingUserRoles;

        try {
            existingUserRoles = userRoleRepository.findAll();

            if (existingUserRoles.isEmpty()) {
                String errorMessage = "User roles list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("User roles exist");
                return existingUserRoles;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("User role service exception while trying to find all user roles." + exception);
        }
        return existingUserRoles;
    }

    @Override
    public UserRole find(Long id) throws ServiceException {
        UserRole userRoleToFindById;

        try {
            userRoleToFindById = userRoleRepository.find(id);
            if (userRoleToFindById == null) {
                String errorMessage = "User role id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("User role service exception while trying to find user role." + exception);
        }

        try {
            logger.info("User role with id " + id + " exists");
            return userRoleRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user role";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public UserRole update(UserRole userRole) throws ServiceException {
        try {
            logger.info("User role with id " + userRole.getId() + " was updated");
            return userRoleRepository.update(userRole);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user role";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public UserRole delete(Long id) throws ServiceException {
        UserRole userRoleToFindById;

        try {
            userRoleToFindById = userRoleRepository.find(id);
            if (userRoleToFindById == null) {
                String errorMessage = "User role id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("User role service exception while trying to delete user role." + exception);
        }

        try {
            logger.info("User role with id " + id + " was deleted");
            return userRoleRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user role";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
