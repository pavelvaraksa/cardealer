package by.varaksa.cardealer;

import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.repository.impl.BodyRepositoryImpl;
import by.varaksa.cardealer.service.BodyService;
import by.varaksa.cardealer.service.impl.BodyServiceImpl;

public class Main {
    public static void main(String[] args) throws RepositoryException, ServiceException {
        Body body = new Body();

        BodyRepository bodyRepository = new BodyRepositoryImpl();
        //bodyRepository.save(body);



    }
}
