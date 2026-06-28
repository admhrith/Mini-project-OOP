package miniProject.onlineshop;

public class Item {
    private String itemId;
    private String itemName;
    private double itemPrice;
    private int unitItem; // Available Stock

    // REQ 6: Method Overloading (def constructor)
    public Item() {
        this.itemId = "UNKNOWN";
        this.itemName = "Generic Product";
        this.itemPrice = 0.0;
        this.unitItem = 0;
    }

    // REQ 6: Method Overloading (Parameterized Constructor)
    public Item(String itemId, String itemName, double itemPrice, int unitItem) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.unitItem = unitItem;
    }

    // Accessors and Mutators
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }

    public int getUnitItem() { return unitItem; }
    public void setUnitItem(int unitItem) { this.unitItem = unitItem; }

    public double calcItemPrice(int quantity) {
        return this.itemPrice * quantity;
    }
}