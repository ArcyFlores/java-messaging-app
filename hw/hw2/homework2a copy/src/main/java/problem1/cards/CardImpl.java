package problem1.cards;

/**
 * CardImpl is the class that implements the Card Interface and contains all the methods to
 * create an object of type Card and retrieve a Card's Suit and Rank.
 */
public class CardImpl implements Card {

  /**
   * A Card's properties.
   */
  private Rank rank;
  private Suit suit;
  private int value;

  /**
   * Constructor that creates a new card of the given Suit and Rank.
   */
  public CardImpl(String rank, String suit) {
    this.rank = new RankImpl(rank);
    this.suit = new SuitImpl(suit);
    this.value = setValue();
  }

  /**
   * Helper function to determine a card's rank for ease of sorting both
   * face and normal values, different from a card's pip.
   *
   * @return an integer representing a problem1.cards order
   */
  private int setValue() {
    switch (this.rank.getName()) {
      case "jack":
        return 11;
      case "queen":
        return 12;
      case "king":
        return 13;
      default:
        return this.getRank().getPips();
    }
  }

  /**
   * Method to return a Card's rank.
   *
   * @return a rank
   */
  public Rank getRank() {
    return this.rank;
  }

  /**
   * Method to return a Card's Suit.
   *
   * @return the Card's suit
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Returns a card's ordering value.
   *
   * @return an integer representing a card's order
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Method that returns a string representation of a Card.
   * TODO: Remove this method.
   *
   * @return a string
   */
  public String toString() {
    return "" + this.rank.getShortName() + " " + this.suit.getSymbol();
  }

}
