package problem2.decks;

/**
 * The type Deck factory.
 */
public class DeckFactory {

  /**
   * Declaring our DeckFactory to be null.
   */
  private static DeckFactory factory = null;

  /**
   * Private Deck Factory constructor for Singleton Design Pattern.
   */
  private DeckFactory() {

  }

  /**
   * Instance deck factory.
   *
   * @return the deck factory
   */
  public static DeckFactory instance() {
    if (factory == null) {
      factory = new DeckFactory();
    }
    return factory;
  }

  /**
   * Make euchre euchre.
   *
   * @return the euchre
   */
  public Euchre makeEuchre() {
    return new EuchreImpl();
  }

  /**
   * Make pinochle pinochle.
   *
   * @return the pinochle
   */
  public Pinochle makePinochle() {
    return new PinochleImpl();
  }

  /**
   * Make standard standard.
   *
   * @return the standard
   */
  public Standard makeStandard() {
    return new StandardImpl();
  }


  /**
   * Make vegas vegas.
   *
   * @return the vegas
   */
  public Vegas makeVegas() {
    return new VegasImpl();
  }

  /**
   * Make specified vegas vegas.
   *
   * @param deckType      the deck type
   * @param numberOfDecks the number of decks
   * @return vegas vegas
   */
  public Vegas makeSpecifiedVegas(String deckType, int numberOfDecks) {
    Vegas vegasDeck = new VegasImpl();
    vegasDeck.createDeck(deckType, numberOfDecks);
    return vegasDeck;
  }
}
