public abstract class Product {
    private int productId;
    private String name;
    private double price;
    private int stockLevel;

    public Product(int productId, String name, double price, int stockLevel) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockLevel = stockLevel;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockLevel() { return stockLevel; }

    public void reduceStock(int quantity) {
        this.stockLevel -= quantity;
    }
}
