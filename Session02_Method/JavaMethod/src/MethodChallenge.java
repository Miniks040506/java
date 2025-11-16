public class MethodChallenge {
    public static void main (String[] args) {

        int highScorePosition = calculateHighScorePosition(1500);
        displayHighScorePosition("Tim", highScorePosition);

        highScorePosition = calculateHighScorePosition(1000);
        displayHighScorePosition("Bob", highScorePosition);

        highScorePosition = calculateHighScorePosition(999);
        displayHighScorePosition("Helen", highScorePosition);

        highScorePosition = calculateHighScorePosition(500);
        displayHighScorePosition("Tina", highScorePosition);

        highScorePosition = calculateHighScorePosition(499);
        displayHighScorePosition("Jessica", highScorePosition);

        highScorePosition = calculateHighScorePosition(100);
        displayHighScorePosition("Matt", highScorePosition);

        highScorePosition = calculateHighScorePosition(99);
        displayHighScorePosition("Bee", highScorePosition);

        highScorePosition = calculateHighScorePosition(25);
        displayHighScorePosition("miniks", highScorePosition);

    }

    public static void displayHighScorePosition(String playerName, int highScorePosition) {
        System.out.println(playerName + " managed to get into position " + highScorePosition + " on the high score list!!!");
    }

    public static int calculateHighScorePosition(int playerScore) {

        int pos = 4;
        if (playerScore >= 1000) {
            pos = 1;
        }
        else if (playerScore >= 500) {
            pos = 2;
        }
        else if (playerScore >= 100) {
            pos = 3;
        }
        return pos;
    }
}
