package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.UserOrder;

public interface UserOrderRepository extends CrudRepository<Long, UserOrder> {
    UserOrder find(Long id);

    UserOrder save(UserOrder userOrder);

    UserOrder update(Long id);

    UserOrder delete(Long id);
}
