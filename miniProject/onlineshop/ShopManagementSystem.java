package miniProject.onlineshop;

import java.util.ArrayList;
import java.util.List;

public class ShopManagementSystem {
    // Attributes
    private ArrayList<Item> productCatalog;
    private ArrayList<Customer> customerList;
    private ArrayList<Transaction> transactionHistory;

    
    public ShopManagementSystem() {
        productCatalog = (ArrayList<Item>) Item.getItemCatalog(); 
        customerList = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }
    
    public Item findItem(String itemId) {
       for (Item item : productCatalog) {
           if (item.getItemId().equals(itemId)) {
               return item;
           }
       }
       return null;
    }

    // Item management
    public boolean addItem(Item item) {
          if (findItem(item.getItemId()) != null) {
              System.out.println("Error: Item with ID " + item.getItemId() + " already exists.");
              return false;
          }
          productCatalog.add(item);
          return true;
    }

    public boolean editItem(String itemId, String newName, double newPrice, int newUnitItem) {
          Item item = findItem(itemId);
          if (item == null) {
              System.out.println("Error: Item ID " + itemId + " not found.");
              return false;
          }
          item.setItemName(newName);
          item.setItemPrice(newPrice);
          item.setUnitItem(newUnitItem);
          return true;
    }

    public boolean deleteItem(String itemId) {
          Item item = findItem(itemId);
          if (item == null) {
              System.out.println("Error: Item ID " + itemId + " not found.");
              return false;
          }
          productCatalog.remove(item);
          return true;
    }

    public void displayCatalog() {
        System.out.println("=== Product Catalog ===");
        for (Item item : productCatalog) {
            System.out.println(item.getItemId() + " | " + item.getItemName() +
                               " | Price: " + item.getItemPrice() +
                               " | Units: " + item.getUnitItem());
        }
    }

    
    public boolean isStockAvailable(String itemId, int quantity) {
          for (Item item : productCatalog) {
              if (item.getItemId().equals(itemId)) {
                  return item.getUnitItem() >= quantity;
              }
          }
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
