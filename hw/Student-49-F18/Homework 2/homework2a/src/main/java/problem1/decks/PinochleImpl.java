package problem1.decks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import problem1.cards.Card;
import problem1.cards.CardImpl;
import problem1.cards.comparators.CardsByRank;
import problem1.cards.comparators.CardsBySuit;

/**
 * @author aflores
 * The type Pinochle. The PinochleImpl implements the Pinochle interface and the
 */
public class PinochleImpl extends DeckImpl implements Pinochle {

  /**
   * Constructor for a Pinochle deck.
   */
  public PinochleImpl() {
    super();
  }

  /**
   * Method that will create a deck depending on the kind of deck wanted.
   *
   * @return a List of Cards of the given size.
   */
  protected List<Card> createDeck() {
    List<Card> pinochleDeck1 = makePinochle();
    List<Card> pinochleDeck2 = makePinochle();
    pinochleDeck1.addAll(pinochleDeck2);
    Collections.sort(pinochleDeck1, new CardsBySuit());
    Collections.sort(pinochleDeck1, new CardsByRank());
    return pinochleDeck1;
  }

  /**
   * Creates a Pinochle deck.
   *
   * @return a Pinochle deck
   */
  private List<Card> makePinochle() {
    List<Card> euchreDeck = new ArrayList<Card>();
    for (int i = 8; i < ranks.length; i++) {
      for (String suit : suits) {
        euchreDeck.add(new CardImpl(ranks[i], suit));
      }
    }
    for (String suit : suits) {
      euchreDeck.add(new CardImpl("ace", suit));
    }
    return euchreDeck;
  }

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   *
   * @return an integer giving the size of the type of Deck instantiated.
   */
  public int officialSize() {
    return 48;
  }

}
