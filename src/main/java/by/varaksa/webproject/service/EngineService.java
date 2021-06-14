package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Engine;
import by.varaksa.webproject.exception.ServiceException;

public interface EngineService {
    Engine find(Long id) throws ServiceException;

    Engine save(Engine engine) throws ServiceException;

    Engine update(Long id) throws ServiceException;

    Engine delete(Long id) throws ServiceException;
}
