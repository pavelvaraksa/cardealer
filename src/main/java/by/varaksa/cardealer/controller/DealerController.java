package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.model.entity.City;
import by.varaksa.cardealer.model.entity.Dealer;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.DealerRepository;
import by.varaksa.cardealer.model.repository.impl.DealerRepositoryImpl;
import by.varaksa.cardealer.model.service.DealerService;
import by.varaksa.cardealer.model.service.impl.DealerServiceImpl;
import by.varaksa.cardealer.model.validator.CarValidator;
import by.varaksa.cardealer.model.validator.DealerValidator;
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

@WebServlet(urlPatterns = {"/dealer/save", "/dealer/find-all", "/dealer/find-by-id", "/dealer/update", "/dealer/delete"})
public class DealerController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUI = true;
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
            switch (commandName) {
                case FIND_ALL_DEALERS:
                    findAllDealers(request, response);
                    break;
                case FIND_DEALER_BY_ID:
                    findDealer(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException exception) {
            String errorMessage = "Dealer controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_DEALER:
                    saveDealer(request, response);
                    break;
                case UPDATE_DEALER:
                    updateDealer(request, response);
                    break;
                case DELETE_DEALER:
                    deleteDealer(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "Dealer controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveDealer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException {
        String name = (request.getParameter("name"));
        String address = (request.getParameter("address"));
        LocalDate foundationDate = LocalDate.parse((request.getParameter("foundation_date")));
        City city = City.valueOf(((request.getParameter("city"))));

        if (DealerValidator.isDealerValidate(DealerValidator.DEALER_NAME_REGEXP, request.getParameter("name")) == isCheckStringFromUI &&
                DealerValidator.isDealerValidate(DealerValidator.DEALER_ADDRESS_REGEXP, request.getParameter("address")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.CAR_ID_REGEXP, request.getParameter("car_id")) == isCheckStringFromUI) {

            Long carId = Long.valueOf(request.getParameter("car_id"));
            Dealer dealer = new Dealer(name, address, foundationDate, city, carId);

            dealerService.save(dealer);
            response.sendRedirect("/dealer/find-all");
            return;
        }

        logger.error("Dealer wasn't saved");
        response.sendRedirect("/body/find-all");
    }

    private void findAllDealers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<Dealer> dealerList = dealerService.findAll();
        logger.info("Dealers were watched");
        request.setAttribute("dealerList", dealerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-dealers-page");
        dispatcher.forward(request, response);
    }

    private void findDealer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        Dealer existingDealer = dealerService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("find-by-id");
        request.setAttribute("oneDealer", existingDealer);
        dispatcher.forward(request, response);
    }

    private void updateDealer(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Dealer dealer = dealerService.find(id);

        dealer.setName(request.getParameter("name"));
        dealer.setAddress(request.getParameter("address"));
        dealer.setFoundationDate(LocalDate.parse(request.getParameter("foundation_date")));
        dealer.setCity(City.valueOf(request.getParameter("city")));

        if (DealerValidator.isDealerValidate(DealerValidator.DEALER_NAME_REGEXP, request.getParameter("name")) == isCheckStringFromUI &&
                DealerValidator.isDealerValidate(DealerValidator.DEALER_ADDRESS_REGEXP, request.getParameter("address")) == isCheckStringFromUI) {

            dealerService.update(dealer);
            response.sendRedirect("/dealer/find-all");
            return;
        }

        logger.error("Dealer wasn't updated");
        response.sendRedirect("/body/find-all");
    }

    private void deleteDealer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        dealerService.delete(id);
        response.sendRedirect("/dealer/find-all");
    }
}
