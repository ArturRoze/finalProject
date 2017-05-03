package com.gojava.controller.interactive;

import com.gojava.service.UserService;
import com.gojava.model.Interactive;
import com.gojava.model.User;
import com.gojava.service.impl.UserServiceImpl;

import static com.gojava.dao.Utils.*;

public class UsersMenu implements Interactive {

    private Interactive previousMenu;
    private UserService<User> userService = new UserServiceImpl();

    public UsersMenu(Interactive interactive) {
        this.previousMenu = interactive;
    }

    private BookingMenu bookingMenu;


    @Override
    public void showMenu() {
        printBorder();
        System.out.println("Users Menu");
        System.out.println("1) Add user");
        System.out.println("2) Update user");
        System.out.println("3) Delete user");
        System.out.println("4) Show all users");
        System.out.println("5) Booking menu");
        System.out.println("6) Back to main menu");
        printBorder();

        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null) {
            System.out.println("Incorrect input. Please try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    createUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    showAllUsers();
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
        System.out.println("User " + user + " successfully created");
        showMenu();
    }

    private void showAllUsers() {
        System.out.println("Count of users: " + userService.getAll().size());
        if (userService.getAll().isEmpty()) {
            showMenu();
        } else
            userService.getAll().values().forEach(System.out::println);
        showMenu();
    }

    private void updateUser() {

        Long usersId = enterUsersId();

        User userToUpdate = userService.findById(usersId);

        if (userToUpdate == null) {
            System.out.println("User with this id has't been found");
            showMenu();
        } else {
            String newFirstName = provideStringInputStream("Enter new first name: ");
            String newLastName = provideStringInputStream("Enter new last name: ");
            userToUpdate.setFirstName(newFirstName);
            userToUpdate.setLastName(newLastName);
            System.out.println("This user with " + usersId + " changed");
        }
        showMenu();
    }

    private void deleteUser() {

        Long idUser = enterUsersId();
        User userToDelete = userService.findById(idUser);

        if (userToDelete == null) {
            System.out.println("User with this id has't been found");
            showMenu();
        } else {
            userService.delete(userToDelete);
        }
        showMenu();

    }

    private void toBookingMenu() {
        long usersId = enterUsersId();
        User userToBook = userService.findById(usersId);

        if (userToBook != null)
            bookingMenu = new BookingMenu(userToBook, this);
        else
            bookingMenu.showMenu();
    }

    private long enterUsersId() {
        Integer idUser = null;

        idUser = provideIntInputStreamWithString("Enter id of user: ");
        if (idUser == null) {
            System.out.println("Incorrect input. Please try again");
            showMenu();
        } else {
            try {
                // todo pars
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return idUser;
    }
}
