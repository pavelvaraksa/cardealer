package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.Engine;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.EngineRepository;
import by.varaksa.cardealer.service.EngineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EngineServiceImpl implements EngineService {
    private static Logger logger = LogManager.getLogger();
    private EngineRepository engineRepository;

    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Override
    public List<Engine> findAll() throws ServiceException {
        List<Engine> existingEngines;

        try {
            existingEngines = engineRepository.findAll();
            if (existingEngines.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("Engines exist");
                return existingEngines;
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Engine service exception while trying to find all engines." + stackTrace.getMessage());
        }
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Engine service exception while trying to find an engine." + stackTrace.getMessage());
        }

        try {
            logger.info("Engine with id " + id + " exists");
            return engineRepository.find(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Engine save(Engine engine) throws ServiceException {
        List<Engine> existingEngines;

        try {
            existingEngines = engineRepository.findAll();
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get all engines";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Engine existingEngine : existingEngines) {
            boolean hasSameEngine = existingEngine.getId().equals(engine.getId());

            if (hasSameEngine) {
                String errorMessage = "Engine with id " + engine.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Engine savedEngine = engineRepository.save(engine);
            logger.info("Engine " + engine + " was saved");
            return savedEngine;
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Engine service exception while trying to save an engine." + stackTrace.getMessage());
        }
    }

    @Override
    public Engine update(Long id) throws ServiceException {

        try {
            logger.info("Engine with id " + id + " was updated");
            return engineRepository.update(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an engine";
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Engine service exception while trying to delete an engine." + stackTrace.getMessage());
        }

        try {
            logger.info("Engine with id " + id + " was deleted");
            return engineRepository.delete(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an engine";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
