package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.UserOrder;
import by.varaksa.webproject.exception.ServiceException;

public interface UserOrderService {
    UserOrder find(Long id) throws ServiceException;

    UserOrder save(UserOrder userOrder) throws ServiceException;

    UserOrder update(Long id) throws ServiceException;

    UserOrder delete(Long id) throws ServiceException;
}
