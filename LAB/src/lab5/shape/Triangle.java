package lab5.shape;

public class Triangle extends Shape {
    private int base;
    private int height;

    public Triangle(String color, int base, int height) {
        super(color);
        this.base = base;
        this.height = height;
    }
    @Override
    public String toString() {
        return "Triangle {" + getColor() +
                "base=" + base +
                ", height=" + height +
                '}';
    }

    @Override
    public double getArea() {
        return (double) (base * height) /2;
    }
}
