package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface BodyService {
    List<Body> findAll() throws ServiceException;

    Body find(Long id) throws ServiceException;

    Body save(Body body) throws ServiceException;

    Body update(Long id) throws ServiceException;

    Body delete(Long id) throws ServiceException;
}
