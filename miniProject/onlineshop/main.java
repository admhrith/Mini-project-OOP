package miniProject.onlineshop;

import java.util.Scanner;

public class main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        ShopManagementSystem shop = new ShopManagementSystem();

        //==========================
        // Sample Products
        //==========================
        shop.addItem(new Item("I001", "Laptop", 3500.00, 10));
        shop.addItem(new Item("I002", "Gaming Mouse", 120.00, 30));
        shop.addItem(new Item("I003", "Mechanical Keyboard", 280.00, 15));
        shop.addItem(new Item("I004", "Monitor 24\"", 650.00, 12));

        //==========================
        // Sample Customers
        //==========================
        Customer c1 = new Customer("C001", "Hazim", "hazim@gmail.com", "Johor");
        Customer c2 = new Customer("C002", "Ali", "ali@gmail.com", "Kuala Lumpur");

        shop.addCustomer(c1);
        shop.addCustomer(c2);

        int choice;

        do {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("        ONLINE SHOP MANAGEMENT SYSTEM");
            System.out.println("==================================================");
            System.out.println("1. Admin Panel");
            System.out.println("2. Customer Portal");
            System.out.println("3. Exit");
            System.out.println("==================================================");
            System.out.print("Enter choice : ");

            choice = input.nextInt();

            switch(choice){

                case 1:
                    adminMenu(shop);
                    break;

                case 2:
                    customerMenu(shop);
                    break;

                case 3:
                    System.out.println("\nThank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        }while(choice!=3);

    }

    //====================================================
    // ADMIN MENU
    //====================================================

    public static void adminMenu(ShopManagementSystem shop){

        int choice;

        do{

            System.out.println();
            System.out.println("============== ADMIN PANEL ==============");
            System.out.println("1. View Product Catalog");
            System.out.println("2. View Customers");
            System.out.println("3. View Transactions");
            System.out.println("4. Back");
            System.out.print("Choice : ");

            choice=input.nextInt();

            switch(choice){

                case 1:
                    shop.displayCatalog();
                    break;

                case 2:
                    shop.displayCustomers();
                    break;

                case 3:
                    shop.displayAllTransactions();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        }while(choice!=4);

    }

    //====================================================
    // CUSTOMER MENU
    //====================================================

    public static void customerMenu(ShopManagementSystem shop){

        int choice;

        do{

            System.out.println();
            System.out.println("============== CUSTOMER PORTAL ==============");
            System.out.println("1. View Products");
            System.out.println("2. Buy Product");
            System.out.println("3. Back");
            System.out.print("Choice : ");

            choice=input.nextInt();

            switch(choice){

                case 1:

                    shop.displayCatalog();
                    break;

                case 2:

                    buyProduct(shop);
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        }while(choice!=3);

    }

    //====================================================
    // BUY PRODUCT
    //====================================================

    public static void buyProduct(ShopManagementSystem shop){

        shop.displayCatalog();

        System.out.print("\nEnter Product ID : ");
        String id=input.next();

        System.out.print("Quantity : ");
        int qty=input.nextInt();

        try{

            Item selected=null;

            for(Item item:shop.getProductCatalog()){

                if(item.getItemId().equalsIgnoreCase(id)){

                    selected=item;
                    break;

                }

            }

            if(selected==null){

                System.out.println("Product not found.");
                return;

            }

            Transaction transaction =
                    new Transaction("T"+System.currentTimeMillis(),
                            "29/06/2026",
                            "C001");

            transaction.addAndVerifyItem(selected, qty);

            transaction.processPayment(transaction.getTotalPrice());

            transaction.printReceipt();

            shop.recordTransaction(transaction);

        }

        catch(OutOfStockException e){

            System.out.println(e.getMessage());

        }

    }

}