package problem1.decks;

import java.util.ArrayList;
import java.util.List;

import problem1.cards.Card;
import problem1.cards.CardImpl;

/**
 * A Standard Deck implementation with 52 cards.
 */
public class StandardImpl extends DeckImpl implements Standard {

  /**
   * Constructor that creates a Deck of the given type.
   */
  public StandardImpl() {
    super();
  }

  /**
   * Method that will create a deck depending on the kind of deck wanted.
   *
   * @return a List of Cards of the given size.
   */
  protected List<Card> createDeck() {
    List<Card> standardDeck = new ArrayList<Card>();
    for (String rank : ranks) {
      for (String suit : suits) {
        standardDeck.add(new CardImpl(rank, suit));
      }
    }
    return standardDeck;
  }

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   *
   * @return an integer giving the size of the type of Deck instantiated.
   */
  public int officialSize() {
    return 52;
  }

}
