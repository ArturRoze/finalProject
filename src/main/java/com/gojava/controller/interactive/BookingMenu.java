package com.gojava.controller.interactive;

import com.gojava.service.UserService;
import com.gojava.model.Interactive;
import com.gojava.model.User;
import com.gojava.service.impl.RoomServiceImpl;
import com.gojava.service.impl.UserServiceImpl;

import static com.gojava.dao.Utils.printBorder;
import static com.gojava.dao.Utils.provideIntInputStream;

/**
 *
 */
public class BookingMenu implements Interactive {

    private User currentUser;
    private String usersFullName = currentUser.getFirstName() + " " + currentUser.getLastName();
    private Interactive previousMenu;
    private UserService<User> userService = new UserServiceImpl();
    private RoomServiceImpl roomService = new RoomServiceImpl();


    public BookingMenu(User currentUser, Interactive previousMenu) {
        this.currentUser = currentUser;
        this.previousMenu = previousMenu;
    }

    @Override
    public void showMenu() {
        printBorder();
        System.out.println("Booking  menu");
        System.out.println("1) Booking of room on users name");
        System.out.println("2) Un booking room");
        System.out.println("3) Show all booked rooms on  " + usersFullName);
        System.out.println("4) Back to users menu");
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
                    showAllBookedRooms();
                case 4:
                    previousMenu.showMenu();
                    break;
                default:
                    showMenu();
                    break;
            }
        }
    }

    private void showAllBookedRooms() {
        System.out.print(usersFullName + " room ids: ");
        currentUser.getRooms().forEach(System.out::print);
        System.out.println();
        //todo
    }

    private void bookRoomOnUsersName() {
        //TODO do it
    }

    private void unBookRoom() {
        //TODO do it
    }


}
