package blackjack;

import cards.Card;
import game.Hand;

/**
 * A Dealer class that will serve as our Dealer in the BlackJack game.
 */
public class Dealer extends Player{

  Card faceUpCard;
  /**
   * Creates an object of type Player.
   */
  public Dealer(Hand hand) {
    super(hand);
    this.showHand();
  }


}
