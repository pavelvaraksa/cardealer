package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Transmission;
import by.varaksa.webproject.exception.ServiceException;

import java.util.List;

public interface TransmissionService {
    List<Transmission> findAll() throws ServiceException;

    Transmission find(Long id) throws ServiceException;

    Transmission save(Transmission transmission) throws ServiceException;

    Transmission update(Long id) throws ServiceException;

    Transmission delete(Long id) throws ServiceException;
}
