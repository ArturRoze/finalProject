package com.gojava.service;

import com.gojava.dao.impl.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

/**
 * @author Vancho
 * @author Artur Roze
 */
public interface RoomService<T extends Room> extends Crud<T> {

    boolean bookUser(Room room, User user);

    boolean unBookUserFromRoom(Room room);
}
