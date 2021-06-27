package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Order;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface OrderService {
    Order save(Order order) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    Order find(Long id) throws ServiceException;

    Order update(Order order) throws ServiceException;

    Long delete(Order order) throws ServiceException;
}
