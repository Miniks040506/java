public class WhileLoop {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i ++) {
            System.out.print(i + " ");
        }
        System.out.println(" ");

        int j = 1;
        while (j <= 5) {
            System.out.print((j++) + " ");
        }
        System.out.println(" ");

        int k = 1;
        while (true) {
            if (k > 5) break;
            System.out.print((k ++) + " ");
        }
    }
}
