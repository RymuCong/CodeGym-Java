package entity;

public abstract class Staff {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private int salary;

    public Staff(String id, String name, String email, String phoneNumber, int salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public abstract void display();
}