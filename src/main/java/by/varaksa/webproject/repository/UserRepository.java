package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.User;

public interface UserRepository extends CrudRepository<Long, User> {
    User find(Long id);

    User save(User user);

    User update(Long id);

    User delete(Long id);
}
