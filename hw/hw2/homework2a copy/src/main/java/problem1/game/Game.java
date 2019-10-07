package problem1.game;

/**
 * Game interface that represents a current problem1.game. It contains methods for creating problem1.decks,
 * and setting the number of hands for the problem1.game.
 */
public interface Game {

  /**
   * Method that creates a deck of specific types: Standard, Euchre, Pinochle, or a Vegas deck.
   *
   * @param deckType the type of deck to create
   */
  void createDeck(String deckType);

  /**
   * Creates multiple problem1.decks of the given type.
   *
   * @param deckType      the type of deck to create
   * @param numberOfDecks the number of problem1.decks of the given type to create
   */
  void createDeck(String deckType, int numberOfDecks);

  /**
   * Method that sets the number of hands that will be set for the given problem1.game.
   */
  void setNumberOfHands(int numberOfHands);

  /**
   * Method that deals the problem1.cards for the given problem1.game.
   */
  void deal();

}
