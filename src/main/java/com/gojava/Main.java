package com.gojava;

import com.gojava.controller.interactive.MainMenu;

/**
 * @author Vancho
 * @author Artur Roze
 */
public class Main {

    /**
     * Before show menu, read all data from DB.
     */
    public static void main(String[] args) {

        new MainMenu().startGame();
    }
}
