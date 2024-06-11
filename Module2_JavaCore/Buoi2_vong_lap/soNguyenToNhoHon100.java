public class soNguyenToNhoHon100 {
    public static void main(String[] args) {
        int n = 2;
        System.out.println("Các số nguyên tố nhỏ hơn 100 là:");
        while (n < 100) {
            if (soNguyenTo.isPrimeNumber(n)) {
                System.out.print(n + " ");
            }
            n++;
        }
    }
}
