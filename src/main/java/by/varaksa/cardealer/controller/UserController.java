package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.command.Commands;
import by.varaksa.cardealer.entity.Role;
import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.UserRepository;
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
    private static final long serialVersionUID = 1L;
    private static Logger logger = LogManager.getLogger();
    public UserRepository userRepository = new UserRepositoryImpl();
    public UserService userService = new UserServiceImpl(userRepository);

    public UserController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Commands commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case LOGIN:
                    login(request, response);
                    break;
                case CREATE:
                    saveUser(request, response);
                    break;
                case FIND_ALL:
                    findAllUsers(request, response);
                    break;
                case FIND_BY_ID:
                    findUser(request, response);
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
        } catch (ServiceException | ControllerException stackTrace) {
            String errorMessage = "Login or password weren't corrected!" + stackTrace;
            logger.error(errorMessage);
            response.sendRedirect("/login-auth");
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        User userParameters = new User();
        userParameters.setLogin(userLogin);
        userParameters.setPassword(userPassword);

        if (userService.isLoginValidate(userParameters)) {
            response.sendRedirect("/loginsuccess");
        }
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User newUser = new User(name, surname, birthDate, login, password);
        userService.save(newUser);
    }

    private void findAllUsers(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ServiceException {
        List<User> listUser = userService.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
        dispatcher.forward(request, response);
    }

    private void findUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException, ControllerException {
        long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("oneUser", existingUser);
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



