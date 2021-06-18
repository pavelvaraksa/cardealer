package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.User;
import by.varaksa.webproject.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> findAll() throws ServiceException;

    User find(Long id) throws ServiceException;

    User save(User user) throws ServiceException;

    User update(Long id) throws ServiceException;

    User delete(Long id) throws ServiceException;
}
