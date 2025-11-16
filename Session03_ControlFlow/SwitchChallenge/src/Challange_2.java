import java.util.Scanner;

public class Challange_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the day: ");
        int n = scanner.nextInt();
        printDayOfWeek(n);
        printWeekDay(n);
    }

    public static void printDayOfWeek (int day) {
        String dayOfWeek = switch (day) {
            case 1 -> {
                yield "Sunday";
            }
            case 2 -> "Monday";
            case 3 -> "Tuesday";
            case 4 -> "Wednesday";
            case 5 -> "Thursday";
            case 6 -> "Friday";
            case 7 -> "Saturday";
            default -> "Invalid day";
        };

        System.out.println(day + " stands for " + dayOfWeek);
    }

    public static void printWeekDay (int day) {
        String dayOfWeek = "Invalid day";
        if (day == 1) {
            dayOfWeek = "Sunday";
        }
        else if (day == 2) {
            dayOfWeek = "Monday";
        }
        else if (day == 3) {
            dayOfWeek = "Tuesday";
        }
        else if (day == 4) {
            dayOfWeek = "Wednesday";
        }
        else if (day == 5) {
            dayOfWeek = "Thursday";
        }
        else if (day == 6) {
            dayOfWeek = "Friday";
        }
        else if (day == 7) {
            dayOfWeek = "Saturday";
        }
        System.out.println(day + " stands for " + dayOfWeek);
    }
}
