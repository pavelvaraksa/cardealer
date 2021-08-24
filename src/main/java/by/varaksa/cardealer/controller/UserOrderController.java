package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.UserOrder;
import by.varaksa.cardealer.model.repository.UserOrderRepository;
import by.varaksa.cardealer.model.repository.impl.UserOrderRepositoryImpl;
import by.varaksa.cardealer.model.service.UserOrderService;
import by.varaksa.cardealer.model.service.impl.UserOrderServiceImpl;
import by.varaksa.cardealer.validator.UserOrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/user-order/save", "/user-order/find-all", "/user-order/find-by-id",
        "/user-order/update", "/user-order/delete"})
public class UserOrderController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUI = true;
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
                case FIND_ALL_USER_ORDERS:
                    findAllUserOrders(request, response);
                    break;
                case FIND_USER_ORDER_BY_ID:
                    findUserOrder(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException exception) {
            String errorMessage = "UserOrder controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_USER_ORDER:
                    saveUserOrder(request, response);
                    break;
                case UPDATE_USER_ORDER:
                    updateUserOrder(request, response);
                    break;
                case DELETE_USER_ORDER:
                    deleteUserOrder(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "User order controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveUserOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException {
        String orderName = (request.getParameter("order_name"));

        if (UserOrderValidator.isUserOrderValidate(UserOrderValidator.ORDER_NAME_REGEXP, request.getParameter("order_name")) == isCheckStringFromUI &&
                UserOrderValidator.isUserOrderValidate(UserOrderValidator.USER_ID_REGEXP, request.getParameter("user_id")) == isCheckStringFromUI) {
            Long userId = Long.valueOf((request.getParameter("user_id")));
            UserOrder userOrder = new UserOrder(orderName, userId);

            userOrderService.save(userOrder);
            response.sendRedirect("/user-order/find-all");
            return;
        }

        logger.error("User order wasn't saved");
        response.sendRedirect("/user-order/find-all");
    }

    private void findAllUserOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<UserOrder> userOrderList = userOrderService.findAll();
        logger.info("User orders were watched");
        request.setAttribute("userOrderList", userOrderList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-user-orders-page");
        dispatcher.forward(request, response);
    }

    private void findUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        UserOrder existingUserOrder = userOrderService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("find-by-id");
        request.setAttribute("oneUserOrder", existingUserOrder);
        dispatcher.forward(request, response);
    }

    private void updateUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        UserOrder userOrder = userOrderService.find(id);

        userOrder.setOrderName(request.getParameter("order_name"));

        if (UserOrderValidator.isUserOrderValidate(UserOrderValidator.ORDER_NAME_REGEXP, request.getParameter("order_name")) == isCheckStringFromUI) {

            userOrderService.update(userOrder);
            response.sendRedirect("/user-order/find-all");
            return;
        }

        logger.error("User order wasn't updated");
        response.sendRedirect("/user-order/find-all");
    }

    private void deleteUserOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        userOrderService.delete(id);
        response.sendRedirect("/user-order/find-all");
    }
}
