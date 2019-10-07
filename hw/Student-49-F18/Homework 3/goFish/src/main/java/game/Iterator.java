package game;

import cards.Card;

/**
 * @author aflores
 * Custom Iterator interface.
 */
public interface Iterator<T> {
  /**
   * Initializes a value for iterator to use.
   */
  void first();     // set to first

  /**
   * Method that retrieves the next item in the Hand.
   *
   * @return the next card in the hand
   */
  void next();      // advance

  /**
   * Method that returns true when our handCount is the same size as
   * our newHand, i.e. we finished iterating through a hand.
   *
   * @return true if done, false otherwise
   */
  boolean isDone(); // is done

  /**
   * Determines whether a hand has another item in it.
   *
   * @return boolean if a hand has a next item in it, false otherwise
   */
  boolean hasNext();

  /**
   * Get the current card at the given index of handCount.
   *
   * @return the card at index handCount
   */
  T current(); // get current
}
