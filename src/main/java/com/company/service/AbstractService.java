package com.company.service;

import java.util.List;

public interface AbstractService<T> {
    List<T> findAll();

    T findById(Long id);

    void delete(Long id);

    T create(T d);

    T update(T d);

    Long countAll();
}
