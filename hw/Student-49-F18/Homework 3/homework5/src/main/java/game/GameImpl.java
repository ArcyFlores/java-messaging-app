package game;

import java.util.Map;
import java.util.TreeMap;

import decks.Deck;
import decks.DeckFactory;
import decks.EuchreImpl;
import decks.PinochleImpl;
import decks.StandardImpl;
import decks.Vegas;
import decks.VegasImpl;

/**
 * A GameImpl object implements the Game interface and contains all the methods dictated by
 * Game.
 */
public class GameImpl implements GameDeck  {

  /**
   * Properties of a Game.
   */
  private DeckFactory deckFactory;
  private Deck gameDeck;
  private int numberOfHands;
  private Map<Integer, Hand> hands;

  /**
   * Game constructor using a factory.
   */
  public GameImpl() {
    deckFactory = DeckFactory.instance();
    this.numberOfHands = 0;
    this.hands = new TreeMap<Integer, Hand>();
  }

  /**
   * Method that creates a deck of specific types: Standard, Euchre, Pinochle, or a Vegas deck.
   *
   * @param deckType the type of deck to create
   */
  public void createDeck(String deckType) {
    if (validateDeckType(deckType)) {
      if (deckType.equalsIgnoreCase("standard")) {
        this.gameDeck = deckFactory.makeStandard();
      } else if (deckType.equalsIgnoreCase("euchre")) {
        this.gameDeck = deckFactory.makeEuchre();
      } else if (deckType.equalsIgnoreCase("pinochle")) {
        this.gameDeck = deckFactory.makePinochle();
      } else if (deckType.equalsIgnoreCase("vegas")) {
        this.gameDeck = deckFactory.makeVegas();
      }
    } else {
      throw new IllegalArgumentException("Invalid use: Decks must be one of "
              + "< Standard, Euchre, Pinochle, or Vegas >. ");
    }
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
   * Creates multiple decks of the given type.
   *
   * @param deckType      the type of deck to create
   * @param numberOfDecks the number of decks of the given type to create
   */
  public void createDeck(String deckType, int numberOfDecks) {
    this.gameDeck = deckFactory.makeSpecifiedVegas(deckType, numberOfDecks);
  }

  /**
   * Method that sets the number of hands that will be set for the given game.
   *
   * @param numberOfHands the number of hands a Game has
   * @throws IllegalArgumentException if numberOfHands > gameDeck size
   */
  public void setNumberOfHands(int numberOfHands) {
    if (numberOfHands > gameDeck.getCards().size()) {
      throw new IllegalArgumentException("Number of hands cannot be "
              + "greater than the size of the deck.");
    }
    this.numberOfHands = numberOfHands;
  }

  /**
   * Method that deals 4 the cards to each hand in the game.
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
        for (int j = 1; j <= 4; j++) {
          h.accept(gameDeck.pullCard());
          hands.put(i, h);
        }
      }
    } else {
      throw new IllegalAccessException("No more cards in deck!");
    }
  }


  /**
   * Method that deals specified amount of cards for the given game.
   *
   * @throws IllegalAccessException if we try to deal from a deck with no more cards.
   */
  public void deal(int cardsPerHand) throws IllegalAccessException {
    gameDeck.shuffle();
    if (numberOfHands * cardsPerHand > gameDeck.getCards().size()) {
      throw new IndexOutOfBoundsException("Not enough cards in deck.");
    }
    if (gameDeck.pullCard() != null) {
      for (int i = 1; i <= numberOfHands; i++) {
        Hand h = new HandImpl();
        for (int j = 1; j <= cardsPerHand; j++) {
          h.accept(gameDeck.pullCard());
          hands.put(i, h);
        }
      }
    } else {
      throw new IllegalAccessException("No more cards in deck!");
    }
  }
  /**
   * Method for retrieiving a game's deck of cards.
   *
   * @return this Game's Deck of Card
   */
  public Deck getDeck() {
    return this.gameDeck;
  }

  /**
   * Method to return the hands for this current game.
   */
  public Map<Integer, Hand> getHands(){
    return this.hands;
  }

}
