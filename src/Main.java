import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        POSOperations pos = new POSImplementation();
        Scanner scanner = new Scanner(System.in);

        // Sample inventory
        List<Product> inventory = new ArrayList<>();
        inventory.add(new Beverage(1, "Coffee", 5.0, 10));
        inventory.add(new Snack(2, "Sandwich", 7.5, 5));
        inventory.add(new Beverage(3, "Tea", 4.0, 8));
        inventory.add(new Snack(4, "Chips", 3.0, 15));

        System.out.println("Welcome to the Cafeteria POS System!");
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        Order order = new Order(orderId);

        boolean ordering = true;
        while (ordering) {
            System.out.println("\nMenu:");
            for (Product product : inventory) {
                System.out.printf("%d - %s: $%.2f (Stock: %d)\n",
                        product.getProductId(), product.getName(), product.getPrice(), product.getStockLevel());
            }
            System.out.print("\nEnter product ID to add to order (or 0 to finish): ");
            int productId = scanner.nextInt();

            if (productId == 0) {
                ordering = false;
                break;
            }

            Product selectedProduct = inventory.stream()
                    .filter(p -> p.getProductId() == productId)
                    .findFirst().orElse(null);

            if (selectedProduct == null) {
                System.out.println("Invalid product ID. Please try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            if (quantity > selectedProduct.getStockLevel()) {
                System.out.println("Sorry, not enough stock available.");
                continue;
            }

            order.addItem(selectedProduct, quantity);
            System.out.println(quantity + " x " + selectedProduct.getName() + " added to your order.");
        }

        try {
            pos.createOrder(order);
        } catch (ProductOutOfStockException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Enter payment type (cash/card): ");
        String paymentType = scanner.next();
        Transaction transaction = new Transaction(1, orderId, order.getTotalCost(), paymentType);

        pos.processPayment(order, transaction);
        System.out.println("Order processed successfully. Total cost: $" + order.getTotalCost());
        scanner.close();
    }
}
