package com.gojava.service.impl;

import com.gojava.service.HotelService;
import com.gojava.dao.impl.HotelDaoImpl;
import com.gojava.model.Hotel;

import java.util.Map;

public class HotelServiceImpl implements HotelService<Hotel> {

    private HotelService<Hotel> hotelDao = new HotelDaoImpl();

    @Override
    public Hotel create(Hotel hotel) {
        return hotelDao.create(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
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
}
