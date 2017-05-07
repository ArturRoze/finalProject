package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;

import java.util.Map;
import java.util.Set;

public interface HotelService<T extends Hotel> extends Crud<T> {

    Map<Long, Room> getAllHotelRooms (Hotel hotel);

    boolean isRoomNumberExistsInHotel(Integer number, Hotel hotel);

    boolean isHotelNameExists(String name);

    boolean isHotelExistsInCity(String hotelName, String city);

    boolean isCityContainsHotels(String city);

    Room addRoomToHotel (Room room, Hotel hotel);

    Room findRoomByNumberInHotel(Integer number, Hotel hotel);

    T findHotelByNameInCity(String name, String city);
}
