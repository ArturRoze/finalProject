package com.gojava.model;

import java.util.Random;

/**
 * @author Vancho
 */
public class IdGenerator {
    /**
     * This method generate unique id from 0 to 10_000.
     */
    public static long getRandomId() {
        return new Random().nextInt(10000);
    }
}
