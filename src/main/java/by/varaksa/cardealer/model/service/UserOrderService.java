package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.UserOrder;

import java.util.List;

/**
 * Interface {@code UserOrderService} designed for communication between service
 * and repository for actions related to user order
 *
 * @author Pavel Varaksa
 */
public interface UserOrderService {

    /**
     * Save new user order to the database
     *
     * @param userOrder {@code UserOrder} user order
     * @throws ServiceException if service exception happened
     */
    UserOrder save(UserOrder userOrder) throws ServiceException;

    /**
     * Find all user orders from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<UserOrder> findAll() throws ServiceException;

    /**
     * Find user order from the database by id
     *
     * @param id {@code Long} user order id
     * @throws ServiceException if service exception happened
     */
    UserOrder find(Long id) throws ServiceException;

    /**
     * Update user order from the database by user order
     *
     * @param userOrder {@code UserOrder} user order
     * @throws ServiceException if service exception happened
     */
    UserOrder update(UserOrder userOrder) throws ServiceException;

    /**
     * Delete user order from the database by id
     *
     * @param id {@code Long} user order id
     * @throws ServiceException if service exception happened
     */
    UserOrder delete(Long id) throws ServiceException;
}
