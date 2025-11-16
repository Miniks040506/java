public class Main {
    public static void main(String[] args) {
        String bulletIt = "Print a bulleted List: \n" +
                "\t\u2022 First Point \n" +
                "\t\t\u2022 Sub Point";

        System.out.println(bulletIt);

        String textBlock = """
                Print a bulleted List: 
                    \u2022 First Point
                        \u2022 Sub Point""";

        System.out.println(textBlock);

        //Like C
        int age = 35;
        System.out.printf("Your age is : %d%n", age);

        int yob = 2025 - age;
        System.out.printf("Age = %d, Year of Birth = %d%n", age, yob);

        System.out.printf("Your age is : %.2f%n", (float) age);

        String formattedString = String.format("Your age is : %d", age);
        System.out.println(formattedString);

        String fS = "Your age is : %d".formatted(age);
        System.out.println(fS);
    }
}
