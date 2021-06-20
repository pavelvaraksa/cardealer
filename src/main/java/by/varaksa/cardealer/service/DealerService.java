package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Dealer;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface DealerService {
    List<Dealer> findAll() throws ServiceException;

    Dealer find(Long id) throws ServiceException;

    Dealer save(Dealer dealer) throws ServiceException;

    Dealer update(Long id) throws ServiceException;

    Dealer delete(Long id) throws ServiceException;
}
