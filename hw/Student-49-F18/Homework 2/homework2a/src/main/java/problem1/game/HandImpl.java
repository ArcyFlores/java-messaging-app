package problem1.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import problem1.cards.Card;
import problem1.cards.comparators.CardsByRank;
import problem1.cards.comparators.CardsBySuit;

/**
 * @author aflores
 * This class is an implementaion of the Hand interface.
 * There can be multiple hands during a single game;
 */
public class HandImpl implements Hand {

  /**
   * A HandImpl's properties.
   */
  private List<Card> hand;
  private CardsByRank rankComparator;
  private CardsBySuit suitComparator;

  /**
   * Constructor for a HandImpl.
   */
  HandImpl() {
    hand = new ArrayList<Card>();
    rankComparator = new CardsByRank();
    suitComparator = new CardsBySuit();
  }

  /**
   * Method that returns a Hand as a List of cards.
   *
   * @return a List
   */
  public List<Card> showCards() {
    return this.hand;
  }

  /**
   * Method that adds a single card into our Hand.
   *
   * @param newCard the new card to add to our Hand
   */
  public void accept(Card newCard) {
    hand.add(newCard);
  }

  /**
   * Method that adds multiple cards into our current hand.
   *
   * @param newCards the cards to add to our hand
   */
  public void accept(List<Card> newCards) {
    hand.addAll(newCards);
  }

  /**
   * Method that returns the first card in the hand.
   *
   * @return the first card in our Hand
   * @throws IllegalAccessException if we try to pull from an empty deck
   */
  public Card pullCard() throws IllegalAccessException {
    if (!hand.isEmpty()) {
      return hand.remove(0);
    } else {
      throw new IllegalAccessException("No more cards in deck.");
    }
  }

  /**
   * Method checks whether the given card is actually in the current Hand.
   *
   * @param cardToCheck is the card we want to check against the current Hand
   * @return true if card is in deck, false otherwise
   */
  public Boolean hasCard(Card cardToCheck) {
    return hand.contains(cardToCheck);
  }

  /**
   * Method that sorts the hand by given sorting type.
   *
   * @param sortType one of bySuit, byRank, or both
   */
  public void sort(String sortType) {
    if(validString(sortType)){
      if (sortType.equalsIgnoreCase("byRank")) {
        Collections.sort(hand, rankComparator);
      } else if (sortType.equalsIgnoreCase("bySuit")) {
        Collections.sort(hand, suitComparator);
      } else {
        Collections.sort(hand, suitComparator);
        Collections.sort(hand, rankComparator);
      }
    } else {
      throw new IllegalArgumentException("Incorrect usage: input one of " +
              "<byRank, bySuit, both>");
    }

  }

  /**
   * Checks whether a given input is valid.
   * @param sortType string to validate
   * @return true if string is valid
   */
  private boolean validString(String sortType) {
    return sortType.equalsIgnoreCase("bySuit")
            || sortType.equalsIgnoreCase("byRank")
            || sortType.equalsIgnoreCase("both");
  }


  /**
   * Method the shuffles the current Hand.
   */
  public void shuffle() {
    Collections.shuffle(hand);
  }
}
