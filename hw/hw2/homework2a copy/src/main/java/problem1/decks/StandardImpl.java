package problem1.decks;

import java.util.ArrayList;
import java.util.List;

import problem1.cards.Card;
import problem1.cards.CardImpl;

/**
 * A Standard Deck implementation with 52 problem1.cards.
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


//  public boolean sort() {
//    return false;
//  }

//  /**
//   * Returns the current deck of problem1.cards as a List.
//   *
//   * @return a list of problem1.cards representing our Deck
//   */
//  public List<Card> getCards() {
//    return this.deck;
//  }

//  /**
//   * Method that sorts our Deck by the sorting type.
//   */
//  public void sort(String rankStyle) {
//    //Collections.sort(deck);
//  }

  /**
   *
   */
  public void cut(int cutPoint) {

  }

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   *
   * @return an integer giving the size of the type of Deck instantiated.
   */
  public int officialSize() {
    return 52;
  }

  public static void main(String[] args){
    StandardImpl newDeck = new StandardImpl();
    System.out.println("Standard Deck created: \n" + newDeck.createDeck());
    System.out.println("Deck size: " + newDeck.getCards().size() + "\n");
    newDeck.shuffle();
    System.out.println("Deck shuffled: \n" + newDeck.getCards() + "\n");
    newDeck.sort("byRank");
    System.out.println("Deck sorted: \n" + newDeck.getCards());
  }
}
