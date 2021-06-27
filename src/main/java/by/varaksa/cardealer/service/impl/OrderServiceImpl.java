package by.varaksa.cardealer.service.impl;

import by.varaksa.cardealer.entity.Order;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.OrderRepository;
import by.varaksa.cardealer.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static Logger logger = LogManager.getLogger();
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) throws ServiceException {
        List<Order> existingOrders;

        try {
            existingOrders = orderRepository.findAll();
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get all orders";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Order existingOrder : existingOrders) {
            boolean hasSameOrder = existingOrder.getId().equals(order.getId());

            if (hasSameOrder) {
                String errorMessage = "Order with id " + order.getId() + " already exists";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Order savedOrder = orderRepository.save(order);
            logger.info("Order " + order + " was saved");
            return savedOrder;
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Order service exception while trying to save an order." + stackTrace);
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        List<Order> existingOrders;

        try {
            existingOrders = orderRepository.findAll();
            if (existingOrders.isEmpty()) {
                String errorMessage = "A list is empty";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                logger.info("Orders exist");
                return existingOrders;
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Order service exception while trying to find all orders." + stackTrace);
        }
    }

    @Override
    public Order find(Long id) throws ServiceException {
        Order orderToFindById;

        try {
            orderToFindById = orderRepository.find(id);
            if (orderToFindById == null) {
                String errorMessage = "Order id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Order service exception while trying to find an order." + stackTrace);
        }

        try {
            logger.info("Order with id " + id + " exists");
            return orderRepository.find(id);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Order update(Order order) throws ServiceException {

        try {
            logger.info("Order with id " + order.getId() + " was updated");
            return orderRepository.update(order);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Long delete(Order order) throws ServiceException {
        Order orderToFindById;

        try {
            orderToFindById = orderRepository.find(order.getId());
            if (orderToFindById == null) {
                String errorMessage = "Order id can't be null";
                logger.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException stackTrace) {
            throw new ServiceException("Order service exception while trying to delete an order." + stackTrace);
        }

        try {
            logger.info("Order with id " + order.getId() + " was deleted");
            return orderRepository.delete(order);
        } catch (RepositoryException stackTrace) {
            String errorMessage = "Can't get an order";
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}
