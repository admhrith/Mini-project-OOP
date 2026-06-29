package miniProject.onlineshop;

import java.util.ArrayList;
import java.util.List;

public class ShopManagementSystem {
    // Attributes
    private ArrayList<Item> productCatalog;
    private ArrayList<Customer> customerList;
    private ArrayList<Transaction> transactionHistory;

    // Constructor loads shared reference from Item.java
    public ShopManagementSystem() {
        productCatalog = (ArrayList<Item>) Item.getItemCatalog(); 
        customerList = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }


    // Item management
    public void addItem(Item item) {
        productCatalog.add(item);
    }

    public void editItem(String itemId, String newName, double newPrice, int newUnitItem) {
        for (Item item : productCatalog) {
            if (item.getItemId().equals(itemId)) {
                item.setItemName(newName);
                item.setItemPrice(newPrice);
                item.setUnitItem(newUnitItem);
                return;
            }
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void deleteItem(String itemId) {
        productCatalog.removeIf(item -> item.getItemId().equals(itemId));
    }

    public void displayCatalog() {
        System.out.println("=== Product Catalog ===");
        for (Item item : productCatalog) {
            System.out.println(item.getItemId() + " | " + item.getItemName() +
                               " | Price: " + item.getItemPrice() +
                               " | Units: " + item.getUnitItem());
        }
    }

    // Deduct stock when customer buys
    public boolean deductStock(String itemId, int quantity) throws OutOfStockException {
        for (Item item : productCatalog) {
            if (item.getItemId().equals(itemId)) {
                if (item.getUnitItem() >= quantity) {
                    item.setUnitItem(item.getUnitItem() - quantity);
                    return true;
                } else {
                    throw new OutOfStockException("Not enough stock for " + item.getItemName());
                }
            }
        }
        System.out.println("Item not found.");
        return false;
    }

    // Customer Management
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public Customer loginCustomer(String userId, String email) {
        for (Customer c : customerList) {
            if (c.getUserId().equals(userId) && c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public void displayCustomers() {
        System.out.println("=== Customer List ===");
        for (Customer c : customerList) {
            System.out.println(c.getUserId() + " | " + c.getName() + " | " + c.getAddress());
        }
    }

    // Transaction Management
    public void recordTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public void displayAllTransactions() {
        System.out.println("=== Transaction History ===");
        for (Transaction t : transactionHistory) {
            System.out.println("Transaction ID: " + t.getTransactionId() +
                               " | Date: " + t.getDate() +
                               " | Total: " + t.getTotalPrice());
            for (TransactionItem ti : t.getOrderedItems()) {
                System.out.println("   Item: " + ti.getItemName() +
                                   " | Qty: " + ti.getQuantity() +
                                   " | Subtotal: " + ti.getSubTotal());
            }
        }
    }
}
