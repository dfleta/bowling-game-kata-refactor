package edu.gorillas;

public class ScoreCardB {

    // Properties of the class //

    // private final int STRIKE = 10;
    // private final int SPARE = 10;
    // private final int ZERO = 0;
    private String scoreCard = "";
    private int totalScore = 0;
    private final String pins = "-123456789";

    // Constructor //

    public ScoreCardB() {
        this.scoreCard = "";
    }

    public ScoreCardB(String scoreCard) {
        this.scoreCard = scoreCard;
    }

    // Setters and getters of the class //

    public String getScoreCard() {
        return this.scoreCard;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public String getPins() {
        return this.pins;
    }

    // Behaviours //

    public int pinValue(char pin) {
        return this.getPins().indexOf(pin);
    }

    public int computePins(char pin) {
        return this.pinValue(pin);
    }

    public boolean isNormalRoll(char roll) {
        return roll != 'X' && roll != '/';
    }

    public  boolean isStrike(Character strike) {
        return strike.equals('X');
    }

    public int computeStrike(char strike) {
        return strike == 'X'? SYMBOLS.STRIKE.getValue() 
                            : SYMBOLS.ZERO.getValue();
    }

    public  boolean isSpare(char spare) {
        return spare == '/';
    }

    public int computeSpare(char spare) {
        return spare == '/' ? SYMBOLS.SPARE.getValue()
                            : SYMBOLS.ZERO.getValue();
    }

    public void updateTotalScore(int score) {
        this.totalScore += score;
    }

    public int calculateScore(String scoreCard) {

        for (int roll = 0; roll < scoreCard.length(); roll++) {

            char pin = scoreCard.charAt(roll);

            try {
                // Check if the result of the actual roll are a regular pin.
                if (isNormalRoll(pin)) {

                    if (roll == 20 && scoreCard.charAt(19) == '/') {
                        break;
                    } else if (roll == scoreCard.length() - 2 && scoreCard.charAt(roll - 1) == 'X') {
                        break;
                    } updateTotalScore(computePins(pin));

                }
                // Here we check if the result of the actual roll is a Spare.
                if (isSpare(pin)) {

                    char nextResult = scoreCard.charAt(roll + 1);
                    char previousResult = scoreCard.charAt(roll - 1);
                    if (isStrike(nextResult)) {
                        updateTotalScore(computeSpare(pin) + computeStrike(nextResult) - computePins(previousResult));
                    } else {
                        updateTotalScore(computeSpare(pin) + computePins(nextResult) - computePins(previousResult));
                    }

                }
                // Here we check if the result of the actual roll is a Strike.
                else if (isStrike(pin)) {

                    char nextResult = scoreCard.charAt(roll + 1);
                    char nextResult2 = scoreCard.charAt(roll + 2);
                    if (isStrike(nextResult) && isStrike(nextResult2)) {
                        updateTotalScore(computeStrike(pin) * 3);
                    }
                    else if (isStrike(nextResult) && !isStrike(nextResult2)) {
                        updateTotalScore((computeStrike(pin) * 2) + computePins(nextResult2));
                    }
                    else if (isNormalRoll(nextResult) && isStrike(nextResult2)) {
                        updateTotalScore(computePins(nextResult) + computeStrike(pin) * 2);
                    }
                    else if (isNormalRoll(nextResult) && isSpare(nextResult2)) {
                        updateTotalScore(computeStrike(pin) + computeSpare(nextResult2));
                    }
                    else if (isNormalRoll(nextResult) && isNormalRoll(nextResult2)) {
                        updateTotalScore(computeStrike(pin) + computePins(nextResult) + computePins(nextResult2));
                    } else;

                } else;

            } catch (IndexOutOfBoundsException e) {
                return getTotalScore();
            }

        } 
        
        return getTotalScore();
    }
}

