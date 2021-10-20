package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.model.entity.Transmission;
import by.varaksa.cardealer.model.entity.TransmissionType;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.repository.TransmissionRepository;
import by.varaksa.cardealer.model.repository.impl.TransmissionRepositoryImpl;
import by.varaksa.cardealer.model.service.TransmissionService;
import by.varaksa.cardealer.model.service.impl.TransmissionServiceImpl;
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

/**
 * Class {@code TransmissionController} designed for communication between controller
 * and service for actions related to transmission
 *
 * @author Pavel Varaksa
 */
@WebServlet(urlPatterns = {"/transmission/save", "/transmission/find-all", "/transmission/update", "/transmission/delete"})
public class TransmissionController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
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
        } catch (ServiceException | ControllerException exception) {
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
        } catch (ServiceException | IOException | RepositoryException | ServletException | ControllerException exception) {
            String errorMessage = "Transmission controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveTransmission(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException, ControllerException {
        try {
            TransmissionType transmissionType = TransmissionType.valueOf(request.getParameter("transmission_type"));
            Integer gearsCount = Integer.valueOf(request.getParameter("gears_count"));
            Integer weight = Integer.valueOf(request.getParameter("weight"));
            Long carId = Long.valueOf((request.getParameter("car_id")));
            Transmission transmission = new Transmission(transmissionType, gearsCount, weight, carId);

            transmissionService.save(transmission);
            response.sendRedirect("/transmission/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't save transmission";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void findAllTransmissions(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, ControllerException {
        try {
            List<Transmission> transmissionList = transmissionService.findAll();
            request.setAttribute("transmissionList", transmissionList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-transmissions");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find transmissions";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void updateTransmission(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Transmission transmission = transmissionService.find(id);

            transmission.setTransmissionType(TransmissionType.valueOf(request.getParameter("transmission_type")));
            transmission.setGearsCount(Integer.valueOf(request.getParameter("gears_count")));
            transmission.setWeight(Integer.valueOf((request.getParameter("weight"))));
            transmissionService.update(transmission);

            request.setAttribute("transmission", transmission);
            response.sendRedirect("/transmission/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't update transmission";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void deleteTransmission(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            transmissionService.delete(id);
            response.sendRedirect("/transmission/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't delete transmission";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }
}
