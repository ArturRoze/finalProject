package com.gojava.service.impl;

import com.gojava.dao.impl.RoomDaoImpl;
import com.gojava.model.Crud;
import com.gojava.service.HotelService;
import com.gojava.service.RoomService;
import com.gojava.model.Hotel;
import com.gojava.model.Room;
import com.gojava.model.User;

import java.util.Map;

/**
 *
 */

public class RoomServiceImpl implements RoomService<Room> {

    private HotelService<Hotel> hotelService = new HotelServiceImpl();
    private Crud<Room> roomDaoImpl = new RoomDaoImpl();

    @Override
    public Room create(Room entity) {
        return roomDaoImpl.create(entity);
    }

    @Override
    public boolean update(Room entity) {
        return roomDaoImpl.update(entity);
    }

    @Override
    public Room delete(Room entity) {
        return roomDaoImpl.delete(entity);
    }

    @Override
    public Map<Long, Room> getAll() {
        return roomDaoImpl.getAll();
    }

    @Override
    public Room findById(long id) {
        return roomDaoImpl.findById(id);
    }

    @Override
    public boolean bookUser(Room aRoom, User user) {
        return false;
    }

    @Override
    public boolean unBookUser(Room aRoom) {
        return false;
    }

    public Map<Long, Room> getAllHotelRooms(long hotelId) {
        return hotelService.findById(hotelId).getRooms();
    }


}
