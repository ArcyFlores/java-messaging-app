package problem1.decks;

import java.util.List;

import problem1.cards.Card;

/**
 * Method that
 */
public class VegasImpl extends DeckImpl implements Vegas {

  /**
   * A Vegas deck;
   */
  List<Card> vegasDeck;

  /**
   * Constructor for a Vegas deck.
   */
  VegasImpl() {
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
    return vegasDeck.size();
  }
}
