package com.gojava.service.impl;

import com.gojava.model.Crud;
import com.gojava.model.Room;
import com.gojava.service.HotelService;
import com.gojava.dao.impl.HotelDaoImpl;
import com.gojava.model.Hotel;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<Hotel> getAllHotels() {
        return getAll().values().stream().collect(Collectors.toSet());
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
    public Set<Hotel> findHotelsByCity(String city, Set<Hotel> hotels) {
        return hotels.stream().filter(hotel -> city.equals(hotel.getCity())).collect(Collectors.toSet());
    }

    @Override
    public Set<Hotel> findHotelsByName(String name, Set<Hotel> hotels) {
        return hotels.stream().filter(hotel -> name.equals(hotel.getCity())).collect(Collectors.toSet());
    }
}
