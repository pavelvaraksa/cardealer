package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.UserRole;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.UserRoleRepository;
import by.varaksa.cardealer.service.UserRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {
    private static Logger logger = LogManager.getLogger();
    private UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole save(UserRole userRole) throws ServiceException {
        List<UserRole> existingUserRoles;

        try {
            existingUserRoles = userRoleRepository.findAll();
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get all user roles";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (UserRole existingUserRole : existingUserRoles) {
            boolean hasSameUserRole = existingUserRole.getId().equals(userRole.getId());

            if (hasSameUserRole) {
                String errorMessage = "User role with id " + userRole.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            UserRole savedUserRole = userRoleRepository.save(userRole);
            logger.info("User role " + userRole + " was saved");
            return savedUserRole;
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User role service exception while trying to save an user role." + stackTrace);
        }
    }

    @Override
    public List<UserRole> findAll() throws ServiceException {
        List<UserRole> existingUserRoles;

        try {
            existingUserRoles = userRoleRepository.findAll();
            if (existingUserRoles.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("User roles exist");
                return existingUserRoles;
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User role service exception while trying to find all user roles." + stackTrace);
        }
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User role service exception while trying to find an user role." + stackTrace);
        }

        try {
            logger.info("User role with id " + id + " exists");
            return userRoleRepository.find(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user role";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public UserRole update(UserRole userRole) throws ServiceException {

        try {
            logger.info("User role with id " + userRole.getId() + " was updated");
            return userRoleRepository.update(userRole);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user role";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Long delete(UserRole userRole) throws ServiceException {
        UserRole userRoleToFindById;

        try {
            userRoleToFindById = userRoleRepository.find(userRole.getId());
            if (userRoleToFindById == null) {
                String errorMessage = "User role id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User role service exception while trying to delete an user role." + stackTrace);
        }

        try {
            logger.info("User role with id " + userRole.getId() + " was deleted");
            return userRoleRepository.delete(userRole);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user role";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
