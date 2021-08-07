package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.Body;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.BodyRepository;
import by.varaksa.cardealer.model.service.BodyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BodyServiceImpl implements BodyService {
    private static final Logger logger = LogManager.getLogger();
    private final BodyRepository bodyRepository;

    public BodyServiceImpl(BodyRepository bodyRepository) {
        this.bodyRepository = bodyRepository;
    }

    @Override
    public Body save(Body body) throws ServiceException {
        try {
            Body savedBody = bodyRepository.save(body);
            logger.info("Body " + body + " was saved");
            return savedBody;
        } catch (RepositoryException exception) {
            throw new ServiceException("Body service exception while trying to save body." + exception);
        }
    }

    @Override
    public List<Body> findAll() throws ServiceException {
        List<Body> existingBodies;

        try {
            existingBodies = bodyRepository.findAll();

            if (existingBodies.isEmpty()) {
                String errorMessage = "Bodies list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("Bodies exist");
                return existingBodies;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("Body service exception while trying to find all bodies." + exception);
        }
        return existingBodies;
    }

    @Override
    public Body find(Long id) throws ServiceException {
        Body bodyToFindById;

        try {
            bodyToFindById = bodyRepository.find(id);

            if (bodyToFindById == null) {
                String errorMessage = "Body id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("Body service exception while trying to find body." + exception);
        }

        try {
            logger.info("Body with id " + id + " exists");
            return bodyRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get a body";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Body update(Body body) throws ServiceException {
        try {
            logger.info("Body with id " + body.getId() + " was updated");
            return bodyRepository.update(body);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get body";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Body delete(Long id) throws ServiceException {
        Body bodyToFindById;

        try {
            bodyToFindById = bodyRepository.find(id);
            if (bodyToFindById == null) {
                String errorMessage = "Body id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Body service exception while trying to delete body." + exception);
        }

        try {
            logger.info("Body with id " + id + " was deleted");
            return bodyRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get body";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
