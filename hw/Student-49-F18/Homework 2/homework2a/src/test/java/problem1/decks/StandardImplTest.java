package problem1.decks;

import org.junit.Before;
import org.junit.Test;

import problem1.cards.Card;
import problem1.cards.CardImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * The type Standard impl test.
 */
public class StandardImplTest {

  /**
   * Variable declared for testing.
   */
  private Standard test;

  /**
   * Sets up test cases.
   */
  @Before
  public void setUp() {
    test = new StandardImpl();
  }


  /**
   * Test pull card.
   */
  @Test
  public void testPullCard() {
    Card c = test.pullCard();
    assertFalse(test.getCards().contains(c));
    c = test.pullCard();
    assertEquals(50, test.getCards().size());
    assertFalse(test.getCards().contains(c));
  }


  /**
   * Tests that a newly declared EuchreImpl
   * does not yet contain a deck with cards in it.
   */
  @Test
  public void isEmpty() {
    for (int i = 0; i < 52; i++) {
      test.pullCard();
    }
    assertTrue(test.emptyDeck());
  }

  /**
   * Deck of has been correctly made.
   */
  @Test
  public void testDeckNotEmpty() {
    assertFalse(test.emptyDeck());
  }

  /**
   * Method to test creation of desk.
   */
  @Test
  public void createDeckTest() {
    assertEquals("[A ♥, A ♣, A ♠, A ♦, 2 ♥, 2 ♣, 2 ♠, 2 ♦,"
            + " 3 ♥, 3 ♣, 3 ♠, 3 ♦, 4 ♥, 4 ♣, 4 ♠, 4 ♦, 5 ♥, 5 ♣, 5 ♠, "
            + "5 ♦, 6 ♥, 6 ♣, 6 ♠, 6 ♦, 7 ♥, 7 ♣, 7 ♠, 7 ♦, 8 ♥, 8 ♣, 8 ♠, "
            + "8 ♦, 9 ♥, 9 ♣, 9 ♠, 9 ♦, T ♥, T ♣, T ♠, T ♦, J ♥, J ♣, J ♠,"
            + " J ♦, Q ♥, Q ♣, Q ♠, Q ♦, K ♥, K ♣, K ♠, K ♦]", test.getCards().toString());
  }

  /**
   * Deck of has been correctly made.
   */
  @Test
  public void correctSize() {
    assertEquals(52, test.getCards().size());
    test.pullCard();
    assertEquals(51, test.getCards().size());
    for (int i = 0; i < 20; i++) {
      test.pullCard();
    }
    assertEquals(31, test.getCards().size());
  }

  /**
   * Official size.
   */
  @Test
  public void testOfficialSize() {
    assertEquals(52, test.officialSize());
    test.pullCard();
    test.pullCard();
    assertEquals(52, test.officialSize());
  }

  /**
   * Test shuffle.
   */
  @Test
  public void testShuffle() {
    assertEquals("[A ♥, A ♣, A ♠, A ♦, 2 ♥, 2 ♣, 2 ♠, 2 ♦, " +
                    "3 ♥, 3 ♣, 3 ♠, 3 ♦, 4 ♥, 4 ♣, 4 ♠, 4 ♦, 5 ♥, 5 ♣, 5 ♠," +
                    " 5 ♦, 6 ♥, 6 ♣, 6 ♠, 6 ♦, 7 ♥, 7 ♣, 7 ♠, 7 ♦, 8 ♥, 8 ♣," +
                    " 8 ♠, 8 ♦, 9 ♥, 9 ♣, 9 ♠, 9 ♦, T ♥, T ♣, T ♠, T ♦, J ♥, " +
                    "J ♣, J ♠, J ♦, Q ♥, Q ♣, Q ♠, Q ♦, K ♥, K ♣, K ♠, K ♦]",
            test.getCards().toString());
    test.shuffle();
    assertNotEquals("[A ♣, A ♦, A ♥, A ♠, 9 ♣, 9 ♦, 9 ♥, 9 ♠, "
            + "T ♣, T ♦, T ♥, T ♠, J ♣, Q ♣, J ♦, Q ♦, J ♥, Q ♥, J ♠, "
            + "Q ♠, K ♣, K ♦, K ♥, K ♠]", test.getCards().toString());
  }

  /**
   * Test sort by rank.
   */
  @Test
  public void testSortByRank() {
    test.sort("byRank");
    for (int i = 0; i < 4; i++) {
      assertEquals('A', test.getCards().get(i).getRank().getShortName());
    }
    for (int i = 4; i < 7; i++) {
      assertEquals(50, test.getCards().get(i).getRank().getShortName());
    }
    for (int i = 20; i < 24; i++) {
      assertEquals(54, test.getCards().get(i).getRank().getShortName());
    }
  }

  /**
   * Test sort by suit.
   */
  @Test
  public void testSortBySuit() {
    test.sort("bySuit");
    System.out.println(test.getCards());
    for (int i = 0; i < 12; i++) {
      assertEquals("clubs", test.getCards().get(i).getSuit().getName());
    }
    for (int i = 20; i < 25; i++) {
      assertEquals("diamonds", test.getCards().get(i).getSuit().getName());
    }
    for (int i = 26; i < 35; i++) {
      assertEquals("hearts", test.getCards().get(i).getSuit().getName());
    }
    for (int i = 40; i < 52; i++) {
      assertEquals("spades", test.getCards().get(i).getSuit().getName());
    }
  }

  /**
   * Test by suit rank.
   */
  @Test
  public void testBySuitRank() {
    test.sort("both");
    for (int i = 0; i < 4; i++) {
      assertEquals('A', test.getCards().get(i).getRank().getShortName());
    }
    for (int i = 4; i < 8; i++) {
      assertEquals('2', test.getCards().get(i).getRank().getShortName());
    }
    for (int i = 8; i < 12; i++) {
      assertEquals('3', test.getCards().get(i).getRank().getShortName());
    }

    for (int i = 20; i < 24; i++) {
      assertEquals('6', test.getCards().get(i).getRank().getShortName());
    }
    for (int i = 24; i < 28; i++) {
      assertEquals('7', test.getCards().get(i).getRank().getShortName());
    }
    for (int i = 28; i < 32; i++) {
      assertEquals('8', test.getCards().get(i).getRank().getShortName());
    }

    for (int i = 48; i < 52; i++) {
      assertEquals('K', test.getCards().get(i).getRank().getShortName());
    }
  }

  /**
   * Test cut deck.
   */
  @Test
  public void testCutDeck() {
    test.cut(5);
    assertEquals("2 ♣", test.getCards().get(0).toString());

    test.cut(1);
    assertEquals("2 ♠", test.getCards().get(0).toString());

    test.cut(20);
    assertEquals("7 ♠", test.getCards().get(0).toString());

    test.cut(10);
    assertEquals("T ♥", test.getCards().get(0).toString());

    test.cut(52 / 2);
    assertEquals("3 ♠", test.getCards().get(0).toString());
  }

  /**
   * Test invalid cut.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCut() {
    test.cut(-1);
  }

  /**
   * Test cut invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCutInvalid() throws IllegalArgumentException {
    test.cut(52);
  }

}