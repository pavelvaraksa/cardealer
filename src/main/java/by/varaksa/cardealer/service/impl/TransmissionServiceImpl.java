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
    private static Logger logger = LogManager.getLogger();
    private TransmissionRepository transmissionRepository;

    public TransmissionServiceImpl(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public Transmission save(Transmission transmission) throws ServiceException {
        List<Transmission> existingTransmissions;

        try {
            existingTransmissions = transmissionRepository.findAll();
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get all transmissions";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Transmission existingTransmission : existingTransmissions) {
            boolean hasSameTransmission = existingTransmission.getId().equals(transmission.getId());

            if (hasSameTransmission) {
                String errorMessage = "Transmission with id " + transmission.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Transmission savedTransmission = transmissionRepository.save(transmission);
            logger.info("Transmission " + transmission + " was saved");
            return savedTransmission;
        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to save a transmission." + exception);
        }
    }

    @Override
    public List<Transmission> findAll() throws ServiceException {
        List<Transmission> existingTransmissions;

        try {
            existingTransmissions = transmissionRepository.findAll();
            if (existingTransmissions.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("Transmissions exist");
                return existingTransmissions;
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to find all transmissions." + exception);
        }
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
            throw new ServiceException("Transmission service exception while trying to find a transmission." + exception);
        }

        try {
            logger.info("Transmission with id " + id + " exists");
            return transmissionRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get a transmission";
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
            String errorMessage = "Can't get a transmission";
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
            throw new ServiceException("Transmission service exception while trying to delete a transmission." + exception);
        }

        try {
            logger.info("Transmission with id " + id + " was deleted");
            return transmissionRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get a transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
