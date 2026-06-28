package miniProject.onlineshop;

import java.util.ArrayList;

// REQ 4: Interface Implementation
public class Transaction implements PaymentProcessor {
    private String transactionId;
    private String date;
    private String customerId;
    private double totalPrice;
    
    // REQ 2: Composition
    // Transaction control its items
    private ArrayList<TransactionItem> orderedItems;

    public Transaction(String transactionId, String date, String customerId) {
        this.transactionId = transactionId;
        this.date = date;
        this.customerId = customerId;
        this.orderedItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addAndVerifyItem(Item item, int quantity) throws OutOfStockException {
        // REQ 8 (BONUS): Check a condition and if it fail, throw a exception.
        if (item.getUnitItem() < quantity) {
            throw new OutOfStockException("Purchase Failed: '" + item.getItemName() + "' only has " + item.getUnitItem() + " units left.");
        }

        // Reduce stock if verification succeed
        item.setUnitItem(item.getUnitItem() - quantity);

        // Take a snapshot of this purchase
        TransactionItem receiptLine = new TransactionItem(item.getItemId(), item.getItemName(), quantity, item.getItemPrice());
        // Add it into the receipt list
        orderedItems.add(receiptLine);
        
        calcTotalPrice();
    }

    public void calcTotalPrice() {
        double total = 0.0;
        for (TransactionItem line : orderedItems) {
            total += line.getSubTotal();
        }
        this.totalPrice = total;
    }

    public double getTotalPrice() { return totalPrice; }
    public String getTransactionId() { return transactionId; }

    // REQ 4: Overriding Interface Abstract Method
    @Override
    public void processPayment(double amount) {
        System.out.println("Gateway: Processing secure terminal transaction total of RM " + amount);
        System.out.println("Gateway: Authorization Status [SUCCESS] for ID: " + transactionId);
    }

    public void printReceipt() {
        System.out.println("\n----------- ORDER RECEIPT -----------");
        System.out.println("Receipt ID  : " + transactionId);
        System.out.println("Date Issued : " + date);
        System.out.println("-------------------------------------");
        for (TransactionItem line : orderedItems) {
            System.out.println("- " + line.getItemName() + " x" + line.getQuantity() + " | Sub: RM " + line.getSubTotal());
        }
        System.out.println("-------------------------------------");
        System.out.println("TOTAL NET BILL: RM " + totalPrice);
        System.out.println("-------------------------------------\n");
    }

    public ArrayList<TransactionItem> getOrderedItems() {
    return orderedItems;
    }
}