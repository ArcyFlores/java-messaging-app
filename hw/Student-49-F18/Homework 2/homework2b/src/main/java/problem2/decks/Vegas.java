package problem2.decks;

/**
 * A Vegas deck is a type of Deck that contains two or more standard decks - typically -
 * 6 - 8 standard decks.
 */
public interface Vegas extends Deck {
  /*
   * All Vegas methods inherited from a Deck and implemented in the Abstract class DeckImpl and
   * Vegas Impl.
   */

  /**
   * Method that will create a Vegas deck consisting of however many decks are required.
   *
   * @param typeOfDeck one of Euchre, Pinochle, or Standard
   * @param numDecks   the number of Standard decks to create our Vegas deck
   * @return a List of Cards.
   */
  void createDeck(String typeOfDeck, int numDecks);
}
