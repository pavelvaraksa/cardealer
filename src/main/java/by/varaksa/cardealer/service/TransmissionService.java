package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Transmission;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface TransmissionService {
    List<Transmission> findAll() throws ServiceException;

    Transmission find(Long id) throws ServiceException;

    Transmission save(Transmission transmission) throws ServiceException;

    Transmission update(Long id) throws ServiceException;

    Transmission delete(Long id) throws ServiceException;
}
