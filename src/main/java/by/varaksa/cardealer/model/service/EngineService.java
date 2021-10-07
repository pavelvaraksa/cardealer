package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Engine;

import java.util.List;

/**
 * Interface {@code EngineService} designed for communication between service
 * and repository for actions related to engine
 *
 * @author Pavel Varaksa
 */
public interface EngineService {

    /**
     * Save new engine to the database
     *
     * @param engine {@code Engine} engine
     * @throws ServiceException if service exception happened
     */
    Engine save(Engine engine) throws ServiceException;

    /**
     * Find all engines from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<Engine> findAll() throws ServiceException;

    /**
     * Find engine from the database by id
     *
     * @param id {@code Long} engine id
     * @throws ServiceException if service exception happened
     */
    Engine find(Long id) throws ServiceException;

    /**
     * Update engine from the database by engine
     *
     * @param engine {@code Engine} engine
     * @throws ServiceException if service exception happened
     */
    Engine update(Engine engine) throws ServiceException;

    /**
     * Delete engine from the database by id
     *
     * @param id {@code Long} engine id
     * @throws ServiceException if service exception happened
     */
    Engine delete(Long id) throws ServiceException;
}
