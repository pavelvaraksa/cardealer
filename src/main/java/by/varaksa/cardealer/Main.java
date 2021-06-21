package by.varaksa.cardealer;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.repository.impl.BodyRepositoryImpl;
import by.varaksa.cardealer.service.BodyService;
import by.varaksa.cardealer.service.impl.BodyServiceImpl;

public class Main {
    public static void main(String[] args) throws RepositoryException, ServiceException {
        BodyRepository bodyRepository = new BodyRepositoryImpl();
        bodyRepository.findAll();

        BodyRepository bodyRepository1 = new BodyRepositoryImpl();

        BodyService bodyService = new BodyServiceImpl(bodyRepository1);
        bodyService.find(6L);

    }
}
