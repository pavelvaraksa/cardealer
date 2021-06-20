package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.UserRole;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserRoleService {
    List<UserRole> findAll() throws ServiceException;

    UserRole find(Long id) throws ServiceException;

    UserRole save(UserRole userRole) throws ServiceException;

    UserRole update(Long id) throws ServiceException;

    UserRole delete(Long id) throws ServiceException;
}
