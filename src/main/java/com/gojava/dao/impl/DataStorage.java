package com.gojava.dao.impl;

import com.gojava.model.Hotel;
import com.gojava.model.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vancho
 * @author Artur Roze
 * This class is "Singleton"
 */
public class DataStorage implements Serializable {

    private static DataStorage INSTANCE;

    private Map<Long, Hotel> hotels = new HashMap<>();
    private Map<Long, User> users = new HashMap<>();

    /* A private Constructor prevents any other
    * class from instantiating.
    */
    private DataStorage() {
    }
    /* Static 'instance' method */
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
