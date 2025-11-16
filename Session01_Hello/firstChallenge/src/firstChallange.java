public class firstChallange {
    public static void main(String[] args) {
        double firstVar = 20.00;
        double secondVar = 80.00;

        double cal = (firstVar + secondVar) * 100.00;
        System.out.println("The cal = " + cal);

        double remainder = cal % 40.00;
        System.out.println("The remainder: " + remainder);

        boolean isNoRemainder = (remainder == 0.00) ? true : false;
        System.out.println("isNoRemainder: " + isNoRemainder);

        if (!isNoRemainder) {
            System.out.println("Got some remainder");
        }

    }
}
