package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.service.BodyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BodyServiceImpl implements BodyService {
    private static Logger logger = LogManager.getLogger();
    private BodyRepository bodyRepository;

    public BodyServiceImpl(BodyRepository bodyRepository) {
        this.bodyRepository = bodyRepository;
    }

    @Override
    public Body save(Body body) throws ServiceException {
        List<Body> existingBodies;

        try {
            existingBodies = bodyRepository.findAll();
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get all bodies";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Body existingBody : existingBodies) {
            boolean hasSameBody = existingBody.getId().equals(body.getId());

            if (hasSameBody) {
                String errorMessage = "Body with id " + body.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Body savedBody = bodyRepository.save(body);
            logger.info("Body " + body + " was saved");
            return savedBody;
        } catch (RepositoryException exception) {
            throw new ServiceException("Body service exception while trying to save a body." + exception);
        }
    }

    @Override
    public List<Body> findAll() throws ServiceException {
        List<Body> existingBodies;

        try {
            existingBodies = bodyRepository.findAll();
            if (existingBodies.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("Bodies exist");
                return existingBodies;
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Body service exception while trying to find all bodies." + exception);
        }
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
            throw new ServiceException("Body service exception while trying to find a body." + exception);
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
            String errorMessage = "Can't get a body";
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
            throw new ServiceException("Body service exception while trying to delete a body." + exception);
        }

        try {
            logger.info("Body with id " + id + " was deleted");
            return bodyRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get a body";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
