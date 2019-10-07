package decks;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * The type Euchre impl test.
 *
 * @author aflores
 * A Junit test for the Euchre Interface.
 */
public class EuchreImplTest {

  /**
   * Variable declared for testing.
   */
  private Euchre test;

  /**
   * Sets up test cases.
   */
  @Before
  public void setUp()  {
    test = new EuchreImpl();
  }

  /**
   * Test pull card.
   */
  @Test
  public void testPullCard() {
    assertEquals("A ♣", test.pullCard().toString());
    assertEquals("A ♦", test.pullCard().toString());
    assertEquals(22, test.getCards().size());
  }

  /**
   * Tests that a newly declared EuchreImpl
   * does not yet contain a deck with cards in it.
   */
  @Test
  public void isEmpty() {
    while (test.getCards().size() != 0) {
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
    assertEquals("[A ♣, A ♦, A ♥, A ♠, 9 ♣, 9 ♦, 9 ♥, 9 ♠, "
            + "T ♣, T ♦, T ♥, T ♠, J ♣, Q ♣, J ♦, Q ♦, J ♥, Q ♥, J ♠, "
            + "Q ♠, K ♣, K ♦, K ♥, K ♠]", test.getCards().toString());
  }

  /**
   * Deck of has been correctly made.
   */
  @Test
  public void correctSize() {
    assertEquals(24, test.getCards().size());
    test.pullCard();
    assertEquals(23, test.getCards().size());
  }

  /**
   * Official size.
   */
  @Test
  public void testOfficialSize() {
    assertEquals(24, test.officialSize());
    test.pullCard();
    test.pullCard();
    assertEquals(24, test.officialSize());
  }

  /**
   * Test shuffle.
   */
  @Test
  public void testShuffle() {
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
      assertEquals('9', test.getCards().get(i).getRank().getShortName());
    }
    for (int i = 20; i < 24; i++) {
      assertEquals('K', test.getCards().get(i).getRank().getShortName());
    }
  }

  /**
   * Test sort by suit.
   */
  @Test
  public void testSortBySuit() {
    test.sort("bySuit");
    System.out.println(test.getCards());
    for (int i = 0; i < 4; i++) {
      assertEquals("clubs", test.getCards().get(i).getSuit().getName());
    }
    for (int i = 6; i < 11; i++) {
      assertEquals("diamonds", test.getCards().get(i).getSuit().getName());
    }
    for (int i = 20; i < 24; i++) {
      assertEquals("spades", test.getCards().get(i).getSuit().getName());
    }
  }

  /**
   * Test by suit rank.
   */
  @Test
  public void testBySuitRank() {
    test.sort("both");
    assertEquals("[A ♣, A ♦, A ♥, A ♠, 9 ♣, 9 ♦, 9 ♥, 9 ♠, "
            + "T ♣, T ♦, T ♥, T ♠, J ♣, Q ♣, J ♦, Q ♦, J ♥, Q ♥, J ♠, "
            + "Q ♠, K ♣, K ♦, K ♥, K ♠]", test.getCards().toString());
  }

  /**
   * Test cut deck.
   */
  @Test
  public void testCutDeck() {
    test.cut(5);
    assertEquals("9 ♦", test.getCards().get(0).toString());

    test.cut(1);
    assertEquals("9 ♥", test.getCards().get(0).toString());
    System.out.println(test.getCards());
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
  public void testCutInvalid() {
    test.cut(24);
  }

}