package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.model.entity.UserOrder;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface UserOrderService {
    UserOrder save(UserOrder userOrder) throws ServiceException;

    List<UserOrder> findAll() throws ServiceException;

    UserOrder find(Long id) throws ServiceException;

    UserOrder update(UserOrder userOrder) throws ServiceException;

    UserOrder delete(Long id) throws ServiceException;
}
