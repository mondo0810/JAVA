package lab3;

import java.io.*;

public class ReadWriteFile {
    public void readData() {
        try {
            File f = new File("test.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int i;
            while ((i = bis.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("");
            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeData(String str) {

//chuyen chuoi thanh byte
        byte[] b = str.getBytes();
        try {
            FileOutputStream fos = new FileOutputStream("test.txt");
            fos.write(b);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void copyFile(String sourceFilePath, String destinationFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("File copied successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadWriteFile d = new ReadWriteFile();
        d.readData();
        d.writeData("hom nay hoc java.io");

        String sourceFilePath = "test.txt";
        String destinationFilePath = "copy.txt";
        copyFile(sourceFilePath, destinationFilePath);
    }
}