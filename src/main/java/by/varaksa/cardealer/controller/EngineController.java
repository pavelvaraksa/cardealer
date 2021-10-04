package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Engine;
import by.varaksa.cardealer.model.entity.FuelType;
import by.varaksa.cardealer.model.repository.EngineRepository;
import by.varaksa.cardealer.model.repository.impl.EngineRepositoryImpl;
import by.varaksa.cardealer.model.service.EngineService;
import by.varaksa.cardealer.model.service.impl.EngineServiceImpl;
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
 * Class {@code EngineController} designed for communication between controller
 * and service for actions related to engine
 *
 * @author Pavel Varaksa
 */
@WebServlet(urlPatterns = {"/engine/save", "/engine/find-all", "/engine/update", "/engine/delete"})
public class EngineController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private Commands commandName;
    public EngineRepository engineRepository = new EngineRepositoryImpl();
    public EngineService engineService = new EngineServiceImpl(engineRepository);

    public EngineController() {
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
            if (commandName == Commands.FIND_ALL_ENGINES) {
                findAllEngines(request, response);
            }
        } catch (ServiceException | ControllerException exception) {
            String errorMessage = "Engine controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_ENGINE -> saveEngine(request, response);
                case UPDATE_ENGINE -> updateEngine(request, response);
                case DELETE_ENGINE -> deleteEngine(request, response);
                default -> {
                }
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException | ControllerException exception) {
            String errorMessage = "Engine controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveEngine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException, ControllerException {
        try {
            FuelType fuelType = FuelType.valueOf(request.getParameter("fuel_type"));
            Double volume = Double.valueOf((request.getParameter("volume")));
            Integer cylindersCount = Integer.valueOf((request.getParameter("cylinders_count")));
            Long carId = Long.valueOf(request.getParameter("car_id"));
            Engine engine = new Engine(fuelType, volume, cylindersCount, carId);

            engineService.save(engine);
            response.sendRedirect("/engine/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't save engine";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void findAllEngines(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, ControllerException {
        try {
            List<Engine> engineList = engineService.findAll();
            logger.info("Engines were watched");
            request.setAttribute("engineList", engineList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-engines");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find engines";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void updateEngine(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Engine engine = engineService.find(id);

            engine.setFuelType(FuelType.valueOf(request.getParameter("fuel_type")));
            engine.setVolume(Double.valueOf(request.getParameter("volume")));
            engine.setCylindersCount(Integer.valueOf(request.getParameter("cylinders_count")));
            engineService.update(engine);

            request.setAttribute("engine", engine);
            response.sendRedirect("/engine/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't update engine";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void deleteEngine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            engineService.delete(id);
            response.sendRedirect("/engine/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't delete engine";
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }
}
