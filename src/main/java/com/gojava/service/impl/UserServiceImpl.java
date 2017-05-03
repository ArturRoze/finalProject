package com.gojava.service.impl;

import com.gojava.dao.UserCrud;
import com.gojava.dao.impl.UserDaoImpl;
import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

import java.util.Map;

public class UserServiceImpl implements UserCrud<User> {

    Crud<User> userDaoImpl = new UserDaoImpl();

    @Override
    public User create(User entity) {
        return userDaoImpl.create(entity);
    }

    @Override
    public User update(User entity) {
        return userDaoImpl.update(entity);
    }

    @Override
    public User delete(User entity) {
        return userDaoImpl.delete(entity);
    }

    @Override
    public Map<Long, User> getAll() {
        return userDaoImpl.getAll();
    }

    @Override
    public User findById(Long id) {
        return userDaoImpl.getAll().get(id);
    }

    @Override
    public boolean isLoginExists(String login) {
        return getAll().values().stream().anyMatch(user -> login.equals(user.getLogin()));
    }

    @Override
    public boolean removeBookRoom(Room room) {
        return false;
    }

    @Override
    public boolean bookRoom(Room aRoom, User user) {
        return false;
    }
}
