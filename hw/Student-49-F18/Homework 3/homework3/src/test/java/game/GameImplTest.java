package game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The type Game impl test.
 */
public class GameImplTest {

  private GameDeck g1 = new GameImpl();
  private GameDeck g2 = new GameImpl();
  private GameDeck g3 = new GameImpl();
  private GameDeck g4 = new GameImpl();
  private GameDeck g5 = new GameImpl();

  /**
   * Sets up different games.
   *
   */
  @Before
  public void setUp() {
    g1.createDeck("euchre");
    g2.createDeck("pinochle");
    g3.createDeck("standard");
    g4.createDeck("vegas");
    g5.createDeck("standard", 3);
  }

  /**
   * Create deck.
   */
  @Test
  public void createDeck() {
    assertEquals(24, g1.getDeck().getCards().size());
    assertEquals(48, g2.getDeck().getCards().size());
    assertEquals(52, g3.getDeck().getCards().size());
    assertEquals(312, g4.getDeck().getCards().size());
    assertEquals(156, g5.getDeck().getCards().size());
  }

  /**
   * Deal method Test.
   * @throws IllegalAccessException if we try to deal from an empty deck
   */
  @Test
  public void deal() throws IllegalAccessException {
    g1.setNumberOfHands(3);
    g2.setNumberOfHands(4);
    g3.setNumberOfHands(5);
    g4.setNumberOfHands(7);
    g5.setNumberOfHands(8);
    g1.deal();
    g2.deal();
    g3.deal();
    g4.deal();
    g5.deal();

    assertEquals(3, g1.getHands().size());
    assertEquals(4, g2.getHands().size());
    assertEquals(5, g3.getHands().size());
    assertEquals(7, g4.getHands().size());
    assertEquals(8, g5.getHands().size());

    Hand h1 = (HandImpl)g1.getHands().get(1);
    assertNotNull(h1);
    Hand h2 = (HandImpl)g1.getHands().get(2);
    assertNotNull(h2);
    Hand h3 = (HandImpl)g1.getHands().get(3);
    assertNotNull(h3);
  }

  /**
   * Testing for setting number of hands.
   */
  @Test
  public void setNumberOfHands() throws IllegalAccessException {
    g1.setNumberOfHands(3);
    g1.deal();
    assertEquals(3, g1.getHands().size());

    g2.setNumberOfHands(4);
    g2.deal();
    assertEquals(4, g2.getHands().size());

    g3.setNumberOfHands(5);
    g3.deal();
    assertEquals(5, g3.getHands().size());

    g4.setNumberOfHands(7);
    g4.deal();
    assertEquals(7, g4.getHands().size());

    g5.setNumberOfHands(8);
    g5.deal();
    assertEquals(8, g5.getHands().size());

  }



}