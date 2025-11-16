public class WhileChallenge {
    public static void main(String[] args) {
        int number = 0;
        int finishNumber = 20;
        int oddCount = 0;
        int evenCount = 0;

        System.out.println("Even numbers : ");
        while (number <= finishNumber) {
            number ++;
            if (! isEven(number)) {
                oddCount ++;
                continue;
            }
            System.out.print(number + " ");
            evenCount ++;
        }
        System.out.println();
        System.out.println("Total even numbers found = " + evenCount);
        System.out.println("Total odd numbers found = " + oddCount);
    }

    public static boolean isEven (int number) {
        if (number % 2 == 0) return true;
        return false;
    }
}
