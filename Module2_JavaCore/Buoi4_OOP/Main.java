public class Main {
    public static void main(String[] args) {

        hello h = new hello();
        h.sayHello();

        bye b = new bye();
        b.sayBye();

        Student s = new Student();
        s.printStudent();
    }

    static {
        System.out.println("This is a static block");
        System.out.println("wtf");
    }

    public static class hello {
        public void sayHello() {
            System.out.println("Hello World");
        }
    }

    public static class bye {
        public void sayBye() {
            System.out.println("Bye World");
        }
    }
}
