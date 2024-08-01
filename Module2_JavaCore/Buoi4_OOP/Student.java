public class Student {
    static String name = "John";
    int age;
    public void sayHello() {
        System.out.println("Hello World");
    }

    Student() {
        this.name = "John";
        this.age = 20;
    }

    void Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printStudent() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}
