import java.util.Scanner;

public class vonglap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            System.out.println("Vui lòng nhập vào 1 số:");
            number = scanner.nextInt();
        } while (number <= 0 || number >= 50);

        System.out.println("Bạn đã nhập: " + number);
    }
}