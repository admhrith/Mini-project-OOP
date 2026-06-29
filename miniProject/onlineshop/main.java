package miniProject.onlineshop;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        ShopManagementSystem shop = new ShopManagementSystem();
        Scanner sc = new Scanner(System.in);

        System.out.println("Are you an Admin or a Customer? (Enter 'admin' or 'customer')");
        String role = sc.nextLine().trim().toLowerCase();

        if (role.equals("admin")) {
            
            List<Admin> admins = Admin.getDefaultAdmins();

            System.out.print("Enter Admin Username: ");
            String inputUser = sc.nextLine();
            System.out.print("Enter Admin Password: ");
            String inputPass = sc.nextLine();

            Admin loggedInAdmin = null;
            for (Admin admin : admins) {
                if (inputUser.equals(admin.getUsername()) && inputPass.equals(admin.getPassword())) {
                    loggedInAdmin = admin;
                    break;
                }
            }

            if (loggedInAdmin != null) {
                System.out.println("\nLogin successful!");
                loggedInAdmin.displayProfile();

                
                boolean running = true;
                while (running) {
                    System.out.println("\n=== ADMIN MENU ===");
                    System.out.println("1. Add Item");
                    System.out.println("2. Remove Item");
                    System.out.println("3. Edit Item");
                    System.out.println("4. View Catalog");
                    System.out.println("5. Exit");
                    System.out.print("Choose option: ");
                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter Item ID: ");
                            String itemId = sc.nextLine();
                            System.out.print("Enter Item Name: ");
                            String itemName = sc.nextLine();
                            System.out.print("Enter Item Price: ");
                            double itemPrice = sc.nextDouble();
                            System.out.print("Enter Item Stock: ");
                            int unitItem = sc.nextInt();
                            sc.nextLine();

                            shop.addItem(new Item(itemId, itemName, itemPrice, unitItem));
                            System.out.println("Item added successfully!");
                            break;

                        case 2:
                            System.out.print("Enter Item ID to remove: ");
                            String removeId = sc.nextLine();
                            
                            Item itemToRemove = null;
                            for (Item item : Item.getItemCatalog()) { 
                                  if (item.getItemId().equals(removeId)) {
                                  itemToRemove = item;
                                  break;
                                  }
                            }
    
                            if (itemToRemove == null) {
                                  System.out.println("Error: Item with ID " + removeId + " not found.");
                                  System.out.println("Returning to Admin Menu...");
                                  break; 
                            }
    
                            shop.deleteItem(removeId);
                            System.out.println("Item removed successfully!");
                            break;

                        case 3: 
                            System.out.print("Enter Item ID to edit: ");
                            String editId = sc.nextLine();
                            
                            Item itemToEdit = null;
                            for (Item item : Item.getItemCatalog()) { 
                                  if (item.getItemId().equals(editId)) {
                                  itemToEdit = item;
                                  break;
                                  }
                            }
                            
                            if (itemToEdit == null) {
                                 System.out.println("Error: Item with ID " + editId + " not found.");
                                 System.out.println("Returning to Admin Menu...");
                                 break; 
                            }
    
                            System.out.print("Enter new Item Name: ");
                            String newName = sc.nextLine();
                            System.out.print("Enter new Item Price: ");
                            double newPrice = sc.nextDouble();
                            System.out.print("Enter new Item Stock: ");
                            int newStock = sc.nextInt();
                            sc.nextLine();

                            shop.editItem(editId, newName, newPrice, newStock);
                            System.out.println("Item updated successfully!");
                            break;
                        case 4:
                            shop.displayCatalog();
                            break;

                        case 5:
                            running = false;
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            } else {
                System.out.println("Login failed. Invalid credentials.");
            }

        } else if (role.equals("customer")) {
            System.out.println("\n=== CUSTOMER MODE ===");
            
            for (Customer c : Customer.getDefaultCustomers()) {
                shop.addCustomer(c);
            }
            
            System.out.println("1. Register");
            System.out.println("2. Login");
            int choice = sc.nextInt();
            sc.nextLine();

            Customer currentCustomer = null;

            if (choice == 1) {
                System.out.print("Enter Customer ID: ");
                String id = sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                System.out.print("Enter Address: ");
                String address = sc.nextLine();

                currentCustomer = new Customer(id, name, email, address);
                shop.addCustomer(currentCustomer);
                System.out.println("Registration successful!");
            } 
            
            else if (choice == 2) {
                boolean loginSuccess = false;

                while (!loginSuccess) {
                      System.out.print("Enter Customer ID: ");
                      String id = sc.nextLine();
                      System.out.print("Enter Email: ");
                      String email = sc.nextLine();

                      currentCustomer = shop.loginCustomer(id, email);

                      if (currentCustomer == null) {
                            System.out.println("\nLogin failed. Invalid credentials.");
                            System.out.println("\nTry again or type 'back' to return to Customer Menu.");

                            System.out.print("Enter choice: ");
                            String retry = sc.nextLine();
                            if (retry.equalsIgnoreCase("back")) {
                                  break;
                            }
                      } 
                      
                      else {
                            System.out.println("\nLogin successful! Welcome " + currentCustomer.getName());
                            loginSuccess = true;
                      }
                }
            }


            // Shopping flow
            shop.displayCatalog();
            Transaction t1 = new Transaction("T1001", "2026-06-29", currentCustomer.getUserId());

            boolean shopping = true;
            while (shopping) {
                System.out.print("\nEnter Item ID to buy (or 'done'): ");
                String itemId = sc.nextLine();
                if (itemId.equalsIgnoreCase("done")) break;

                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();

                try {
                    if (shop.deductStock(itemId, qty)) {
                        for (Item i : Item.getItemCatalog()) {
                            if (i.getItemId().equals(itemId)) {
                                t1.addAndVerifyItem(i, qty);
                                System.out.println(qty + " x " + i.getItemName() + " added.");
                                break;
                            }
                        }
                    }
                } catch (OutOfStockException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            t1.processPayment(t1.getTotalPrice());
            t1.printReceipt();
            shop.recordTransaction(t1);
        } else {
            System.out.println("Invalid input. Please restart.");
        }

        sc.close();
    }
}
