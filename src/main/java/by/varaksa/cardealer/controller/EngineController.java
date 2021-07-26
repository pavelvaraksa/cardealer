package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.command.Commands;
import by.varaksa.cardealer.entity.*;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.EngineRepository;
import by.varaksa.cardealer.repository.impl.EngineRepositoryImpl;
import by.varaksa.cardealer.service.EngineService;
import by.varaksa.cardealer.service.impl.EngineServiceImpl;
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

@WebServlet(urlPatterns = {"/engine/save", "/engine/find-all", "/engine/find-by-id", "/engine/update", "/engine/delete"})
public class EngineController extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
            switch (commandName) {
                case FIND_ALL_ENGINES:
                    findAllEngines(request, response);
                    break;
                case FIND_ENGINE_BY_ID:
                    findEngine(request, response);
                    break;
                default:
                    break;
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
                case SAVE_ENGINE:
                    saveEngine(request, response);
                    break;
                case UPDATE_ENGINE:
                    updateEngine(request, response);
                    break;
                case DELETE_ENGINE:
                    deleteEngine(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "Engine controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveEngine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException {
        EngineType engineType = EngineType.valueOf(request.getParameter("engine_type"));
        FuelType fuelType = FuelType.valueOf(request.getParameter("fuel_type"));
        Double volume = Double.valueOf((request.getParameter("volume")));
        Integer cylindersCount = Integer.valueOf((request.getParameter("cylinders_count")));
        Long carId = Long.valueOf((request.getParameter("car_id")));
        Engine engine = new Engine(engineType, fuelType, volume, cylindersCount, carId);

        engineService.save(engine);
        response.sendRedirect("/engine/find-all");
    }

    private void findAllEngines(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<Engine> engineList = engineService.findAll();
        logger.info("Engines were watched");
        request.setAttribute("engineList", engineList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-engines-page");
        dispatcher.forward(request, response);
    }

    private void findEngine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        Engine existingEngine = engineService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("find-by-id");
        request.setAttribute("oneEngine", existingEngine);
        dispatcher.forward(request, response);
    }

    private void updateEngine(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Engine engine = engineService.find(id);

        engine.setEngineType(EngineType.valueOf(request.getParameter("engine_type")));
        engine.setFuelType(FuelType.valueOf(request.getParameter("fuel_type")));
        engine.setVolume(Double.valueOf(request.getParameter("volume")));
        engine.setCylindersCount(Integer.valueOf(request.getParameter("cylinders_count")));

        engineService.update(engine);
        response.sendRedirect("/engine/find-all");
    }

    private void deleteEngine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        engineService.delete(id);
        response.sendRedirect("/engine/find-all");
    }
}
