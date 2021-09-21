package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.model.entity.Transmission;
import by.varaksa.cardealer.model.entity.TransmissionType;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.TransmissionRepository;
import by.varaksa.cardealer.model.repository.impl.TransmissionRepositoryImpl;
import by.varaksa.cardealer.model.service.TransmissionService;
import by.varaksa.cardealer.model.service.impl.TransmissionServiceImpl;
import by.varaksa.cardealer.validator.CarValidator;
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

@WebServlet(urlPatterns = {"/transmission/save", "/transmission/find-all", "/transmission/update", "/transmission/delete"})

public class TransmissionController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUI = true;
    private Commands commandName;
    public TransmissionRepository transmissionRepository = new TransmissionRepositoryImpl();
    public TransmissionService transmissionService = new TransmissionServiceImpl(transmissionRepository);

    public TransmissionController() {
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

            if (commandName == Commands.FIND_ALL_TRANSMISSIONS) {
                findAllTransmissions(request, response);
            }
        } catch (ServiceException exception) {
            String errorMessage = "Transmission controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_TRANSMISSION -> saveTransmission(request, response);
                case UPDATE_TRANSMISSION -> updateTransmission(request, response);
                case DELETE_TRANSMISSION -> deleteTransmission(request, response);
                default -> {
                }
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "Transmission controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveTransmission(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException {
        TransmissionType transmissionType = TransmissionType.valueOf(request.getParameter("transmission_type"));

        if (CarValidator.isCarValidate(CarValidator.GEARS_COUNT_REGEXP, request.getParameter("gears_count")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.WEIGHT_REGEXP, request.getParameter("weight")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.CAR_ID_REGEXP, request.getParameter("car_id")) == isCheckStringFromUI) {

            Integer gearsCount = Integer.valueOf(request.getParameter("gears_count"));
            Integer weight = Integer.valueOf(request.getParameter("weight"));
            Long carId = Long.valueOf((request.getParameter("car_id")));
            Transmission transmission = new Transmission(transmissionType, gearsCount, weight, carId);

            transmissionService.save(transmission);
            response.sendRedirect("/transmission/find-all");
            return;
        }

        logger.error("Transmission wasn't saved");
        response.sendRedirect("/transmission/find-all");
    }

    private void findAllTransmissions(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<Transmission> transmissionList = transmissionService.findAll();
        logger.info("Transmissions were watched");
        request.setAttribute("transmissionList", transmissionList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-transmissions");
        dispatcher.forward(request, response);
    }

    private void updateTransmission(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Transmission transmission = transmissionService.find(id);

        transmission.setTransmissionType(TransmissionType.valueOf(request.getParameter("transmission_type")));

        if (CarValidator.isCarValidate(CarValidator.GEARS_COUNT_REGEXP, request.getParameter("gears_count")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.WEIGHT_REGEXP, request.getParameter("weight")) == isCheckStringFromUI) {

            transmission.setGearsCount(Integer.valueOf(request.getParameter("gears_count")));
            transmission.setWeight(Integer.valueOf((request.getParameter("weight"))));
            transmissionService.update(transmission);
            response.sendRedirect("/transmission/find-all");
            return;
        }

        logger.error("Transmission wasn't updated");
        response.sendRedirect("/transmission/find-all");
    }

    private void deleteTransmission(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        transmissionService.delete(id);
        response.sendRedirect("/transmission/find-all");
    }
}
