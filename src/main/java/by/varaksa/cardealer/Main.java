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

import java.sql.Date;

public class Main {
    public static void main(String[] args) throws RepositoryException, ServiceException {
        //User user1 = new User("b", "b", new Date(2021, 4, 11), "q8", "q",
                //Role.USER, false, new Date(2021, 4, 11), new Date(2021, 4, 11), 5L);

        //User user2 = new User("b", "b", new Date(2021, 4, 11), "mike212", "q",
                //Role.USER, false, new Date(2021, 4, 11), new Date(2021, 4, 11),9L);


        UserRepository userRepository1 = new UserRepositoryImpl();
        //userRepository1.findAll();

        UserRepository userRepository2 = new UserRepositoryImpl();
        //userRepository2.find(99L);

        UserRepository userRepository3 = new UserRepositoryImpl();
        //userRepository3.save(user);

        UserRepository userRepository4 = new UserRepositoryImpl();
        //userRepository4.update(user2);

        UserRepository userRepository5 = new UserRepositoryImpl();
        //userRepository5.delete(6L);

    }
}
