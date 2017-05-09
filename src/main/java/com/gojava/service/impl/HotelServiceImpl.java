package com.gojava.service.impl;

import com.gojava.dao.impl.HotelDaoImpl;
import com.gojava.model.Crud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;
import com.gojava.service.HotelService;

import java.util.Map;

public class HotelServiceImpl implements HotelService<Hotel> {

    private Crud<Hotel> hotelDao = new HotelDaoImpl();

    @Override
    public Hotel create(Hotel hotel) {
        return hotelDao.create(hotel);
    }

    @Override
    public boolean update(Hotel hotel) {
        return hotelDao.update(hotel);
    }

    @Override
    public Hotel delete(Hotel hotel) {
        return hotelDao.delete(hotel);
    }

    @Override
    public Map<Long, Hotel> getAll() {
        return hotelDao.getAll();
    }

    @Override
    public Hotel findById(long id) {
        return hotelDao.findById(id);
    }

    @Override
    public Map<Long, Room> getAllHotelRooms(Hotel hotel) {
        return hotel.getRooms();
    }

    @Override
    public boolean isRoomNumberExistsInHotel(Integer number, Hotel hotel) {
        return getAllHotelRooms(hotel).values().stream()
                .anyMatch(room -> number.equals(room.getNumber()));
    }

    @Override
    public boolean isHotelNameExists(String name) {
        return getAll().values().stream()
                .anyMatch(hotel -> hotel.getName().equals(name));
    }

    @Override
    public boolean isHotelExistsInCity(String hotelName, String city) {
        return getAll().values().stream()
                .filter(hotel -> hotel.getCity().equals(city))
                .anyMatch(hotel -> hotel.getName().equals(hotelName));
    }

    @Override
    public boolean isCityContainsHotels(String city) {
        return getAll().values().stream()
                .anyMatch(hotel -> hotel.getCity().equals(city));
    }

    @Override
    public Room addRoomToHotel(Room room, Hotel hotel) {
        return getAllHotelRooms(hotel).put(room.getId(), room);
    }

    @Override
    public Room findRoomByNumberInHotel(Integer number, Hotel hotel) {
        return getAllHotelRooms(hotel).values().stream()
                .filter(room -> number.equals(room.getNumber()))
                .findFirst()
                .get();
    }

    @Override
    public Hotel findHotelByNameInCity(String name, String city) {

        return getAll().values().stream()
                .filter(hotel -> city.equals(hotel.getCity()))
                .filter(hotel -> name.equals(hotel.getName()))
                .findFirst()
                .get();
    }

}
