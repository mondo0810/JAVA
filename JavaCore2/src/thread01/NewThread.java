package thread01;

public class NewThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
        System.out.println("Done");
    }
}
