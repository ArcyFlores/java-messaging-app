package problem1.decks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import problem1.cards.Card;
import problem1.cards.comparators.CardsByRank;
import problem1.cards.comparators.CardsBySuit;

/**
 * The type Deck.
 *
 * @author aflores
 * A DeckImpl is an Abstract class that represents a Deck
 * and contains all the methods dictated by the Deck interface.
 */
public abstract class DeckImpl implements Deck {

  /**
   * All of the attribute the CardsImpl in a DeckImpl contain.
   */
  String[] suits = {"hearts", "clubs", "spades", "diamonds"};
  /**
   * The Ranks.
   */
  String[] ranks = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack",
          "queen", "king"};

  /**
   * A deck will be represented as a List of Cards.
   */
  protected List<Card> deck;
  private CardsByRank rankComparator = new CardsByRank();
  private CardsBySuit suitComparator = new CardsBySuit();

  /**
   * Constructor that creates a Deck of the given type.
   */
  DeckImpl() {
    this.deck = createDeck();
  }

  /**
   * Method that will create a deck depending on the kind of deck wanted.
   */
  protected abstract List<Card> createDeck();

  /**
   * Returns the current deck of cards as a List.
   *
   * @return a list of cards representing our Deck
   */
  public List<Card> getCards() {
    return this.deck;
  }

  /**
   * Method that shuffles a deck.
   */
  public void shuffle() {
    Collections.shuffle(deck);
  }

  /**
   * Method that sorts a deck depending on the type of deck desired.
   *
   * @param rankStyle the kind of method to rank, one of: byRank, bySuit, or both
   */
  public void sort(String rankStyle) {
    if (rankStyle.equalsIgnoreCase("byRank")) {
      Collections.sort(this.deck, rankComparator);
    } else if (rankStyle.equalsIgnoreCase("bySuit")) {
      Collections.sort(this.deck, suitComparator);
    } else {
      Collections.sort(this.deck, suitComparator);
      Collections.sort(this.deck, rankComparator);
    }
  }

  /**
   * Method that cuts a deck depending on the given integer.
   *
   * @param cutPoint the index in the deck to cut deck
   * @throws IllegalArgumentException if the cut point is invalid
   */
  public void cut(int cutPoint) {
    if(cutPoint < 0 || cutPoint >= deck.size()){
      throw new IllegalArgumentException("Cut point cannot be negative or "
              + "larger than the size of the deck.");
    }
    List<Card> cutDeck = new ArrayList<Card>();
    int counter = cutPoint;
    while(counter != 0){
      cutDeck.add(deck.remove(0));
      counter--;
    }
    deck.addAll(cutDeck);
  }

  /**
   * Method that returns the first Card in a Deck.
   *
   * @return the card located at the first index
   */
  public Card pullCard() {
    return deck.remove(0);
  }

  /**
   * Method that checks whether a deck is empty.
   *
   * @return true if empty, false otherwise
   */
  public boolean emptyDeck() {
    return deck.isEmpty();
  }

}
