package problem2.cards;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *  @author aflores
 *  A Junit class for testing the Rank interface and its implemented class.
 */
public class RankImplTest {
  private Rank test1;
  private Rank test2;
  private Rank test3;
  private Rank test4;

  /**
   * Sets up test cases.
   *
   */
  @Before
  public void setUp() {
    test1 = new RankImpl("Ace");
    test2 = new RankImpl("6");
    test3 = new RankImpl("8");
    test4 = new RankImpl("QUeen");
  }

  /**
   * Test rank creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRankCreation() {
    test1 = new RankImpl("three");
  }

  /**
   * Tests the gets name method.
   */
  @Test
  public void getName() {
    assertEquals("ace", test1.getName());
    assertEquals("6", test2.getName());
    assertEquals("8", test3.getName());
    assertEquals("queen", test4.getName());
  }

  /**
   * Tests the gets pips method.
   */
  @Test
  public void getPips() {
    assertEquals(1, test1.getPips());
    assertEquals(6, test2.getPips());
    assertEquals(8, test3.getPips());
    assertEquals(0, test4.getPips());
  }

  /**
   * Tests the get short name method.
   */
  @Test
  public void getShortName() {
    assertEquals('A', test1.getShortName());
    assertEquals('6', test2.getShortName());
    assertEquals('8', test3.getShortName());
    assertEquals('Q', test4.getShortName());
  }
}