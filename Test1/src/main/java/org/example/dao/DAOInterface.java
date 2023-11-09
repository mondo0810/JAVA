package org.example.dao;
import java.util.List;
public interface DAOInterface<T> {
    List<T> getAll();
    T getById(int id);
    void add(T entity);
    void update(T entity);
    void delete(int id);
}
