package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.Deck;

public class DeckImpl implements Deck {

    private List<Card> deck = new ArrayList<Card>();
    private RankComparator rankComparator = new RankComparator();
    private SuitComparator suitComparator = new SuitComparator();

    public DeckImpl() {
      for (CardImpl.Suit suit : CardImpl.Suit.values()) {
        for (CardImpl.Rank rank : CardImpl.Rank.values()) {
          deck.add(new CardImpl(rank, suit));
        }
      }
    }

    public void shuffle() {
      Collections.shuffle(deck);
    }

    public void sortByRank() {
      Collections.sort(deck, rankComparator);
    }

    public void sortBySuit() {
      Collections.sort(deck, suitComparator);
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (Card card : deck) {
        sb.append(card + "\n");
      }
      return sb.toString();
    }

    class RankComparator implements Comparator<Card> {
      public int compare(Card card1, Card card2) {
        if (card1.getRank() == card2.getRank()) {
          return card1.getSuit().compareTo(card2.getSuit());
        }
        return card1.getRank().compareTo(card2.getRank());
      }
    }

    class SuitComparator implements Comparator<Card> {
      public int compare(Card card1, Card card2) {
        if (card1.getSuit() == card2.getSuit()) {
          return card1.getRank().compareTo(card2.getRank());
        }
        return card1.getSuit().compareTo(card2.getSuit());
      }
    }

    public static void main(String[] args) {
      DeckImpl myDeck = new DeckImpl();
      System.out.println("New deck...");
      System.out.println(myDeck);
      myDeck.shuffle();
      System.out.println("Shuffled...");
      System.out.println(myDeck);
      myDeck.sortByRank();
      System.out.println("Sorted by rank...");
      System.out.println(myDeck);
      myDeck.sortBySuit();
      System.out.println("Sorted by suit...");
      System.out.println(myDeck);
    }

}
