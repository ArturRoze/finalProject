package com.gojava.service;

import com.gojava.dao.impl.DataStorage;

public interface FileManager {

    DataStorage readData(String str);

    void writeData(DataStorage dataStorage, String str);
}
