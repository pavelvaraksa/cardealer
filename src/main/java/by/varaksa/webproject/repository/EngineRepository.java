package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.Engine;

public interface EngineRepository extends CrudRepository<Long, Engine> {
    Engine find(Long id);

    Engine save(Engine engine);

    Engine update(Long id);

    Engine delete(Long id);
}