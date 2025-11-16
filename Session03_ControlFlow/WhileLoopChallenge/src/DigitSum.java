import java.util.Scanner;

public class DigitSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the number: ");
        int n = scanner.nextInt();

        System.out.println("Sum of all digits of " + n + " = " + sumDigits(n));
    }

    public static int sumDigits (int number) {
        if (number < 0) return -1;
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
