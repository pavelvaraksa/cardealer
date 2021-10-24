package by.varaksa.cardealer.model.service.impl;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.UserOrder;
import by.varaksa.cardealer.model.repository.UserOrderRepository;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.StringValidator;
import by.varaksa.cardealer.model.service.UserOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class {@code UserOrderServiceImpl} designed for communication between service
 * and repository for actions related to user order
 *
 * @author Pavel Varaksa
 */
public class UserOrderServiceImpl implements UserOrderService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_USER_ID = RegexpPropertiesReader.getRegexp("user.id.regexp");
    private static final String REGEXP_CAR_ID = RegexpPropertiesReader.getRegexp("car.id.regexp");
    private static final boolean isCheckStringFromUi = true;
    private final UserOrderRepository userOrderRepository;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @Override
    public UserOrder save(UserOrder userOrder) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_USER_ID, String.valueOf(userOrder.getUserId())) == isCheckStringFromUi &&
                    (StringValidator.isStringValidate(REGEXP_CAR_ID, String.valueOf(userOrder.getUserId())) == isCheckStringFromUi)) {
                logger.info("User order for user with id " + userOrder.getUserId() + " was saved");
                return userOrderRepository.save(userOrder);
            }

            String errorMessage = "Wasn't correct input format for save user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("User order service exception while trying to save user order." + exception);
        }
    }

    @Override
    public List<UserOrder> findAll() throws ServiceException {
        List<UserOrder> existingUserOrders;

        try {
            existingUserOrders = userOrderRepository.findAll();
            existingUserOrders = existingUserOrders.stream().sorted(Comparator.comparing(UserOrder::getId)).collect(Collectors.toList());

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
    public List<UserOrder> findAllForUser(String login) throws ServiceException {
        List<UserOrder> existingUserOrders;

        try {
            existingUserOrders = userOrderRepository.findAllForUser(login);
            existingUserOrders = existingUserOrders.stream().sorted(Comparator.comparing(UserOrder::getCreated)).collect(Collectors.toList());

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
            String errorMessage = "Can't find user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public UserOrder update(UserOrder userOrder) throws ServiceException {
        try {
            if (StringValidator.isStringValidate(REGEXP_USER_ID, String.valueOf(userOrder.getUserId())) == isCheckStringFromUi &&
                    (StringValidator.isStringValidate(REGEXP_CAR_ID, String.valueOf(userOrder.getUserId())) == isCheckStringFromUi)) {
                logger.info("User order for user with id " + userOrder.getUserId() + " was updated");
                return userOrderRepository.update(userOrder);
            }

            String errorMessage = "Wasn't correct input format for update user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (RepositoryException exception) {
            throw new ServiceException("User order service exception while trying to update user order." + exception);
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
            String errorMessage = "Can't delete user order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
