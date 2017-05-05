package com.gojava.model;

import java.util.Map;

public interface Crud<T extends HaveId> {

    T create(T entity);

    boolean update(T entity);

    T delete(T entity);

    Map<Long, T> getAll();

    T findById(long id);
}
