package com.gojava.controller.interactive;

import com.gojava.dao.impl.DataStorage;
import com.gojava.model.Interactive;
import com.gojava.model.User;
import com.gojava.service.UserService;
import com.gojava.service.impl.RoomServiceImpl;
import com.gojava.service.impl.UserServiceImpl;

import java.util.TreeSet;

import static com.gojava.dao.Utils.*;
import static com.gojava.service.impl.FileManager.writeData;

/**
 * @author Vancho
 * @author Artur Roze
 */

public class UsersMenu implements Interactive {

    private Interactive previousMenu;
    private UserService<User> userService = new UserServiceImpl();
    private RoomServiceImpl roomService = new RoomServiceImpl();

    UsersMenu(Interactive interactive) {
        this.previousMenu = interactive;
    }

    @Override
    public void showMenu() {
        printBorder();
        System.out.println("Users Menu");
        System.out.println("1) Show all users");
        System.out.println("2) Add user");
        System.out.println("3) Update user");
        System.out.println("4) Delete user");
        System.out.println("5) Book room");
        System.out.println("6) Back to main menu");
        printBorder();

        Integer selectedItem = provideIntInputStream();
        printBorder();

        if (selectedItem == null) {
            System.out.println("Incorrect input. Please try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    showAllUsers();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    toBookingMenu();
                    break;
                case 6:
                    previousMenu.showMenu();
                    break;
                default:
                    System.out.println("Incorrect input. Please try again");
                    showMenu();
            }
        }
    }

    private void showAllUsers() {

        System.out.println("Count of users: " + userService.getAll().size());
        if (userService.getAll().isEmpty()) {
            showMenu();
        } else
            new TreeSet<>(userService.getAll().values()).forEach(System.out::println);
        showMenu();
    }

    private void createUser() {

        String login = provideStringInputStream("Enter user's login: ");

        if (login.length() < 3) {
            System.out.println("User's login should have at least 3 symbols. Please try again");
            createUser();
        }

        if (userService.isLoginExists(login)) {
            System.out.println("User with this login is already exists. Please choose another login.");
            createUser();
        }

        String name = provideStringInputStream("Enter user's name: ");
        String lastName = provideStringInputStream("Enter user's lastName: ");

        User user = new User(login, name, lastName);
        userService.create(user);
        writeData(DataStorage.getInstance(), "file.txt");
        System.out.println("User " + user + " successfully created");
        showMenu();
    }
    /**
     * This method changes name or last Name of user.
     */
    private void updateUser() {

        String userLogin = provideStringInputStream("Enter user's login: ");

        if (!isValidString(userLogin))
            showMenu();

        if (!userService.isLoginExists(userLogin)) {
            System.out.println("User with login " + userLogin + " doesn't exist. Please choose another login.");
            toBookingMenu();
        }

        User userToUpdate = userService.findUserByLogin(userLogin);

        if (userToUpdate == null) {
            System.out.println("User with this id has't been found");
            showMenu();
        } else {
            String newFirstName = provideStringInputStream("Enter new first name: ");
            String newLastName = provideStringInputStream("Enter new last name: ");
            userToUpdate.setFirstName(newFirstName);
            userToUpdate.setLastName(newLastName);
            writeData(DataStorage.getInstance(), "file.txt");
            System.out.println("This user with login = " + userLogin + " changed");
        }
        showMenu();
    }

    private void deleteUser() {

        String userLogin = provideStringInputStream("Enter user's login: ");

        if (!isValidString(userLogin))
            showMenu();

        if (!userService.isLoginExists(userLogin)) {
            System.out.println("User with login = " + userLogin + " doesn't exist. Please choose another login.");
            toBookingMenu();
        }

        User userToDelete = userService.findUserByLogin(userLogin);

        if (userToDelete == null) {
            System.out.println("User with this id has't been found");
            showMenu();
        } else {

            if (!userToDelete.getBookedRoomIds().isEmpty())
            userToDelete.getBookedRoomIds().forEach(id -> roomService.unBookUserFromRoom(roomService.findById(id)));

            userService.delete(userToDelete);
            writeData(DataStorage.getInstance(), "file.txt");
            System.out.println("User " + userLogin + " deleted.");
        }
        showMenu();
    }

    private void toBookingMenu() {

        String userLogin = provideStringInputStream("Enter user's login: ");

        if (!isValidString(userLogin))
            showMenu();

        if (!userService.isLoginExists(userLogin)) {
            System.out.println("User with login " + userLogin + " doesn't exist. Please choose another login.");
            toBookingMenu();
        }

        User userToBook = userService.findUserByLogin(userLogin);

        if (userToBook != null) {
            new UserBookingMenu(userToBook, this).showMenu();
        } else
            showMenu();
    }
}
