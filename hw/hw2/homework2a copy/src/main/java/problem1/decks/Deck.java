package problem1.decks;

import java.util.List;

import problem1.cards.Card;

/**
 * The Deck will be the container to hold our Cards within Games. The Deck contains Cards
 * of the following suit: clubs, diamonds, hearts, and spades. Depending on the kind of Deck,
 * the following ranks can be within our Deck: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen,
 * and King.
 */
public interface Deck {

  /**
   * Returns the current deck of problem1.cards as a List.
   * @return a list of problem1.cards representing our Deck
   */
  List<Card> getCards();

  /**
   * Method that shuffles a Deck.
   */
  void shuffle();

  /**
   * Method that sorts our Deck by the sorting type.
   * @param rankStyle, 'byRank', 'bySuit', or by 'both' to rank by both Rank and Suit
   */
  void sort(String rankStyle);

  /**
   * Method that cuts a deck at the point given.
   */
  void cut(int cutPoint);

  /**
   * Method that returns the "top" or first card of a DeckImpl.
   * @return a Card
   */
  Card pullCard();

  /**
   *
   * @return
   */
  boolean emptyDeck();

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   * @return an integer giving the size of the type of Deck instantiated.
   */
  int officialSize();

}
