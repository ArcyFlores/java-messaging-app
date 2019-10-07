package problem1.decks;

import java.util.ArrayList;
import java.util.List;

import problem1.cards.Card;

/**
 * @author aflores
 *
 * Class representing a Vegas deck. In this Vegas Deck implementation,
 * a Vegas deck is made up of 6 Standard decks.
 */
public class VegasImpl extends DeckImpl implements Vegas {

  /**
   * A Vegas deck's properties.
   */
  private int size;

  /**
   * Constructor for a Vegas deck.
   */
  public VegasImpl() {
    super();
    this.size = deck.size();
  }

  /**
   * Method that will create a Vegas deck consisting of 6 Standard decks.
   *
   * @return a List of Cards.
   */
  protected List<Card> createDeck() {
    List<Card> vegasDeck = new ArrayList<Card>();
    int counter = 6;
    while (counter != 0) {
      vegasDeck.addAll(new StandardImpl().getCards());
      counter--;
    }
    return vegasDeck;
  }

  /**
   * Method that will create a Vegas deck consisting of however many decks are required and
   * of the wanted deck type.
   *
   * @param typeOfDeck the type of deck our Vegas deck will be made up of
   * @param numDecks   the number of Standard decks to create our Vegas deck
   */
  public void createDeck(String typeOfDeck, int numDecks) {
    if (validDeck(typeOfDeck.toLowerCase())) {
      this.deck = makeDeck(typeOfDeck, numDecks);
    } else {
      throw new IllegalArgumentException("Invalid deck type, enter one of: Euchre, "
              + "Pinochle, or Standard");
    }
  }

  /**
   * Helper method to createDeck that creates a Vegas Deck consisting of the wanted
   * deck and set number of that deck.
   *
   * @param typeOfDeck the type of deck
   * @param numDecks   the number of decks od the above type
   * @return a List of Cards
   */
  private List<Card> makeDeck(String typeOfDeck, int numDecks) {
    int counter = numDecks;
    List<Card> vegasDeck = new ArrayList<Card>();
    if (typeOfDeck.equalsIgnoreCase("euchre")) {
      while (counter != 0) {
        vegasDeck.addAll(new EuchreImpl().getCards());
        counter--;
      }
    } else if (typeOfDeck.equalsIgnoreCase("pinochle")) {
      while (counter != 0) {
        vegasDeck.addAll(new PinochleImpl().getCards());
        counter--;
      }
    } else {
      while (counter != 0) {
        vegasDeck.addAll(new StandardImpl().getCards());
        counter--;
      }
    }
    return vegasDeck;
  }


  /**
   * Function to validate that the input string is indeed an existing deck type.
   *
   * @param typeOfDeck String to validate
   * @return true if valid deck, false otherwise
   */
  private boolean validDeck(String typeOfDeck) {
    return typeOfDeck.equalsIgnoreCase("euchre")
            || typeOfDeck.equalsIgnoreCase("pinochle")
            || typeOfDeck.equalsIgnoreCase("standard");
  }

  /**
   * Method returning the size of the official deck, depending on the type of deck.
   *
   * @return an integer giving the size of the type of Deck instantiated.
   */
  public int officialSize() {
    return this.size;
  }

}
