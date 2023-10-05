package thread01;

public class TestThreadApp {
    public static void main(String[] args) throws InterruptedException {
        // Tạo đối tượng Thread(class)
        MultilThread mThread = new MultilThread();
        mThread.start(); // Bắt đầu chạy luồng

        //Tạo đối tượng Thread(Runable interface)
        MultiThreadRunable mAble = new MultiThreadRunable();
        Thread thread = new Thread(mAble);
        thread.start();

        // Tạo Thread "New Thread()";
        NewThread newThread = new NewThread();
        System.out.println("Name: " + newThread.getName());
        System.out.println("Id: " + newThread.getId());
        newThread.start();
        newThread.join(2000);
//         Khi newThread hoàn thành nhiệm vụ trong 2000 mili giây thì các luồng khác mới bắt đầu thực hiện
// join(): Chờ 1 luồng kết thúc(chết). Hay: Nó làm cho các thread đang chạy ngừng hoạt động cho đến khi luồng mà nó
//        tham gia hoàn thành 1 nhiệm vụ nào đó. Bắt buộc sử dụng throws InterruptedException
        NewThread newThread1 = new NewThread();
        NewThread newThread2 = new NewThread();
        newThread1.start();
        newThread2.start();
// public void run(): Thực hiện hành động luồng
//        start(): Bắt đầu hực thi
//                sleep(): Tạm ngưng tron bao lâu
//                interrupt(): check luồng kết thúc. Ngắt luồng -> Gián đoạn Thread
    }
}
