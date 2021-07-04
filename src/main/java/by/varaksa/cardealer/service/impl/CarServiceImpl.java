package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.Car;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.CarRepository;
import by.varaksa.cardealer.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CarServiceImpl implements CarService {
    private static Logger logger = LogManager.getLogger();
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) throws ServiceException {
        List<Car> existingCars;

        try {
            existingCars = carRepository.findAll();
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get all cars";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Car existingCar : existingCars) {
            boolean hasSameCar = existingCar.getId().equals(car.getId());

            if (hasSameCar) {
                String errorMessage = "Car with id " + car.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Car savedCar = carRepository.save(car);
            logger.info("Car " + car + " was saved");
            return savedCar;
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Car service exception while trying to save a car." + stackTrace);
        }
    }

    @Override
    public List<Car> findAll() throws ServiceException {
        List<Car> existingCars;

        try {
            existingCars = carRepository.findAll();
            if (existingCars.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("Cars exist");
                return existingCars;
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Car service exception while trying to find all cars." + stackTrace);
        }
    }

    @Override
    public Car find(Long id) throws ServiceException {
        Car carToFindById;

        try {
            carToFindById = carRepository.find(id);
            if (carToFindById == null) {
                String errorMessage = "Car id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Car service exception while trying to find a car." + stackTrace);
        }

        try {
            logger.info("Car with id " + id + " exists");
            return carRepository.find(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get a car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Car update(Car car) throws ServiceException {

        try {
            logger.info("Car with id " + car.getId() + " was updated");
            return carRepository.update(car);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get a car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Long delete(Long id) throws ServiceException {
        Car carToFindById;

        try {
            carToFindById = carRepository.find(id);
            if (carToFindById == null) {
                String errorMessage = "Car id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Car service exception while trying to delete a car." + stackTrace.getMessage());
        }

        try {
            logger.info("Car with id " + id + " was deleted");
            return carRepository.delete(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get a car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
