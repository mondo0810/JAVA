package test;

import com.google.gson.Gson;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter methods for name and age

    // Override toString() for custom representation (optional)
    @Override
    public String toString() {
        return "test.Person [Name: " + name + ", Age: " + age + "]";
    }
}

public class JsonConversionExample {
    public static void main(String[] args) {
        // Tạo một đối tượng test.Person
        Person person = new Person("John", 30);
        System.out.println(person.toString());
        // Sử dụng Gson để chuyển đối tượng thành chuỗi JSON
        Gson gson = new Gson();
        String json = gson.toJson(person);

        // In ra chuỗi JSON
        System.out.println(json);
    }
}
