package problem2;

/**
 * The type Card.
 */
public class CardImpl implements Card {

  /**
   * The enum Rank.
   */
  public enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
    NINE, TEN, JACK, QUEEN, KING
  }

  /**
   * The enum Suit
   */
  public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}

  /**
   *
   */
  private Rank rank;
  private Suit suit;

  /**
   *
   * @param rank
   * @param suit
   */
  public CardImpl(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  /**
   * @return the rank
   */
  public Rank getRank() {
    return rank;
  }

  /**
   * @return the suit
   */
  public Suit getSuit() {
    return suit;
  }

  /**
   *
   * @return
   */
  public String toString() {
    return rank + " of " + suit;
  }

}
