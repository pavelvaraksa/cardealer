package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.Transmission;

public interface TransmissionRepository extends CrudRepository<Long, Transmission> {
    Transmission find(Long id);

    Transmission save(Transmission transmission);

    Transmission update(Long id);

    Transmission delete(Long id);
}
