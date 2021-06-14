package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Body;
import by.varaksa.webproject.exception.ServiceException;

public interface BodyService {
    Body find(Long id) throws ServiceException;

    Body save(Body body) throws ServiceException;

    Body update(Long id) throws ServiceException;

    Body delete(Long id) throws ServiceException;
}
