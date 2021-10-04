package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.model.entity.UserOrder;

/**
 * Interface {@code UserOrderRepository} designed for communication between repository
 * and database for actions related to user order
 *
 * @author Pavel Varaksa
 */
public interface UserOrderRepository extends CrudRepository<Long, UserOrder> {
}
