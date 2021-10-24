package by.varaksa.cardealer.controller.filter;

import by.varaksa.cardealer.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code TransmissionFilter} designed for filter requests
 * from unauthenticated users
 *
 * @author Pavel Varaksa
 */
@WebFilter(filterName = "TransmissionFilter")
public class TransmissionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Object login = session.getAttribute("login");
        Object role = session.getAttribute("role");

        if (login == null || role != Role.ADMIN) {
            logger.error("Transmission filter blocked an attempt to enter a forbidden page");
            response.sendRedirect("/error-403");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
