package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.entity.UserOrder;

import java.util.List;

/**
 * Interface {@code UserOrderRepository} designed for communication between repository
 * and database for actions related to user order
 *
 * @author Pavel Varaksa
 */
public interface UserOrderRepository extends CrudRepository<Long, UserOrder> {

    /**
     * Find all user orders for user from the database
     *
     * @throws RepositoryException if repository exception happened
     */
    List<UserOrder> findAllForUser(String login) throws RepositoryException;
}
