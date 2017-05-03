package com.gojava.service.impl;

import com.gojava.dao.HotelCrud;
import com.gojava.dao.RoomCrud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;
import com.gojava.model.User;

import java.util.Map;
import java.util.Set;

/**
 *
 */

public class RoomServiceImpl implements RoomCrud<Room> {

    private HotelCrud<Hotel> hotelService = new HotelServiceImpl();

    @Override
    public Room create(Room entity) {
        return null;
    }

    @Override
    public Room update(Room entity) {
        return null;
    }

    @Override
    public Room delete(Room entity) {
        return null;
    }

    @Override
    public Map<Long, Room> getAll() {
        return null;
    }

    @Override
    public boolean bookUser(Room aRoom, User user) {
        return false;
    }

    @Override
    public boolean unBookUser(Room aRoom) {
        return false;
    }

    public Set<Room> getAllRooms() {
        return (Set<Room>) getAll().values();
    }

    public Set<Room> getAllHotelRooms(Hotel hotel) {
        return hotel.getRooms();
    }

    public Room findRoomById(Long id) {
        //todo
        return null;
    }
}
