package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> findAll() throws ServiceException;

    User find(Long id) throws ServiceException;

    User save(User user) throws ServiceException;

    User update(Long id) throws ServiceException;

    User delete(Long id) throws ServiceException;
}
