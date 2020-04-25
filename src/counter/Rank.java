package counter;

public enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    private int score, runPos;

    static {
        ACE.score = 1;
        TWO.score = 2;
        THREE.score = 3;
        FOUR.score = 4;
        FIVE.score = 5;
        SIX.score = 6;
        SEVEN.score = 7;
        EIGHT.score = 8;
        NINE.score = 9;
        TEN.score = 10;
        JACK.score = 10;
        QUEEN.score = 10;
        KING.score = 10;

        ACE.runPos = 1;
        TWO.runPos = 2;
        THREE.runPos = 3;
        FOUR.runPos = 4;
        FIVE.runPos = 5;
        SIX.runPos = 6;
        SEVEN.runPos = 7;
        EIGHT.runPos = 8;
        NINE.runPos = 9;
        TEN.runPos = 10;
        JACK.runPos = 11;
        QUEEN.runPos = 12;
        KING.runPos = 13;
    }

    public int getScore() {
        return score;
    }

    public int getRunPos() {
        return runPos;
    }

    public static Rank rankFromString(String rank) {
        rank = rank.toUpperCase();
        switch (rank) {
            case "A":
                return Rank.ACE;
            case "2":
                return Rank.TWO;
            case "3":
                return Rank.THREE;
            case "4":
                return Rank.FOUR;
            case "5":
                return Rank.FIVE;
            case "6":
                return Rank.SIX;
            case "7":
                return Rank.SEVEN;
            case "8":
                return Rank.EIGHT;
            case "9":
                return Rank.NINE;
            case "10":
            case "T":
                return Rank.TEN;
            case "J":
                return Rank.JACK;
            case "Q":
                return Rank.QUEEN;
            case "K":
                return Rank.KING;
            default:
                return null;
        }
    }
}
