package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Transmission;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface TransmissionService {
    Transmission save(Transmission transmission) throws ServiceException;

    List<Transmission> findAll() throws ServiceException;

    Transmission find(Long id) throws ServiceException;

    Transmission update(Transmission transmission) throws ServiceException;

    Long delete(Long id) throws ServiceException;
}
