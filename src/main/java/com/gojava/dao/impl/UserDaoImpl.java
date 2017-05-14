package com.gojava.dao.impl;

import com.gojava.model.User;

import java.util.Map;

/**
 * @author Vancho
 * @author Artur Roze
 */
public class UserDaoImpl implements Crud<User> {

    @Override
    public User create(User entity) {
        DataStorage.getInstance().getUsers().put(entity.getId(), entity);
        return entity;
    }

    @Override
    public boolean update(User entity) {
        create(entity);
        return true;
    }

    @Override
    public User delete(User entity) {
        return DataStorage.getInstance().getUsers().remove(entity.getId());
    }

    @Override
    public Map<Long, User> getAll() {
        return DataStorage.getInstance().getUsers();
    }

    @Override
    public User findById(long id) {
        return getAll().get(id);
    }
}
