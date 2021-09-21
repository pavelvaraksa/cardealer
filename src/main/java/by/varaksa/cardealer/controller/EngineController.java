package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Engine;
import by.varaksa.cardealer.model.entity.FuelType;
import by.varaksa.cardealer.model.repository.EngineRepository;
import by.varaksa.cardealer.model.repository.impl.EngineRepositoryImpl;
import by.varaksa.cardealer.model.service.EngineService;
import by.varaksa.cardealer.model.service.impl.EngineServiceImpl;
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

@WebServlet(urlPatterns = {"/engine/save", "/engine/find-all", "/engine/update", "/engine/delete"})
public class EngineController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUI = true;
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
        } catch (ServiceException exception) {
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
        } catch (ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "Engine controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveEngine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException {
        FuelType fuelType = FuelType.valueOf(request.getParameter("fuel_type"));

        if (CarValidator.isCarValidate(CarValidator.VOLUME_REGEXP, request.getParameter("volume")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.CYLINDERS_COUNT_REGEXP, request.getParameter("cylinders_count")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.CAR_ID_REGEXP, request.getParameter("car_id")) == isCheckStringFromUI) {

            Double volume = Double.valueOf((request.getParameter("volume")));
            Integer cylindersCount = Integer.valueOf((request.getParameter("cylinders_count")));
            Long carId = Long.valueOf((request.getParameter("car_id")));
            Engine engine = new Engine(fuelType, volume, cylindersCount, carId);

            engineService.save(engine);
            response.sendRedirect("/engine/find-all");
            return;
        }

        logger.error("Engine wasn't saved");
        response.sendRedirect("/engine/find-all");
    }

    private void findAllEngines(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException, ServiceException {
        List<Engine> engineList = engineService.findAll();
        logger.info("Engines were watched");
        request.setAttribute("engineList", engineList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-engines");
        dispatcher.forward(request, response);
    }

    private void findEngine(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        Engine existingEngine = engineService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("find-by-id");
        request.setAttribute("oneEngine", existingEngine);
        dispatcher.forward(request, response);
    }

    private void updateEngine(HttpServletRequest request, HttpServletResponse response) throws
            ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Engine engine = engineService.find(id);

        engine.setFuelType(FuelType.valueOf(request.getParameter("fuel_type")));

        if (CarValidator.isCarValidate(CarValidator.VOLUME_REGEXP, request.getParameter("volume")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.CYLINDERS_COUNT_REGEXP, request.getParameter("cylinders_count")) == isCheckStringFromUI) {

            engine.setVolume(Double.valueOf(request.getParameter("volume")));
            engine.setCylindersCount(Integer.valueOf(request.getParameter("cylinders_count")));

            engineService.update(engine);
            response.sendRedirect("/engine/find-all");
            return;
        }

        logger.error("Engine wasn't updated");
        response.sendRedirect("/engine/find-all");
    }

    private void deleteEngine(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        engineService.delete(id);
        response.sendRedirect("/engine/find-all");
    }
}
