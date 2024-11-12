public class Transaction {
    private int transactionId;
    private int orderId;
    private double amount;
    private String paymentType;

    public Transaction(int transactionId, int orderId, double amount, String paymentType) {
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public int getTransactionId() { return transactionId; }
    public int getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getPaymentType() { return paymentType; }
}
