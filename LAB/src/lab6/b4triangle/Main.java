package lab6.b4triangle;

import java.util.Scanner;


class InvalidTriangleException extends Exception {
    public InvalidTriangleException(String message) {
        super(message);
    }
}


class Triangle {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) throws InvalidTriangleException {
        if (!isValidTriangle(side1, side2, side3)) {
            throw new InvalidTriangleException("Invalid triangle: The sum of any two sides must be greater than the third side.");
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double calculateArea() {
        // Calculate and return the area of the triangle
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    private boolean isValidTriangle(double a, double b, double c) {
        return a + b > c && b + c > a && c + a > b;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Declare an array of 5 Triangle objects
        Triangle[] triangles = new Triangle[5];

        // Initialize the triangles with user-input edge values
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Enter the edges of Triangle " + (i + 1) + ":");
                double side1 = scanner.nextDouble();
                double side2 = scanner.nextDouble();
                double side3 = scanner.nextDouble();

                triangles[i] = new Triangle(side1, side2, side3);
            } catch (InvalidTriangleException ex) {
                System.out.println("Error: " + ex.getMessage());
                i--; // Re-enter values for the current triangle
            }
        }

        // Calculate and print the area of the fifth triangle
        double area = triangles[4].calculateArea();
        System.out.println("Area of the fifth triangle: " + area);

        scanner.close();
    }
}
