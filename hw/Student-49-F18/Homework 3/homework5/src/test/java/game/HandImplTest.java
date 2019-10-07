package game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.CardImpl;
import decks.Deck;
import decks.EuchreImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;

/**
 * The type Hand impl test.
 */
public class HandImplTest {

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
    test1 = new HandImpl();
    test2 = new HandImpl();
    test3 = new HandImpl();
    test4 = new HandImpl();
    Card c1 = new CardImpl("ace", "spades");
    Card c2 = new CardImpl("queen", "hearts");
    Card c3 = new CardImpl("5", "clubs");
    test1.accept(c1);
    test1.accept(c2);
    test1.accept(c3);
    Deck deck = new EuchreImpl();
    for(int i = 0; i < 5; i++){
      test3.accept(deck.pullCard());
    }

    Card c4 = new CardImpl("9", "hearts");
    Card c5 = new CardImpl("10", "hearts");
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
    assertFalse(test1.hasCard(new CardImpl("6", "diamonds")));
    assertEquals(3, test1.showCards().size());
    assertEquals("[A ♠, Q ♥, 5 ♣]", test1.showCards().toString());
  }



  /**
   * Accept a card.
   */
  @Test
  public void acceptMultiple() {
    List<Card> other = new ArrayList<Card>();
    other.add(new CardImpl("ace", "spades"));
    other.add(new CardImpl("queen", "hearts"));
    other.add(new CardImpl("2", "spades"));
    other.add(new CardImpl("3", "hearts"));
    other.add(new CardImpl("5", "diamonds"));
    other.add(new CardImpl("8", "clubs"));
    other.add(new CardImpl("9", "clubs"));
    other.add(new CardImpl("10", "clubs"));
    other.add(new CardImpl("jack", "diamonds"));
    other.add(new CardImpl("king", "hearts"));
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