package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.Engine;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.EngineRepository;
import by.varaksa.cardealer.model.service.EngineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EngineServiceImpl implements EngineService {
    private static final Logger logger = LogManager.getLogger();
    private final EngineRepository engineRepository;

    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Override
    public Engine save(Engine engine) throws ServiceException {
        try {
            Engine savedEngine = engineRepository.save(engine);
            logger.info("Engine " + engine + " was saved");
            return savedEngine;
        } catch (RepositoryException exception) {
            throw new ServiceException("Engine service exception while trying to save engine." + exception);
        }
    }

    @Override
    public List<Engine> findAll() throws ServiceException {
        List<Engine> existingEngines;

        try {
            existingEngines = engineRepository.findAll();

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
            String errorMessage = "Can't get engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Engine update(Engine engine) throws ServiceException {
        try {
            logger.info("Engine with id " + engine.getId() + " was updated");
            return engineRepository.update(engine);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
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
            String errorMessage = "Can't get engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
