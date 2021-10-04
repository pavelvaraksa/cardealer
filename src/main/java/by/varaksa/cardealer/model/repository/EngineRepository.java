package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.model.entity.Engine;

/**
 * Interface {@code EngineRepository} designed for communication between repository
 * and database for actions related to engine
 *
 * @author Pavel Varaksa
 */
public interface EngineRepository extends CrudRepository<Long, Engine> {
}