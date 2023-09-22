package lab6.b1;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.doUnchecked("hello");
        client.doChecked();
    }
}
