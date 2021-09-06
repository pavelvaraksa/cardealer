package by.varaksa.cardealer.controller.filter;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.model.repository.UserRepository;
import by.varaksa.cardealer.model.repository.impl.UserRepositoryImpl;
import by.varaksa.cardealer.model.service.UserService;
import by.varaksa.cardealer.model.service.impl.UserServiceImpl;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import by.varaksa.cardealer.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebFilter(filterName = "UserUpdateFilter")
public class UpdateFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_FIRSTNAME = RegexpPropertiesReader.getRegexp("firstname.regexp");
    private static final String REGEXP_LASTNAME = RegexpPropertiesReader.getRegexp("lastname.regexp");
    private static final boolean isCheckStringFromUi = true;
    public UserRepository userRepository = new UserRepositoryImpl();
    public UserService userService = new UserServiceImpl(userRepository);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Long id = Long.parseLong(request.getParameter("id"));
        User user = null;
        try {
            user = userService.find(id);
        } catch (ServiceException exception) {
            exception.printStackTrace();
        }

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

        if (UserValidator.isUserValidate(REGEXP_FIRSTNAME, user.getFirstName()) != isCheckStringFromUi ||
                UserValidator.isUserValidate(REGEXP_LASTNAME, user.getLastName()) != isCheckStringFromUi) {

            logger.error("User wasn't updated");
            response.sendRedirect("/error-400");
            return;
        }

        try {
            request.setAttribute("user", user);
            userService.update(user);
        } catch (ServiceException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }
}
