public class HelloJ {
    public static void main (String[] args) {
        System.out.println("Welcome to Java World!!!");

        boolean isAlian = false;
        if(!isAlian) {
            System.out.println("It is not an alian!!!");
            System.out.println("And i am scared of alian");
        }

        int topScore = 80;
        if (topScore < 100) {
            System.out.println("You got the high score!");
        }

        int secondTopScore = 60;
        if ((topScore > secondTopScore) && (topScore < 100)) {
            System.out.println("Greater than secondTopScore and less than 100");
        }

        if ((topScore > 90) || (secondTopScore <= 90)) {
            System.out.println("Either or both of the condition are true");
        }

        int newValue = 50;
        if (newValue == 50) {   // use == to show that var = operator....
            System.out.println("this is a correct syntax");
        }

        boolean isCar = false;
        if (!isCar) {
            System.out.println("This is not suppose to happen!");
        }

        String makeOfCar = "Volkswagen";
        boolean isDomestic = (makeOfCar == "Volkswagen") ? false : true;;

        if (!isDomestic) {
            System.out.println("The car is domestic to our country");
        }

        String s = (isDomestic) ? "This car is domestic" : "This car is not domestic";
        System.out.println(s);

        int ageOfClient = 20;
        String ageText = (ageOfClient >= 18) ? "Over Eighteen" : "Still a Kid";
        System.out.println("Our client is " + ageText);

    }
}
