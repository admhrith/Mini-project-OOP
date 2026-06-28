package miniProject.onlineshop;

public class TransactionItem {
    private String itemId;
    private String itemName;
    private int quantity;
    private double priceAtPurchase;

    public TransactionItem(String itemId, String itemName, int quantity, double priceAtPurchase) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public double getSubTotal() {
        return quantity * priceAtPurchase;
    }

    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getPriceAtPurchase() { return priceAtPurchase; }
}