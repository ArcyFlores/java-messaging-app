package game;

import java.util.List;

import cards.Card;
import cards.Rank;
import cards.comparators.CardsByRank;
import cards.comparators.CardsBySuit;

/**
 * @author aflores
 * A NewHandImpl class.
 */
public class NewHandImpl extends HandImpl implements NewHand {


  /**
   * A NewHandImpl's properties.
   */
  private List<Card> newHand;
  private CardsByRank rankComparator;
  private CardsBySuit suitComparator;

  /**
   * A new Type of Hand that has an Iterator.
   */
  public NewHandImpl() {
    super();
    this.rankComparator = new CardsByRank();
    this.suitComparator = new CardsBySuit();
  }

  /**
   * Method that determines whether a NewHand has the
   * specified card.
   *
   * @param cardToFind the card to search for in our NewHand
   * @return true if card is found, false otherwise
   */
  public Boolean hasCard(Card cardToFind) {
    Iterator<Card> it = this.iterator();
    for(it.first(); !it.isDone(); it.next()){
      if(it.current().getRank().getName().equals(cardToFind.getRank().getName())
              && it.current().getSuit().getName().equals(cardToFind.getSuit().getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method that determines how many times the specified card is in
   * the NewHand.
   *
   * @param cardToFind the card to search for
   * @return an integer representing the number of times the Card in in
   * the hand
   */
  public int occurrencesInHand(Card cardToFind) {
    int cardOccurrences = 0;
    Iterator<Card> it = this.iterator();
    for(it.first(); !it.isDone(); it.next()){
      if(it.current().getRank().getName().equals(cardToFind.getRank().getName())
              && it.current().getSuit().getName().equals(cardToFind.getSuit().getName())) {
        cardOccurrences++;
      }
    }
      return cardOccurrences;
    }


  /**
   * Method that determines how many times the specified Rank is in
   * the NewHand.
   *
   * @param rankToFind the Rank to search for
   * @return the number of times the Rank is in the Hand
   */
  public int occurrencesInHand(Rank rankToFind) {
    int rankOccurrences = 0;
    Iterator<Card> it = this.iterator();
    for(it.first(); !it.isDone(); it.next()) {
      if (it.current().getRank().getName().equals(rankToFind.getName())) {
        rankOccurrences++;
      }
    }
    return rankOccurrences;
  }

  /**
   * Method that returns a new Iterator for the NewHandImpl.
   *
   * @return a new instance of the hand iterator.
   */
  public Iterator<Card> iterator() {
    return new NewHandIterator(this);
  }

  /**
   * A Iterator for the NewHandImpl Class.
   */
  private class NewHandIterator implements Iterator<Card> {

    /**
     * A NewHandIterator's attributes.
     */
    private List<Card> newHand;
    private int handCount;

    /**
     * Constructor for creating a newHand, it takes in a NewHand object
     * and keeps track of a handCount.
     *
     * @param h an object of type NewHand
     */
    public NewHandIterator(NewHandImpl h) {
      this.newHand = h.showCards();
      this.handCount = 0;
    }

    /**
     * Determines whether a hand has another item in it.
     * @return boolean if a hand has a next item in it, false otherwise
     */
    public boolean hasNext() {
      return handCount < newHand.size();
    }

    /**
     * Intitializes handCount;
     */
    public void first() {
      handCount = 0;
    }

    /**
     * Method that increments handCount.
     */
    public void next() {
      handCount++;
    }

    /**
     * Method that returns true when our handCount is the same size as
     * our newHand, i.e. we finished iterating through a hand.
     *
     * @return true if done, false otherwise
     */
    public boolean isDone() {
      return handCount == newHand.size();
    }

    /**
     * Get the current card at the given index of handCount.
     *
     * @return the card at index handCount
     */
    public Card current() {
      return newHand.get(handCount);
    }
  }
}
