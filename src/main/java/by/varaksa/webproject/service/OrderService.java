package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Order;
import by.varaksa.webproject.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> findAll() throws ServiceException;

    Order find(Long id) throws ServiceException;

    Order save(Order order) throws ServiceException;

    Order update(Long id) throws ServiceException;

    Order delete(Long id) throws ServiceException;
}
