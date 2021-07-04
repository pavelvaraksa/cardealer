package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Engine;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface EngineService {
    Engine save(Engine engine) throws ServiceException;

    List<Engine> findAll() throws ServiceException;

    Engine find(Long id) throws ServiceException;

    Engine update(Engine engine) throws ServiceException;

    Long delete(Long id) throws ServiceException;
}
