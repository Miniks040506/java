public class Console {
    public static void main(String[] args) {
        int currentYear = 2025;

        System.out.println(getInputFromConsole(currentYear));

        System.out.println(getInputFromScanner(currentYear));
    }

    public static String getInputFromConsole (int currentYear) {

        String name = System.console().readLine("Hi. What's your Name ?");
        System.out.println("Hi " + name + ", thanks for taking the course");

        return "";
    }

    public static String getInputFromScanner (int currentYear) {
        return "";
    }


}
