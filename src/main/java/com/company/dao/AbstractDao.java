package com.company.dao;

import java.util.List;

public interface AbstractDao<T> {
    List<T> findAll();

    T findById(Long id);

    T create(T entity);

    T update(T entity);

    boolean delete(Long id);

    Long countAll();
}
