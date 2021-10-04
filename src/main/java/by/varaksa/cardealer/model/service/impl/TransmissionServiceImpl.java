package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.Transmission;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.TransmissionRepository;
import by.varaksa.cardealer.model.service.TransmissionService;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class {@code TransmissionServiceImpl} designed for communication between service
 * and repository for actions related to transmission
 *
 * @author Pavel Varaksa
 */
public class TransmissionServiceImpl implements TransmissionService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_GEARS_COUNT = RegexpPropertiesReader.getRegexp("gears.count.regexp");
    private static final String REGEXP_WEIGHT = RegexpPropertiesReader.getRegexp("weight.regexp");
    private static final boolean isCheckStringFromUi = true;
    private final TransmissionRepository transmissionRepository;

    public TransmissionServiceImpl(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public Transmission save(Transmission transmission) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_GEARS_COUNT, String.valueOf(transmission.getGearsCount())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_WEIGHT, String.valueOf(transmission.getWeight())) == isCheckStringFromUi) {
                logger.info("Transmission type " + transmission.getTransmissionType() + " with gears count = " + transmission.getGearsCount() + " was saved");
                return transmissionRepository.save(transmission);
            }

            String errorMessage = "Wasn't correct input format for save transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to save transmission." + exception);
        }
    }

    @Override
    public List<Transmission> findAll() throws ServiceException {
        List<Transmission> existingTransmissions;

        try {
            existingTransmissions = transmissionRepository.findAll();
            existingTransmissions = existingTransmissions.stream().sorted(Comparator.comparing(Transmission::getId)).collect(Collectors.toList());

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
            String errorMessage = "Can't find transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Transmission update(Transmission transmission) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_GEARS_COUNT, String.valueOf(transmission.getGearsCount())) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_WEIGHT, String.valueOf(transmission.getWeight())) == isCheckStringFromUi) {
                logger.info("Transmission type " + transmission.getTransmissionType() + " with gears count = " + transmission.getGearsCount() + " was updated");
                return transmissionRepository.update(transmission);
            }

            String errorMessage = "Wasn't correct input format for update transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Transmission service exception while trying to update transmission." + exception);
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
            String errorMessage = "Can't delete transmission";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
