package lab6;

import java.io.IOException;

public class TestException {
    void aMethod() throws IOException {
        throw new IOException("Device error");
    }
    //
    public static void main(String args[]) {
        try {
            TestException obj = new TestException();
            obj.aMethod();
            System.out.println(23);

        } catch (Exception e) {
            System.out.println("Exception handled!");
        }

        System.out.println("Hello Codelearn!");
    }
}
