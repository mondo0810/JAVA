package lab4;

import java.io.*;
import java.util.Date;


public class ReadWriteObject {
    public static void main(String[] args) {
        Date currentDate = new Date();
        //write object
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("object.dat"))); // write a String object
            oos.writeObject("The current Date and Time is ");
            oos.writeObject(currentDate);
            System.out.println("Object writed!");
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //read object
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            String message = (String) ois.readObject();  // Read the String message
            Date d = (Date) ois.readObject();  // Read the Date object

            System.out.println(message + d);  // Print the concatenated message and date
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}