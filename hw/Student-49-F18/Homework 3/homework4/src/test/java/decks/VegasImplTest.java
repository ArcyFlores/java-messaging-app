package decks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * The type Vegas impl test.
 */
public class VegasImplTest {
  /**
   * TODO: A deck after a card is pulled
   * has one less card than before that card
   * was pulled (and that card is no longer in the deck).
   * Beware of the pinochle deck for this test case!
   */

  /**
   * Variable declared for testing.
   */
  private Vegas test;

  /**
   * Sets up test cases.
   */
  @Before
  public void setUp()  {
    test = new VegasImpl();
  }


  /**
   * Method to test creation of desk.
   */
  @Test
  public void createDeckTest() {
    assertEquals(312, test.getCards().size());
  }

  /**
   * Test pull card.
   */
  @Test
  public void testPullCard(){
   for(int i = 0; i < 50; i++){
     test.pullCard();
   }
    assertEquals(262, test.getCards().size());
  }


  /**
   * Deck of has been correctly made.
   */
  @Test
  public void testDeckNotEmpty(){
    assertFalse(test.emptyDeck());
  }


  /**
   * Deck of has been correctly made.
   */
  @Test
  public void correctSize(){
    assertEquals(312, test.getCards().size());
    for(int i = 0; i < 300; i++){
      test.pullCard();
    }
    assertEquals(12, test.getCards().size());
  }

  /**
   * Official size.
   */
  @Test
  public void testOfficialSize() {
    assertEquals(312, test.officialSize());
    test.pullCard();
    test.pullCard();
    assertEquals(312, test.officialSize());
  }

  /**
   * Test shuffle.
   */
  @Test
  public void testShuffle(){
    test.shuffle();
    assertNotEquals("[A ♣, A ♦, A ♥, A ♠, 9 ♣, 9 ♦, 9 ♥, 9 ♠, "
            + "T ♣, T ♦, T ♥, T ♠, J ♣, Q ♣, J ♦, Q ♦, J ♥, Q ♥, J ♠, "
            + "Q ♠, K ♣, K ♦, K ♥, K ♠]", test.getCards().toString());
  }

  /**
   * Test cut deck.
   */
  @Test
  public void testCutDeck(){
    test.cut(100);
    assertEquals("K ♥", test.getCards().get(0).toString());

    test.cut(1);
    assertEquals("K ♣", test.getCards().get(0).toString());
    System.out.println(test.getCards());
  }

  /**
   * Test invalid cut.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCut(){
    test.cut(-1);
  }

  /**
   * Test cut invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCutInvalid(){
    test.cut(500);
  }

}