package com.gojava.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Room in a hotel.
 *
 * @author Firsov
 * @author Vancho
 * @author Artur Roze
 * @version 1.0
 */
public class User implements Serializable, HaveId, Comparable<User> {

    private static final long serialVersionUID = 1L;

    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private Set<Long> bookedRoomIds;

    public User(String login, String firstName, String lastName) {
        id = IdGenerator.getRandomId();
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        bookedRoomIds = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Long> getBookedRoomIds() {
        return bookedRoomIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        return bookedRoomIds != null ? bookedRoomIds.equals(user.bookedRoomIds) : user.bookedRoomIds == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (bookedRoomIds != null ? bookedRoomIds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User: first name: " + firstName +
                ", last name: " + lastName +
                ", login: " + login +
                ", booked rooms count: " + bookedRoomIds.size() +
                ", id = " + id;
    }

    @Override
    public int compareTo(User o) {
        return login.compareTo(o.getLogin());
    }
}
