package miniProject.onlineshop;

public class Admin extends User{

    private String adminRole;

    public Admin(String userId, String name, String email, String adminRole) {
        super(userId, name, email);
        this.adminRole = adminRole;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public void displayProfile() {
        System.out.println("===== ADMIN PROFILE =====");
        System.out.println("Admin ID : " + userId);
        System.out.println("Name     : " + name);
        System.out.println("Email    : " + email);
        System.out.println("Role     : " + adminRole);
    }
}