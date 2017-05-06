package com.gojava.model;

import java.io.Serializable;

/**
 * Simple JavaBean domain object that represents a Room in a hotel.
 *
 * @author Firsov
 * @version 1.0
 */


public class Room implements Serializable, HaveId, Comparable<Room> {

    private static final long serialVersionUID = 1L;

    private long id;
    private Hotel hotel;
    private int number;
    private boolean available;
    private String bookedUserName;

    public Room(Hotel hotel, int number) {
        id = IdGenerator.getRandomId();
        this.hotel = hotel;
        this.number = number;
        available = true;

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBookedUserName(String bookedUserName) {
        this.bookedUserName = bookedUserName;
    }


    @Override
    public long getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getBookedUserName() {
        return bookedUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (getId() != room.getId()) return false;
        if (getNumber() != room.getNumber()) return false;
        if (isAvailable() != room.isAvailable()) return false;
        if (getHotel() != null ? !getHotel().equals(room.getHotel()) : room.getHotel() != null) return false;
        return getBookedUserName() != null ? getBookedUserName().equals(room.getBookedUserName()) : room.getBookedUserName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getHotel() != null ? getHotel().hashCode() : 0);
        result = 31 * result + getNumber();
        result = 31 * result + (isAvailable() ? 1 : 0);
        result = 31 * result + (getBookedUserName() != null ? getBookedUserName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room №" + number +
                ", in " + hotel.getName() + " hotel, in " + hotel.getCity() +
                " city, Id = " + id +
                (isAvailable() ? ", available" : (", booked by " + bookedUserName));
    }


    @Override
    public int compareTo(Room o) {
        if (!hotel.getCity().equals(o.getHotel().getCity())){
            return hotel.getCity().compareTo(o.getHotel().getCity());
        } else if (!hotel.getName().equals(o.getHotel().getName())){
            return hotel.getName().compareTo(o.getHotel().getName());
        }
        return Integer.compare(number, o.getNumber());
    }
}
