package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.model.entity.Transmission;

/**
 * Interface {@code TransmissionRepository} designed for communication between repository
 * and database for actions related to transmission
 *
 * @author Pavel Varaksa
 */
public interface TransmissionRepository extends CrudRepository<Long, Transmission> {
}
