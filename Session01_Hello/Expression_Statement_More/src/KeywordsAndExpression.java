public class KeywordsAndExpression {

    public static void main(String[] args) {
         //int int = 5; // variable name can't same with the keywords
        //int int2 = 5; //variable name is set correctly

        double kilometters = (100 *1.609344);

        int highScore = 50;

        if(highScore > 25) {
            highScore = 1000 +highScore;
        }

        int health = 100;

        if((health < 25) && (highScore >1000)) {
            highScore = highScore  - 1000;
        }

    }
}
