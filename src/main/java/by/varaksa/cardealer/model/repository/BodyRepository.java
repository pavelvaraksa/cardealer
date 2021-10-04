package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.model.entity.Body;

/**
 * Interface {@code BodyRepository} designed for communication between repository
 * and database for actions related to body
 *
 * @author Pavel Varaksa
 */
public interface BodyRepository extends CrudRepository<Long, Body> {
}
