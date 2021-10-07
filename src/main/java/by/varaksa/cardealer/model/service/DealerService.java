package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Dealer;

import java.util.List;

/**
 * Interface {@code DealerService} designed for communication between service
 * and repository for actions related to dealer
 *
 * @author Pavel Varaksa
 */
public interface DealerService {

    /**
     * Save new dealer to the database
     *
     * @param dealer {@code Dealer} dealer
     * @throws ServiceException if service exception happened
     */
    Dealer save(Dealer dealer) throws ServiceException;

    /**
     * Find all dealers from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<Dealer> findAll() throws ServiceException;

    /**
     * Find dealer from the database by id
     *
     * @param id {@code Long} dealer id
     * @throws ServiceException if service exception happened
     */
    Dealer find(Long id) throws ServiceException;

    /**
     * Update dealer from the database by dealer
     *
     * @param dealer {@code Dealer} dealer
     * @throws ServiceException if service exception happened
     */
    Dealer update(Dealer dealer) throws ServiceException;

    /**
     * Delete dealer from the database by id
     *
     * @param id {@code Long} dealer id
     * @throws ServiceException if service exception happened
     */
    Dealer delete(Long id) throws ServiceException;
}
