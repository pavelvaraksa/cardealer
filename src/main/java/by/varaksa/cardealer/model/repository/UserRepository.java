package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.entity.Role;
import by.varaksa.cardealer.model.entity.User;

public interface UserRepository extends CrudRepository<Long, User> {
    boolean isAuthenticate(User user) throws RepositoryException;

    boolean isUserExist(String login) throws RepositoryException;

    Role findRoleByLogin(String login) throws RepositoryException;
}
