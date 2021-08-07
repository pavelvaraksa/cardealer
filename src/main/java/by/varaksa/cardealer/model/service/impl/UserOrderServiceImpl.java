package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.model.entity.UserOrder;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.UserOrderRepository;
import by.varaksa.cardealer.model.service.UserOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserOrderServiceImpl implements UserOrderService {
    private static final Logger logger = LogManager.getLogger();
    private final UserOrderRepository userOrderRepository;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @Override
    public UserOrder save(UserOrder userOrder) throws ServiceException {
        try {
            UserOrder savedUserOrder = userOrderRepository.save(userOrder);
            logger.info("User order " + userOrder + " was saved");
            return savedUserOrder;
        } catch (RepositoryException exception) {
            throw new ServiceException("User order service exception while trying to save user order." + exception);
        }
    }

    @Override
    public List<UserOrder> findAll() throws ServiceException {
        List<UserOrder> existingUserOrders;

        try {
            existingUserOrders = userOrderRepository.findAll();

            if (existingUserOrders.isEmpty()) {
                String errorMessage = "User orders list is empty";
                logger.error(errorMessage);
            } else {
                logger.info("User orders exist");
                return existingUserOrders;
            }

        } catch (RepositoryException exception) {
            throw new ServiceException("User order service exception while trying to find all user orders." + exception);
        }
        return existingUserOrders;
    }

    @Override
    public UserOrder find(Long id) throws ServiceException {
        UserOrder userOrderToFindById;

        try {
            userOrderToFindById = userOrderRepository.find(id);
            if (userOrderToFindById == null) {
                String errorMessage = "User order id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("User order service exception while trying to find user order." + exception);
        }

        try {
            logger.info("User order with id " + id + " exists");
            return userOrderRepository.find(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public UserOrder update(UserOrder userOrder) throws ServiceException {
        try {
            logger.info("User order with id " + userOrder.getId() + " was updated");
            return userOrderRepository.update(userOrder);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public UserOrder delete(Long id) throws ServiceException {
        UserOrder userOrderToFindById;

        try {
            userOrderToFindById = userOrderRepository.find(id);
            if (userOrderToFindById == null) {
                String errorMessage = "User order id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException exception) {
            throw new ServiceException("User order service exception while trying to delete user order." + exception);
        }

        try {
            logger.info("User order with id " + id + " was deleted");
            return userOrderRepository.delete(id);
        } catch (RepositoryException exception) {
            String errorMessage = "Can't get user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
