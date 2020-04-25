package counter;

public enum Suit {
    SPADES, CLUBS, HEARTS, DIAMONDS;

    public static Suit suitFromChar(char c) {
        char lowerC = Character.toLowerCase(c);
        switch (lowerC) {
            case 's':
                return SPADES;
            case 'c':
                return CLUBS;
            case 'h':
                return HEARTS;
            case 'd':
                return DIAMONDS;
            default:
                return null;
        }
    }

}