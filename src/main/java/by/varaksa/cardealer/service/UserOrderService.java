package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.UserOrder;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserOrderService {
    List<UserOrder> findAll() throws ServiceException;

    UserOrder find(Long id) throws ServiceException;

    UserOrder save(UserOrder userOrder) throws ServiceException;

    UserOrder update(Long id) throws ServiceException;

    UserOrder delete(Long id) throws ServiceException;
}
