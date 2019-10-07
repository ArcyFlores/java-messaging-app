package game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.CardImpl;

public class NewHandImplTest {
  List<Card> cards = new ArrayList<Card>();

  NewHand test1;
  NewHand test2;
  NewHand test3;
  NewHand test4;

  @Before
  public void setUp() throws Exception {

     test1 = new NewHandImpl();
     test1.accept(new CardImpl("2", "hearts"));
     test1.accept(new CardImpl("3", "spades"));
     test2 = new NewHandImpl();
     test3 = new NewHandImpl();
     test4 = new NewHandImpl();
  }

  @Test
  public void hasCard() {

  }

  @Test
  public void occurrencesInHand() {
  }

  @Test
  public void occurrencesInHand1() {
  }
}