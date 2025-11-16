import java.util.Scanner;

public class primeNumber {
    public static void main(String[] args) {
/*
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the number: ");
        int n = scanner.nextInt();

        System.out.println(n + " is " + (isPrimeNumber(n) ? "" : "not ") + "a prime number");
 */

        int count = 0;

        for (int i = 10 ; i < 50; i ++) {
            if (isPrimeNumber(i)) {
                System.out.println(i + " is a prime number!");
                count ++;
                if (count == 3) {
                    System.out.println("Found 3 - exiting the loop");
                    break;
                }
            }
        }
    }

    public static boolean isPrimeNumber (int num) {
        if (num <= 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i ++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
