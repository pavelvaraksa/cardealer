package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.Body;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.BodyRepository;
import by.varaksa.cardealer.model.service.BodyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class {@code BodyServiceImpl} designed for communication between service
 * and repository for actions related to body
 *
 * @author Pavel Varaksa
 */
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
            logger.info("Body type " + body.getBodyType() + " with color " + body.getColor() + " was saved");
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
            existingBodies = existingBodies.stream().sorted(Comparator.comparing(Body::getId)).collect(Collectors.toList());

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
            String errorMessage = "Can't find body";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Body update(Body body) throws ServiceException {
        try {
            logger.info("Body type " + body.getBodyType() + " with color " + body.getColor() + " was updated");
            return bodyRepository.update(body);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't update body";
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
            String errorMessage = "Can't delete body";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
