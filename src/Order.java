import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<OrderItem> items = new ArrayList<>();
    private double totalCost;

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public List<OrderItem> getItems() { return items; }
    public int getOrderId() { return orderId; }
    public double getTotalCost() { return totalCost; }

    public void calculateTotalCost() {
        totalCost = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
