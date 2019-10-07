package decks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cards.Card;
import cards.CardImpl;
import cards.comparators.CardsByRank;
import cards.comparators.CardsBySuit;


/**
 * The type Euchre.
 *
 * @author aflores
 * A Euchre Deck.
 */
public class EuchreImpl extends DeckImpl implements Euchre {

  /**
   * Constructor that creates a Deck of the given type.
   */
  public EuchreImpl() {
    super();
  }

  /**
   * Method that will create a Euchre deck.
   * @return a deck of Cards as a list
   */
  protected List<Card> createDeck() {
    List<Card> euchreDeck = new ArrayList<Card>();
    for (int i = 8; i < ranks.length; i++) {
      for (String suit : suits) {
        euchreDeck.add(new CardImpl(ranks[i], suit));
      }
    }
    for (String suit : suits) {
      euchreDeck.add(new CardImpl("ace", suit));
    }
    Collections.sort(euchreDeck, new CardsBySuit());
    Collections.sort(euchreDeck, new CardsByRank());
    return euchreDeck;
  }

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   *
   * @return an integer giving the size of the type of Deck instantiated.
   */
  public int officialSize() {
    return 24;
  }

}
