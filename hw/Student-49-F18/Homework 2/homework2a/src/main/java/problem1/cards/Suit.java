package problem1.cards;

/**
 * The interface Suit.
 *
 * @author aflores
 * A Suit is a classification for a type of card.
 * It can be one of 4 types: hearts, spade, clubs, or diamonds.
 */
public interface Suit {

  /**
   * The name of the suit.
   *
   * @return a string representing the name of the suit
   */
  String getName();

  /**
   * A symbol representing either clubs, hearts, diamonds, or spades.
   *
   * @return a char symbol
   */
  char getSymbol();
}
