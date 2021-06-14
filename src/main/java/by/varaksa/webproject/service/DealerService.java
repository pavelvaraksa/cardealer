package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Dealer;
import by.varaksa.webproject.exception.ServiceException;

public interface DealerService {
    Dealer find(Long id) throws ServiceException;

    Dealer save(Dealer dealer) throws ServiceException;

    Dealer update(Long id) throws ServiceException;

    Dealer delete(Long id) throws ServiceException;
}
