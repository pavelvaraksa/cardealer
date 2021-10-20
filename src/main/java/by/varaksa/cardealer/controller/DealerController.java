package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.model.entity.City;
import by.varaksa.cardealer.model.entity.Dealer;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.DealerRepository;
import by.varaksa.cardealer.model.repository.impl.DealerRepositoryImpl;
import by.varaksa.cardealer.model.service.DealerService;
import by.varaksa.cardealer.model.service.impl.DealerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Class {@code DealerController} designed for communication between controller
 * and service for actions related to dealer
 *
 * @author Pavel Varaksa
 */
@WebServlet(urlPatterns = {"/dealer/save", "/dealer/find-all", "/dealer/update", "/dealer/delete"})
public class DealerController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private Commands commandName;
    public DealerRepository dealerRepository = new DealerRepositoryImpl();
    public DealerService dealerService = new DealerServiceImpl(dealerRepository);

    public DealerController() {
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
            if (commandName == Commands.FIND_ALL_DEALERS) {
                findAllDealers(request, response);
            }
        } catch (ServiceException | ControllerException exception) {
            String errorMessage = "Dealer controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_DEALER -> saveDealer(request, response);
                case UPDATE_DEALER -> updateDealer(request, response);
                case DELETE_DEALER -> deleteDealer(request, response);
                default -> {
                }
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException | ControllerException exception) {
            String errorMessage = "Dealer controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveDealer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException, ControllerException {
        try {
            String name = (request.getParameter("name"));
            String address = (request.getParameter("address"));
            LocalDate foundationDate = LocalDate.parse((request.getParameter("foundation_date")));
            City city = City.valueOf(((request.getParameter("city"))));
            Dealer dealer = new Dealer(name, address, foundationDate, city);

            dealerService.save(dealer);
            response.sendRedirect("/dealer/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't save dealer";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void findAllDealers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, ControllerException {
        try {
            List<Dealer> dealerList = dealerService.findAll();
            request.setAttribute("dealerList", dealerList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-dealers");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find dealers";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void updateDealer(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Dealer dealer = dealerService.find(id);

            dealer.setName(request.getParameter("name"));
            dealer.setAddress(request.getParameter("address"));
            dealer.setFoundationDate(LocalDate.parse(request.getParameter("foundation_date")));
            dealer.setCity(City.valueOf(request.getParameter("city")));
            dealerService.update(dealer);

            request.setAttribute("dealer", dealer);
            response.sendRedirect("/dealer/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't update dealer";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void deleteDealer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            dealerService.delete(id);
            response.sendRedirect("/dealer/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't delete dealer";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }
}
