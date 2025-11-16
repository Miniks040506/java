public class ForLoop {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i ++) {
            System.out.println(i);
        }

        for (double rate = 1; rate <= 5; rate ++) {
            System.out.println("10000 at " +  rate + " interest: " + calculateInterest(10000, rate));
        }

        for (double rate = 7.5; rate <= 10; rate += 0.25) {
            if (rate > 9) break;
            System.out.println("100$ at " +  rate + " interest: " + calculateInterest(100, rate) + " $");
        }
    }

    public static double calculateInterest (double amount, double interestRate) {
        return (amount * (interestRate / 100));
    }
}
