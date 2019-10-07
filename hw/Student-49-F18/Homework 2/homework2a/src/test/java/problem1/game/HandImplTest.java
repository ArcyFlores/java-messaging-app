package problem1.game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import problem1.cards.Card;
import problem1.cards.CardImpl;
import problem1.decks.Deck;
import problem1.decks.EuchreImpl;
import problem1.decks.StandardImpl;

import static org.junit.Assert.*;

/**
 * The type Hand impl test.
 */
public class HandImplTest {

  /**
   * Various hands we'll be testing out and accompanying variables.
   */
  private Hand test1;
  private Hand test2;
  private Hand test3;
  private Hand test4;

  /**
   * Sets up.
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
    System.out.println("Deck size: " + deck.getCards().size());

    Card c4 = new CardImpl("9", "hearts");
    Card c5 = new CardImpl("10", "hearts");
    Card c6 = new CardImpl("10", "hearts");
    Card c7 = new CardImpl("9", "spades");
    Card c8 = new CardImpl("jack", "clubs");
    Card c9 = new CardImpl("jack", "clubs");
    test4.accept(c4);
    test4.accept(c5);
    test4.accept(c6);
    test4.accept(c6);
    test4.accept(c7);
    test4.accept(c8);
    test4.accept(c9);
  }

  /**
   * Show cards.
   */
  @Test
  public void showCards() {
    assertEquals("[A ♠, Q ♥, 5 ♣]", test1.showCards().toString());
    assertEquals("[]", test2.showCards().toString());
    assertEquals("[A ♣, A ♦, A ♥, A ♠, 9 ♣]", test3.showCards().toString());
    assertEquals("[9 ♥, T ♥, T ♥, T ♥, 9 ♠, J ♣, J ♣]", test4.showCards().toString());
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

  /**
   * Test invalid pull.
   *
   * @throws IllegalAccessException the illegal access exception
   */
  @Test (expected = IllegalAccessException.class)
  public void testInvalidPull() throws IllegalAccessException {
    test2.pullCard();
  }

  /**
   * Pull card.
   *
   * @throws IllegalAccessException the illegal access exception
   */
  @Test
  public void pullCard() throws IllegalAccessException {
    test1.pullCard();
    test3.pullCard();
    test4.pullCard();
    assertEquals(2, test1.showCards().size());
    assertEquals(0, test2.showCards().size());
    assertEquals(4, test3.showCards().size());
    assertEquals(6, test4.showCards().size());
    test1.pullCard();
    assertEquals(1, test1.showCards().size());
  }

  /**
   * Tests the sorting method.
   */
  @Test
  public void sort() {
    test3.sort("bySuit");
    assertEquals("[A ♣, 9 ♣, A ♦, A ♥, A ♠]", test3.showCards().toString());

    test1.sort("byRank");
    assertEquals("[A ♠, 5 ♣, Q ♥]", test1.showCards().toString());

    test4.sort("both");
    assertEquals("[9 ♥, 9 ♠, T ♥, T ♥, T ♥, J ♣, J ♣]", test4.showCards().toString());

  }

  /**
   * Tests the Shuffle of the hand.
   */
  @Test
  public void shuffle() {
    assertEquals("[A ♣, A ♦, A ♥, A ♠, 9 ♣]", test3.showCards().toString());
    test3.shuffle();
    assertNotEquals("[A ♣, 9 ♣, A ♦, A ♥, A ♠]", test3.showCards().toString());
  }

  /**
   * Tests contains method.
   * @throws IllegalArgumentException if we pull from an empty deck
   *
   */
  @Test
  public void testContains() throws IllegalAccessException {
    Card c = test4.showCards().get(2);
    assertTrue(test4.hasCard(c));
    test4.pullCard();
  }
}