package lab5.shape;

public class Main {
    public static void main(String[] args) {
        Rectangle myRectangle = new Rectangle("Blue", 5, 8);
        System.out.println(myRectangle.toString());
        System.out.println("Area: " + myRectangle.getArea());

        Triangle myTriangle = new Triangle("Blue", 5, 8);
        System.out.println(myTriangle.toString());
        System.out.println("Area: " + myTriangle.getArea());

    }
}
