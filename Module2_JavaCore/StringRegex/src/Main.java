public class Main {
    public static void main(String[] args) {
        String name1 = "Công";
        String name2 = "Công";
        String name3 = new String("Công");

        System.out.println(name1.equals(name2));
        System.out.println(name1.equals(name3));
        System.out.println(name1 == name2);
        System.out.println(name1 == name3);
        name1.concat(name2);
        System.out.println(name1);

        StringBuilder num = new StringBuilder();
        for (int i = 0; i < 20; i++)
            num.append(i);
        System.out.println(num);
    }
}