package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.controller.command.Commands;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.model.entity.Car;
import by.varaksa.cardealer.model.entity.Country;
import by.varaksa.cardealer.model.entity.Model;
import by.varaksa.cardealer.model.repository.CarRepository;
import by.varaksa.cardealer.model.repository.impl.CarRepositoryImpl;
import by.varaksa.cardealer.model.service.CarService;
import by.varaksa.cardealer.model.service.impl.CarServiceImpl;
import by.varaksa.cardealer.model.validator.CarValidator;
import by.varaksa.cardealer.model.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/car/save", "/car/find-all", "/car/find-by-id", "/car/update", "/car/delete"})
public class CarController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final boolean isCheckStringFromUI = true;
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
                case FIND_ALL_CARS:
                    findAllCars(request, response);
                    break;
                case FIND_CAR_BY_ID:
                    findCar(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException exception) {
            String errorMessage = "Car controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void processPostRequests(HttpServletRequest request, HttpServletResponse response) {
        commandName = Commands.findByCommandName(request.getServletPath());

        try {
            switch (commandName) {
                case SAVE_CAR:
                    saveCar(request, response);
                    break;
                case UPDATE_CAR:
                    updateCar(request, response);
                    break;
                case DELETE_CAR:
                    deleteCar(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServiceException | IOException | RepositoryException | ServletException exception) {
            String errorMessage = "Car controller exception." + exception;
            logger.error(errorMessage);
        }
    }

    private void saveCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, RepositoryException, ServletException {
        Model model = Model.valueOf(request.getParameter("model"));
        Country country = Country.valueOf(request.getParameter("issue_country"));

        if (CarValidator.isCarValidate(CarValidator.GUARANTEE_PERIOD_REGEXP, request.getParameter("guarantee_period")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.PRICE_REGEXP, request.getParameter("price")) == isCheckStringFromUI) {

            Integer guaranteePeriod = Integer.valueOf((request.getParameter("guarantee_period")));
            Integer price = Integer.valueOf((request.getParameter("price")));

            Car car = new Car(model, country, guaranteePeriod, price);

            carService.save(car);
            response.sendRedirect("/car/find-all");
            return;
        }

        logger.error("Car wasn't saved");
        response.sendRedirect("/car/find-all");
    }

    private void findAllCars(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        List<Car> carList = carService.findAll();
        logger.info("Cars were watched");
        request.setAttribute("carList", carList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-all-cars-page");
        dispatcher.forward(request, response);
    }

    private void findCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        Car existingCar = carService.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("find-by-id");
        request.setAttribute("oneCar", existingCar);
        dispatcher.forward(request, response);
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Car car = carService.find(id);

        car.setModel(Model.valueOf(request.getParameter("model")));
        car.setIssueCountry(Country.valueOf((request.getParameter("issue_country"))));

        if (CarValidator.isCarValidate(CarValidator.GUARANTEE_PERIOD_REGEXP, request.getParameter("guarantee_period")) == isCheckStringFromUI &&
                CarValidator.isCarValidate(CarValidator.PRICE_REGEXP, request.getParameter("price")) == isCheckStringFromUI &&
                UserValidator.isUserValidate(UserValidator.USER_ORDER_ID, request.getParameter("user_order_id")) == isCheckStringFromUI) {

            car.setGuaranteePeriod(Integer.valueOf(request.getParameter("guarantee_period")));
            car.setPrice(Integer.valueOf((request.getParameter("price"))));
            car.setUserOrderId(Long.valueOf((request.getParameter("user_order_id"))));

            carService.update(car);
            response.sendRedirect("/car/find-all");
            return;
        }

        logger.error("Car wasn't updated");
        response.sendRedirect("/car/find-all");
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        carService.delete(id);
        response.sendRedirect("/car/find-all");
    }
}
