package com.gojava.service.impl;

import com.gojava.dao.Utils;
import com.gojava.dao.impl.DataStorage;

/**
 * @author Vancho
 */
public class FileManager {
    /**
     * This method read data from DB.
     */
    public static DataStorage readData(String str) {
        return Utils.readFile(str);
    }
    /**
     * This method write data to DB.
     */
    public static void writeData(DataStorage dataStorage, String str) {
        Utils.writeFile(str, dataStorage);
    }
}
