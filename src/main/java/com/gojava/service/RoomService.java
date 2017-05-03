package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

public interface RoomService<T extends Room> extends Crud<T> {
    boolean bookUser(Room aRoom, User user);

    boolean unBookUser(Room aRoom);
}
