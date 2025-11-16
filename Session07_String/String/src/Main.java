public class Main {

    static int count = 0;

    public static void main(String[] args) {

        printInformation("Hello myself");
        printInformation("");
        printInformation("\t  \n");

        String str = "Hello World";
        System.out.printf("Index of 'W' is %d %n", str.indexOf('W'));
        System.out.printf("First index of 'l' is %d %n", str.indexOf('l'));
        System.out.printf("Last index of 'l' is %d %n", str.lastIndexOf('l'));

        System.out.printf("Second index of 'l' is %d %n", str.indexOf('l', 3)); // find l index, start at index 3
                                                                                            // from 3 -> length - 1
        System.out.printf("Second index of 'l' is %d %n", str.lastIndexOf('l', 8)); // find last l index, start at 8
                                                                                            // from 8 -> 0

        String str2 = str.toLowerCase();
        if (str.equals(str2)) {
            System.out.println(str + " and " + str2 + " are matching exactly!");
        }
        if (str.equalsIgnoreCase(str2)) {
            System.out.println(str + " and " + str2 + " are matching, ignoring case!");
        }

        if (str.endsWith("World")) {
            System.out.println(str + " end with World");
        }
        if (str.startsWith("Hello")) {
            System.out.println(str + " start with Hello");
        }
        if (str.contains("llo")) {
            System.out.println(str + " contains llo");
        }
        if (str.contentEquals("Hello World")) {
            System.out.println(str + " match exactly!");
        }
    }

    public static void printInformation (String string) {

        count ++;
        int length = string.length();

        if (string.isEmpty()) {
            System.out.println("String " + count + " is empty");
            return;
        }

        if (string.isBlank()) {
            System.out.println("String " + count + " is blank");
        }

        System.out.printf("String " + count + " Length = %d %n", length);
        System.out.printf("First char of String %d = %c %n", count, string.charAt(0));
        System.out.printf("Last char of String %d = %c %n", count, string.charAt(length - 1));
    }
}
