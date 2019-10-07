package problem1.decks;

import java.util.List;

import problem1.cards.Card;

/**
 * The interface Deck.
 *
 * @author aflores
 * The Deck will be the container to hold our Cards within Games.
 * The Deck contains Cards of the following suit: clubs, diamonds, hearts, and spades.
 * Depending on the kind of Deck, the following ranks can be within our
 * Deck: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, and King.
 */
public interface Deck {


  /**
   * Method that shuffles a Deck.
   */
  void shuffle();

  /**
   * Method that sorts our Deck by the sorting type.
   *
   * @param rankStyle the rank style
   */
  void sort(String rankStyle);

  /**
   * Method that cuts a deck at the point given.
   *
   * @param cutPoint the cut point
   */
  void cut(int cutPoint);

  /**
   * Method that returns the "top" or first card of a DeckImpl.
   *
   * @return a Card
   */
  Card pullCard();

  /**
   * Method that returns true if a deck is empty, false otherwise.
   *
   * @return boolean indicating whether a deck is empty
   */
  boolean emptyDeck();

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   *
   * @return an integer giving the size of the type of Deck instantiated.
   */
  int officialSize();

  /**
   * Returns the deck of Cards.
   *
   * @return a list of cards representing the deck
   */
  List<Card> getCards();
}
