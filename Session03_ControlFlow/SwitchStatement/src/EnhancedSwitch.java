import java.util.Scanner;

public class EnhancedSwitch {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.print("Input n: ");
        int n = read.nextInt();

        switch(n) {
            case 1 -> System.out.println("Value was 1");
            case 2 -> System.out.println("Value was 2");
            case 3, 4, 5 -> {
                System.out.println("Value was a 3, a 4 or a 5");
                System.out.println("Actually it was a " + n);
            }
            default -> System.out.println("Value were not 1, 2 , 3, 4 or 5!");
        }

        System.out.print("Please enter the month (upper case): ");
        String month = read.next();
        System.out.println(month + " is in the " + getQuarter(month) + " quarter");
    }

    public static String getQuarter(String month) {
        return switch (month) {
            case "JANUARY", "FEBRUARY", "MARCH" -> { yield  "1st"; }
            case "APRIL", "MAY", "JUNE" -> "2nd";
            case "JULY", "AUGUST", "SEPTEMBER" -> "3rd";
            case "OCTOBER", "NOVEMBER", "DECEMBER" -> "4th";
            default -> {
                String badResponse = month + " is bad";
                yield  badResponse;
            }
        };
    }
}

