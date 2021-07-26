package by.varaksa.cardealer.controller;

import by.varaksa.cardealer.command.Commands;
import by.varaksa.cardealer.entity.Brand;
import by.varaksa.cardealer.entity.Car;
import by.varaksa.cardealer.entity.Country;
import by.varaksa.cardealer.entity.Model;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.CarRepository;
import by.varaksa.cardealer.repository.impl.CarRepositoryImpl;
import by.varaksa.cardealer.service.CarService;
import by.varaksa.cardealer.service.impl.CarServiceImpl;
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
    private static final long serialVersionUID = 1L;
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
        Brand brand = Brand.valueOf(request.getParameter("brand"));
        Model model = Model.valueOf(request.getParameter("model"));
        Country country = Country.valueOf(request.getParameter("issue_country"));
        Integer guaranteePeriod = Integer.valueOf((request.getParameter("guarantee_period")));
        Integer price = Integer.valueOf((request.getParameter("price")));
        Car car = new Car(brand, model, country, guaranteePeriod, price);

        carService.save(car);
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

        car.setBrand(Brand.valueOf(request.getParameter("brand")));
        car.setModel(Model.valueOf(request.getParameter("model")));
        car.setIssueCountry(Country.valueOf((request.getParameter("issue_country"))));
        car.setGuaranteePeriod(Integer.valueOf(request.getParameter("guarantee_period")));
        car.setPrice(Integer.valueOf((request.getParameter("price"))));
        car.setUserOrderId(Long.valueOf((request.getParameter("user_order_id"))));

        carService.update(car);
        response.sendRedirect("/car/find-all");
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Long id = Long.parseLong(request.getParameter("id"));
        carService.delete(id);
        response.sendRedirect("/car/find-all");
    }
}
