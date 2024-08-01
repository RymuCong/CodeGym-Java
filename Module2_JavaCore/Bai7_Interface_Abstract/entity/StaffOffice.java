package entity;

public class StaffOffice extends Staff {

    public StaffOffice(String id, String name, String email, String phoneNumber, int salary) {
        super(id, name, email, phoneNumber, salary);
    }

    @Override
    public void display() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone number: " + getPhoneNumber());
        System.out.println("Salary: " + getSalary());
    }
}
