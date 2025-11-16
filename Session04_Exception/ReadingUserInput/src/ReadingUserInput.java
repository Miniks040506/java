public class ReadingUserInput {
    public static void main(String[] args) {
        int currentYear = 2025;
        String yearOfBirth = "2006";

        int userBirth = Integer.parseInt(yearOfBirth);

        System.out.println("Age = " + (currentYear - userBirth));

        String string = "20.50";
        double dou = Double.parseDouble(string);
        System.out.println("dou : " + dou);
    }
}
