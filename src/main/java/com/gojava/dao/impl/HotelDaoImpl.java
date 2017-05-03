package com.gojava.dao.impl;

import com.gojava.service.HotelService;
import com.gojava.model.Hotel;

import java.util.Map;

public class HotelDaoImpl implements HotelService<Hotel> {

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
    public Hotel update(Hotel hotel) {
        //TODO remake it
        DataStorage.getInstance().getHotels().put(hotel.getId(), hotel);
        return hotel;
    }

    @Override
    public Hotel delete(Hotel hotel) {
        return DataStorage.getInstance().getHotels().remove(hotel.getId());
    }

    @Override
    public Map<Long, Hotel> getAll() {
        return DataStorage.getInstance().getHotels();
    }
}
