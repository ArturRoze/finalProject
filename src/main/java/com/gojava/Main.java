package com.gojava;

import com.gojava.controller.interactive.MainMenu;

public class Main {

    public static void main(String[] args) {
        try {
            MainMenu mainMenu = new MainMenu();
            mainMenu.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
