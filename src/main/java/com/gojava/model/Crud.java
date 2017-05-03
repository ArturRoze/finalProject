package com.gojava.model;

import java.util.Map;

public interface Crud<T extends HaveId> {

    T create(T entity);

    T update(T entity);

    T delete(T entity);

    Map<Long, T> getAll();

}
