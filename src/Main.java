import counter.Card;
import counter.Cribbage;
import counter.Rank;
import counter.Suit;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Card> hand = new ArrayList<>(4);
        Card cut = null;

        Scanner sc = new Scanner(System.in);
        while (hand.size() < 4) {
            System.out.println("Please enter card number " + (hand.size() + 1) + " in your hand");
            System.out.println("The card should be entered as RS, where R is rank and S is suit, e.g KC for King of Clubs or 4S for the Four of Spades");
            Card card = getCard(sc.nextLine());
            if (card != null)
                hand.add(card);
            else
                System.out.println("Invalid card entered\n");
        }

        while (cut == null) {
            System.out.println("Please enter the cut card");
            System.out.println("The card should be entered as RS, where R is rank and S is suit, e.g KC for King of Clubs or 4S for the Four of Spades");
            Card card = getCard(sc.nextLine());
            if (card != null)
                cut = card;
            else
                System.out.println("Invalid card entered\n");
        }

        boolean crib = false;
        System.out.println("Are you counting the cards in the crib?");
        System.out.println("y/n");
        String answer = sc.nextLine();
        if (answer.equals("y") || answer.equals("yes"))
            crib = true;

        int totalPoints = Cribbage.calculate(hand, cut, crib);
        int fifteens = Cribbage.fifteens(hand, cut);
        int runs = Cribbage.runs(hand, cut);
        int pairs = Cribbage.pairs(hand, cut);
        int flush = Cribbage.flush(hand, cut, crib);
        int nob = Cribbage.nob(hand, cut);

        System.out.println("Your total points are " + totalPoints);
        System.out.println("The points you got from fifteens are " + fifteens);
        System.out.println("The points you got from runs are " + runs);
        System.out.println("The points you got from pairs are " + pairs);
        System.out.println("The points you got from flush are " + flush);
        System.out.println("The points you got from nob are " + nob);
    }


    private static Card getCard(String card) {
        String rankTyped;
        //Extract the rank
        if (card.length() == 2)
            rankTyped = card.substring(0, 1);
        else if (card.length() == 3) //If the user writes 10X
            rankTyped = card.substring(0, 2);
        else return null; //Badly typed input
        Rank rank = Rank.rankFromString(rankTyped);
        if (rank == null) return null; //Badly typed rank

        //Getting the suit
        char suitC = card.charAt(card.length() - 1);
        Suit suit = Suit.suitFromChar(suitC);
        if (suit == null) return null; //Badly typed suit

        return new Card(rank, suit);
    }
}
