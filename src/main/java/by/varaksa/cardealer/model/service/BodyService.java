package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.model.entity.Body;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

/**
 * Interface {@code BodyService} designed for communication between service
 * and repository for actions related to body
 *
 * @author Pavel Varaksa
 */
public interface BodyService {

    /**
     * Save new body to the database
     *
     * @param body {@code Body} body
     * @throws ServiceException if service exception happened
     */
    Body save(Body body) throws ServiceException;

    /**
     * Find all bodies from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<Body> findAll() throws ServiceException;

    /**
     * Find body from the database by id
     *
     * @param id {@code Long} body id
     * @throws ServiceException if service exception happened
     */
    Body find(Long id) throws ServiceException;

    /**
     * Update body from the database by body
     *
     * @param body {@code Body} body
     * @throws ServiceException if service exception happened
     */
    Body update(Body body) throws ServiceException;

    /**
     * Delete body from the database by id
     *
     * @param id {@code Long} body id
     * @throws ServiceException if service exception happened
     */
    Body delete(Long id) throws ServiceException;
}
