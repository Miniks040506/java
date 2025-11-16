public class DoWhileLoop {
    public static void main(String[] args) {
        int j = 1;
        boolean isReady = false;

        do {
            if (j > 5) break;
            System.out.print((j ++) + " ");
            isReady = (j > 0);
        } while (isReady);
    }
}
