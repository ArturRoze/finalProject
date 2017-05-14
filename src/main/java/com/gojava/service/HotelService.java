package com.gojava.service;

import com.gojava.dao.impl.Crud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;

import java.util.Map;

/**
 * @author Vancho
 * @author Artur Roze
 */
public interface HotelService<T extends Hotel> extends Crud<T> {

    Map<Long, Room> getAllHotelRooms(Hotel hotel);

    boolean isRoomNumberExistsInHotel(Integer number, Hotel hotel);

    boolean isHotelNameExists(String name);

    boolean isHotelExistsInCity(String hotelName, String city);

    boolean isCityContainsHotels(String city);

    Room addRoomToHotel(Room room, Hotel hotel);

    Room findRoomByNumberInHotel(Integer number, Hotel hotel);

    T findHotelByNameInCity(String name, String city);
}
