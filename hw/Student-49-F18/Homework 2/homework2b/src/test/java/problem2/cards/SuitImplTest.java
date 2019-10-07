package problem2.cards;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author aflores
 * A Junit class for testing the Suit interface and its implemented class.
 */
public class SuitImplTest {
  private Suit test1;
  private Suit test2;
  private Suit test3;
  private Suit test4;

  /**
   * Sets up test cases.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    try {
      test1 = new SuitImpl("hearts");
      test2 = new SuitImpl("cLubs");
      test3 = new SuitImpl("diamonds");
      test4 = new SuitImpl("Spades");
    } catch (Exception e) {
      throw new RuntimeException("Initialization failed!");
    }

  }

  /**
   * Test suit creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSuitCreation() {
    test1 = new SuitImpl("sp@des");
  }

  /**
   * Try to create a suit using incorrect format.
   */
  @Test
  public void trySuits() {
    try {
      test1 = new SuitImpl("he#rts");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  /**
   * Test gets name method.
   */
  @Test
  public void getName() {
    assertEquals("hearts", test1.getName());
    assertEquals("clubs", test2.getName());
    assertEquals("diamonds", test3.getName());
    assertEquals("spades", test4.getName());
  }

  /**
   * Tests gets symbol method.
   */
  @Test
  public void getSymbol() {
    assertEquals('\u2665', test1.getSymbol());
    assertEquals('\u2663', test2.getSymbol());
    assertEquals('\u2666', test3.getSymbol());
    assertEquals('\u2660', test4.getSymbol());
  }

}