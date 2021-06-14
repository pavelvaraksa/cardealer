package by.varaksa.webproject.service;

import by.varaksa.webproject.entity.Car;
import by.varaksa.webproject.exception.ServiceException;

public interface CarService {
    Car find(Long id) throws ServiceException;

    Car save(Car car) throws ServiceException;

    Car update(Long id) throws ServiceException;

    Car delete(Long id) throws ServiceException;
}
