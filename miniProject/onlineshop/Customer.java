package miniProject.onlineshop;

import java.util.ArrayList;

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
}
