package by.varaksa.cardealer.service;

import by.varaksa.cardealer.entity.Car;
import by.varaksa.cardealer.exception.ServiceException;

import java.util.List;

public interface CarService {
    Car save(Car car) throws ServiceException;

    List<Car> findAll() throws ServiceException;

    Car find(Long id) throws ServiceException;

    Car update(Car car) throws ServiceException;

    Car delete(Long id) throws ServiceException;
}
