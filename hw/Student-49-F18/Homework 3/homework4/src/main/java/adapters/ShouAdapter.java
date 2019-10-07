package adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cards.Card;
import cards.comparators.CardsByRank;
import cards.comparators.CardsBySuit;
import diErBao.Ka;
import diErBao.ShouShiXia;
import game.Hand;

/**
 * The type Shou adapter.
 */
public class ShouAdapter implements Hand {

  /**
   * Adapting methods from Shou.
   */
  private ShouShiXia adaptedHand;
  private List<Card> hand;
  private CardsByRank cardByRanks;
  private CardsBySuit cardsBySuits;

  /**
   * Instantiates a new Shou adapter.
   */
  ShouAdapter() {
    adaptedHand = new ShouShiXia();
    hand = new ArrayList<>();
    for (Ka k : adaptedHand.showHand()) {
      hand.add(new KaAdapter(k.getRank(), k.getSuit()));
    }
    cardByRanks = new CardsByRank();
    cardsBySuits = new CardsBySuit();
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
   * Returns the ShouShiXia.
   * @return ShouShiXia
   */
  public ShouShiXia getAdaptedHand(){
    return this.adaptedHand;
  }
  /**
   * Method that adds a single card into our Hand.
   *
   * @param newCard the new card to add to our Hand
   */
  @Override
  public void accept(Card newCard) {
    KaAdapter k = new KaAdapter(newCard.getRank().getName(), newCard.getSuit().getName());
    this.adaptedHand.accept(k.getKa());
    this.hand.add(newCard);
  }

  /**
   * Method that adds multiple cards into our current hand.
   *
   * @param newCards the cards to add to our hand
   */
  @Override
  public void accept(List<Card> newCards) {
    this.hand.addAll(newCards);
  }


  /**
   * Method that returns the first card in the hand.
   *
   * @return the first card in our Hand
   */
  @Override
  public Card pullCard() {
    return this.hand.remove(0);
  }

  /**
   * Method checks whether the given card is actually in the current Hand.
   *
   * @param cardToCheck is the card we want to check against the current Hand
   * @return true if card is in deck, false otherwise
   */
  @Override
  public Boolean hasCard(Card cardToCheck) {
    KaAdapter k = new KaAdapter(cardToCheck.getRank().getName(),
            cardToCheck.getSuit().getName());
    return adaptedHand.hasKa(k.getKa());
  }

  /**
   * Method that sorts the hand by given sorting type.
   *
   * @param sortType one of bySuit, byRank, or both
   */
  @Override
  public void sort(String sortType) {
    if (sortType.equals("byRank")) {
      adaptedHand.sort();
      Collections.sort(hand, cardByRanks);
    } else if (sortType.equalsIgnoreCase("bySuit")) {
      Collections.sort(hand, cardsBySuits);
    } else {
      Collections.sort(hand, cardsBySuits);
      Collections.sort(hand, cardByRanks);
    }

  }



  /**
   * Method the shuffles the current Hand.
   */
  @Override
  public void shuffle() {
    Collections.shuffle(this.hand);
  }

}
