package com.gojava.controller.interactive;

import com.gojava.model.Interactive;
import com.gojava.model.Room;
import com.gojava.model.User;
import com.gojava.service.UserService;
import com.gojava.service.impl.RoomServiceImpl;
import com.gojava.service.impl.UserServiceImpl;

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

        //TODO do it
        System.out.println("not ready yet");
        previousMenu.showMenu();
    }
}
