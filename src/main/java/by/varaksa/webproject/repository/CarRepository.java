package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.Car;

public interface CarRepository extends CrudRepository<Long, Car> {
    Car find(Long id);

    Car save(Car car);

    Car update(Long id);

    Car delete(Long id);
}
