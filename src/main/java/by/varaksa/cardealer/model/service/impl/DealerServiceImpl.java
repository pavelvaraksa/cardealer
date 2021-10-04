package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.Dealer;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.DealerRepository;
import by.varaksa.cardealer.model.service.DealerService;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class {@code DealerServiceImpl} designed for communication between service
 * and repository for actions related to dealer
 *
 * @author Pavel Varaksa
 */
public class DealerServiceImpl implements DealerService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_DEALER_NAME = RegexpPropertiesReader.getRegexp("dealer.name.regexp");
    private static final String REGEXP_DEALER_ADDRESS = RegexpPropertiesReader.getRegexp("dealer.address.regexp");
    private static final boolean isCheckStringFromUi = true;
    private final DealerRepository dealerRepository;

    public DealerServiceImpl(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    @Override
    public Dealer save(Dealer dealer) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_DEALER_NAME, dealer.getName()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_DEALER_ADDRESS, dealer.getAddress()) == isCheckStringFromUi) {
                logger.info("Dealer " + dealer.getName() + " was saved");
                return dealerRepository.save(dealer);
            }

            String errorMessage = "Wasn't correct input format for save dealer";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Dealer service exception while trying to save dealer." + exception);
        }
    }

    @Override
    public List<Dealer> findAll() throws ServiceException {
        List<Dealer> existingDealers;

        try {
            existingDealers = dealerRepository.findAll();
            existingDealers = existingDealers.stream().sorted(Comparator.comparing(Dealer::getId)).collect(Collectors.toList());

            if (existingDealers.isEmpty()) {
                String errorMessage = "Dealers list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("Dealers exist");
                return existingDealers;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("Dealer service exception while trying to find all dealers." + exception);
        }
        return existingDealers;
    }

    @Override
    public Dealer find(Long id) throws ServiceException {
        Dealer dealerToFindById;

        try {
            dealerToFindById = dealerRepository.find(id);

            if (dealerToFindById == null) {
                String errorMessage = "Dealer id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Dealer service exception while trying to find dealer." + exception);
        }

        try {
            logger.info("Dealer with id " + id + " exists");
            return dealerRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get dealer";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Dealer update(Dealer dealer) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_DEALER_NAME, dealer.getName()) == isCheckStringFromUi &&
                    StringValidator.isStringValidate(REGEXP_DEALER_ADDRESS, dealer.getAddress()) == isCheckStringFromUi) {
                logger.info("Dealer " + dealer.getName() + " was updated");
                return dealerRepository.update(dealer);
            }

            String errorMessage = "Wasn't correct input format for update dealer";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("Dealer service exception while trying to update dealer." + exception);
        }
    }

    @Override
    public Dealer delete(Long id) throws ServiceException {
        Dealer dealerToFindById;

        try {
            dealerToFindById = dealerRepository.find(id);

            if (dealerToFindById == null) {
                String errorMessage = "Dealer id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("Dealer service exception while trying to delete dealer." + exception);
        }

        try {
            logger.info("Dealer with id " + id + " was deleted");
            return dealerRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get dealer";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
