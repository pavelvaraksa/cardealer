package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;

import java.util.List;

public interface UserService {
    User save(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User find(Long id) throws ServiceException;

    User update(User user) throws ServiceException;

    User delete(Long id) throws ServiceException;

    boolean isAuthenticate(User user) throws ServiceException;

    boolean isUserExist(String login) throws ServiceException;

    Role findRoleByLogin(String login) throws ServiceException;
}
