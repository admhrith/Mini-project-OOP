package miniProject.onlineshop;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String itemId;
    private String itemName;
    private double itemPrice;
    private int unitItem; 

    // Default constructor
    public Item() {
        this.itemId = "UNKNOWN";
        this.itemName = "Generic Product";
        this.itemPrice = 0.0;
        this.unitItem = 0;
    }

    // Parameterized constructor
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

    // Static factory method to generate a catalog
    private static List<Item> catalog = new ArrayList<>();
    static {
        catalog.add(new Item("001", "Laptop", 3500.00, 10));
        catalog.add(new Item("002", "Mouse", 50.00, 50));
        catalog.add(new Item("003", "Keyboard", 120.00, 30));
        catalog.add(new Item("004", "Monitor", 800.00, 20));
        catalog.add(new Item("005", "Printer", 600.00, 15));
        catalog.add(new Item("006", "External Hard Drive", 250.00, 40));
        catalog.add(new Item("007", "USB Flash Drive", 30.00, 100));
        catalog.add(new Item("008", "Smartphone", 2800.00, 25));
        catalog.add(new Item("009", "Tablet", 1500.00, 18));
        catalog.add(new Item("010", "Headphones", 200.00, 35));
        catalog.add(new Item("011", "Webcam", 150.00, 22));
        catalog.add(new Item("012", "Router", 350.00, 12));
        catalog.add(new Item("013", "Smartwatch", 900.00, 10));
        catalog.add(new Item("014", "Gaming Chair", 1200.00, 8));
    }
    
    public static List<Item> getItemCatalog() {
        return catalog; 
    }
}
