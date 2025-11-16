public class CodeBlocksAndIfKeyWord {
    public static void main(String[] args) {

        boolean gameOver = true;
        int score = 1000;
        int levelCompleted = 5;
        int bonus = 100;

        do {
            score += bonus * levelCompleted;
            if (score >= 5000) {
                score += bonus;
                System.out.println("You are win!!Your score was " + score + " !!");
            }
            else if (score > 2000 && score < 5000) {
                System.out.println("You are lose!!");
                System.out.println("LevelCompleted need to up!!");
                levelCompleted ++;
            }
            else {
                System.out.println("You are too weak!!");
                System.out.println("I will give you power!!");
                levelCompleted += 3;
            }
        } while (score < 5000);

    }
}
