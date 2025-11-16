import java.util.Scanner;

public class Challange_1 {
    public static void main(String[] args) {
        Scanner myKeyboard = new Scanner(System.in);
        System.out.print("Input the letter: ");
        String alphabet = myKeyboard.next();

        switch (alphabet) {
            case "A":
                System.out.println("A is able");
                break;
            case "B":
                System.out.println("B is baker");
                break;
            case "C":
                System.out.println("C is charlie");
                break;
            case "D":
                System.out.println("D is dog");
                break;
            case "E":
                System.out.println("E is easy");
                break;
            default:
                System.out.println("Letter " + alphabet + " is not found in the alphabet");
        }
    }
}
