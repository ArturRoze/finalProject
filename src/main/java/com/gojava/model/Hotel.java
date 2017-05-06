package com.gojava.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Room in a hotel.
 *
 * @author Firsov
 * @version 1.0
 */

public class Hotel implements Serializable, HaveId, Comparable<Hotel> {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String city;
    private Map<Long, Room> rooms;

    public Hotel(String name, String city) {
        id = IdGenerator.getRandomId();
        this.name = name;
        this.city = city;
        rooms = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<Long, Room> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (getId() != hotel.getId()) return false;
        if (getName() != null ? !getName().equals(hotel.getName()) : hotel.getName() != null) return false;
        if (getCity() != null ? !getCity().equals(hotel.getCity()) : hotel.getCity() != null) return false;
        return getRooms() != null ? getRooms().equals(hotel.getRooms()) : hotel.getRooms() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getRooms() != null ? getRooms().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  "Hotel: " + name +
                ", in " + city +
                " city, count of rooms: " +rooms.size() +
                ", id = " + id;
    }

    @Override
    public int compareTo(Hotel o) {
        if (!city.equals(o.getCity()))
            return city.compareTo(o.getCity());
        return name.compareTo(o.getName());
    }
}
