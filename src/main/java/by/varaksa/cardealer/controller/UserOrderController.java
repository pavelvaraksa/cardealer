package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.UserOrder;
import by.varaksa.cardealer.model.repository.UserOrderRepository;
import by.varaksa.cardealer.model.repository.impl.UserOrderRepositoryImpl;
import by.varaksa.cardealer.model.service.UserOrderService;
import by.varaksa.cardealer.model.service.impl.UserOrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class {@code UserOrderController} designed for communication between controller
 * and service for actions related to user order
 *
 * @author Pavel Varaksa
 */
@WebServlet(urlPatterns = {"/user-order/save", "/user-order/save-for-user", "/user-order/find-all", "/user-order/find-all-for-user", "/user-order/update", "/user-order/delete"})
public class UserOrderController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private Commands commandName;
    public UserOrderRepository userOrderRepository = new UserOrderRepositoryImpl();
    public UserOrderService userOrderService = new UserOrderServiceImpl(userOrderRepository);

    public UserOrderController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processPostRequests(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processGetRequests(request, response);
    }

    private void processGetRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case FIND_ALL_USER_ORDERS -> findAllUserOrders(request, response);
                case FIND_ALL_USER_ORDERS_FOR_USER -> findAllUserOrdersForUser(request, response);
                default -> {
                }
            }
        } catch (ServiceException | ControllerException exception) {
            String errorMessage = "UserOrder controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_USER_ORDER -> saveUserOrder(request, response);
                case SAVE_USER_ORDER_FOR_USER -> saveUserOrderForUser(request, response);
                case UPDATE_USER_ORDER -> updateUserOrder(request, response);
                case DELETE_USER_ORDER -> deleteUserOrder(request, response);
                default -> {
                }
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException | ControllerException exception) {
            String errorMessage = "User order controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveUserOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException, ControllerException {
        try {
            Long userId = Long.valueOf(request.getParameter("user_id"));
            Long carId = Long.valueOf(request.getParameter("car_id"));
            UserOrder userOrder = new UserOrder(userId, carId);

            userOrderService.save(userOrder);
            response.sendRedirect("/user-order/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't save user order";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void saveUserOrderForUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException, ControllerException {
        try {
            HttpSession session = request.getSession();

            Long userId = Long.parseLong(String.valueOf(session.getAttribute("id")));
            Long carId = Long.parseLong(request.getParameter("id"));
            UserOrder userOrder = new UserOrder(userId, carId);

            userOrderService.save(userOrder);
            response.sendRedirect("/car/find-all-for-order");
        } catch (
                ServiceException exception) {
            String errorMessage = "Can't save user order";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void findAllUserOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, ControllerException {
        try {
            List<UserOrder> userOrderList = userOrderService.findAll();
            request.setAttribute("userOrderList", userOrderList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-user-orders");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find user orders";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void findAllUserOrdersForUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, ControllerException {
        try {
            HttpSession session = request.getSession();
            String login = String.valueOf(session.getAttribute("login"));
            List<UserOrder> userOrderList = userOrderService.findAllForUser(login);
            session.setAttribute("userOrderList", userOrderList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-user-orders-for-user");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find user orders";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void updateUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            UserOrder userOrder = userOrderService.find(id);

            userOrder.setUserId(Long.valueOf(request.getParameter("user_id")));
            userOrder.setCarId(Long.valueOf(request.getParameter("car_id")));
            userOrderService.update(userOrder);

            request.setAttribute("user_order", userOrder);
            response.sendRedirect("/user-order/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't update user order";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void deleteUserOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            userOrderService.delete(id);

            response.sendRedirect("/user-order/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't delete user order";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }
}
