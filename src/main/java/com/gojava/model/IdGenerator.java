package com.gojava.model;

import java.util.Random;

/**
 * Created by root on 24.04.2017.
 */
public class IdGenerator {

    public static long getRandomId() {
        return new Random().nextInt(10000);
    }
}
