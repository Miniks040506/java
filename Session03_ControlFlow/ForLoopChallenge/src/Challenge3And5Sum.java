public class Challenge3And5Sum {
    public static void main(String[] args) {

        int count = 0;
        int sum = 0;

        for (int loopNumber = 1; loopNumber <= 1000; loopNumber++) {
            if ((loopNumber % 3 == 0) && (loopNumber % 5 == 0)) {
                count++;
                sum += loopNumber;
                System.out.println("Found a match = " + loopNumber);
            }

            if (count == 5) {
                break;
            }
        }

        System.out.println("Sum = " + sum);
    }
}
