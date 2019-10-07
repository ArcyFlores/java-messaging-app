package problem2.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import problem2.cards.Card;
import problem2.cards.CardImpl;
import problem2.cards.comparators.CardsByRank;
import problem2.cards.comparators.CardsBySuit;
import problem2.decks.Deck;
import problem2.decks.StandardImpl;

/**
 * @author aflores
 * This class is an implementaion of the Hand interface.
 * There can be multiple hands during a single game;
 */
public class HandImpl implements Hand {

  /**
   * A HandImpl's properties.
   */
  private List<Card> hand;
  private CardsByRank rankComparator;
  private CardsBySuit suitComparator;

  /**
   * Constructor for a HandImpl.
   */
  protected HandImpl(){
    hand = new ArrayList<Card>();
    rankComparator = new CardsByRank();
    suitComparator = new CardsBySuit();
  }
  /**
   * Method that returns a Hand as a List of cards.
   *
   * @return a List
   */
  public List<Card> showCards() {
    return this.hand;
  }

  /**
   * Method that adds a single card into our Hand.
   *
   * @param newCard the new card to add to our Hand
   */
  public void accept(Card newCard) {
    hand.add(newCard);
  }

  /**
   * Method that adds multiple cards into our current hand.
   *
   * @param newCards the cards to add to our hand
   */
  public void accept(List<Card> newCards) {
    hand.addAll(newCards);
  }

  /**
   * Method that returns the first card in the hand.
   *
   * @return the first card in our Hand
   * @throws IndexOutOfBoundsException if we try to pull from an empty deck
   */
  public Card pullCard() throws IndexOutOfBoundsException {
    if(!hand.isEmpty()){
      return hand.remove(0);
    } else {
      throw new IndexOutOfBoundsException("No more cards in deck.");
    }
  }

  /**
   * Method checks whether the given card is actually in the current Hand.
   *
   * @param cardToCheck is the card we want to check against the current Hand
   * @return true if card is in deck, false otherwise
   */
  public Boolean hasCard(Card cardToCheck) {
    return hand.contains(cardToCheck);
  }

  /**
   * Method that sorts the hand by given sorting type.
   *
   * @param sortType one of bySuit, byRank, or both
   */
  public void sort(String sortType) {
    if (sortType.equalsIgnoreCase("byRank")) {
      Collections.sort(hand, rankComparator);
    } else if(sortType.equalsIgnoreCase("bySuit")){
      Collections.sort(hand, suitComparator);
    } else {
      Collections.sort(hand, suitComparator);
      Collections.sort(hand, rankComparator);
    }
  }

  /**
   * Method the shuffles the current Hand.
   */
  public void shuffle() {
    Collections.shuffle(hand);
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Deck deck = new StandardImpl();
    Hand newHand = new HandImpl();
    for(int i = 0; i < 3; i++){
      newHand.accept(deck.pullCard());
    }
    System.out.println("Deck size: " + deck.getCards().size());

    Card c1 = new CardImpl("9", "hearts");
    Card c2 = new CardImpl("10", "hearts");
    newHand.accept(c1);
    newHand.accept(c2);
    newHand.shuffle();
    System.out.println(newHand.showCards());

    newHand.sort("bySuit");
    System.out.println(newHand.showCards());
  }
}
