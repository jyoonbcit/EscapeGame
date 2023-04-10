package ca.bcit.comp2522.termproject.escapegame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * DataSaver.
 *
 * @author Jihoon Yoon, Wilbur Lin
 * @version 2023-04-09
 */
public class DataSaver {
    /**
     * Saves data of serializable object.
     *
     * @param data data of serializable object
     */
    public static void saveKeys(final Serializable data) {
        try {
            FileOutputStream fos = new FileOutputStream("keys.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            System.out.println("Could not save key data.");
        }
    }

    /**
     * Loads data of serialized object.
     *
     * @return serialized object
     * @throws IOException if text file is not found
     * @throws ClassNotFoundException if class is not found
     */
    public static Object loadKeys() {
        try {
            FileInputStream fis = new FileInputStream("keys.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load key data.");
        }
        return null;
    }
}
