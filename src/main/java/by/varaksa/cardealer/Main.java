package by.varaksa.cardealer;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.repository.impl.BodyRepositoryImpl;

public class Main {
    public static void main(String[] args) throws RepositoryException {
        BodyRepository bodyRepository = new BodyRepositoryImpl();
        bodyRepository.findAll();

    }
}
