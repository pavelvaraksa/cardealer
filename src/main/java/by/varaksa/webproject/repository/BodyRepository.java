package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.Body;

public interface BodyRepository extends CrudRepository<Long, Body> {
    Body find(Long id);

    Body save(Body body);

    Body update(Long id);

    Body delete(Long id);
}
