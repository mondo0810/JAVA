package collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {

    public static void initArrayList() {
        //Để tạo ArrayList có 2 cách
        // Cách 1: Thông qua instance của ArrayList
        ArrayList<String> animals = new ArrayList<>();

        animals.add("Dog");
        animals.add("Bird");
        animals.add(1, "Cat");

        System.out.println(animals.size());

        animals.set(0, "Hhihi");

        System.out.println(animals);
        animals.remove(0);
        for (String animal : animals) {
            System.out.println(animal);
        }

        // Cách 2: Thông qua list interface
        List<String> students = new ArrayList<>();
    }

    public static void main(String[] args) {

        initArrayList();
    }
}
