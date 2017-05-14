package com.gojava.dao.impl;

import com.gojava.model.Hotel;

import java.util.Map;

/**
 * @author Vancho
 * @author Artur Roze
 */
public class HotelDaoImpl implements Crud<Hotel> {

    @Override
    public Hotel create(Hotel hotel) {

        if (hotel == null) {
            throw new RuntimeException("Hotel can't be null");
        } else {
            DataStorage.getInstance().getHotels().put(hotel.getId(), hotel);
            return hotel;
        }
    }

    @Override
    public boolean update(Hotel hotel) {
        if (hotel == null) {
            throw new RuntimeException("Hotel can't be null");
        } else {
            DataStorage.getInstance().getHotels().put(hotel.getId(), hotel);
            return true;
        }
    }

    @Override
    public Hotel delete(Hotel hotel) {
        return DataStorage.getInstance().getHotels().remove(hotel.getId());
    }

    @Override
    public Map<Long, Hotel> getAll() {
        return DataStorage.getInstance().getHotels();
    }

    @Override
    public Hotel findById(long id) {
        return getAll().get(id);
    }
}
