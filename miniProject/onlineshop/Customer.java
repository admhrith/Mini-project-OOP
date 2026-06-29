package miniProject.onlineshop;

import java.util.ArrayList;

import java.util.List;

public class Customer extends User{

    private String address;
    private ArrayList<Item> shoppingCart;

     public Customer(String userId, String name, String email, String address) {
        super(userId, name, email);
        this.address = address;
        this.shoppingCart = new ArrayList<>();
    }

    public void addToCart(Item item) {
        shoppingCart.add(item);
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void displayProfile() {
        System.out.println("===== CUSTOMER PROFILE =====");
        System.out.println("Customer ID : " + userId);
        System.out.println("Name        : " + name);
        System.out.println("Email       : " + email);
        System.out.println("Address     : " + address);
        System.out.println("Cart Items  : " + shoppingCart.size());
    }
    
    public static List<Customer> getDefaultCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("C001", "Ali Bin Ahmad", "ali@gmail.com", "Kuala Lumpur"));
        customers.add(new Customer("C002", "Saleh", "saleh@gmail.com", "Johor Bahru"));
        customers.add(new Customer("C003", "Johan", "johan@gmail.com", "Penang"));
        return customers;
    }
}
