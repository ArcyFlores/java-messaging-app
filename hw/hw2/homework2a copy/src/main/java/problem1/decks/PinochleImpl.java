package problem1.decks;

import java.util.List;

import problem1.cards.Card;

public class PinochleImpl extends DeckImpl implements Pinochle {

  /**
   * Constructor for a Pinochle deck.
   */
  PinochleImpl(){
    super();
  }
  /**
   * Method that will create a deck depending on the kind of deck wanted.
   *
   * @return a List of Cards of the given size.
   */
  protected List<Card> createDeck() {
    return null;
  }

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   *
   * @return an integer giving the size of the type of Deck instantiated.
   */
  public int officialSize() {
    return 0;
  }
}
