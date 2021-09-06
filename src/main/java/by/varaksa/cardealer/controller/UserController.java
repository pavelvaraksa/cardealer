package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.model.repository.UserRepository;
import by.varaksa.cardealer.model.repository.impl.UserRepositoryImpl;
import by.varaksa.cardealer.model.service.UserService;
import by.varaksa.cardealer.model.service.impl.UserServiceImpl;
import by.varaksa.cardealer.util.NotificationUserEmail;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/user/save", "/user/find-all", "/user/find-by-id",
        "/user/update", "/user/delete", "/register/verify", "/logout"})

public class UserController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUi = true;
    private static final String REGEXP_FIRSTNAME = RegexpPropertiesReader.getRegexp("firstname.regexp");
    private static final String REGEXP_LASTNAME = RegexpPropertiesReader.getRegexp("lastname.regexp");
    private static final String REGEXP_LOGIN = RegexpPropertiesReader.getRegexp("login.regexp");
    private static final String REGEXP_PASSWORD = RegexpPropertiesReader.getRegexp("password.regexp");
    private static final String REGEXP_EMAIL = RegexpPropertiesReader.getRegexp("email.regexp");
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
                case FIND_ALL_USERS -> findAllUsers(request, response);
                case FIND_USER_BY_ID -> findUser(request, response);
                case LOGOUT -> logOut(request, response);
            }
        } catch (ServiceException | ControllerException exception) {
            String errorMessage = "User controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_USER -> saveUser(request, response);
                case VERIFY_USER -> verifyUser(request, response);
                case UPDATE_USER -> updateUser(request, response);
                case DELETE_USER -> deleteUser(request, response);
            }
        } catch (ControllerException | ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "User controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private List<User> findAllUsers(HttpServletRequest request, HttpServletResponse response) throws ControllerException,
            ServiceException, ServletException, IOException {
        List<User> userList = userService.findAll();
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-users");
        dispatcher.forward(request, response);

        return userList;
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ControllerException, ServiceException, RepositoryException, ServletException {
        HttpSession session = request.getSession();
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        LocalDate birthDate;

        if (request.getParameter("birth_date").isEmpty()) {
            birthDate = null;
        } else {
            birthDate = LocalDate.parse(request.getParameter("birth_date"));
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (UserValidator.isUserValidate(REGEXP_FIRSTNAME, firstname) != isCheckStringFromUi ||
                UserValidator.isUserValidate(REGEXP_LASTNAME, lastname) != isCheckStringFromUi ||
                UserValidator.isUserValidate(REGEXP_LOGIN, login) != isCheckStringFromUi ||
                UserValidator.isUserValidate(REGEXP_PASSWORD, password) != isCheckStringFromUi ||
                UserValidator.isUserValidate(REGEXP_EMAIL, email) != isCheckStringFromUi) {
            logger.error("Wasn't correct input format for register user");
            response.sendRedirect("/register");
            return;
        }

        NotificationUserEmail userNotificationUserEmail = new NotificationUserEmail();
        String userCode = userNotificationUserEmail.getRandom();

        User user = new User(firstname, lastname, birthDate, login, password, email, userCode);
        session.setAttribute("user", user);

        List<User> existingUsers = findAllUsers(request, response);

        for (User existingUser : existingUsers) {
            boolean hasSameUser = existingUser.getLogin().equals(user.getLogin());

            if (hasSameUser) {
                String errorMessage = "User with login " + user.getLogin() + " already exists";
                logger.error(errorMessage);
                response.sendRedirect("/register");
                throw new ControllerException(errorMessage);
            }
        }

        boolean confirmCode = userNotificationUserEmail.sendEmail(user);

        if (confirmCode) {
            session.setAttribute("authCode", user);
            response.sendRedirect("/register/verify-page");
        }
    }

    private void verifyUser(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServiceException {
        HttpSession session = request.getSession();
        String code = request.getParameter("authCode");
        User user = (User) session.getAttribute("authCode");
        String login = user.getLogin();

        if (code.equals(user.getCodeToRegister())) {
            logger.info("Confirmation code was right for user with login " + user.getLogin());
            userService.save(user);
            response.sendRedirect("/user-menu");
            session.setAttribute("login", login);
            return;
        }

        logger.error("Confirmation code was wrong for user with login " + user.getLogin());
        response.sendRedirect("/register/verify-page");
    }

    private void findUser(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userService.find(id);
        request.setAttribute("oneUser", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-by-id");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.find(id);
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        LocalDate birthDate;

        if (request.getParameter("birth_date").isEmpty()) {
            birthDate = null;
        } else {
            birthDate = LocalDate.parse(request.getParameter("birth_date"));
        }

        user.setBirthDate(birthDate);
        user.setRole(Role.valueOf((request.getParameter("role"))));
        user.setBlocked(Boolean.parseBoolean(request.getParameter("is_blocked")));

        request.setAttribute("user", user);
        response.sendRedirect("/user/find-all");

        userService.update(user);

        response.sendRedirect("/error-400");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.delete(id);
        response.sendRedirect("/user/find-all");
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");

        session.invalidate();

        logger.info("Logout was completed for user with login " + login);
        response.sendRedirect("/login-auth");
    }
}



