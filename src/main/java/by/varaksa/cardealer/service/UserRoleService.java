package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.UserRole;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserRoleService {
    UserRole save(UserRole userRole) throws ServiceException;

    List<UserRole> findAll() throws ServiceException;

    UserRole find(Long id) throws ServiceException;

    UserRole update(UserRole userRole) throws ServiceException;

    Long delete(UserRole userRole) throws ServiceException;
}
