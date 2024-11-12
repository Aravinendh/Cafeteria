import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class POSImplementation implements POSOperations {

    @Override
    public void createOrder(Order order) throws ProductOutOfStockException {
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            if (product.getStockLevel() < item.getQuantity()) {
                throw new ProductOutOfStockException("Sorry, " + product.getName() + " is out of stock.");
            }
            product.reduceStock(item.getQuantity());  // Reduce stock for each ordered item
        }
        order.calculateTotalCost();
    }

    @Override
    public void processPayment(Order order, Transaction transaction) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO transactions (transaction_id, order_id, amount, payment_type) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, transaction.getTransactionId());
            statement.setInt(2, order.getOrderId());
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getPaymentType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
