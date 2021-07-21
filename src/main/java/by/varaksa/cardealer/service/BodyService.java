package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface BodyService {
    Body save(Body body) throws ServiceException;

    List<Body> findAll() throws ServiceException;

    Body find(Long id) throws ServiceException;

    Body update(Body body) throws ServiceException;

    Body delete(Long id) throws ServiceException;
}
