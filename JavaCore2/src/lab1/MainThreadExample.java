package lab1;
 class ThreeThread extends Thread {
    private String threadName;

    ThreeThread(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        try {
            // Simulate some work
            Thread.sleep(2000);
            System.out.println(threadName + " is exiting.");
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted.");
        }
    }
}

public class MainThreadExample {
    public static void main(String[] args) {
        ThreeThread thread1 = new ThreeThread("Thread 1");
        ThreeThread thread2 = new ThreeThread("Thread 2");
        ThreeThread thread3 = new ThreeThread("Thread 3");

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            // Wait for threads to finish
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
        }

        System.out.println("Main thread is exiting.");
    }
}
