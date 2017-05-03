package com.gojava.model;

import java.io.Serializable;

/**
 * Simple JavaBean domain object that represents a Room in a hotel.
 *
 * @author Firsov
 * @version 1.0
 */


public class Room implements Serializable, HaveId {

    private static final long serialVersionUID = 1L;

    private long id;
    private String hotelName;
    private boolean available;

    public Room(String hotelName) {
        id = IdGenerator.getRandomId();
        this.hotelName = hotelName;
        available = true;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (getId() != room.getId()) return false;
        if (isAvailable() != room.isAvailable()) return false;
        return getHotelName() != null ? getHotelName().equals(room.getHotelName()) : room.getHotelName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getHotelName() != null ? getHotelName().hashCode() : 0);
        result = 31 * result + (isAvailable() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", available=" + available +
                '}';
    }
}
