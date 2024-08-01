import entity.StaffOffice;

public class Main {
    public static void main(String[] args) {
        // Create an object of StaffOffice
        StaffOffice staffOffice = new StaffOffice("1", "John", "z6Ueh@example.com", "1234567890", 5000);
        // Display the information of the staff
        staffOffice.display();
    }
}
