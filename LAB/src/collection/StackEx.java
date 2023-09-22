package collection;

import lab2.b1.Student;

import java.util.Stack;

public class StackEx {

public static void initStack(){
    Stack<Student> students = new Stack<>();
    Stack<String> employees = new Stack<>();
    employees.add("hello");
    employees.push("world");
    System.out.println(employees);
}

    public static void main(String[] args) {
        initStack();
    }
}
