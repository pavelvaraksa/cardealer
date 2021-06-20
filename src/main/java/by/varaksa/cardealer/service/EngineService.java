package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Engine;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface EngineService {
    List<Engine> findAll() throws ServiceException;

    Engine find(Long id) throws ServiceException;

    Engine save(Engine engine) throws ServiceException;

    Engine update(Long id) throws ServiceException;

    Engine delete(Long id) throws ServiceException;
}
