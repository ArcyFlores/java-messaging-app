package problem1.game;


import problem1.decks.Deck;
import problem1.decks.DeckImpl;

/**
 * A GameImpl object implements the Game interface and contains all the methods dictated by
 * Game.
 */
public class GameImpl implements Game {

  private Deck gameDeck;
  private int numberOfHands;

  /**
   * Game constructor.
   */
  public GameImpl() {
    this.numberOfHands = 0;
    this.gameDeck = null;
  }

  /**
   * Method that creates a deck of specific types: Standard, Euchre, Pinochle, or a Vegas deck.
   *
   * @param deckType the type of deck to create
   */
  public void createDeck(String deckType) {
//    gameDeck = new DeckImpl(deckType);
  }

  /**
   *  @param deckType
   * @param numberOfDecks
   */
  public void createDeck(String deckType, int numberOfDecks) {

  }

  /**
   *
   * @param numberOfHands
   */
  public void setNumberOfHands(int numberOfHands) {

  }

  /**
   *
   */
  public void deal() {

  }
}
