package com.gojava.controller.interactive;

import com.gojava.dao.impl.DataStorage;
import com.gojava.model.Hotel;
import com.gojava.model.Interactive;
import com.gojava.model.Room;
import com.gojava.service.HotelService;
import com.gojava.service.impl.HotelServiceImpl;

import java.util.TreeSet;

import static com.gojava.dao.Utils.*;
import static com.gojava.service.impl.FileManager.writeData;

/**
 *
 */
public class HotelRoomsMenu implements Interactive {

    private Hotel currentHotel;
    private Interactive previousMenu;
    private Interactive roomMenu;
    private HotelService<Hotel> hotelService = new HotelServiceImpl();

    HotelRoomsMenu(Hotel currentHotel, Interactive previousMenu) {
        this.currentHotel = currentHotel;
        this.previousMenu = previousMenu;
    }

    @Override
    public void showMenu() {
        printBorder();
        System.out.println(currentHotel);
        System.out.println("1) Show all rooms");
        System.out.println("2) Add room to hotel");
        System.out.println("3) Manage room");
        System.out.println("4) Back to hotels menu");
        printBorder();

        Integer selectedItem = provideIntInputStream();
        printBorder();

        if (selectedItem == null) {
            System.err.println("not correct entered data, try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    showAllRooms();
                    break;
                case 2:
                    addRoom();
                    break;
                case 3:
                    manageRoom();
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

    private void showAllRooms() {

        System.out.println("Count of rooms: " + currentHotel.getRooms().values().size());
        if (currentHotel.getRooms().values().isEmpty()) {
            showMenu();
        } else
            new TreeSet<>(currentHotel.getRooms().values()).forEach(System.out::println);
        showMenu();
    }

    private void addRoom() {

        Integer roomNumber = provideIntInputStreamWithMessage("Enter number of room you want to create or press 'Enter' to return to menu: ");

        if (roomNumber == null)
            showMenu();
        else if (hotelService.isRoomNumberExistsInHotel(roomNumber, currentHotel)) {
            System.out.println("Room with number = " + roomNumber + " already exists in this hotel. Choose another number");
            addRoom();
        } else {
            Room room = new Room(currentHotel, roomNumber);
            hotelService.addRoomToHotel(room, currentHotel);
            writeData(DataStorage.getInstance(), "file.txt");
            addRoom();
        }
    }

    private void manageRoom() {

        Integer roomNumber = provideIntInputStreamWithMessage("Enter Room number or press 'Enter' to return to menu: ");

        if (roomNumber == null)
            showMenu();

        if (!hotelService.isRoomNumberExistsInHotel(roomNumber, currentHotel)) {
            System.out.println("Room with number = " + roomNumber + " doesn't exist in this hotel. Choose another number");
            manageRoom();
        } else {
            Room room = hotelService.findRoomByNumberInHotel(roomNumber, currentHotel);
            new RoomMenu(room, this).showMenu();
        }
    }
}
