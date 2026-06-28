package miniProject.onlineshop;

public class Exception {
    private String message;

    public Exception(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println("Exception: " + message);
    }
}
