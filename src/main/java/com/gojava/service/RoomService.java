package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.model.User;

import java.util.Map;

public interface RoomService<T extends Room> extends Crud<T> {

    boolean bookUser(Room room, User user);

    boolean unBookUserFromRoom(Room room);

    Map<Long, Room> getAllHotelRooms(long hotelId);
}
