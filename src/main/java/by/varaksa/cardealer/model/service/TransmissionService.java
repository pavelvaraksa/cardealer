package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.model.entity.Transmission;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

/**
 * Interface {@code TransmissionService} designed for communication between service
 * and repository for actions related to transmission
 *
 * @author Pavel Varaksa
 */
public interface TransmissionService {

    /**
     * Save new transmission to the database
     *
     * @param transmission {@code Transmission} transmission
     * @throws ServiceException if service exception happened
     */
    Transmission save(Transmission transmission) throws ServiceException;

    /**
     * Find all transmissions from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<Transmission> findAll() throws ServiceException;

    /**
     * Find transmission from the database by id
     *
     * @param id {@code Long} transmission id
     * @throws ServiceException if service exception happened
     */
    Transmission find(Long id) throws ServiceException;

    /**
     * Update transmission from the database by transmission
     *
     * @param transmission {@code Transmission} transmission
     * @throws ServiceException if service exception happened
     */
    Transmission update(Transmission transmission) throws ServiceException;

    /**
     * Delete transmission from the database by id
     *
     * @param id {@code Long} transmission id
     * @throws ServiceException if service exception happened
     */
    Transmission delete(Long id) throws ServiceException;
}
