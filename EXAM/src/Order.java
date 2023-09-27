import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Order {
    private String customerName;
    private Date transactionTime;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

    public Order(String customerName, Date transactionTime) {
        this.customerName = customerName;
        this.transactionTime = transactionTime;
    }

    public boolean addProduct(Product product, int quantity) {
        if (quantity <= product.getQuantity()) {
            LineItem lineItem = new LineItem(product, quantity);
            lineItems.add(lineItem);
            int newQuantity = product.getQuantity() - quantity;
            product.setQuantity(newQuantity);
            return true;
        } else {
            return false;
        }
    }

    public double cost() {
        double totalCost = 0;
        for (LineItem lineItem : lineItems) {
            totalCost += lineItem.cost();
        }
        return totalCost;
    }

    public void printOrderDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Customer: " + customerName);
        System.out.println("Transaction Time: " + dateFormat.format(transactionTime));
        System.out.println("Line Items:");
        for (LineItem lineItem : lineItems) {
            System.out.println("- " + lineItem.getProduct().getName() + " (Quantity: " + lineItem.getQuantity() + ")");
        }
        System.out.println("Total Cost: $" + cost());
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", transactionTime=" + transactionTime +
                ", lineItems=" + lineItems +
                '}';
    }
}
