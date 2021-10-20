package by.varaksa.cardealer.controller.filter;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.model.repository.UserRepository;
import by.varaksa.cardealer.model.repository.impl.UserRepositoryImpl;
import by.varaksa.cardealer.model.service.UserService;
import by.varaksa.cardealer.model.service.impl.UserServiceImpl;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code AuthFilter} designed for filter requests
 * for authorization and authentication
 *
 * @author Pavel Varaksa
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUi = true;
    private static final String REGEXP_LOGIN = RegexpPropertiesReader.getRegexp("login.regexp");
    private static final String REGEXP_PASSWORD = RegexpPropertiesReader.getRegexp("password.regexp");
    public UserRepository userRepository = new UserRepositoryImpl();
    public UserService userService = new UserServiceImpl(userRepository);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        if (session != null && session.getAttribute("login") != null) {

            Role role = (Role) session.getAttribute("role");

            moveToMenu(response, role);

        } else {
            try {
                if (StringValidator.isStringValidate(REGEXP_LOGIN, login) != isCheckStringFromUi ||
                        StringValidator.isStringValidate(REGEXP_PASSWORD, password) != isCheckStringFromUi) {
                    logger.error("Wasn't correct input format for login or password");
                    response.sendRedirect("/login-auth");
                    return;
                }

                if (userService.isUserExist(login)) {

                    if (userService.isAuthenticate(user)) {

                        Long id = userService.findIdByLogin(login);
                        Role role = userService.findRoleByLogin(login);

                        request.getSession().setAttribute("id", id);
                        request.getSession().setAttribute("login", login);
                        request.getSession().setAttribute("role", role);

                        moveToMenu(response, role);
                    } else {
                        response.sendRedirect("/login-auth");
                    }
                } else {
                    moveToMenu(response, Role.GUEST);
                }
            } catch (ServiceException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
    }

    private void moveToMenu(HttpServletResponse response, Role role) throws IOException {

        if (role.equals(Role.ADMIN)) {
            response.sendRedirect("/admin-menu");
        } else if (role.equals(Role.USER)) {
            response.sendRedirect("/user-menu");
        } else {
            response.sendRedirect("/login-auth");
        }
    }
}
