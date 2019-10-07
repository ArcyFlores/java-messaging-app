package adapters;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import decks.Deck;
import decks.EuchreImpl;
import game.Hand;

import static org.junit.Assert.*;

public class ShouAdapterTest {
  /**
   * Hands to test.
   */
  private Hand test1;
  private Hand test2;
  private Hand test3;
  private Hand test4;

  /**
   * Sets up.
   *
   */
  @Before
  public void setUp(){
    test1 = new ShouAdapter();
    test2 = new ShouAdapter();
    test3 = new ShouAdapter();
    test4 = new ShouAdapter();
    Card c1 = new KaAdapter("ace", "spades");
    Card c2 = new KaAdapter("queen", "hearts");
    Card c3 = new KaAdapter("5", "clubs");
    test1.accept(c1);
    test1.accept(c2);
    test1.accept(c3);
    Deck deck = new EuchreImpl();
    for(int i = 0; i < 5; i++){
      test3.accept(deck.pullCard());
    }

    Card c4 = new KaAdapter("9", "hearts");
    Card c5 = new KaAdapter("10", "hearts");
    test4.accept(c4);
    test4.accept(c5);
  }

  /**
   * Show cards.
   */
  @Test
  public void showCards() {
    assertEquals("[A ♠, Q ♥, 5 ♣]", test1.showCards().toString());
    assertEquals("[]", test2.showCards().toString());
    assertEquals("[A ♣, A ♦, A ♥, A ♠, 9 ♣]", test3.showCards().toString());
    assertEquals("[9 ♥, T ♥]", test4.showCards().toString());
  }

  /**
   * Has card.
   */
  @Test
  public void hasCard() {
    assertFalse(test1.hasCard(new KaAdapter("6", "diamonds")));
    assertEquals(3, test1.showCards().size());
    assertEquals("[A ♠, Q ♥, 5 ♣]", test1.showCards().toString());
  }



  /**
   * Accept a card.
   */
  @Test
  public void acceptMultiple() {
    List<Card> other = new ArrayList<Card>();
    other.add(new KaAdapter("ace", "spades"));
    other.add(new KaAdapter("queen", "hearts"));
    other.add(new KaAdapter("2", "spades"));
    other.add(new KaAdapter("3", "hearts"));
    other.add(new KaAdapter("5", "diamonds"));
    other.add(new KaAdapter("8", "clubs"));
    other.add(new KaAdapter("9", "clubs"));
    other.add(new KaAdapter("10", "clubs"));
    other.add(new KaAdapter("jack", "diamonds"));
    other.add(new KaAdapter("king", "hearts"));
    test2.accept(other);
    assertEquals("[A ♠, Q ♥, 2 ♠, 3 ♥, 5 ♦, 8 ♣, 9 ♣, T ♣, J ♦, K ♥]",
            test2.showCards().toString());
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testInvalidPull(){
    test2.pullCard();
  }

  /**
   * Pull card.
   */
  @Test
  public void pullCard() {
    test1.pullCard();
    test3.pullCard();
    test4.pullCard();
    assertEquals(2, test1.showCards().size());
    assertEquals(0, test2.showCards().size());
    assertEquals(4, test3.showCards().size());
    assertEquals(1, test4.showCards().size());
    test1.pullCard();
    assertEquals(1, test1.showCards().size());
  }

  /**
   * Sort.
   */
  @Test
  public void sort() {
    test3.sort("bySuit");
    assertEquals("[A ♣, 9 ♣, A ♦, A ♥, A ♠]", test3.showCards().toString());

    test1.sort("byRank");
    assertEquals("[A ♠, 5 ♣, Q ♥]", test1.showCards().toString());

    test4.sort("both");
    assertEquals("[9 ♥, T ♥]", test4.showCards().toString());

  }

  /**
   * Shuffle.
   */
  @Test
  public void shuffle() {
    test3.shuffle();
    assertNotEquals("[A ♣, 9 ♣, A ♦, A ♥, A ♠]", test3.showCards().toString());
  }
}