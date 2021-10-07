package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Engine;
import by.varaksa.cardealer.model.repository.EngineRepository;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.StringValidator;
import by.varaksa.cardealer.model.service.EngineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class {@code EngineServiceImpl} designed for communication between service
 * and repository for actions related to engine
 *
 * @author Pavel Varaksa
 */
public class EngineServiceImpl implements EngineService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_VOLUME = RegexpPropertiesReader.getRegexp("volume.regexp");
    private static final String REGEXP_CYLINDERS_COUNT = RegexpPropertiesReader.getRegexp("cylinders.count.regexp");
    private static final String REGEXP_CAR_ID = RegexpPropertiesReader.getRegexp("car.id.regexp");
    private static final boolean isCheckStringFromUi = true;
    private final EngineRepository engineRepository;

    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Override
    public Engine save(Engine engine) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_VOLUME, String.valueOf(engine.getVolume())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_CYLINDERS_COUNT, String.valueOf(engine.getCylindersCount())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_CAR_ID, String.valueOf(engine.getCarId())) == isCheckStringFromUi) {
                logger.info("Engine type " + engine.getFuelType() + " with volume " + engine.getVolume() + " was saved");
                return engineRepository.save(engine);
            }

            String errorMessage = "Wasn't correct input format for save engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Engine service exception while trying to save engine." + exception);
        }
    }

    @Override
    public List<Engine> findAll() throws ServiceException {
        List<Engine> existingEngines;

        try {
            existingEngines = engineRepository.findAll();
            existingEngines = existingEngines.stream().sorted(Comparator.comparing(Engine::getId)).collect(Collectors.toList());

            if (existingEngines.isEmpty()) {
                String errorMessage = "Engines list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("Engines exist");
                return existingEngines;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("Engine service exception while trying to find all engines." + exception);
        }
        return existingEngines;
    }

    @Override
    public Engine find(Long id) throws ServiceException {
        Engine engineToFindById;

        try {
            engineToFindById = engineRepository.find(id);
            if (engineToFindById == null) {
                String errorMessage = "Engine id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Engine service exception while trying to find engine." + exception);
        }

        try {
            logger.info("Engine with id " + id + " exists");
            return engineRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't find engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Engine update(Engine engine) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_VOLUME, String.valueOf(engine.getVolume())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_CYLINDERS_COUNT, String.valueOf(engine.getCylindersCount())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_CAR_ID, String.valueOf(engine.getCarId())) == isCheckStringFromUi) {
                logger.info("Engine type " + engine.getFuelType() + " with volume " + engine.getVolume() + " was updated");
                return engineRepository.update(engine);
            }

            String errorMessage = "Wasn't correct input format for update engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Engine service exception while trying to update engine." + exception);
        }
    }

    @Override
    public Engine delete(Long id) throws ServiceException {
        Engine engineToFindById;

        try {
            engineToFindById = engineRepository.find(id);
            if (engineToFindById == null) {
                String errorMessage = "Engine id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Engine service exception while trying to delete engine." + exception);
        }

        try {
            logger.info("Engine with id " + id + " was deleted");
            return engineRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't delete engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
