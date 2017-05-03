package com.gojava.dao;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

public interface RoomCrud<T extends Room> extends Crud<T> {
    boolean bookUser(Room aRoom, User user);

    boolean unBookUser(Room aRoom);
}
