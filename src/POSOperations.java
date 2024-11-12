public interface POSOperations {
    void createOrder(Order order) throws ProductOutOfStockException;
    void processPayment(Order order, Transaction transaction);
}
