package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.Dealer;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.DealerRepository;
import by.varaksa.cardealer.service.DealerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DealerServiceImpl implements DealerService {
    private static Logger logger = LogManager.getLogger();
    private DealerRepository dealerRepository;

    public DealerServiceImpl(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    @Override
    public List<Dealer> findAll() throws ServiceException {
        List<Dealer> existingDealers;

        try {
            existingDealers = dealerRepository.findAll();
            if (existingDealers.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("Dealers exist");
                return existingDealers;
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Dealer service exception while trying to find all dealers." + stackTrace.getMessage());
        }
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Dealer service exception while trying to find a dealer." + stackTrace.getMessage());
        }

        try {
            logger.info("Dealer with id " + id + " exists");
            return dealerRepository.find(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get a dealer";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Dealer save(Dealer dealer) throws ServiceException {
        List<Dealer> existingDealers;

        try {
            existingDealers = dealerRepository.findAll();
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get all dealers";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Dealer existingDealer : existingDealers) {
            boolean hasSameDealer = existingDealer.getId().equals(dealer.getId());

            if (hasSameDealer) {
                String errorMessage = "Dealer with id " + dealer.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Dealer savedDealer = dealerRepository.save(dealer);
            logger.info("Dealer " + dealer + " was saved");
            return savedDealer;
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Dealer service exception while trying to save a dealer." + stackTrace.getMessage());
        }
    }

    @Override
    public Dealer update(Long id) throws ServiceException {

        try {
            logger.info("Dealer with id " + id + " was updated");
            return dealerRepository.update(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get a dealer";
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Dealer service exception while trying to delete a dealer." + stackTrace.getMessage());
        }

        try {
            logger.info("Dealer with id " + id + " was deleted");
            return dealerRepository.delete(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get a dealer";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
