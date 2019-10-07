package problem2.game;

/**
 * Game interface that represents a current game. It contains methods for creating decks,
 * and setting the number of hands for the game.
 */
public interface Game {

  /**
   * Method that creates a deck of specific types: Standard, Euchre, Pinochle, or a Vegas deck.
   *
   * @param deckType the type of deck to create
   */
  void createDeck(String deckType);

  /**
   * Creates multiple decks of the given type.
   *
   * @param deckType      the type of deck to create
   * @param numberOfDecks the number of decks of the given type to create
   */
  void createDeck(String deckType, int numberOfDecks);

  /**
   * Method that sets the number of hands that will be set for the given game.
   *
   * @param numberOfHands the number of hands a Game has
   */
  void setNumberOfHands(int numberOfHands);

  /**
   * Method that deals the cards for the given game.
   */
  void deal() throws IllegalAccessException;

}
