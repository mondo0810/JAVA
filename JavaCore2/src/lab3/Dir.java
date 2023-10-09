package lab3;

import java.io.File;

public class Dir {

    static void listPath(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        listPath(file);
                    } else {
                        System.out.println("File " + file.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("Invalid directory: " + directory.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        File rootDirectory = new File("C:\\WINDOWS");
        listPath(rootDirectory);
    }
}
