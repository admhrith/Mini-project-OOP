package miniProject.onlineshop;

public class main {
    public static void main(String[] args) {
        // 1. Initialize the system
        ShopManagementSystem shop = new ShopManagementSystem();

        // 2. Add items to catalog
        Item laptop = new Item("I001", "Laptop", 3500.00, 10);
        shop.addItem(laptop);
        shop.addItem(new Item("I002", "Mouse", 50.00, 50));

        // 3. Display catalog
        shop.displayCatalog();

        // 4. Perform a transaction
        try {
            Transaction t1 = new Transaction("T1001", "2026-06-29", "C001");
            t1.addAndVerifyItem(laptop, 2);
            t1.processPayment(t1.getTotalPrice());
            t1.printReceipt();
            
            shop.recordTransaction(t1);
        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}