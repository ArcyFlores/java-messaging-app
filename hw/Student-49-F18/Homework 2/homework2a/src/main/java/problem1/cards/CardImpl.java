package problem1.cards;

/**
 * @author aflores
 * CardImpl is the class that implements the Card Interface and contains all the methods to
 * create an object of type Card and retrieve a Card's Suit and Rank.
 */
  public class CardImpl implements CardExtension {

  /**
   * A Card's properties.
   */
  private Rank rank;
  private Suit suit;

  /**
   * Constructor that creates a new card of the given Suit and Rank.
   *
   * @param rank the rank
   * @param suit the suit
   */
  public CardImpl(String rank, String suit) {
    this.rank = new RankImpl(rank);
    this.suit = new SuitImpl(suit);
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
   * Method that returns a string representation of a Card.
   *
   * @return a string
   */
  public String toString() {
    return "" + this.rank.getShortName() + " " + this.suit.getSymbol();
  }

}
