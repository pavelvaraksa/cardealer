package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Car;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface CarService {
    List<Car> findAll() throws ServiceException;

    Car find(Long id) throws ServiceException, RepositoryException;

    Car save(Car car) throws ServiceException;

    Car update(Long id) throws ServiceException;

    Car delete(Long id) throws ServiceException;
}
