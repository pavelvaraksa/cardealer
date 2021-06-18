package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Car;
import by.varaksa.webproject.exception.RepositoryException;
import by.varaksa.webproject.exception.ServiceException;

import java.util.List;

public interface CarService {
    List<Car> findAll() throws ServiceException;

    Car find(Long id) throws ServiceException, RepositoryException;

    Car save(Car car) throws ServiceException;

    Car update(Long id) throws ServiceException;

    Car delete(Long id) throws ServiceException;
}
