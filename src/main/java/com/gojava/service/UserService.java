package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

/**
 *
 */
public interface UserService<T extends User> extends Crud<T> {

    boolean isLoginExists(String login);

    boolean bookRoomOnUser(Room room, User user);

    boolean unBookRoomFromUser(Room room, User user);

    User findUserByLogin(String login);
}
