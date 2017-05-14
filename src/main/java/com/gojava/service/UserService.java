package com.gojava.service;

import com.gojava.dao.impl.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

/**
 * @author Vancho
 * @author Artur Roze
 */
public interface UserService<T extends User> extends Crud<T> {

    boolean isLoginExists(String login);

    boolean bookRoomOnUser(Room room, User user);

    boolean unBookRoomFromUser(Room room, User user);

    User findUserByLogin(String login);
}
