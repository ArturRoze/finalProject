package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;

import java.util.Map;

public interface HotelService<T extends Hotel> extends Crud<T> {

    Map<Long, Room> getAllHotelRooms (Hotel hotel);

    boolean isRoomNumberExistsInHotel(Integer number, Hotel hotel);

    Room addRoomToHotel (Room room, Hotel hotel);

    Room findRoomByNumberInHotel(Integer number, Hotel hotel);
}
