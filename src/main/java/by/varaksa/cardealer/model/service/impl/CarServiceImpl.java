package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.Car;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.CarRepository;
import by.varaksa.cardealer.model.service.CarService;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_GUARANTEE_PERIOD = RegexpPropertiesReader.getRegexp("guarantee.period.regexp");
    private static final String REGEXP_PRICE = RegexpPropertiesReader.getRegexp("price.regexp");
    private static final String REGEXP_USER_ORDER_ID = RegexpPropertiesReader.getRegexp("user.order.id.regexp");
    private static final boolean isCheckStringFromUi = true;
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_GUARANTEE_PERIOD, String.valueOf(car.getGuaranteePeriod())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_PRICE, String.valueOf(car.getPrice())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_USER_ORDER_ID, String.valueOf(car.getUserOrderId())) == isCheckStringFromUi) {
                logger.info("Car model " + car.getModel() + " was saved");
                return carRepository.save(car);
            }

            String errorMessage = "Wasn't correct input format for save car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Car service exception while trying to save car." + exception);
        }
    }

    @Override
    public List<Car> findAll() throws ServiceException {
        List<Car> existingCars;

        try {
            existingCars = carRepository.findAll();
            existingCars = existingCars.stream().sorted(Comparator.comparing(Car::getId)).collect(Collectors.toList());

            if (existingCars.isEmpty()) {
                String errorMessage = "Cars list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("Cars exist");
                return existingCars;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("Car service exception while trying to find all cars." + exception);
        }
        return existingCars;
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
            throw new ServiceException("Car service exception while trying to find car." + exception);
        }

        try {
            logger.info("Car with id " + id + " exists");
            return carRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't find car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Car update(Car car) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_GUARANTEE_PERIOD, String.valueOf(car.getGuaranteePeriod())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_PRICE, String.valueOf(car.getPrice())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_USER_ORDER_ID, String.valueOf(car.getUserOrderId())) == isCheckStringFromUi) {
                logger.info("Car model " + car.getModel() + " was updated");
                return carRepository.update(car);
            }

            String errorMessage = "Wasn't correct input format for update car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Car service exception while trying to update car." + exception);
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
            throw new ServiceException("Car service exception while trying to delete car." + exception);
        }

        try {
            logger.info("Car with id " + id + " was deleted");
            return carRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't delete car";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
