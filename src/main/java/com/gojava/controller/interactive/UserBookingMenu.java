package com.gojava.controller.interactive;

import com.gojava.model.Hotel;
import com.gojava.model.Room;
import com.gojava.service.HotelService;
import com.gojava.service.UserService;
import com.gojava.model.Interactive;
import com.gojava.model.User;
import com.gojava.service.impl.HotelServiceImpl;
import com.gojava.service.impl.RoomServiceImpl;
import com.gojava.service.impl.UserServiceImpl;

import java.util.NoSuchElementException;
import java.util.Set;

import static com.gojava.dao.Utils.*;
import static com.gojava.dao.Utils.isValidString;

/**
 *
 */
public class UserBookingMenu implements Interactive {

    private User currentUser;
    private Interactive previousMenu;
    private HotelService<Hotel> hotelService = new HotelServiceImpl();
    private UserService<User> userService = new UserServiceImpl();
    private RoomServiceImpl roomService = new RoomServiceImpl();


    UserBookingMenu(User currentUser, Interactive previousMenu) {
        this.currentUser = currentUser;
        this.previousMenu = previousMenu;
    }

    @Override
    public void showMenu() {
        printBorder();
        System.out.println("Booking  menu");
        System.out.println("1) Show all booked rooms ids on " + currentUser.getLogin());
        System.out.println("2) Show all booked rooms on " + currentUser.getLogin());
        System.out.println("3) Book room on users name");
        System.out.println("4) Find and book room on users name");
        System.out.println("5) Un book room");
        System.out.println("6) Back to users menu");
        printBorder();

        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null) {
            System.err.println("not correct entered data, try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    showAllBookedRoomIds();
                    break;
                case 2:
                    showAllBookedRooms();
                    break;
                case 3:
                    bookRoomByIdOnUsersName();
                    break;
                case 4:
                    findAndBookRoomOnUsersName();
                    break;
                case 5:
                    unBookRoom();
                    break;
                case 6:
                    previousMenu.showMenu();
                    break;
                default:
                    showMenu();
                    break;
            }
        }
    }


    private void showAllBookedRoomIds() {
        printBorder();
        System.out.print(currentUser.getLogin() + " room ids: ");
        System.out.println("Count of booked rooms: " + currentUser.getBookedRoomIds().size());
        if (currentUser.getBookedRoomIds().isEmpty()) {
            showMenu();
        } else {
            System.out.print("Booked room ids: ");
            currentUser.getBookedRoomIds().forEach(roomId -> System.out.print(roomId + ", "));
            System.out.println();
        }
        showMenu();
    }

    private void showAllBookedRooms() {
        printBorder();
        System.out.println("Rooms booked on " + currentUser.getLogin() + ":");
        if (currentUser.getBookedRoomIds().isEmpty()) {
            System.out.println(currentUser.getLogin() + " doesn't have any booked rooms.");
            showMenu();
        } else
            currentUser.getBookedRoomIds().forEach(roomId -> System.out.println(roomService.findById(roomId)));
        showMenu();
    }

    private void bookRoomByIdOnUsersName() {

        printBorder();
        Integer roomId = provideIntInputStreamWithMessage("Enter room id  or press 'Enter' to return to menu: ");

        if (roomId == null)
            showMenu();

        Room roomToBook = roomService.findById(roomId);

        if (roomToBook == null) {
            System.out.println("Room with id = " + roomId + " doesn't exist. Please choose another room");
            bookRoomByIdOnUsersName();
        } else if (!roomToBook.isAvailable()) {
            System.out.println("Room with id = " + roomId + " is already booked. Please choose another room");
            bookRoomByIdOnUsersName();
        }

        userService.bookRoomOnUser(roomToBook, currentUser);
        roomService.bookUser(roomToBook, currentUser);

        System.out.println(roomToBook + " booked by " + currentUser);
        showMenu();
    }

    private void findAndBookRoomOnUsersName() {

        printBorder();
        if (roomService.getAll().isEmpty()) {
            System.out.println("Room database is empty.");
            showMenu();
        }

        String hotelCity = provideStringInputStream("Enter city name where ou want book room or press 'Enter' to return to menu: ");

        if (!isValidString(hotelCity))
            showMenu();

        if (!hotelService.isCityContainsHotels(hotelCity)) {
            System.out.println("There is no hotels in '" + hotelCity + "' city, choose another city.");
            findAndBookRoomOnUsersName();
        }

        String hotelName = provideStringInputStream("Enter hotel name or press 'Enter' to return to menu: ");

        if (!isValidString(hotelName))
            showMenu();


        if (!hotelService.isHotelExistsInCity(hotelName, hotelCity)) {
            System.out.println("There is no hotels named " + hotelName + "in " + hotelCity + " choose another hotel.");
            findAndBookRoomOnUsersName();
        }

        Hotel hotel = hotelService.findHotelByNameInCity(hotelName, hotelCity);

        Integer roomNumber = provideIntInputStreamWithMessage("Enter room number you want to book or press 'Enter' to return to menu: ");

        if (roomNumber == null)
            showMenu();

        Room roomToBook = hotelService.findRoomByNumberInHotel(roomNumber, hotel);

        if (roomToBook == null) {
            System.out.println("Room №" + roomNumber + " doesn't exist in " + hotel + ". Please choose another room");
            findAndBookRoomOnUsersName();
        } else if (!roomToBook.isAvailable()) {
            System.out.println("Room with id = " + roomNumber + " is already booked. Please choose another room");
            findAndBookRoomOnUsersName();
        }

        userService.bookRoomOnUser(roomToBook, currentUser);
        roomService.bookUser(roomToBook, currentUser);

        System.out.println(roomToBook + " booked by " + currentUser);
        showMenu();
    }

    private void unBookRoom() {

        printBorder();
        Integer roomId = provideIntInputStreamWithMessage("Enter room id you want to un book or press 'Enter' to return to menu: ");

        if (roomId == null)
            showMenu();

        Room roomToUnBook = roomService.findById(roomId);
        userService.unBookRoomFromUser(roomToUnBook, currentUser);
        roomService.unBookUserFromRoom(roomToUnBook);
        System.out.println("Room with id = " + roomId + " has been un booked.");
        showMenu();
    }
}
