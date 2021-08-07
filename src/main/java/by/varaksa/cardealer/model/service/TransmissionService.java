package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.model.entity.Transmission;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface TransmissionService {
    Transmission save(Transmission transmission) throws ServiceException;

    List<Transmission> findAll() throws ServiceException;

    Transmission find(Long id) throws ServiceException;

    Transmission update(Transmission transmission) throws ServiceException;

    Transmission delete(Long id) throws ServiceException;
}