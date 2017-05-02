package com.gojava.dao.impl;

import com.gojava.dao.UserCrud;
import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements Crud<User> {

    @Override
    public User create(User entity) {
        DataStorage.getInstance().getUsers().put(entity.getId(), entity);
        return entity;
    }

    @Override
    public User update(User entity) {
        create(entity);
        return entity;
    }

    @Override
    public User delete(User entity) {
        return DataStorage.getInstance().getUsers().remove(entity.getId());
    }

    @Override
    public Map<Long, User> getAll() {
        return DataStorage.getInstance().getUsers();
    }


}
