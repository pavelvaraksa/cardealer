package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.model.repository.UserRepository;
import by.varaksa.cardealer.model.repository.impl.UserRepositoryImpl;
import by.varaksa.cardealer.model.service.UserService;
import by.varaksa.cardealer.model.service.impl.UserServiceImpl;
import by.varaksa.cardealer.util.NotificationUserEmail;
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

/**
 * Class {@code UserController} designed for communication between controller
 * and service for actions related to user
 *
 * @author Pavel Varaksa
 */
@WebServlet(urlPatterns = {"/user/save", "/user/find-all", "/user/find", "/user-info/find-user", "/user/update", "/user-info/update-user", "/register/verify", "/logout"})
public class UserController extends HttpServlet {
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
                case FIND_ALL_USERS -> findAllUsers(request, response);
                case FIND_USER_BY_LOGIN -> findUserByLogin(request, response);
                case FIND_USER_BY_ID -> findUserById(request, response);
                case LOGOUT -> logOut(request, response);
            }
        } catch (ControllerException exception) {
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
                case UPDATE_USER_BY_LOGIN -> updateUserByLogin(request, response);
            }
        } catch (ControllerException | ServiceException | IOException | ServletException exception) {
            String errorMessage = "User controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void findAllUsers(HttpServletRequest request, HttpServletResponse response) throws ControllerException, ServletException, IOException {
        try {
            List<User> userList = userService.findAll();
            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-users");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find users";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void findUserByLogin(HttpServletRequest request, HttpServletResponse response) throws ControllerException, ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String login = String.valueOf(session.getAttribute("login"));
            List<User> user = userService.findOneByLogin(login);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-user-by-login");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find user";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void findUserById(HttpServletRequest request, HttpServletResponse response) throws ControllerException, ServletException, IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            User user = userService.find(id);
            request.setAttribute("user", user);
            response.sendRedirect("/user/find");
        } catch (ServiceException exception) {
            String errorMessage = "Can't find user";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws ControllerException, ServletException, IOException, ServiceException {
        try {
            HttpSession session = request.getSession();
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            LocalDate birthDate;

            if (request.getParameter("birth_date").isEmpty()) {
                birthDate = null;
            } else {
                birthDate = LocalDate.parse(request.getParameter("birth_date"));
            }

            String phoneNumber = request.getParameter("phone_number");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            NotificationUserEmail userEmail = new NotificationUserEmail();
            String userCode = userEmail.getRandom();
            User user = new User(firstname, lastname, birthDate, phoneNumber, login, password, email, userCode);
            session.setAttribute("user", user);
            userService.checkBeforeSave(user);
            boolean confirmCode = userEmail.sendEmail(user);

            if (confirmCode) {
                session.setAttribute("authCode", user);
                session.setAttribute("login", login);
                response.sendRedirect("/register/verify-page");
            }

        } catch (ServiceException exception) {
            String errorMessage = "Can't save user";
            logger.error(errorMessage);
            response.sendRedirect("/register");
            throw new ControllerException(errorMessage);
        }
    }

    private void verifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ControllerException {
        HttpSession session = request.getSession();
        String code = request.getParameter("authCode");
        String login = (String) session.getAttribute("login");
        User user = (User) session.getAttribute("authCode");

        if (code.equals(user.getCodeToRegister())) {
            logger.info("Confirmation code was right for user with login " + user.getLogin());

            try {
                userService.save(user);
            } catch (ServiceException exception) {
                String errorMessage = "Can't save user";
                logger.error(errorMessage);
                response.sendRedirect("/register");
                throw new ControllerException(errorMessage);
            }

            Long id = userService.findIdByLogin(login);
            session.setAttribute("id", id);
            session.setAttribute("login", login);
            response.sendRedirect("/user-menu");
            return;
        }

        logger.error("Confirmation code was wrong for user with login " + user.getLogin());
        response.sendRedirect("/register/verify-page");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {
        try {
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
            user.setPhoneNumber(request.getParameter("phone_number"));
            user.setRole(Role.valueOf((request.getParameter("role"))));
            user.setBlocked(Boolean.parseBoolean(request.getParameter("is_blocked")));
            userService.update(user);
            request.setAttribute("user", user);
            response.sendRedirect("/user/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't update user";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void updateUserByLogin(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {
        try {
            HttpSession session = request.getSession();
            String login = String.valueOf(session.getAttribute("login"));
            User user = userService.findByLogin(login);
            user.setFirstName(request.getParameter("firstname"));
            user.setLastName(request.getParameter("lastname"));
            LocalDate birthDate;

            if (request.getParameter("birth_date").isEmpty()) {
                birthDate = null;
            } else {
                birthDate = LocalDate.parse(request.getParameter("birth_date"));
            }

            user.setBirthDate(birthDate);
            user.setPhoneNumber(request.getParameter("phone_number"));
            userService.update(user);
            request.setAttribute("user", user);
            response.sendRedirect("/user-info/find-user");
        } catch (ServiceException exception) {
            String errorMessage = "Can't update user";
            response.sendRedirect("/user-info/update-user-by-login");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");
        String login = (String) session.getAttribute("login");
        session.removeAttribute("id");
        session.removeAttribute("login");
        session.invalidate();
        logger.info("Logout was completed for user with login " + login + " and id = " + id);
        response.sendRedirect("/login-auth");
    }
}



