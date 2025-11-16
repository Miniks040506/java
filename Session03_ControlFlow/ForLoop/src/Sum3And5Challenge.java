public class Sum3And5Challenge {
    public static void main(String[] args) {
        int count = 0, sum = 0;

        for (int i = 1; i <= 1000; i ++) {
            if (canDivine(i)) {
                System.out.println("Found the match = " + i);
                count ++;
                sum += i;
                if (count == 5) break;
            }
        }
        System.out.println("Sum = " + sum);
    }

    public static boolean canDivine (int num) {
        if (num % 3 == 0 && num % 5 == 0) return true;
        return false;
    }
}
