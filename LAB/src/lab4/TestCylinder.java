package lab4;

class Circle {
    private double radius;
    private String color;

    public Circle() {
        this.radius = 1.0;
        this.color = "red";
    }

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class Cylinder extends Circle {
    private double height;

    public Cylinder() {
        super(); // Calls the default constructor of the superclass Circle
        this.height = 1.0;
    }

    public Cylinder(double radius, String color, double height) {
        super(radius, color); // Calls the parameterized constructor of the superclass Circle
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return getArea() * height;
    }
}

public class TestCylinder {
    public static void main(String[] args) {
        Cylinder cylinder1 = new Cylinder(); // Default constructor
        System.out.println("Cylinder 1 - Radius: " + cylinder1.getRadius() + ", Color: " + cylinder1.getColor() + ", Height: " + cylinder1.getHeight() + ", Volume: " + cylinder1.getVolume());

        Cylinder cylinder2 = new Cylinder(3.0, "blue", 5.0); // Parameterized constructor
        System.out.println("Cylinder 2 - Radius: " + cylinder2.getRadius() + ", Color: " + cylinder2.getColor() + ", Height: " + cylinder2.getHeight() + ", Volume: " + cylinder2.getVolume());
    }
}
