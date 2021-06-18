package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Dealer;
import by.varaksa.webproject.exception.ServiceException;

import java.util.List;

public interface DealerService {
    List<Dealer> findAll() throws ServiceException;

    Dealer find(Long id) throws ServiceException;

    Dealer save(Dealer dealer) throws ServiceException;

    Dealer update(Long id) throws ServiceException;

    Dealer delete(Long id) throws ServiceException;
}
