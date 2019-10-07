package game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.CardImpl;
import cards.RankImpl;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * @author aflores
 * A Junit test for the NewHand Implementation.
 */
public class NewHandImplTest {
  /**
   * The Cards.
   */
  Card c = new CardImpl("ace", "spades");
  Card c1 = new CardImpl("2", "diamonds");
  Card c2 = new CardImpl("queen", "hearts");
  Card c3 = new CardImpl("5", "spades");
  Card c4 = new CardImpl("10", "diamonds");
  Card c5 = new CardImpl("10", "diamonds");
  List<Card> cards = new ArrayList<Card>();

  /**
   * The test cases.
   */
  NewHand test1;
  NewHand test2;
  NewHand test3;

  /**
   * Sets up tests.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {

    test1 = new NewHandImpl();
    test1.accept(new CardImpl("2", "hearts"));
    test1.accept(new CardImpl("3", "spades"));
    test2 = new NewHandImpl();
    test2.accept(c);
    test2.accept(c1);
    test2.accept(c2);
    test2.accept(c3);
    test2.accept(c4);
    test2.accept(c5);
    test3 = new NewHandImpl();

  }

  /**
   * Tests has card.
   */
  @Test
  public void hasCard() {
    Card c6 = new CardImpl("2", "hearts");
    assertTrue(test1.hasCard(c6));
    Card c7 = test1.showCards().get(0);
    assertTrue(test1.hasCard(c7));
    assertFalse(test3.hasCard(c));

    assertTrue(test2.hasCard(c));
    assertTrue(test2.hasCard(c1));
    assertTrue(test2.hasCard(c2));
    assertTrue(test2.hasCard(c3));
    assertTrue(test2.hasCard(c4));
    assertTrue(test2.hasCard(c5));
    assertTrue(test2.hasCard(new CardImpl("ace", "spades")));
    assertTrue(test2.hasCard(new CardImpl("2", "diamonds")));
    assertTrue(test2.hasCard(new CardImpl("5", "spades")));
  }

  /**
   * Tests occurrences in hand.
   */
  @Test
  public void occurrencesInHandCard() {
    assertEquals(1, test1.occurrencesInHand(new CardImpl("2", "hearts")));
    assertEquals(2, test2.occurrencesInHand(c4));
    assertEquals(2, test2.occurrencesInHand(new CardImpl("10", "diamonds")));
    assertEquals(0, test3.occurrencesInHand(c));
    for (int i = 0; i < 3; i++) {
      test3.accept(c);
    }

    assertEquals(3, test3.occurrencesInHand(c));
  }

  /**
   * Tests Occurrences in hand for given ranks.
   */
  @Test
  public void occurrencesInHandRank() {
    assertEquals(0, test3.occurrencesInHand(new RankImpl("ace")));
    assertEquals(1, test2.occurrencesInHand(c1.getRank()));
    assertEquals(1, test1.occurrencesInHand(new RankImpl("2")));
    for (int i = 0; i < 10; i++) {
      test3.accept(c3);
    }
    assertEquals(10, test3.occurrencesInHand(c3.getRank()));
    assertEquals(0, test3.occurrencesInHand(c4.getRank()));
  }
}