package game;

import cards.Card;

/**
 * The type Go fish.
 */
public class GoFish extends GameImpl {


  /**
   * Instantiates a new Go fish.
   *
   * @param numPlayers the num players
   * @throws IllegalArgumentException the illegal argument exception
   * @throws IllegalAccessException   the illegal access exception
   */
  public GoFish(int numPlayers) throws IllegalArgumentException, IllegalAccessException {
    super();
    createDeck("standard");
    setNumberOfHands(numPlayers);
    if (numPlayers > 7) {
      throw new IllegalArgumentException("Pick less players");
    } else if (numPlayers == 2 || numPlayers == 3) {
      this.deal(7);
    } else {
      this.deal(5);
    }
  }

  /**
   * Draw card.
   *
   * @return the card
   */
  public Card draw(){
    return this.getDeck().pullCard();
  }



}
