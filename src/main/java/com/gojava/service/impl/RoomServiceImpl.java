package com.gojava.service.impl;

import com.gojava.dao.impl.RoomDaoImpl;
import com.gojava.model.Crud;
import com.gojava.model.Hotel;
import com.gojava.model.Room;
import com.gojava.model.User;
import com.gojava.service.HotelService;
import com.gojava.service.RoomService;

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
    public boolean bookUser(Room room, User user) {
        room.setAvailable(false);
        room.setBookedUserName(user.getLogin());
        return true;
    }

    @Override
    public boolean unBookUserFromRoom(Room room) {
        room.setAvailable(true);
        room.setBookedUserName(null);
        return true;
    }

}
