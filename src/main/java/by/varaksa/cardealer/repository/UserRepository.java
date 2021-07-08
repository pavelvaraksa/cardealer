package by.varaksa.cardealer.repository;

import by.varaksa.cardealer.entity.User;
import by.varaksa.cardealer.exception.RepositoryException;

public interface UserRepository extends CrudRepository<Long, User> {
    boolean isLoginValidate(User user) throws RepositoryException;
}
