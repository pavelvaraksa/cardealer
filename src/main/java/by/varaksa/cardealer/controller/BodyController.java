package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Body;
import by.varaksa.cardealer.model.entity.BodyType;
import by.varaksa.cardealer.model.entity.Color;
import by.varaksa.cardealer.model.repository.BodyRepository;
import by.varaksa.cardealer.model.repository.impl.BodyRepositoryImpl;
import by.varaksa.cardealer.model.service.BodyService;
import by.varaksa.cardealer.model.service.impl.BodyServiceImpl;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
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

@WebServlet(urlPatterns = {"/body/save", "/body/find-all", "/body/update", "/body/delete"})
public class BodyController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUI = true;
    private Commands commandName;
    public BodyRepository bodyRepository = new BodyRepositoryImpl();
    public BodyService bodyService = new BodyServiceImpl(bodyRepository);
    private static final String REGEXP_CAR_ID = RegexpPropertiesReader.getRegexp("car.id.regexp");

    public BodyController() {
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

            if (commandName == Commands.FIND_ALL_BODIES) {
                findAllBodies(request, response);
            }
        } catch (ServiceException exception) {
            String errorMessage = "Body controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_BODY -> saveBody(request, response);
                case UPDATE_BODY -> updateBody(request, response);
                case DELETE_BODY -> deleteBody(request, response);
                default -> {
                }
            }
        } catch (ControllerException | ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "Body controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveBody(HttpServletRequest request, HttpServletResponse response) throws IOException, ControllerException, ServiceException, RepositoryException, ServletException {
        Color color = Color.valueOf(request.getParameter("color"));
        BodyType bodyType = BodyType.valueOf(request.getParameter("body_type"));

        if (CarValidator.isCarValidate(REGEXP_CAR_ID, request.getParameter("car_id")) == isCheckStringFromUI) {
            Long carId = Long.valueOf(request.getParameter("car_id"));

            Body body = new Body(color, bodyType, carId);

            bodyService.save(body);
            response.sendRedirect("/body/find-all");
            return;
        }

        logger.error("Body wasn't saved");
        response.sendRedirect("/body/find-all");
    }

    private void findAllBodies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<Body> bodyList = bodyService.findAll();
        logger.info("Bodies were watched");
        request.setAttribute("bodyList", bodyList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-bodies");
        dispatcher.forward(request, response);
    }

    private void updateBody(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Body body = bodyService.find(id);

        body.setColor(Color.valueOf(request.getParameter("color")));
        body.setBodyType(BodyType.valueOf(request.getParameter("body_type")));

        bodyService.update(body);
        response.sendRedirect("/body/find-all");
    }

    private void deleteBody(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        bodyService.delete(id);
        response.sendRedirect("/body/find-all");
    }
}

