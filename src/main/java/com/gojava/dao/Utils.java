package com.gojava.dao;

import com.gojava.dao.impl.DataStorage;

import java.io.*;

public final class Utils {

    private Utils() {
    }

    public static void printBorder() {
        System.out.println("-----------------------------------------------");
    }

    public static Integer provideIntInputStream() {
        return provideIntInputStreamWithMessage("Select choice (confirm Enter): ");
    }

    //TODO close streams

    public static Integer provideIntInputStreamWithMessage(String message) {
        System.out.print(message);
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            line = br.readLine();
            try {
                return Integer.valueOf(line);
            } catch (NumberFormatException e) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String provideStringInputStream(String enterData) {
        System.out.print(enterData);
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File checkFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static <T> void writeFile(String fileName, T object) {
        //TODO use try with resources
        try {
            File file = checkFile(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends DataStorage> T readFile(String fileName) {
        //TODO use try with resources
        try {
            T result = null;
            File file = new File(fileName);
            if (file.exists() || file.length()>1) {

                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream oin = new ObjectInputStream(fis);
                result = (T) oin.readObject();
                oin.close();
                return result;
            } else {
                return null;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        return null;
    }
    public static boolean isValidString(String str) {
        return !(str == null || str.isEmpty());
    }
}
