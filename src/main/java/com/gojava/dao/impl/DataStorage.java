package com.gojava.dao.impl;

import com.gojava.model.Hotel;
import com.gojava.model.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataStorage implements Serializable {

    private static DataStorage INSTANCE;

    private Map<Long, Hotel> hotels = new HashMap<>();
    private Map<Long, User> users = new HashMap<>();


    private DataStorage() {
    }

    public static DataStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataStorage();
        }
        return INSTANCE;
    }

    public Map<Long, Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Map<Long, Hotel> hotels) {
        this.hotels = hotels;
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Long, User> users) {
        this.users = users;
    }
}
