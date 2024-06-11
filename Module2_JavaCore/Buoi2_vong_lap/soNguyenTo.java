public class soNguyenTo {
    public static void main(String[] args) {
        int n = 2;
        int count = 0;
        System.out.println("20 số nguyên tố đầu tiên là:");
        while (count < 20) {
            if (isPrimeNumber(n)) {
                System.out.print(n + " ");
                count++;
            }
            n++;
        }
    }

    static boolean isPrimeNumber(int n) {
        if (n < 2) {
            return false;
        }
        int squareRoot = (int) Math.sqrt(n);
        for (int i = 2; i <= squareRoot; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
