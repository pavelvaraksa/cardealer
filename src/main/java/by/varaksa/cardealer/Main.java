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
import by.varaksa.cardealer.service.impl.BodyServiceImpl;
import org.apache.logging.log4j.core.util.SystemMillisClock;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws RepositoryException, ServiceException {
        Timestamp now = new Timestamp(new java.util.Date().getTime());


        User user = new User("b", "b", LocalDate.of(2021,11,22), "mi33", "q",
                Role.USER);


        UserRepository userRepository1 = new UserRepositoryImpl();
        //userRepository1.findAll();

        UserRepository userRepository2 = new UserRepositoryImpl();
        //userRepository2.find(99L);

        UserRepository userRepository3 = new UserRepositoryImpl();
        userRepository3.save(user);

        UserRepository userRepository4 = new UserRepositoryImpl();
        //userRepository4.update(user2);

        UserRepository userRepository5 = new UserRepositoryImpl();
        //userRepository5.delete(6L);

    }
}
