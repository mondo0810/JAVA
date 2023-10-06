package lab1;

public class MyThread extends Thread {

    @Override
    public void run() {
        // Display the name of the current thread
        System.out.println("Current thread name: " + Thread.currentThread().getName());

        // Rename the thread to "myJavaThread"
        Thread.currentThread().setName("myJavaThread");
        System.out.println("Thread renamed to: " + Thread.currentThread().getName());

        // Display the first 10 even numbers with a delay of 1500 ms between each display
        for (int i = 2; i <= 20; i += 2) {
            System.out.println("Even number: " + i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
