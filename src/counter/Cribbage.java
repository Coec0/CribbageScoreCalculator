package counter;

import java.util.*;

public class Cribbage {

    public static int calculate(List<Card> hand, Card cut, boolean crib) {
        int totalScore = 0;
        totalScore += fifteens(hand, cut);
        totalScore += pairs(hand, cut);
        totalScore += runs(hand, cut);
        totalScore += flush(hand, cut, crib);
        totalScore += nob(hand, cut);
        return totalScore;
    }

    public static int fifteens(List<Card> hand, Card cut) {
        List<Card> mutableArray = new LinkedList<>(hand);
        mutableArray.add(cut);
        return fifteens(mutableArray);
    }

    private static int fifteens(List<Card> handAndCut) {
        int score = 0;
        Card tail = handAndCut.remove(handAndCut.size() - 1); //Remove last element to avoid array recalculation
        for (int i = 0; i < handAndCut.size(); i++) {
            int cardIScore = handAndCut.get(i).getRank().getScore();
            if (tail.getRank().getScore() + cardIScore == 15)
                score += 2;
            for (int j = i + 1; j < handAndCut.size(); j++) {
                int cardJScore = handAndCut.get(j).getRank().getScore();
                if (cardIScore + cardJScore + tail.getRank().getScore() == 15)
                    score += 2;
                for (int k = j + 1; k < handAndCut.size(); k++) {
                    int cardKScore = handAndCut.get(k).getRank().getScore();
                    if (cardIScore + cardJScore + cardKScore + tail.getRank().getScore() == 15)
                        score += 2;
                }
            }
        }

        if (handAndCut.size() > 1)
            return score + fifteens(handAndCut);
        return score;
    }

    public static int runs(List<Card> hand, Card cut) {
        List<Card> mutableArray = new LinkedList<>(hand);
        mutableArray.add(cut);
        return runs(mutableArray);
    }

    private static int runs(List<Card> handAndCut) {
        ArrayList<Integer> scores = new ArrayList<>(5);
        for (Card card : handAndCut)
            scores.add(card.getRank().getRunPos());
        Collections.sort(scores);
        List<Integer> longestArray = getLongestConsecutive(scores);
        int longest = longestArray.size();
        if (longest == 5)
            return 5;
        if (longest < 3)
            return 0;

        scores.retainAll(longestArray);

        int multiplier = 0;
        for (int nr : longestArray) {
            int freq = Collections.frequency(scores, nr);
            if (freq > 1)
                multiplier += freq;
        }
        if (multiplier == 0)
            multiplier = 1;
        return longest * multiplier;
    }

    private static List<Integer> getLongestConsecutive(ArrayList<Integer> scores) {
        Set<Integer> setWithoutDuplicates = new LinkedHashSet<>(scores);
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(setWithoutDuplicates);
        List<Integer> array = new LinkedList<>();
        List<Integer> maxArray = new LinkedList<>();
        int maxRun = 1;
        int run = 1;
        array.add(listWithoutDuplicates.get(0));
        for (int i = 0; i < listWithoutDuplicates.size() - 1; i++) {
            if (listWithoutDuplicates.get(i) + 1 == listWithoutDuplicates.get(i + 1)) {
                run++;
                array.add(listWithoutDuplicates.get(i + 1));
            } else {
                if (maxRun < run) {
                    maxRun = run;
                    maxArray = array;
                }
                run = 1;
                array = new ArrayList<>();
                array.add(listWithoutDuplicates.get(i));
            }
        }
        if (maxRun < run)
            maxArray = array;
        return maxArray;
    }

    public static int pairs(List<Card> hand, Card cut) {
        List<Card> mutableArray = new LinkedList<>(hand);
        mutableArray.add(cut);
        return pairs(mutableArray);
    }

    private static int pairs(List<Card> handAndCut) {
        int score = 0;
        Card tail = handAndCut.remove(handAndCut.size() - 1); //Remove last element to avoid array recalculation
        for (Card card : handAndCut) {
            if (tail.getRank() == card.getRank())
                score += 2;
        }
        if (handAndCut.size() > 1)
            return score + pairs(handAndCut);
        return score;
    }

    public static int flush(List<Card> hand, Card cut, boolean crib) {
        Suit suit = hand.get(0).getSuit();
        for (Card card : hand) {
            if (card.getSuit() != suit)
                return 0;
        }
        if (cut.getSuit() == suit)
            return 5;
        return crib ? 0 : 4;
    }

    public static int nob(List<Card> hand, Card cut) {
        for (Card card : hand)
            if (card.getRank() == Rank.JACK && card.getSuit() == cut.getSuit())
                return 1;
        return 0;
    }
}
