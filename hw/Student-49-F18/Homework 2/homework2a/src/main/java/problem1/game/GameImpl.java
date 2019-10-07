package problem1.game;

import java.util.Map;
import java.util.TreeMap;

import problem1.decks.Deck;
import problem1.decks.EuchreImpl;
import problem1.decks.PinochleImpl;
import problem1.decks.StandardImpl;
import problem1.decks.Vegas;
import problem1.decks.VegasImpl;

/**
 * A GameImpl object implements the Game interface and contains all the methods dictated by
 * Game.
 */
public class GameImpl implements GameDeck {

  /**
   * Properties of a Game.
   */
  private Deck gameDeck;
  private int numberOfHands;
  private Map<Integer, Hand> hands;

  /**
   * Game constructor.
   */
  GameImpl() {
    this.numberOfHands = 0;
    this.hands = new TreeMap<Integer, Hand>();
  }

  /**
   * Method that creates a deck of specific types: Standard, Euchre, Pinochle, or a Vegas deck.
   *
   * @param deckType the type of deck to create
   * @throws IllegalArgumentException when incorrect input is entered
   */
  public void createDeck(String deckType) {
    if (validateDeckType(deckType)) {
      if (deckType.equalsIgnoreCase("standard")) {
        this.gameDeck = new StandardImpl();
      } else if (deckType.equalsIgnoreCase("euchre")) {
        this.gameDeck = new EuchreImpl();
      } else if (deckType.equalsIgnoreCase("pinochle")) {
        this.gameDeck = new PinochleImpl();
      } else if (deckType.equalsIgnoreCase("vegas")) {
        this.gameDeck = new VegasImpl();
      }
    } else {
      throw new IllegalArgumentException("Invalid use: Decks must be one of "
              + "< Standard, Euchre, Pinochle, or Vegas >. ");
    }
  }

  /**
   * Creates multiple decks of the given type.
   *
   * @param deckType      the type of deck to create
   * @param numberOfDecks the number of decks of the given type to create
   */
  public void createDeck(String deckType, int numberOfDecks) {
    Vegas vegasDeck = new VegasImpl();
    vegasDeck.createDeck(deckType, numberOfDecks);
    this.gameDeck = vegasDeck;
  }

  /**
   * Helper function to validate input string of a deck type.
   *
   * @param deckName the deck name string to validate
   * @return true if valid deck, false otherwise
   */
  private boolean validateDeckType(String deckName) {
    return deckName.equalsIgnoreCase("standard")
            || deckName.equalsIgnoreCase("euchre")
            || deckName.equalsIgnoreCase("vegas")
            || deckName.equalsIgnoreCase("pinochle");
  }

  /**
   * Method that sets the number of hands that will be set for the given game.
   *
   * @param numberOfHands the number of hands a Game has
   * @throws IllegalArgumentException if numberOfHands > gameDeck size
   */
  public void setNumberOfHands(int numberOfHands) {
    if (numberOfHands > gameDeck.getCards().size() || numberOfHands <= 0) {
      throw new IllegalArgumentException("Number of hands cannot be "
              + "greater than the size of the deck.");
    }
    this.numberOfHands = numberOfHands;
  }

  /**
   * Method that deals the cards for the given game.
   * For this assignment, it distributes 5 cards to each hand.
   *
   * @throws IllegalAccessException if we try to deal from a deck with no more cards.
   */
  public void deal() throws IllegalAccessException {
    int cardsPerHand = 4;
    gameDeck.shuffle();
    if (numberOfHands * cardsPerHand > gameDeck.getCards().size()) {
      throw new IndexOutOfBoundsException("Not enough cards in deck.");
    }
    if (gameDeck.pullCard() != null) {
      for (int i = 1; i <= numberOfHands; i++) {
        Hand h = new HandImpl();
        for (int j = 1; j <= cardsPerHand; j++) {
          h.accept(gameDeck.pullCard());
          this.hands.put(i, h);
        }
      }
    } else {
      throw new IllegalAccessException("No more cards in deck!");
    }
  }

  /**
   * Method for retrieving a game's deck of cards.
   *
   * @return this Game's Deck of Card
   */
  public Deck getDeck() {
    return this.gameDeck;
  }

  /**
   * Method to return the hands for this current game.
   */
  public Map<Integer, Hand> getHands() {
    return this.hands;
  }

}
