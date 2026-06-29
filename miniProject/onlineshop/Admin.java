package miniProject.onlineshop;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private String adminRole;
    private String username;
    private String password;

    public Admin(String userId, String name, String email, String adminRole, String username, String password) {
        super(userId, name, email);
        this.adminRole = adminRole;
        this.username = username;
        this.password = password;
    }

    public String getAdminRole() { return adminRole; }
    public void setAdminRole(String adminRole) { this.adminRole = adminRole; }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    @Override
    public void displayProfile() {
        System.out.println("===== ADMIN PROFILE =====");
        System.out.println("Admin ID : " + userId);
        System.out.println("Name     : " + name);
        System.out.println("Email    : " + email);
        System.out.println("Role     : " + adminRole);
    }

    // ✅ Return multiple predefined admins
    public static List<Admin> getDefaultAdmins() {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("A001", "System Admin", "min@gmail.com", "Inventory Manager", "amin", "1234"));
        admins.add(new Admin("A002", "Tech Admin", "tech@gmail.com", "System Maintenance", "abu", "abcd"));
        admins.add(new Admin("A003", "Sales Admin", "sales@gmail.com", "Sales Manager", "salim", "5678"));
        return admins;
    }
}
