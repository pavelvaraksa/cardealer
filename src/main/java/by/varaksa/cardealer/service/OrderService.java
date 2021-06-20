package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Order;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> findAll() throws ServiceException;

    Order find(Long id) throws ServiceException;

    Order save(Order order) throws ServiceException;

    Order update(Long id) throws ServiceException;

    Order delete(Long id) throws ServiceException;
}
