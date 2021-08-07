package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.Dealer;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.DealerRepository;
import by.varaksa.cardealer.model.service.DealerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DealerServiceImpl implements DealerService {
    private static final Logger logger = LogManager.getLogger();
    private final DealerRepository dealerRepository;

    public DealerServiceImpl(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    @Override
    public Dealer save(Dealer dealer) throws ServiceException {
        try {
            Dealer savedDealer = dealerRepository.save(dealer);
            logger.info("Dealer " + dealer + " was saved");
            return savedDealer;
        } catch (RepositoryException exception) {
            throw new ServiceException("Dealer service exception while trying to save dealer." + exception);
        }
    }

    @Override
    public List<Dealer> findAll() throws ServiceException {
        List<Dealer> existingDealers;

        try {
            existingDealers = dealerRepository.findAll();

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
            logger.info("Dealer with id " + dealer.getId() + " was updated");
            return dealerRepository.update(dealer);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get dealer";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
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
