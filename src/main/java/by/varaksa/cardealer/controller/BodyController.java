package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.command.Commands;
import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.entity.BodyType;
import by.varaksa.cardealer.entity.Color;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.repository.impl.BodyRepositoryImpl;
import by.varaksa.cardealer.service.BodyService;
import by.varaksa.cardealer.service.impl.BodyServiceImpl;
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

@WebServlet(urlPatterns = {"/body/save", "/body/find-all", "/body/find-by-id", "/body/update", "/body/delete"})
public class BodyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();
    private Commands commandName;
    public BodyRepository bodyRepository = new BodyRepositoryImpl();
    public BodyService bodyService = new BodyServiceImpl(bodyRepository);

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
            switch (commandName) {
                case FIND_ALL_BODIES:
                    findAllBodies(request, response);
                    break;
                case FIND_BODY_BY_ID:
                    findBody(request, response);
                    break;
                default:
                    break;
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
                case SAVE_BODY:
                    saveBody(request, response);
                    break;
                case UPDATE_BODY:
                    updateBody(request, response);
                    break;
                case DELETE_BODY:
                    deleteBody(request, response);
                    break;
                default:
                    break;
            }
        } catch (ControllerException | ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "Body controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveBody(HttpServletRequest request, HttpServletResponse response) throws IOException, ControllerException, ServiceException, RepositoryException, ServletException {
        Color color = Color.valueOf(request.getParameter("color"));
        BodyType bodyType = BodyType.valueOf(request.getParameter("body_type"));
        Long carId = Long.valueOf(request.getParameter("car_id"));
        Body body = new Body(color, bodyType, carId);

        List<Body> existingBodies = bodyService.findAll();

        for (Body existingBody : existingBodies) {
            boolean hasSameBody = existingBody.getId().equals(body.getId());

            if (hasSameBody) {
                String errorMessage = "Body with id " + body.getId() + " already exists";
                logger.error(errorMessage);
                response.sendRedirect("/save-body-page");
                throw new ControllerException(errorMessage);
            }
        }
        bodyService.save(body);
        response.sendRedirect("/body/find-all");
    }

    private void findAllBodies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<Body> bodyList = bodyService.findAll();
        logger.info("Bodies were watched");
        request.setAttribute("bodyList", bodyList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-bodies-page");
        dispatcher.forward(request, response);
    }

    private void findBody(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        Body existingBody = bodyService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("find-by-id");
        request.setAttribute("oneBody", existingBody);
        dispatcher.forward(request, response);
    }

    private void updateBody(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Body body = bodyService.find(id);

        body.setColor(Color.valueOf(request.getParameter("color")));
        body.setBodyType(BodyType.valueOf(request.getParameter("body_type")));
        body.setCarId(Long.valueOf(request.getParameter("car_id")));

        bodyService.update(body);
        response.sendRedirect("/body/find-all");
    }

    private void deleteBody(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        bodyService.delete(id);
        response.sendRedirect("/body/find-all");
    }
}

