package miniProject.onlineshop;

import java.util.ArrayList;

public class ShopManagementSystem {
    //Attributes
    private ArrayList<Item> productCatalog;
    private ArrayList<Customer> customerList;
    private ArrayList<Transaction> transactionHistory;

    //Constructor
    public ShopManagementSystem() {
        productCatalog = new ArrayList<>();
        customerList = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }

    //item management
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

    //Customer Management
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void editCustomer(String userId, String newName, String newEmail, String newAddress) {
        for (Customer c : customerList) {
            if (c.getUserId().equals(userId)) {
                c.setName(newName);
                c.setEmail(newEmail);
                c.setAddress(newAddress);
                return;
            }
        }
        System.out.println("Customer with ID " + userId + " not found.");
    }

    public void deleteCustomer(String userId) {
        customerList.removeIf(c -> c.getUserId().equals(userId));
    }

    public void displayCustomers() {
        System.out.println("=== Customer List ===");
        for (Customer c : customerList) {
            System.out.println(c.getUserId() + " | " + c.getName() + " | " + c.getAddress());
        }
    }

    //Transaction Management
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
