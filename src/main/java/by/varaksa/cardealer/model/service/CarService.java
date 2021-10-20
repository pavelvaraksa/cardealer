package by.varaksa.cardealer.model.service;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Car;

import java.util.List;

/**
 * Interface {@code CarService} designed for communication between service
 * and repository for actions related to car
 *
 * @author Pavel Varaksa
 */
public interface CarService {

    /**
     * Save new car to the database
     *
     * @param car {@code Car} car
     * @throws ServiceException if service exception happened
     */
    Car save(Car car) throws ServiceException;

    /**
     * Find all cars from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<Car> findAll() throws ServiceException;

    /**
     * Find all cars for user order from the database
     *
     * @throws ServiceException if service exception happened
     */
    List<Car> findAllForOrder() throws ServiceException;

    /**
     * Find car from the database by id
     *
     * @param id {@code Long} car id
     * @throws ServiceException if service exception happened
     */
    Car find(Long id) throws ServiceException;

    /**
     * Update car from the database by car
     *
     * @param car {@code Car} car
     * @throws ServiceException if service exception happened
     */
    Car update(Car car) throws ServiceException;

    /**
     * Delete car from the database by id
     *
     * @param id {@code Long} car id
     * @throws ServiceException if service exception happened
     */
    Car delete(Long id) throws ServiceException;
}
