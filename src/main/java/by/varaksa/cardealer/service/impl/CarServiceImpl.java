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
        } catch (RepositoryException exception) {
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
        } catch (RepositoryException exception) {
            throw new ServiceException("Car service exception while trying to save a car." + exception);
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
        } catch (RepositoryException exception) {
            throw new ServiceException("Car service exception while trying to find all cars." + exception);
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
        } catch (RepositoryException exception) {
            throw new ServiceException("Car service exception while trying to find a car." + exception);
        }

        try {
            logger.info("Car with id " + id + " exists");
            return carRepository.find(id);
        } catch (RepositoryException exception) {
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
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get a car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Car delete(Long id) throws ServiceException {
        Car carToFindById;

        try {
            carToFindById = carRepository.find(id);
            if (carToFindById == null) {
                String errorMessage = "Car id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Car service exception while trying to delete a car." + exception);
        }

        try {
            logger.info("Car with id " + id + " was deleted");
            return carRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get a car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
