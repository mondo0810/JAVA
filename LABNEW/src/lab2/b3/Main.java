package lab2.b3;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle=new Rectangle();
        rectangle.setHeight(10);
        rectangle.setWidth(10);
        rectangle.display();
        System.out.println(rectangle.getPerimeter());
        System.out.println(rectangle.getArea());
    }
}
