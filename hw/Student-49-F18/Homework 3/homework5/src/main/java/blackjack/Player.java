package blackjack;

import java.util.List;

import cards.Card;
import cards.CardImpl;
import game.Hand;
import game.HandImpl;


/**
 * A BlackJack player
 */
public class Player  {

  /**
   * Player attributes.
   */
  private Hand hand;

  /**
   * Creates an object of type Player.
   * @param hand the hand to pass in
   */
  public Player(Hand hand){
    this.hand = hand;
  }

  /**
   * Returns the current value of the Players hand.
   * @return the value of the current hand.
   */
  public int getHandValue(){
    int totalVal = 0;
    for(Card c : getCards()){
      if(c.getRank().getName().equals("ace")) totalVal += 1;
      else if(c.getRank().getName().equals("queen")
              || c.getRank().getName().equals("jack")
              || c.getRank().getName().equals("king")) {
        totalVal += 10;
      }
      else {
        totalVal += c.getRank().getPips();
      }
    }
    return totalVal;
  }

  /**
   * Add card to the players hand.
   * @param c the
   */
  public void hitMe(Card c){
    hand.accept(c);
  }

  /**
   * The players hand is bust if their totals are greater than 21.
   * @return boolean if bust, false otherwise
   */
  public boolean hasBustedHand() {
    return getHandValue() > 21;
  }

  /**
   * Return the players hand as a list of cards.
   * @return a list of cards
   */
  public List<Card> getCards() {
    return hand.showCards();
  }

  /**
   * Returns a string version of a Players hand.
   */
  public void showHand() {
    for(Card c : hand.showCards()) {
      System.out.print(c + ", ");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    Hand h = new HandImpl();
    h.accept(new CardImpl("4", "hearts"));
    h.accept(new CardImpl("queen", "hearts"));
    h.accept(new CardImpl("king", "spades"));
    Player p = new Player(h);
    System.out.println(p.getHandValue());
    System.out.println(p.getCards());
  }
}
