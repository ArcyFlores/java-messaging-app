package problem2;


import problem2.CardImpl.Rank;
import problem2.CardImpl.Suit;

/**
 * The interface Card.
 */
public interface Card {
  Rank getRank();

  Suit getSuit();
}
