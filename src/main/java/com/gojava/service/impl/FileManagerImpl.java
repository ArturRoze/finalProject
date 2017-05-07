package com.gojava.service.impl;

import com.gojava.dao.Utils;
import com.gojava.dao.impl.DataStorage;
import com.gojava.service.FileManager;

public class FileManagerImpl implements FileManager {

    @Override
    public DataStorage readData(String str) {
        return Utils.readFile(str);
    }

    @Override
    public void writeData(DataStorage dataStorage, String str) {
        Utils.writeFile(str, dataStorage);
    }
}
