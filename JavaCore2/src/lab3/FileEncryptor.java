package lab3;

import java.io.*;

public class FileEncryptor {

    public static void encryptFile(String inputFile, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isLetter(c)) {
                    // Shift the character forward by 3 positions in the ASCII table
                    c = (char) (c + 3);

                    // If the character exceeds 'z' or 'Z', wrap around to 'a' or 'A'
                    if ((Character.isLowerCase(c) && c > 'z') || (Character.isUpperCase(c) && c > 'Z')) {
                        c -= 26;
                    }
                }

                writer.write(c);
            }

            System.out.println("File encrypted successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFile = "input.txt";   // Path to the input file
        String outputFile = "output.txt"; // Path to the output file

        // Encrypt the file
        encryptFile(inputFile, outputFile);
    }
}
