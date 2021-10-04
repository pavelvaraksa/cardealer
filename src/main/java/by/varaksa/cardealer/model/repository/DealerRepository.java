package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.model.entity.Dealer;

/**
 * Interface {@code DealerRepository} designed for communication between repository
 * and database for actions related to dealer
 *
 * @author Pavel Varaksa
 */
public interface DealerRepository extends CrudRepository<Long, Dealer> {
}
