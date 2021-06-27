package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.UserOrder;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.UserOrderRepository;
import by.varaksa.cardealer.service.UserOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserOrderServiceImpl implements UserOrderService {
    private static Logger logger = LogManager.getLogger();
    private UserOrderRepository userOrderRepository;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @Override
    public UserOrder save(UserOrder userOrder) throws ServiceException {
        List<UserOrder> existingUserOrders;

        try {
            existingUserOrders = userOrderRepository.findAll();
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get all user orders";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (UserOrder existingUserOrder : existingUserOrders) {
            boolean hasSameUserOrder = existingUserOrder.getId().equals(userOrder.getId());

            if (hasSameUserOrder) {
                String errorMessage = "User order with id " + userOrder.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            UserOrder savedUserOrder = userOrderRepository.save(userOrder);
            logger.info("User order " + userOrder + " was saved");
            return savedUserOrder;
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User order service exception while trying to save an user order." + stackTrace);
        }
    }

    @Override
    public List<UserOrder> findAll() throws ServiceException {
        List<UserOrder> existingUserOrders;

        try {
            existingUserOrders = userOrderRepository.findAll();
            if (existingUserOrders.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("User orders exist");
                return existingUserOrders;
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User order service exception while trying to find all user orders." + stackTrace);
        }
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
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User order service exception while trying to find an user order." + stackTrace);
        }

        try {
            logger.info("User order with id " + id + " exists");
            return userOrderRepository.find(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public UserOrder update(UserOrder userOrder) throws ServiceException {

        try {
            logger.info("User order with id " + userOrder.getId() + " was updated");
            return userOrderRepository.update(userOrder);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Long delete(UserOrder userOrder) throws ServiceException {
        UserOrder userOrderToFindById;

        try {
            userOrderToFindById = userOrderRepository.find(userOrder.getId());
            if (userOrderToFindById == null) {
                String errorMessage = "User order id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("User order service exception while trying to delete an user order." + stackTrace);
        }

        try {
            logger.info("User order with id " + userOrder.getId() + " was deleted");
            return userOrderRepository.delete(userOrder);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
