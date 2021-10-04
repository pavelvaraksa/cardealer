package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.model.entity.Car;

/**
 * Interface {@code CarRepository} designed for communication between repository
 * and database for actions related to car
 *
 * @author Pavel Varaksa
 */
public interface CarRepository extends CrudRepository<Long, Car> {
}
