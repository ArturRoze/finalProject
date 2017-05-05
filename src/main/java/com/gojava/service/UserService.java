package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

/**
 *
 */
public interface UserService<T extends User> extends Crud<T> {

    boolean isLoginExists(String login);
    boolean bookRoom(Room room, User user);
    boolean removeBookRoom(Room room);
}
