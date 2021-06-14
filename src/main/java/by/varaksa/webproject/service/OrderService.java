package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Order;
import by.varaksa.webproject.exception.ServiceException;

public interface OrderService {
    Order find(Long id) throws ServiceException;

    Order save(Order order) throws ServiceException;

    Order update(Long id) throws ServiceException;

    Order delete(Long id) throws ServiceException;
}
