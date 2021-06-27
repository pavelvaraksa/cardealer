package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Car;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface CarService {
    Car save(Car car) throws ServiceException;

    List<Car> findAll() throws ServiceException;

    Car find(Long id) throws ServiceException, RepositoryException;

    Car update(Car car) throws ServiceException;

    Long delete(Car car) throws ServiceException;
}
