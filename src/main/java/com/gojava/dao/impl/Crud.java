package com.gojava.dao.impl;

import com.gojava.model.HaveId;

import java.util.Map;

/**
 * @author Vancho
 * @author Artur Roze
 */
public interface Crud<T extends HaveId> {

    T create(T entity);

    boolean update(T entity);

    T delete(T entity);

    Map<Long, T> getAll();

    T findById(long id);
}
