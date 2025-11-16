import java.util.Scanner;

public class SwitchCase {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.print("Input n: ");
        int n = read.nextInt();

        switch(n) {
            case 1:
                System.out.println("Value was 1");
                break;
            case 2:
                System.out.println("Value was 2");
                break;
            case 3: case 4: case 5:
                System.out.println("Value was a 3, a 4 or a 5");
                System.out.println("Actually it was a " + n);
                break;
            default:
                System.out.println("Value were not 1, 2 , 3, 4 or 5!");
                break;
        }
    }
}
