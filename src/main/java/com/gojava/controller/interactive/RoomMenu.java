package com.gojava.controller.interactive;

import com.gojava.model.Hotel;
import com.gojava.model.Interactive;
import com.gojava.model.Room;
import com.gojava.model.User;
import com.gojava.service.HotelService;
import com.gojava.service.RoomService;
import com.gojava.service.UserService;
import com.gojava.service.impl.HotelServiceImpl;
import com.gojava.service.impl.RoomServiceImpl;
import com.gojava.service.impl.UserServiceImpl;

import static com.gojava.dao.Utils.*;

/**
 *
 */
public class RoomMenu implements Interactive {

    private Room currentRoom;
    private Interactive previousMenu;
    private HotelService<Hotel> hotelService = new HotelServiceImpl();
    private RoomService<Room> roomService = new RoomServiceImpl();
    private UserService<User> userService = new UserServiceImpl();


    RoomMenu(Room currentRoom, Interactive previousMenu) {
        this.currentRoom = currentRoom;
        this.previousMenu = previousMenu;
    }

    @Override
    public void showMenu() {

        printBorder();
        System.out.println(currentRoom);
        System.out.println("1) Update room");
        System.out.println("2) Delete room");
        System.out.println("3) Book room on user's name");
        System.out.println("4) Un book room");
        System.out.println("5) Back to hotel rooms menu");
        printBorder();

        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null) {
            System.err.println("not correct entered data, try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    updateRoom();
                    break;
                case 2:
                    deleteRoom();
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

    private void updateRoom() {
        Integer roomNumber = provideIntInputStreamWithMessage("Enter new room number or press 'Enter' to return to menu: ");

        if (roomNumber == null)
            showMenu();

        if (roomNumber == currentRoom.getNumber()) {
            System.out.println("Room is already №" + roomNumber);
            showMenu();
        } else if (hotelService.isRoomNumberExistsInHotel(roomNumber, currentRoom.getHotel())) {
            System.out.println("Room №" + roomNumber + " already exists in " + currentRoom.getHotel());
            updateRoom();
        } else {
            System.out.println("Room number was changed from " + currentRoom.getNumber() + " to " + roomNumber);
            currentRoom.setNumber(roomNumber);
            showMenu();
        }
        showMenu();
    }

    private void deleteRoom() {

        String choice = provideStringInputStream("Enter 'y' to delete room " + currentRoom + " or press 'Enter' to return to menu: ");

        if (!isValidString(choice) && !choice.toLowerCase().equals("y"))
            showMenu();
        else {
            System.out.println("Room " + roomService.delete(currentRoom) + " has been deleted.");
            previousMenu.showMenu();
        }
    }

    private void bookRoomOnUsersName() {

        if (!currentRoom.isAvailable()) {
            System.out.println("Room is already booked.");
            showMenu();
        }

        String userLogin = provideStringInputStream("Enter login of the user you want to book or press 'Enter' to return to menu: ");

        if (!isValidString(userLogin))
            showMenu();

        if (!userService.isLoginExists(userLogin)) {
            System.out.println("User with login " + userLogin + " doesn't exist. Please choose another login.");
            bookRoomOnUsersName();
        }

        User currentUser = userService.findUserByLogin(userLogin);

        if (userLogin != null) {
            userService.bookRoomOnUser(currentRoom, currentUser);
            roomService.bookUser(currentRoom, currentUser);
            System.out.println(currentRoom + " has been booked by " + currentUser.getLogin());
            showMenu();
        }
    }

    private void unBookRoom() {

        String choice = provideStringInputStream("Enter 'y' to un book room " + currentRoom + " or press 'Enter' to return to menu: ");

        if (!isValidString(choice) && !choice.toLowerCase().equals("y"))
            showMenu();
        else {
            userService.unBookRoomFromUser(currentRoom, userService.findUserByLogin(currentRoom.getBookedUserName()));
            roomService.unBookUserFromRoom(currentRoom);
            showMenu();
        }
    }
}
