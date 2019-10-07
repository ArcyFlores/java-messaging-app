package adapters;

import org.junit.Before;
import org.junit.Test;

import cards.Card;
import cards.CardImpl;

import static org.junit.Assert.*;

public class KaAdapterTest {

  /**
   * Variables are declared for test cases that follow.
   */
  private Card test1;
  private Card test2;
  private Card test3;
  private Card test4;
  private Card test5;
  private Card test6;
  private Card test7;
  private Card test8;
  private Card test9;
  private Card test10;
  private Card test11;
  private Card test12;

  /**
   * Sets up different Cards.
   *
   */
  @Before
  public void setUp() {
    test1 = new KaAdapter("ACE", "HEARTS");
    test2 = new KaAdapter("3", "clubs");
    test3 = new KaAdapter("QUeen", "diamonds");
    test4 = new KaAdapter("king", "spades");
    test5 = new KaAdapter("9", "diamonDS");
    test6 = new KaAdapter("5", "spades");
    test7 = new KaAdapter("3", "CLUBS");
    test8 = new KaAdapter("4", "Hearts");
    test9 = new KaAdapter("KING", "HEARTS");
    test10 = new KaAdapter("Jack", "clubs");
    test11 = new KaAdapter("ace", "spades");
    test12 = new KaAdapter("2", "HeArts");
  }

  /**
   * Test creation of Card.
   */
  @Before
  public void testInvalidCardCreation(){
    try {
      test1 = new KaAdapter("three", "hearts");
    } catch (IllegalArgumentException e){
      e.getMessage();
    }
    try {
      test1 = new KaAdapter("1", "spades");
    } catch (IllegalArgumentException e){
      e.getMessage();
    }
  }

  /**
   * Testing Card getRank method.
   */
  @Test
  public void getRank() {
    assertEquals("ace", test1.getRank().getName());
    assertEquals("3", test2.getRank().getName());
    assertEquals("queen", test3.getRank().getName());
    assertEquals("king", test4.getRank().getName());
    assertEquals("9", test5.getRank().getName());
    assertEquals("5", test6.getRank().getName());
    assertEquals("3", test7.getRank().getName());
    assertEquals("4", test8.getRank().getName());
    assertEquals("king", test9.getRank().getName());
    assertEquals("jack", test10.getRank().getName());
    assertEquals("ace", test11.getRank().getName());
    assertEquals("2", test12.getRank().getName());
  }

  /**
   * Gets suit.
   */
  @Test
  public void getSuit() {
    assertEquals("hearts", test1.getSuit().getName());
    assertEquals("clubs", test2.getSuit().getName());
    assertEquals("diamonds", test3.getSuit().getName());
    assertEquals("spades", test4.getSuit().getName());
    assertEquals("diamonds", test5.getSuit().getName());
    assertEquals("spades", test6.getSuit().getName());
    assertEquals("clubs", test7.getSuit().getName());
  }

  /**
   * Test the toString method by returning a list of Cards.
   */
  @Test
  public void testToString(){
    assertEquals("4 ♥", test8.toString());
    assertEquals("K ♥", test9.toString());
    assertEquals("J ♣", test10.toString());
    assertEquals("A ♠", test11.toString());
    assertEquals("2 ♥", test12.toString());
    assertEquals("Q ♦", test3.toString());
  }
}