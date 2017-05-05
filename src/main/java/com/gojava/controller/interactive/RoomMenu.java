package com.gojava.controller.interactive;

import com.gojava.model.Hotel;
import com.gojava.model.Interactive;
import com.gojava.model.Room;
import com.gojava.service.HotelService;
import com.gojava.service.RoomService;
import com.gojava.service.impl.HotelServiceImpl;
import com.gojava.service.impl.RoomServiceImpl;

import static com.gojava.dao.Utils.*;

/**
 *
 */
public class RoomMenu implements Interactive {

    private Room currentRoom;
    private Interactive previousMenu;
    private HotelService<Hotel> hotelService = new HotelServiceImpl();
    private RoomService<Room> roomService = new RoomServiceImpl();

    public RoomMenu(Room currentRoom, Interactive previousMenu) {
        this.currentRoom = currentRoom;
        this.previousMenu = previousMenu;
    }

    @Override
    public void showMenu() {

        printBorder();
        System.out.println("Room " + currentRoom + " menu");
        System.out.println("1) Update room");
        System.out.println("2) Delete room");
        System.out.println("3) Book user");
        System.out.println("4) Back to hotel rooms menu");
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
                    toBookingMenu();
                    break;
                case 4:
                    previousMenu.showMenu();
                    break;
                default:
                    showMenu();
                    break;
            }
        }
    }

    private void updateRoom() {
        //TODO do it
        System.out.println("not ready yet");

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

    private void toBookingMenu() {
        Interactive bookingMenu = new RoomBookingMenu(currentRoom, this);
        bookingMenu.showMenu();
    }
}
