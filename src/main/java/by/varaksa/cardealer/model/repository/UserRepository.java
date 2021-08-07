package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.model.entity.User;
import by.varaksa.cardealer.exception.RepositoryException;

public interface UserRepository extends CrudRepository<Long, User> {
    boolean isAuthenticate(User user) throws RepositoryException;

    void logOut(User user) throws RepositoryException;
}
