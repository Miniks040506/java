public class MethodOverloadedChallenge {
    public static void main(String[] args) {

        System.out.println("5 ft, 8 in = " + convertToCentimeters(5, 8) + " cm");
        System.out.println("68 in = " + convertToCentimeters(68) + " cm");

    }

    public static double convertToCentimeters(int inches) {
        return inches * 2.54;
    }

    public static double convertToCentimeters(int feet, int inches) {
        return convertToCentimeters((feet * 12) + inches);
    }

}
