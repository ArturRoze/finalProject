package com.gojava.controller.interactive;

import com.gojava.dao.impl.DataStorage;
import com.gojava.model.Interactive;

import static com.gojava.dao.Utils.printBorder;
import static com.gojava.dao.Utils.provideIntInputStream;
import static com.gojava.service.impl.FileManager.readData;
import static com.gojava.service.impl.FileManager.writeData;

/**
 * @author Vancho
 * @author Artur Roze
 * This class includes the main menu.
 */
public class MainMenu implements Interactive {

    @Override
    public void showMenu() {

        printBorder();
        System.out.println("Main Menu");
        System.out.println("1) Hotels menu");
        System.out.println("2) Users Menu");
        System.out.println("3) Exit");
        printBorder();

        Integer selectedItem = provideIntInputStream();

        if (selectedItem == null) {
            System.err.println("not correct entered data, try again");
            showMenu();
        } else {
            switch (selectedItem) {
                case 1:
                    new HotelsMenu(this).showMenu();
                    break;
                case 2:
                    new UsersMenu(this).showMenu();
                    break;
                case 3:
                    writeData(DataStorage.getInstance(), "file.txt");
                    System.exit(0);
                    break;
                default:
                    showMenu();
                    break;
            }
        }
    }
    /**
     * This method read data from DB and show main menu.
     */
    public void startGame() {
        initializeDataStorage();
        showMenu();
    }

    private void initializeDataStorage() {
        DataStorage storage = readData("file.txt");
        if (storage == null) {
            DataStorage.getInstance();
        } else {
            DataStorage.getInstance().setHotels(storage.getHotels());
            DataStorage.getInstance().setUsers(storage.getUsers());
        }
    }
}
