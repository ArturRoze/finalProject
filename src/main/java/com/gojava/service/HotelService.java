package com.gojava.service;

import com.gojava.model.Crud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;

import java.util.Map;
import java.util.Set;

public interface HotelService<T extends Hotel> extends Crud<T> {

    Set<T> getAllHotels();

    Map<Long, Room> getAllHotelRooms (Hotel hotel);

    boolean isRoomNumberExistsInHotel(Integer number, Hotel hotel);

    boolean isHotelExistsInCity(String hotelName, String city);

    Room addRoomToHotel (Room room, Hotel hotel);

    Room findRoomByNumberInHotel(Integer number, Hotel hotel);

    Set<T> findHotelsByCity(String city, Set<T> hotels);

    Set<T> findHotelsByName(String name, Set<T> hotels);
}
