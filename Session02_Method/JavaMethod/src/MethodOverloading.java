public class MethodOverloading {
    public static void main(String[] args) {

        int score = calculateScore("Tim", 500);
        System.out.println("New score is: " + score);

        calculateScore(100);

        calculateScore();
    }

    public static int calculateScore(String player, int score) {

        System.out.println("Player " + player + " scored " + score + " points!");
        return score * 1000;
    }

    public static int calculateScore(int score) {

        System.out.println("Unnamed player scored " + score + " points!");
        return score * 1000;
    }

    public static void calculateScore() {

        System.out.println("No player name, no player score!");
    }
}
