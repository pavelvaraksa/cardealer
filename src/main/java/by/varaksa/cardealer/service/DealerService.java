package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Dealer;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface DealerService {
    Dealer save(Dealer dealer) throws ServiceException;

    List<Dealer> findAll() throws ServiceException;

    Dealer find(Long id) throws ServiceException;

    Dealer update(Dealer dealer) throws ServiceException;

    Long delete(Dealer dealer) throws ServiceException;
}
