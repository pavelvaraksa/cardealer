package by.varaksa.cardealer.repository;

import by.varaksa.cardealer.exception.RepositoryException;

import java.util.List;

public interface CrudRepository<K, V> {
    List<V> findAll() throws RepositoryException;

    V find(K key) throws RepositoryException;

    V save(V object) throws RepositoryException;

    V update(K key) throws RepositoryException;

    V delete(K key) throws RepositoryException;
}
