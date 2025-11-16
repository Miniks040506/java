import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int currentYear = 2025;

        try {
            System.out.println(getInputFromConsole(currentYear));
        } catch (NullPointerException e) {
            System.out.println(getInputFromScanner(currentYear));
        }
    }

    public static String getInputFromConsole (int currentYear) {

        String name = System.console().readLine("Hi. What is your Name ?");
        System.out.println("Hi " + name + ", thanks for taking the course");

        String dateOfBirth = System.console().readLine("What year were you born");
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return "So you are " + age + " years old!";
    }

    public static String getInputFromScanner (int currentYear) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Hi. What's your Name?: ");
        String name = sc.nextLine();
        System.out.println("Hi " + name + ", thanks for taking the course");

        System.out.print("What year were you born? ");
        boolean validDOB = false;
        int age = 0;

        do {
            System.out.print("(Enter a year of birth >= " +
                    (currentYear - 125) + " and <= " + currentYear + "): ");
            try {
                age = checkData(currentYear, sc.nextLine());
                validDOB = age < 0 ? false : true;
                if (!validDOB) {
                    System.out.println("Try again!");
                }
            } catch (NumberFormatException badUserData) {
                System.out.println("Characters not allow. Please, Try Again!");
            }
        } while (!validDOB);

        return "So you are " + age + " years old!";
    }

    public static int checkData(int currentYear, String dateOfBirth) {
        int dob = Integer.parseInt(dateOfBirth);
        int minimumYear = currentYear -125;

        if (dob < minimumYear || dob > currentYear) {
            return -1;
        }

        return currentYear - dob;
    }
}
