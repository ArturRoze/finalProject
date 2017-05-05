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
    private Hotel hotel;
    private int number;
    private boolean available;
    private User bookedUser;

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

    public void setBookedUser(User bookedUser) {
        this.bookedUser = bookedUser;
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

    public User getBookedUser() {
        return bookedUser;
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
        return getBookedUser() != null ? getBookedUser().equals(room.getBookedUser()) : room.getBookedUser() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getHotel() != null ? getHotel().hashCode() : 0);
        result = 31 * result + getNumber();
        result = 31 * result + (isAvailable() ? 1 : 0);
        result = 31 * result + (getBookedUser() != null ? getBookedUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel=" + hotel.getName() +
                ", number=" + number +
                ", available=" + available +
                ", bookedUser=" + bookedUser +
                '}';
    }
}
