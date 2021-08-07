package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.model.entity.Engine;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface EngineService {
    Engine save(Engine engine) throws ServiceException;

    List<Engine> findAll() throws ServiceException;

    Engine find(Long id) throws ServiceException;

    Engine update(Engine engine) throws ServiceException;

    Engine delete(Long id) throws ServiceException;
}
