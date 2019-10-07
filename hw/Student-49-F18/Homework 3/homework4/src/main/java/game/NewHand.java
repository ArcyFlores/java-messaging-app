package game;


import cards.Card;
import cards.Rank;

/**
 * @author aflores
 * NewHand interface that extends the Hand Interface and contains the below methods.
 */
public interface NewHand extends Hand {

  /**
   * Method that determines whether a NewHand has the
   * specified card.
   *
   * @param cardToFind the card to search for in our NewHand
   * @return true if card is found, false otherwise
   */
  Boolean hasCard(Card cardToFind);

  /**
   * Method that determines how many times the specified card is in
   * the NewHand.
   *
   * @param cardToFind the card to search for
   * @return an integer representing the number of times the Card in in the hand
   */
  int occurrencesInHand(Card cardToFind);

  /**
   * Method that determines how many times the specified Rank is in
   * the NewHand.
   *
   * @param rankToFind the Rank to search for
   * @return the number of times the Rank is in the Hand
   */
  int occurrencesInHand(Rank rankToFind);
}
