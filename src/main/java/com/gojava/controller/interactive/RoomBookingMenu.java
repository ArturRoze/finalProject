package com.gojava.controller.interactive;

import com.gojava.model.Interactive;
import com.gojava.model.Room;
import com.gojava.model.User;
import com.gojava.service.UserService;
import com.gojava.service.impl.RoomServiceImpl;
import com.gojava.service.impl.UserServiceImpl;

import static com.gojava.dao.Utils.*;

/**
 *
 */
public class RoomBookingMenu implements Interactive {

    private Room currentRoom;
    private Interactive previousMenu;
    private UserService<User> userService = new UserServiceImpl();
    private RoomServiceImpl roomService = new RoomServiceImpl();


    public RoomBookingMenu(Room currentRoom, Interactive previousMenu) {
        this.currentRoom = currentRoom;
        this.previousMenu = previousMenu;
    }

    @Override
    public void showMenu() {
        printBorder();
        System.out.println("Booking " + currentRoom + " menu");
        System.out.println("1) Booking of room on users name");
        System.out.println("2) Un booking room");
        System.out.println("3) Back to room menu");
        printBorder();

        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null) {
            System.err.println("not correct entered data, try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    bookRoomOnUsersName();
                    break;
                case 2:
                    unBookRoom();
                    break;
                case 3:
                    previousMenu.showMenu();
                    break;
                default:
                    showMenu();
                    break;
            }
        }
    }

    private void bookRoomOnUsersName() {

        String userLogin = provideStringInputStream("Enter login of the user you want to book or press 'Enter' to return to menu: ");

        if (!isValidString(userLogin))
            showMenu();

        if (!userService.isLoginExists(userLogin)) {
            System.out.println("User with login " + userLogin + " doesn't exist. Please choose another login.");
            bookRoomOnUsersName();
        }

        User currentUser = userService.findUserByLogin(userLogin);

        if (userLogin != null) {
            userService.bookRoom(currentRoom, currentUser);
            roomService.bookUser(currentRoom, currentUser);
            showMenu();
        }
    }

    private void unBookRoom() {

        String choice = provideStringInputStream("Enter 'y' to un book room " + currentRoom + " or press 'Enter' to return to menu: ");

        if (!isValidString(choice) && !choice.toLowerCase().equals("y"))
            showMenu();
        else {
            userService.unBookRoom(currentRoom, userService.findUserByLogin(currentRoom.getBookedUserName()));
            roomService.unBookUser(currentRoom);
            showMenu();
        }
    }
}

