package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

/**
 *
 */
public interface UserService<T extends User> extends Crud<T> {

    User findById(Long id);
    boolean isLoginExists(String login);
    boolean removeBookRoom(Room room);
    boolean bookRoom(Room room, User user);
}
