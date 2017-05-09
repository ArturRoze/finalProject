package com.gojava.dao.impl;

import com.gojava.model.Crud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomDaoImpl implements Crud<Room> {

    private Crud<Hotel> hotelDao = new HotelDaoImpl();

    @Override
    public Room create(Room entity) {
        hotelDao
                .findById(entity.getHotel().getId())
                .getRooms()
                .put(entity.getId(), entity);
        return entity;
    }

    @Override
    public boolean update(Room entity) {
        create(entity);
        return true;
    }

    @Override
    public Room delete(Room entity) {
        return getAll().remove(entity.getId());
    }

    @Override
    public Map<Long, Room> getAll() {
        Map<Long, Room> map = new HashMap<>();
        hotelDao.getAll().forEach((id, hotel) -> map.putAll(hotel.getRooms()));
        return map;
    }

    @Override
    public Room findById(long id) {
        return getAll().get(id);
    }
}
