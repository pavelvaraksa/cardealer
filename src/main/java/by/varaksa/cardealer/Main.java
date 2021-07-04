package by.varaksa.cardealer;

import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.entity.Role;
import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.repository.UserRepository;
import by.varaksa.cardealer.repository.impl.BodyRepositoryImpl;
import by.varaksa.cardealer.repository.impl.UserRepositoryImpl;
import by.varaksa.cardealer.service.BodyService;
import by.varaksa.cardealer.service.UserService;
import by.varaksa.cardealer.service.impl.BodyServiceImpl;
import by.varaksa.cardealer.service.impl.UserServiceImpl;
import org.apache.logging.log4j.core.util.SystemMillisClock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws RepositoryException, ServiceException {

        //User user = new User("b", "b", LocalDate.of(2021,11,22), "Mike", "q", Role.USER);


        UserRepository userRepository1 = new UserRepositoryImpl();
        UserService userService1 = new UserServiceImpl(userRepository1);
        //userService1.findAll();

        UserRepository userRepository2 = new UserRepositoryImpl();
        UserService userService2 = new UserServiceImpl(userRepository1);
        //userService2.find(31L);

        UserRepository userRepository3 = new UserRepositoryImpl();
        UserService userService3 = new UserServiceImpl(userRepository1);
        //userService3.save(user3);

        UserRepository userRepository4 = new UserRepositoryImpl();
        UserService userService4 = new UserServiceImpl(userRepository1);
        //userRepository4.update(user1);

        UserRepository userRepository5 = new UserRepositoryImpl();
        UserService userService5 = new UserServiceImpl(userRepository1);
        //userService.delete(28L);


    }
}
