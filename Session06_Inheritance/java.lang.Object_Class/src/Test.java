import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int num1, num2, result;
        String op;
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the number 1: ");
        num1 = sc.nextInt();
        System.out.println("Input the number 2: ");
        num2 = sc.nextInt();
        System.out.println("Input the operator (+-*/): ");
        op = sc.next();

        if (op.equals("+"))
            System.out.println("the result of " + num1 + " + " + num2 + " = " + (num1 + num2));
        if (op.equals("-"))
            System.out.println("the result of " + num1 + " - " + num2 + " = " + (num1 - num2));
        if (op.equals("*"))
            System.out.println("the result of " + num1 + " * " + num2 + " = " + (num1 * num2));
        if (op.equals("/")) {
            try {
                result = num1 / num2;
                System.out.println("the result of " + num1 + " / " + num2 + " = " + result);
            } catch (ArithmeticException e) {
                System.out.println("cannot divide by zero!");
            }
        }
    }
}
