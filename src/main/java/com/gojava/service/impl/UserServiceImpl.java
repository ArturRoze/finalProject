package com.gojava.service.impl;

import com.gojava.dao.impl.UserDaoImpl;
import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;
import com.gojava.service.UserService;

import java.util.Map;

public class UserServiceImpl implements UserService<User> {

    Crud<User> userDaoImpl = new UserDaoImpl();

    @Override
    public User create(User entity) {
        return userDaoImpl.create(entity);
    }

    @Override
    public boolean update(User entity) {
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
    public User findById(long id) {
        return userDaoImpl.findById(id);
    }


    @Override
    public boolean isLoginExists(String login) {
        return getAll().values().stream().anyMatch(user -> login.equals(user.getLogin()));
    }

    @Override
    public boolean bookRoomOnUser(Room room, User user) {
        user.getBookedRoomIds().add(room.getId());
        return true;
    }

    @Override
    public boolean unBookRoomFromUser(Room room, User user) {
        user.getBookedRoomIds().remove(room.getId());
        return true;
    }

    @Override
    public User findUserByLogin(String login) {
        return getAll().values().stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst()
                .get();
    }
}
