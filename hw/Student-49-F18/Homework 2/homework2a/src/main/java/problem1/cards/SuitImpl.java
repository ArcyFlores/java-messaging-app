package problem1.cards;

/**
 * The type Suit.
 *
 * @author aflores
 *
 * The SuitImpl is the class implementing the Suit interface.
 * It contains all the methods dictated by Suit.
 */
public class SuitImpl implements Suit {

  /**
   * All possible Suit types a SuitImpl can be.
   */
  private static final String HEARTS = "hearts";
  private static final String CLUBS = "clubs";
  private static final String DIAMONDS = "diamonds";
  private static final String SPADES = "spades";

  /**
   * Properties that a SuitImpl contains.
   */
  private String name;
  private char symbol;

  /**
   * SuitImpl constructor that creates an object representing a Card's Suit.
   *
   * @param suitName the name of the Suit.
   */
  SuitImpl(String suitName) {
    if (validateSuit(suitName)) {
      this.name = suitName.toLowerCase();
      this.symbol = createSuiteSymbol(suitName);
    } else {
      throw new IllegalArgumentException("Invalid suit entered: must be one of "
              + "clubs, diamonds, hearts, or spades");
    }
  }

  /**
   * Method to validate that valid string has been inserted.
   *
   * @return a boolean if valid string, false otherwise.
   */
  private boolean validateSuit(String suit) {
    return HEARTS.equalsIgnoreCase(suit) || DIAMONDS.equalsIgnoreCase(suit)
            || SPADES.equalsIgnoreCase(suit) || CLUBS.equalsIgnoreCase(suit);
  }

  /**
   * Helper function to return a Suit's symbol.
   *
   * @return a char representing a suit's symbol
   */
  private char createSuiteSymbol(String suit) {
    suit = suit.toLowerCase();
    if (suit.equals(HEARTS)) symbol = '\u2665';
    else if (suit.equals(SPADES)) {
      symbol = '\u2660';
    } else if (suit.equals(DIAMONDS)) {
      symbol = '\u2666';
    } else if (suit.equals(CLUBS)) symbol = '\u2663';
    return symbol;
  }

  /**
   * The name of the suit.
   *
   * @return a string representing the name of the suit
   */
  public String getName() {
    return this.name.toLowerCase();
  }

  /**
   * A symbol representing either clubs, hearts, diamonds, or spades.
   *
   * @return a char symbol
   */
  public char getSymbol() {
    return this.symbol;
  }

}
