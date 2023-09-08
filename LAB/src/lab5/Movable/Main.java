package lab5.Movable;

public class Main {
    public static void main(String[] args) {
        MovablePoint mov = new MovablePoint(2, 2, 50, 10);
        System.out.println(mov.toString());
        MovableCircle circle = new MovableCircle(2, mov);
        circle.moveUp();
        System.out.println(circle);
    }

}
