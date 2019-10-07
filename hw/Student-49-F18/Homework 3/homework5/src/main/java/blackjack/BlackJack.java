package blackjack;

import java.util.ArrayList;
import java.util.List;

import game.GameImpl;
import game.Hand;
import game.HandImpl;

/**
 * The type Black jack.
 */
public class BlackJack extends GameImpl {
  /**
   * The Players.
   */
  List<Player> players = new ArrayList<>();
  /**
   * The Dealer.
   */
  Dealer dealer;

  /**
   * The BlackJack Game
   *
   * @param numPlayers the num players
   * @throws IllegalAccessException the illegal access exception
   */
  public BlackJack(int numPlayers) throws IllegalAccessException {
    super();
    createDeck("standard", 6);
    setNumberOfHands(numPlayers);
    deal(2);
    this.players = dealCards();
    dealer = new Dealer(dealerCards());
  }

  /**
   *
   * @return
   */
  private Hand dealerCards(){
    Hand dealersHand = new HandImpl();
    dealersHand.accept(this.getDeck().pullCard());
    dealersHand.accept(this.getDeck().pullCard());
    return dealersHand;
  }

  /**
   * Creates a list of players and deals cards to
   * each Player.
   * @return a list of Players
   */
  private List<Player> dealCards() {
    int numHands = this.getHands().size();
    int i = 0;
    while(numHands > 0 ) {
      this.players.add(new Player(this.getHands().get(i)));
      i++;
      numHands--;
    }
    return this.players;
  }

  /**
   * Return the list of players.
   *
   * @return the list of players for the game
   */
  public List<Player> getPlayers(){
    return this.players;
  }


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws IllegalAccessException the illegal access exception
   */
  public static void main(String[] args) throws IllegalAccessException {
    BlackJack game = new BlackJack(3);
    System.out.println(game.getDeck().getCards().size());
    System.out.println(game.getHands().get(1).showCards());
    System.out.println(game.getHands().get(2).showCards());
    System.out.println(game.getHands().get(3).showCards());
    System.out.println(game.getDeck().getCards());
    System.out.println(game.getPlayers().get(2).getCards());
    System.out.println(game.dealer.getCards());
  }

}
