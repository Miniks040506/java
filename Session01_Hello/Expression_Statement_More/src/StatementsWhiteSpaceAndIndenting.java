public class StatementsWhiteSpaceAndIndenting {

    public static void main(String[] args) {
        int myVariables = 50;

        myVariables++;
        myVariables--;

        System.out.println("This is the test");

        System.out.println("This is" +
                " another" +
                " still more!");

        System.out.println("My 1st variable: " + myVariables);

        int myVariables2 = 50;
        myVariables2++;
        System.out.println("My 2nd variable: " + myVariables2);
        // same meaning

        if (myVariables == 0) {
            System.out.println("It's now zero!!!");
        }

    }
}
