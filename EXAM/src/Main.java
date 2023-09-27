import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("Sales Management System\n");

        Product tomato = new Product("F523", "Tomato", "Foot", 1.5, 589);
        Product zaraShirt = new Product("A7763", "Zara shirt", "Appearance", 12.0, 90);
        Product kitchen = new Product("H320", "Football", "Household", 299.0, 13);
        Product iPhone = new Product("E092", "iPhone", "Electronic", 1000.0, 4);
        Product football = new Product("S108", "Football", "Sport", 19.9, 2);

        boolean success;

        //Mike Tyson
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date orderDate = dateFormat.parse("2023-08-08 11:30:00");
        Order mikeTysonOrder = new Order("Mike Tyson", orderDate);

        success = mikeTysonOrder.addProduct(tomato, 5);
        System.out.println("Add 5 “Tomato”: " + (success ? "Success - Cost: " + mikeTysonOrder.cost() : "Fail - (Out of stock)"));
        success = mikeTysonOrder.addProduct(iPhone, 2);
        System.out.println("Add 2 “IPhone”: " + (success ? "Success - Cost: " + mikeTysonOrder.cost() : "Fail - (Out of stock)"));
        success = mikeTysonOrder.addProduct(football, 4);
        System.out.println("Add 4 “Football”: " + (success ? "Success - Cost: " + mikeTysonOrder.cost() : "Fail - (Out of stock)"));

        mikeTysonOrder.printOrderDetails();

        System.out.println("--------------------------------------------------------");

        // Chris Evans
        Date deliveryOrderDate = dateFormat.parse("2023-08-09 13:14:00");
        DeliveryOrder chrisEvansOrder = new DeliveryOrder("Chris Evans", deliveryOrderDate, "123 Cầu Giấy");

        success = chrisEvansOrder.addProduct(zaraShirt, 3);
        System.out.println("Add 3 “Zara shirt”: " + (success ? "Success  - Cost: " + chrisEvansOrder.cost() : "Fail - (Out of stock)"));

        success = chrisEvansOrder.addProduct(iPhone, 3);
        System.out.println("Add 3 “IPhone”: " + (success ? "Success  - Cost: " + chrisEvansOrder.cost() : "Fail - (Out of stock)"));
        chrisEvansOrder.printOrderDetails();
        System.out.println("Address: " + chrisEvansOrder.getAddress());
    }
}