package com.gojava.dao.impl;

import com.gojava.dao.HotelCrud;
import com.gojava.dao.RoomCrud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;
import com.gojava.model.User;

import java.util.HashMap;
import java.util.Map;

public class RoomDaoImpl implements RoomCrud<Room> {

    private HotelCrud<Hotel> hotelDao = new HotelDaoImpl();

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
        return entity;
    }

    @Override
    public Map<Long, Room> getAll() {
        Map<Long, Room> map = new HashMap<>();
        hotelDao.getAll().values()
                .forEach(hotel -> hotel.getRooms()
                        .forEach(room -> map.put(room.getId(), room)));
        return map;
    }

    @Override
    public boolean bookUser(Room aRoom, User user) {
        return false;
    }

    @Override
    public boolean unBookUser(Room aRoom) {
        return false;
    }
}
