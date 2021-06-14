package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.Order;

public interface OrderRepository extends CrudRepository<Long, Order> {
    Order find(Long id);

    Order save(Order order);

    Order update(Long id);

    Order delete(Long id);
}
