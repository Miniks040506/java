public class StringMethods {
    public static void main(String[] args) {

        String birthDay = "04/05/2006";
        int startingIndex = birthDay.indexOf("2006");
        System.out.println("Starting Index = " + startingIndex);
        System.out.println("Birth Year = " + birthDay.substring(startingIndex));

        System.out.println("Month = " + birthDay.substring(3, 5));

        String newDate = String.join("/", "04", "05", "2006");
        System.out.println("New Date = " + newDate);

        newDate = "04";
        newDate = newDate.concat("/");
        newDate = newDate.concat("05");
        newDate = newDate.concat("/");
        newDate = newDate.concat("06");
        System.out.println("New Date = " + newDate);

        newDate = "04".concat("/").concat("05").concat("/").concat("06");
        System.out.println("New Date = " + newDate);

        System.out.println("New Date Format = " + newDate.replace("/", "-"));
        System.out.println(newDate.replace("0", "1"));

        System.out.println("New Date Format = " + newDate.replaceFirst("/", "-"));
        System.out.println("New Date Format = " + newDate.replaceAll("/", "--"));

        System.out.println("ABC\n".repeat(3));
        System.out.println("-".repeat(20));

        System.out.println("ABC\n".repeat(3).indent(8));
        System.out.println("-".repeat(20));

        System.out.println("      ABC\n".repeat(3).indent(-2));
        System.out.println("-".repeat(20));

    }
}
