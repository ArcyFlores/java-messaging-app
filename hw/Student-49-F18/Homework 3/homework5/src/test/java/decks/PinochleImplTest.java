package decks;

import org.junit.Before;
import org.junit.Test;

import cards.Card;
import cards.CardImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author aflores
 * A Junit test for the Pinochle Interface.
 */
public class PinochleImplTest {

  /**
   * The Pinochle deck for this Junit test.
   */
  private Pinochle test;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    test = new PinochleImpl();
  }

  /**
   * Method to test creation of desk.
   */
  @Test
  public void createDeckTest() {
    assertEquals("[A ♣, A ♣, A ♦, A ♦, A ♥, A ♥, A ♠, A ♠,"
            + " 9 ♣, 9 ♣, 9 ♦, 9 ♦, 9 ♥, 9 ♥, 9 ♠, 9 ♠, T ♣, T ♣, "
            + "T ♦, T ♦, T ♥, T ♥, T ♠, T ♠, J ♣, Q ♣, J ♣, Q ♣, J ♦, " +
            "Q ♦, J ♦, Q ♦, J ♥, Q ♥, J ♥, Q ♥, J ♠, Q ♠, J ♠, Q ♠, K ♣, " +
            "K ♣, K ♦, K ♦, K ♥, K ♥, K ♠, K ♠]", test.getCards().toString());
  }

  /**
   * Test pull card.
   */
  @Test
  public void testPullCard() {
    assertEquals("A ♣", test.pullCard().toString());
    assertEquals("A ♣", test.pullCard().toString());
    Card aceOfDiamonds = new CardImpl("ace", "diamonds");
    assertFalse(test.getCards().contains(aceOfDiamonds));
    assertEquals(46, test.getCards().size());

    Card card = test.pullCard();
    // Ace of Spades removed is no longer in deck.
    assertEquals("A ♦", card.toString());
    assertFalse(test.getCards().contains(card));
    // Copies of the Ace of Spades are different cards and still in deck.
    assertEquals("A ♦", test.getCards().get(0).toString());
  }

  /**
   * Tests that a deck does not contain any cards in it.
   */
  @Test
  public void isEmpty() {
    while (test.getCards().size() != 0) {
      test.pullCard();
    }
    assertTrue(test.emptyDeck());
  }

  /**
   * Official size.
   */
  @Test
  public void officialSize() {
    assertEquals(48, test.officialSize());
    test.pullCard();
    test.pullCard();
    assertEquals(48, test.officialSize());
  }

  /**
   * Deck of has been correctly made.
   */
  @Test
  public void testDeckNotEmpty() {
    assertFalse(test.emptyDeck());
  }


  /**
   * Deck of has been correctly made.
   */
  @Test
  public void correctSize() {
    assertEquals(48, test.getCards().size());
    test.pullCard();
    test.pullCard();
    assertEquals(46, test.getCards().size());
  }


  /**
   * Test shuffle.
   */
  @Test
  public void testShuffle() {
    /*
     * Before shuffling.
     */
    assertEquals("[A ♣, A ♣, A ♦, A ♦, A ♥, A ♥, A ♠, A ♠,"
            + " 9 ♣, 9 ♣, 9 ♦, 9 ♦, 9 ♥, 9 ♥, 9 ♠, 9 ♠, T ♣, T ♣, "
            + "T ♦, T ♦, T ♥, T ♥, T ♠, T ♠, J ♣, Q ♣, J ♣, Q ♣, J ♦, " +
            "Q ♦, J ♦, Q ♦, J ♥, Q ♥, J ♥, Q ♥, J ♠, Q ♠, J ♠, Q ♠, K ♣, " +
            "K ♣, K ♦, K ♦, K ♥, K ♥, K ♠, K ♠]", test.getCards().toString());
    test.shuffle();
    /*
     * After shuffling.
     */
    assertNotEquals("[A ♣, A ♣, A ♦, A ♦, A ♥, A ♥, A ♠, A ♠,"
            + " 9 ♣, 9 ♣, 9 ♦, 9 ♦, 9 ♥, 9 ♥, 9 ♠, 9 ♠, T ♣, T ♣, "
            + "T ♦, T ♦, T ♥, T ♥, T ♠, T ♠, J ♣, Q ♣, J ♣, Q ♣, J ♦, " +
            "Q ♦, J ♦, Q ♦, J ♥, Q ♥, J ♥, Q ♥, J ♠, Q ♠, J ♠, Q ♠, K ♣, " +
            "K ♣, K ♦, K ♦, K ♥, K ♥, K ♠, K ♠]", test.getCards().toString());
  }


  /**
   * Test sort by rank.
   */
  @Test
  public void testSortByRank() {
    test.sort("byRank");
    for (int i = 0; i < 8; i++) {
      assertEquals('A', test.getCards().get(i).getRank().getShortName());
    }
    assertEquals('Q', test.getCards().get(35).getRank().getShortName());
    for (int i = 40; i < 48; i++) {
      assertEquals('K', test.getCards().get(i).getRank().getShortName());
    }
  }

  /**
   * Test sort by suit.
   */
  @Test
  public void testSortBySuit() {
    test.sort("bySuit");

    for (int i = 0; i < 8; i++) {
      assertEquals("clubs", test.getCards().get(i).getSuit().getName());
    }
    for (int i = 24; i < 30; i++) {
      assertEquals("hearts", test.getCards().get(i).getSuit().getName());
    }
    for (int i = 40; i < 48; i++) {
      assertEquals("spades", test.getCards().get(i).getSuit().getName());
    }
  }

  /**
   * Test by suit rank.
   */
  @Test
  public void testBySuitRank() {
    test.sort("both");
    assertEquals("[A ♣, A ♣, A ♦, A ♦, A ♥, A ♥, A ♠, A ♠, " +
            "9 ♣, 9 ♣, 9 ♦, 9 ♦, 9 ♥, 9 ♥, 9 ♠, 9 ♠, T ♣, T ♣, T ♦, T ♦, " +
            "T ♥, T ♥, T ♠, T ♠, J ♣, Q ♣, J ♣, Q ♣, J ♦, Q ♦, J ♦, Q ♦, " +
            "J ♥, Q ♥, J ♥, Q ♥, J ♠, Q ♠, J ♠, Q ♠, K ♣, K ♣, K ♦, K ♦, " +
            "K ♥, K ♥, K ♠, K ♠]", test.getCards().toString());
  }

  /**
   * Test cut deck.
   */
  @Test
  public void testCutDeck() {
    test.cut(10);
    assertEquals("9 ♦", test.getCards().get(0).toString());

    test.cut(47);
    assertEquals("9 ♣", test.getCards().get(0).toString());
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
    test.cut(48);
  }

}