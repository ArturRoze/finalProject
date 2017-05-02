package com.gojava.dao;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

/**
 *
 */
public interface UserCrud<T extends User> extends Crud<T> {

    User findById(Long id);
    boolean isLoginExists(String login);
    boolean removeBookRoom(Room room);
    boolean bookRoom(Room room, User user);
}
