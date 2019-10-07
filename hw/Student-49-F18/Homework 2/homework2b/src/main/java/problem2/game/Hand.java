package problem2.game;

import java.util.List;

import problem2.cards.Card;

/**
 * A Hand is a List of Cards, there can be multiple Hands in a single game.
 */
public interface Hand {

  /**
   * Method that returns a Hand as a List of cards.
   *
   * @return a List
   */
  List<Card> showCards();

  /**
   * Method that adds a single card into our Hand.
   *
   * @param newCard the new card to add to our Hand
   */
  void accept(Card newCard);

  /**
   * Method that adds multiple cards into our current hand.
   *
   * @param newCards the cards to add to our hand
   */
  void accept(List<Card> newCards);

  /**
   * Method that returns the first card in the hand.
   *
   * @return the first card in our Hand
   */
  Card pullCard();

  /**
   * Method checks whether the given card is actually in the current Hand.
   *
   * @param cardToCheck is the card we want to check against the current Hand
   * @return true if card is in deck, false otherwise
   */
  Boolean hasCard(Card cardToCheck);

  /**
   * Method that sorts the hand by given sorting type.
   *
   * @param sortType one of bySuit, byRank, or both
   */
  void sort(String sortType);

  /**
   * Method the shuffles the current Hand.
   */
  void shuffle();

}
