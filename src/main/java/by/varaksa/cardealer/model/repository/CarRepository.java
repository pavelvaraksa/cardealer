package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.entity.Car;

import java.util.List;

/**
 * Interface {@code CarRepository} designed for communication between repository
 * and database for actions related to car
 *
 * @author Pavel Varaksa
 */
public interface CarRepository extends CrudRepository<Long, Car> {
    /**
     * Find all cars for user order from the database
     *
     * @throws RepositoryException if repository exception happened
     */
    List<Car> findAllForOrder() throws RepositoryException;
}
