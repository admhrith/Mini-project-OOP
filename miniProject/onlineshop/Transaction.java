package miniProject.onlineshop;

import java.util.ArrayList;

// REQ 4: Interface Implementation
public class Transaction implements PaymentProcessor {
    private String transactionId;
    private String date;
    private String customerId;
    private double totalPrice;
    private String paymentMethod;
    private String paymentStatus;
    
    // REQ 2: Composition
    // Transaction control its items
    private ArrayList<TransactionItem> orderedItems;

    public Transaction(String transactionId, String date, String customerId) {
        this.transactionId = transactionId;
        this.date = date;
        this.customerId = customerId;
        this.orderedItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.paymentMethod = "Cash";
        this.paymentStatus = "Pending";
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
    public String getDate() {
        return date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    // REQ 4: Overriding Interface Abstract Method
    @Override
    public void processPayment(double amount) {

        paymentStatus = "SUCCESS";

        System.out.println();
        System.out.println("===========================================");
        System.out.println("          PAYMENT GATEWAY");
        System.out.println("===========================================");
        System.out.printf("Transaction ID : %s%n", transactionId);
        System.out.printf("Payment Method : %s%n", paymentMethod);
        System.out.printf("Amount Paid    : RM %.2f%n", amount);
        System.out.println("Status         : " + paymentStatus);
        System.out.println("===========================================");
    }

    public void printReceipt() {

        System.out.println();
        System.out.println("======================================================");
        System.out.println("              ONLINE SHOP RECEIPT");
        System.out.println("======================================================");

        System.out.println("Receipt ID : " + transactionId);
        System.out.println("Customer   : " + customerId);
        System.out.println("Date       : " + date);

        System.out.println("------------------------------------------------------");

        System.out.printf("%-20s %-8s %-10s %-10s%n",
                "Item",
                "Qty",
                "Price",
                "Total");

        System.out.println("------------------------------------------------------");

        for(TransactionItem line : orderedItems){

            System.out.printf("%-20s %-8d RM %-7.2f RM %-7.2f%n",
                    line.getItemName(),
                    line.getQuantity(),
                    line.getPriceAtPurchase(),
                    line.getSubTotal());

        }

        System.out.println("------------------------------------------------------");

        System.out.printf("Grand Total : RM %.2f%n", totalPrice);

        System.out.println("Payment     : " + paymentMethod);
        System.out.println("Status      : " + paymentStatus);

        System.out.println("======================================================");
        System.out.println("      Thank You For Shopping With Us!");
        System.out.println("======================================================");
    }

    public ArrayList<TransactionItem> getOrderedItems() {
    return orderedItems;
    }
}