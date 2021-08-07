package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.model.entity.UserRole;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserRoleService {
    UserRole save(UserRole userRole) throws ServiceException;

    List<UserRole> findAll() throws ServiceException;

    UserRole find(Long id) throws ServiceException;

    UserRole update(UserRole userRole) throws ServiceException;

    UserRole delete(Long id) throws ServiceException;
}