package com.gojava.service.impl;

import com.gojava.dao.Utils;
import com.gojava.dao.impl.DataStorage;

public class FileManager {

    public static DataStorage readData(String str) {
        return Utils.readFile(str);
    }

    public static void writeData(DataStorage dataStorage, String str) {
        Utils.writeFile(str, dataStorage);
    }
}
