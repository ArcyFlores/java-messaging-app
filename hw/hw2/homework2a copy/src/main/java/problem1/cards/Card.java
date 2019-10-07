package problem1.cards;

/**
 * A Card represents a single card in a deck that has a single Suit and Rank.
 * Ranks can be either spades, hearts, diamonds, or clubs; Suits range from 2..10, Ace, Jack,
 * Queen, and King.
 */
public interface Card {

  /**
   * Method to return a Card's rank.
   *
   * @return a rank
   */
  Rank getRank();

  /**
   * Method to return a Card's Suit.
   *
   * @return the Card's suit
   */
  Suit getSuit();
}
