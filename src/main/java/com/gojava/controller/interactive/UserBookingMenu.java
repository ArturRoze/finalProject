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


    public UserBookingMenu(User currentUser, Interactive previousMenu) {
        this.currentUser = currentUser;
        this.previousMenu = previousMenu;
    }

    @Override
    public void showMenu() {
        printBorder();
        System.out.println("Booking  menu");
        System.out.println("1) Show all booked rooms ids on  " + currentUser.getLogin());
        System.out.println("1) Show all booked rooms on  " + currentUser.getLogin());
        System.out.println("2) Book room on users name");
        System.out.println("3) Un book room");
        System.out.println("4) Back to users menu");
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
                    bookRoomOnUsersName();
                    break;
                case 4:
                    unBookRoom();
                    break;
                case 5:
                    previousMenu.showMenu();
                    break;
                default:
                    showMenu();
                    break;
            }
        }
    }


    private void showAllBookedRoomIds() {
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
        System.out.println("Rooms booked on " + currentUser.getLogin() + ":");
        if (currentUser.getBookedRoomIds().isEmpty()) {
            System.out.println(currentUser.getLogin() + " doesn't have any booked rooms.");
            showMenu();
        } else
            currentUser.getBookedRoomIds().forEach(roomId -> System.out.println(roomService.findById(roomId)));
        showMenu();
    }

    private void bookRoomByIdOnUsersName() {

    }

    private void bookRoomOnUsersName() {
        //TODO do it

        System.out.println("not ready yet");
        showMenu();

        String hotelCity = provideStringInputStream("Enter user's login or press 'Enter' to return to menu: ");

        if (!isValidString(hotelCity))
            showMenu();

        String hotelName = provideStringInputStream("Enter user's login or press 'Enter' to return to menu: ");

        if (!isValidString(hotelName))
            showMenu();

        Integer roomNumber = provideIntInputStreamWithMessage("Enter room number you want to book or press 'Enter' to return to menu: ");

        if (roomNumber == null)
            showMenu();
    }

    private void unBookRoom() {
        Integer roomId = provideIntInputStreamWithMessage("Enter room id you want to un book or press 'Enter' to return to menu: ");

        if (roomId == null)
            showMenu();

        Room roomToUnBook = roomService.findById(roomId);
        userService.unBookRoomFromUser(roomToUnBook, currentUser);
        roomService.unBookUserFromRoom(roomToUnBook);
        showMenu();
    }
}
