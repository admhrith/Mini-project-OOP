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

    public ArrayList<Item> getProductCatalog() {
        return productCatalog;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
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

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                    PRODUCT CATALOG");
        System.out.println("==============================================================");

        System.out.printf("%-8s %-25s %-12s %-8s %-15s%n",
                "ID",
                "Product",
                "Price",
                "Stock",
                "Status");

        System.out.println("--------------------------------------------------------------");

        for(Item item : productCatalog){

            System.out.printf("%-8s %-25s RM %-9.2f %-8d %-15s%n",
                    item.getItemId(),
                    item.getItemName(),
                    item.getItemPrice(),
                    item.getUnitItem(),
                    item.getStockStatus());

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

    public Item searchItem(String itemId){

        for(Item item : productCatalog){

            if(item.getItemId().equalsIgnoreCase(itemId)){
                return item;
            }

        }

        return null;

    }

    public void displayLowStock(){

        System.out.println("\n========== LOW STOCK PRODUCTS ==========");

        boolean found = false;

        for(Item item : productCatalog){

            if(item.getUnitItem() <= 5){

                found = true;

                System.out.printf("%-10s %-25s Stock : %d%n",
                        item.getItemId(),
                        item.getItemName(),
                        item.getUnitItem());

            }

        }

        

        if(!found){

            System.out.println("No low stock products.");

        }

    }

    public void displayProductReport(){

        System.out.println("\n============== PRODUCT REPORT ==============");

        System.out.printf("%-8s %-25s %-10s %-8s%n",
                "ID",
                "Product",
                "Price",
                "Stock");

        for(Item item : productCatalog){

            System.out.printf("%-8s %-25s RM %-8.2f %-5d%n",
                    item.getItemId(),
                    item.getItemName(),
                    item.getItemPrice(),
                    item.getUnitItem());

        }

    }

    public void displayCustomerReport(){

        System.out.println("\n============== CUSTOMER REPORT ==============");

        for(Customer c : customerList){

            System.out.println("-------------------------------------");
            c.displayProfile();

        }

    }

    public void displayAllTransactions() {

        if(transactionHistory.isEmpty()){

            System.out.println("\nNo transaction found.");
            return;

        }

        System.out.println("\n================ TRANSACTION HISTORY ================");

        for(Transaction t : transactionHistory){

            System.out.println("---------------------------------------------");
            System.out.println("Transaction ID : " + t.getTransactionId());
            System.out.println("Date           : " + t.getDate());
            System.out.printf("Total          : RM %.2f%n", t.getTotalPrice());

        }

    }

    //Transaction Management
    public void recordTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }


    public void displayDashboard() {

        System.out.println("\n========================================");
        System.out.println("            SYSTEM DASHBOARD");
        System.out.println("========================================");

        System.out.println("Total Products     : " + productCatalog.size());
        System.out.println("Total Customers    : " + customerList.size());
        System.out.println("Total Transactions : " + transactionHistory.size());

        double revenue = 0;

        for(Transaction t : transactionHistory){
            revenue += t.getTotalPrice();
        }

        System.out.printf("Total Revenue      : RM %.2f%n", revenue);

        System.out.println("========================================");
    }
}
