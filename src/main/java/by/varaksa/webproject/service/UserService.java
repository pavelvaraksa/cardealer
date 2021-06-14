package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.User;
import by.varaksa.webproject.exception.ServiceException;

public interface UserService {
    User find(Long id) throws ServiceException;

    User save(User user) throws ServiceException;

    User update(Long id) throws ServiceException;

    User delete(Long id) throws ServiceException;
}
