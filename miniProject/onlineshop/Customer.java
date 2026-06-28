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

        System.out.println();
        System.out.println(item.getItemName() + " successfully added into cart.");

    }   

    public void viewCart(){

        System.out.println();
        System.out.println("=============== SHOPPING CART ===============");

        if(shoppingCart.isEmpty()){

            System.out.println("Your shopping cart is empty.");
            return;

        }

        int no = 1;

        for(Item item : shoppingCart){

            System.out.printf("%d. %-25s RM %.2f%n",
                    no++,
                    item.getItemName(),
                    item.getItemPrice());

        }

    }

    public double calculateCartTotal(){

        double total = 0;

        for(Item item : shoppingCart){

            total += item.getItemPrice();

        }

        return total;

    }

    public void removeFromCart(int index){

        if(index >=0 && index < shoppingCart.size()){

            shoppingCart.remove(index);

            System.out.println("Item removed.");

        }

        else{

            System.out.println("Invalid selection.");

        }

    }

    public void clearCart(){

        shoppingCart.clear();

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

        System.out.println();
        System.out.println("============== CUSTOMER PROFILE ==============");

        System.out.println("Customer ID : " + userId);
        System.out.println("Name        : " + name);
        System.out.println("Email       : " + email);
        System.out.println("Address     : " + address);

        System.out.println("----------------------------------------------");

        System.out.println("Items In Cart : " + shoppingCart.size());

        System.out.printf("Cart Total    : RM %.2f%n",
                calculateCartTotal());

        System.out.println("==============================================");

    }
}
