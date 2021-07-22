package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.command.Commands;
import by.varaksa.cardealer.entity.Role;
import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.UserRepository;
import by.varaksa.cardealer.repository.impl.UserRepositoryImpl;
import by.varaksa.cardealer.service.UserService;
import by.varaksa.cardealer.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();
    private Commands commandName;
    public UserRepository userRepository = new UserRepositoryImpl();
    public UserService userService = new UserServiceImpl(userRepository);

    public UserController() {
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
                case FIND_ALL:
                    findAllUsers(request, response);
                    break;
                case FIND_BY_ID:
                    findUser(request, response);
                    break;
                case LOGOUT:
                    logOut(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException exception) {
            String errorMessage = "User controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case LOGIN:
                    confirmAuthenticate(request, response);
                    break;
                case SAVE:
                    saveUser(request, response);
                    break;
                case UPDATE:
                    updateUser(request, response);
                    break;
                case DELETE:
                    deleteUser(request, response);
                    break;
                default:
                    break;
            }
        } catch (ControllerException | ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "User controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    public void confirmAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ControllerException {
        HttpSession session = request.getSession();
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        User user = new User();
        user.setLogin(userLogin);
        user.setPassword(userPassword);

        try {

            if (userService.isAuthenticate(user)) {
                session.setAttribute("user", user);
                logger.info("Login and password were correct");
                response.sendRedirect("/main-menu");
                return;
            }
            logger.error("Login or password weren't correct");
            response.sendRedirect("/login-auth");

        } catch (ServiceException exception) {
            throw new ControllerException(exception);
        }
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (session != null) {
            session.invalidate();
        }

        logger.info("Logout was completed");
        response.sendRedirect("/login-auth");
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ControllerException, ServiceException, RepositoryException, ServletException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        LocalDate birthDate;

        if (request.getParameter("birth_date").isEmpty()) {
            String defaultDate = "01/01/2000";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            birthDate = LocalDate.parse(defaultDate, formatter);
        } else {
            birthDate = LocalDate.parse(request.getParameter("birth_date"));
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User(firstname, lastname, birthDate, login, password, email);

        List<User> existingUsers = userService.findAll();

        for (User existingUser : existingUsers) {
            boolean hasSameUser = existingUser.getLogin().equals(user.getLogin());

            if (hasSameUser) {
                String errorMessage = "User with login " + user.getLogin() + " already exists";
                logger.error(errorMessage);
                response.sendRedirect("/register-page");
                throw new ControllerException(errorMessage);
            }
        }
        userService.save(user);
        response.sendRedirect("/main-menu");
    }

    private void findAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<User> userList = userService.findAll();
        logger.info("Users were watched");
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-page");
        dispatcher.forward(request, response);
    }

    private void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("find-by-id");
        request.setAttribute("oneUser", existingUser);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.find(id);

        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        LocalDate birthDate;

        if (request.getParameter("birth_date").isEmpty()) {
            String defaultDate = "01/01/2000";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            birthDate = LocalDate.parse(defaultDate, formatter);
        } else {
            birthDate = LocalDate.parse(request.getParameter("birth_date"));
        }

        user.setBirthDate(birthDate);
        user.setEmail(request.getParameter("email"));
        user.setRole(Role.valueOf((request.getParameter("role"))));
        user.setBlocked(Boolean.parseBoolean(request.getParameter("is_blocked")));

        userService.update(user);
        response.sendRedirect("/find-all");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.delete(id);
        response.sendRedirect("/find-all");
    }
}



