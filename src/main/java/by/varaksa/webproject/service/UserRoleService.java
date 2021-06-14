package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.UserRole;
import by.varaksa.webproject.exception.ServiceException;

public interface UserRoleService {
    UserRole find(Long id) throws ServiceException;

    UserRole save(UserRole userRole) throws ServiceException;

    UserRole update(Long id) throws ServiceException;

    UserRole delete(Long id) throws ServiceException;
}
