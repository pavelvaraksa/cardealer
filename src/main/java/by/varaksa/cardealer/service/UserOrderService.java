package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.UserOrder;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserOrderService {
    UserOrder save(UserOrder userOrder) throws ServiceException;

    List<UserOrder> findAll() throws ServiceException;

    UserOrder find(Long id) throws ServiceException;

    UserOrder update(UserOrder userOrder) throws ServiceException;

    Long delete(Long id) throws ServiceException;
}
