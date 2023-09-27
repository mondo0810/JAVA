import java.util.ArrayList;
import java.util.Date;

public class DeliveryOrder extends Order {
    private String address;

    public DeliveryOrder(String customerName, Date transactionTime,  String address) {
        super(customerName, transactionTime);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "address='" + address + '\'' +
                '}';
    }
}
