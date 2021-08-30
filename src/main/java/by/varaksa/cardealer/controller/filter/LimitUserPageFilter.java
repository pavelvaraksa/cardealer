package by.varaksa.cardealer.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LimitUserPageFilter")
public class LimitUserPageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Object sessionLogin = session.getAttribute("login");
        Object requestLogin = request.getParameter("login");

        if (sessionLogin == null && requestLogin == null) {
            logger.error("Limit user page filter tried to find a forbidden page");
            response.sendRedirect("/error-403");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
