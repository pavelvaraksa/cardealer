package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.Dealer;

public interface DealerRepository extends CrudRepository<Long, Dealer> {
    Dealer find(Long id);

    Dealer save(Dealer dealer);

    Dealer update(Long id);

    Dealer delete(Long id);
}
