package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.ControllerException;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Car;
import by.varaksa.cardealer.model.entity.Country;
import by.varaksa.cardealer.model.entity.Model;
import by.varaksa.cardealer.model.repository.CarRepository;
import by.varaksa.cardealer.model.repository.impl.CarRepositoryImpl;
import by.varaksa.cardealer.model.service.CarService;
import by.varaksa.cardealer.model.service.impl.CarServiceImpl;
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
import java.util.List;

/**
 * Class {@code CarController} designed for communication between controller
 * and service for actions related to car
 *
 * @author Pavel Varaksa
 */
@WebServlet(urlPatterns = {"/car/save", "/car/find-all", "/car/find-all-for-order", "/car/update", "/car/delete"})
public class CarController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private Commands commandName;
    public CarRepository carRepository = new CarRepositoryImpl();
    public CarService carService = new CarServiceImpl(carRepository);

    public CarController() {
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
                case FIND_ALL_CARS -> findAllCars(request, response);
                case FIND_ALL_CARS_FOR_ORDER -> findAllCarsForOrder(request, response);
                default -> {
                }
            }
        } catch (ServiceException | ControllerException exception) {
            String errorMessage = "Car controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_CAR -> saveCar(request, response);
                case UPDATE_CAR -> updateCar(request, response);
                case DELETE_CAR -> deleteCar(request, response);
                default -> {
                }
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException | ControllerException exception) {
            String errorMessage = "Car controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException, ControllerException {
        try {
            Model model = Model.valueOf(request.getParameter("model"));
            Country country = Country.valueOf(request.getParameter("issue_country"));
            Integer guaranteePeriod = Integer.valueOf(request.getParameter("guarantee_period"));
            Integer price = Integer.valueOf(request.getParameter("price"));
            Long dealerId = Long.valueOf(request.getParameter("dealer_id"));
            Car car = new Car(model, country, guaranteePeriod, price, dealerId);

            carService.save(car);
            response.sendRedirect("/car/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't save car";
            logger.error(errorMessage);
            response.sendRedirect("/error-400");
            throw new ControllerException(errorMessage);
        }
    }

    private void findAllCars(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, ControllerException {
        try {
            List<Car> carList = carService.findAll();
            request.setAttribute("carList", carList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-cars");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find cars";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void findAllCarsForOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, ControllerException {
        try {
            List<Car> carListForOrder = carService.findAllForOrder();
            request.setAttribute("carListForOrder", carListForOrder);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-cars-for-order");
            dispatcher.forward(request, response);
        } catch (ServiceException exception) {
            String errorMessage = "Can't find cars";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Car car = carService.find(id);

            car.setModel(Model.valueOf(request.getParameter("model")));
            car.setIssueCountry(Country.valueOf((request.getParameter("issue_country"))));
            car.setGuaranteePeriod(Integer.valueOf(request.getParameter("guarantee_period")));
            car.setPrice(Integer.valueOf((request.getParameter("price"))));
            car.setDealerId(Long.valueOf(request.getParameter("dealer_id")));
            carService.update(car);

            request.setAttribute("car", car);
            response.sendRedirect("/car/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't update car";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ControllerException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            carService.delete(id);
            response.sendRedirect("/car/find-all");
        } catch (ServiceException exception) {
            String errorMessage = "Can't delete car";
            response.sendRedirect("/error-400");
            logger.error(errorMessage);
            throw new ControllerException(errorMessage);
        }
    }
}
