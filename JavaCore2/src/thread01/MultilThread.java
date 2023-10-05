package thread01;

/*
* Sử dụng Thread: Chạy nhanh, hiệu quả hơn
* Ưu điểm của đa luồng: Có thêt thực hiện nhiều luồng cùng 1 lúc
* Tiết kiệm thời gian
* Các luồng này độc lập với nhau, nêếu có ngoại lệ xảy ra. Thì chỉ xảy ra ở một luồng duy nhất
* Thread: Là một đợn vị nhỏ nhất của máy tính thực hiện 1 cv riêng biệt.
* Đa luồng( Multithreading) và Đa nhiệm (Multitasking)
* Đa luồng: Một tiến trình thực hiện nhiều luồng( nghe nhạc: play, pause, next, subtitle ..)
* LifeCycle of Thread:
* New -> Runable -> Running -> Terminated
* 1.New: Luồng ở trạng thái tạo 1 instance của lớp Thread nhưng trước khi gọi phương thức start()
* 2. Runnable: Luồng ở trạng thái này sau khi gọi start();
* 3. Running: Luồng ở trạng thái này khi thread scheduler đã chọn nó.
* 4. Non Runable: Đây là trạng thái luồng vẫn còn sống nhưng không đủ điều kiện để chạy
* 5. Terminated: Một luồng đã ở trạng thái này khi phương thức run() của nó thoát
* */
public class MultilThread extends Thread {
    public void run() {
        System.out.println("Running Thread");

    }
}

