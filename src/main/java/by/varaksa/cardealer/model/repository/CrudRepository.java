package by.varaksa.cardealer.model.repository;

import by.varaksa.cardealer.exception.RepositoryException;

import java.util.List;

/**
 * Interface {@code CrudRepository} designed to be implemented by various interfaces
 *
 * @author Pavel Varaksa
 */
public interface CrudRepository<K, V> {

    /**
     * Save new object to the database
     *
     * @param object {@code V} new object
     * @throws RepositoryException if repository exception happened
     */
    V save(V object) throws RepositoryException;

    /**
     * Find all objects from the database
     *
     * @throws RepositoryException if repository exception happened
     */
    List<V> findAll() throws RepositoryException;

    /**
     * Find object from the database by key
     *
     * @param key {@code K} new key
     * @throws RepositoryException if repository exception happened
     */
    V find(K key) throws RepositoryException;

    /**
     * Update object from the database by value
     *
     * @param object {@code V} new object
     * @throws RepositoryException if repository exception happened
     */
    V update(V object) throws RepositoryException;

    /**
     * Delete object from the database by key
     *
     * @param key {@code K} new key
     * @throws RepositoryException if repository exception happened
     */
    V delete(K key) throws RepositoryException;
}
