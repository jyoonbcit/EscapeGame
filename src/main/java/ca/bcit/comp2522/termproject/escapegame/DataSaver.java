package ca.bcit.comp2522.termproject.escapegame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataSaver {

    public static void save(Serializable data) {
        try {
            FileOutputStream fos = new FileOutputStream("keys.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            System.out.println("Could not save key data.");
        }
    }

    public static Object load() {
        try {
            FileInputStream fis = new FileInputStream("keys.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException e) {
            System.out.println("Could not load key data.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
