package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.service.UserService;
import jakarta.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    


}
