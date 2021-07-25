package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.Transmission;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.TransmissionRepository;
import by.varaksa.cardealer.service.TransmissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TransmissionServiceImpl implements TransmissionService {
    private static final Logger logger = LogManager.getLogger();
    private final TransmissionRepository transmissionRepository;

    public TransmissionServiceImpl(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public Transmission save(Transmission transmission) throws ServiceException {
        try {
            Transmission savedTransmission = transmissionRepository.save(transmission);
            logger.info("Transmission " + transmission + " was saved");
            return savedTransmission;
        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to save transmission." + exception);
        }
    }

    @Override
    public List<Transmission> findAll() throws ServiceException {
        List<Transmission> existingTransmissions;

        try {
            existingTransmissions = transmissionRepository.findAll();

            if (existingTransmissions.isEmpty()) {
                String errorMessage = "Transmissions list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("Transmissions exist");
                return existingTransmissions;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to find all transmissions." + exception);
        }
        return existingTransmissions;
    }

    @Override
    public Transmission find(Long id) throws ServiceException {
        Transmission transmissionToFindById;

        try {
            transmissionToFindById = transmissionRepository.find(id);
            if (transmissionToFindById == null) {
                String errorMessage = "Transmission id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to find transmission." + exception);
        }

        try {
            logger.info("Transmission with id " + id + " exists");
            return transmissionRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Transmission update(Transmission transmission) throws ServiceException {
        try {
            logger.info("Transmission with id " + transmission.getId() + " was updated");
            return transmissionRepository.update(transmission);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Transmission delete(Long id) throws ServiceException {
        Transmission transmissionToFindById;

        try {
            transmissionToFindById = transmissionRepository.find(id);
            if (transmissionToFindById == null) {
                String errorMessage = "Transmission id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to delete transmission." + exception);
        }

        try {
            logger.info("Transmission with id " + id + " was deleted");
            return transmissionRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
