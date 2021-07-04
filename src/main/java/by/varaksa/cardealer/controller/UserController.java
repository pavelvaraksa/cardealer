package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.command.Commands;
import by.varaksa.cardealer.entity.Role;
import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.impl.UserRepositoryImpl;
import by.varaksa.cardealer.service.UserService;
import by.varaksa.cardealer.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class UserController extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    public UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    public UserController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processGetRequestOrResponse(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processGetRequestOrResponse(request, response);
    }

    private void processGetRequestOrResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user");
            if (dispatcher != null) {
                resolveGetRequestCommands(request, response);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
            if (dispatcher != null) {
                request.setAttribute("trace", e.getMessage());
                dispatcher.forward(request, response);
            }
        }
    }

    private void resolveGetRequestCommands(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ServiceException, ControllerException {
        Commands commandName = Commands.findByCommandName(request.getParameter("command"));

        try {
            switch (commandName) {
                case SHOW:
                    showNewForm(request, response);
                    break;
                case FIND_ALL:
                    findAllUsers(request, response);
                    break;
                case FIND_BY_ID:
                    findUser(request, response);
                    break;
                default:
                    break;
            }
        } catch (IOException stackTrace) {
            String errorMessage = ("User controller exception!");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void resolvePostRequestCommands(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, ControllerException {
        Commands commandName = Commands.findByCommandName(request.getParameter("command"));
        try {
            switch (commandName) {
                case CREATE:
                    saveUser(request, response);
                    break;
                case UPDATE:
                    updateUser(request, response);
                    break;
                case DELETE:
                    deleteUser(request, response);
                    break;
            }
        } catch (IOException stackTrace) {
            String errorMessage = ("User controller exception!");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Role role = Role.valueOf(request.getParameter("role"));
        boolean isBlocked = Boolean.parseBoolean(request.getParameter("is_blocked"));
        User newUser = new User(name, surname, birthDate, login, password, role, isBlocked);
        userService.save(newUser);
        response.sendRedirect("list");
    }

    private void findAllUsers(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ServiceException {
        List<User> listUser = userService.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void findUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {
        long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Role role = Role.valueOf(request.getParameter("role"));
        boolean isBlocked = Boolean.parseBoolean(request.getParameter("is_blocked"));
        Long id = Long.parseLong(request.getParameter("id"));

        User updateUser = new User(name, surname, birthDate, login, password, role, isBlocked, id);
        userService.update(updateUser);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.delete(id);
        response.sendRedirect("list");
    }
}



