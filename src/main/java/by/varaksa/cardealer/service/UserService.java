package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserService {
    User save(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User find(Long id) throws ServiceException;

    User update(User user) throws ServiceException;

    Long delete(Long id) throws ServiceException;
}
