package collection;

import java.util.LinkedList;

public class LinkedListExample {
    public static void initLinkedList() {

        LinkedList<String> cars = new LinkedList<>();

        LinkedList<String> students = new LinkedList<>();

        cars.add("Honda");
        cars.add("Toyota");
        System.out.println(cars);
        // Thêm vào vị trí xác định
        cars.add(1, "BMW");

        cars.pollFirst();
        cars.removeLast();


        System.out.println(cars.size());
        System.out.println(cars);
        System.out.println(cars.get(cars.size() - 1));
        System.out.println("Phan tu cuoi cung "+cars.getLast());
        System.out.println(cars.getFirst());


    }

    public static void main(String[] args) {
        initLinkedList();
    }
}

