package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Transmission;
import by.varaksa.webproject.exception.ServiceException;

public interface TransmissionService {
    Transmission find(Long id) throws ServiceException;

    Transmission save(Transmission transmission) throws ServiceException;

    Transmission update(Long id) throws ServiceException;

    Transmission delete(Long id) throws ServiceException;
}
