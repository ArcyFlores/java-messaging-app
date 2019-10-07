package problem1.cards;

import org.junit.Before;
import org.junit.Test;

public class SuitImplTest {
  SuitImpl suit1;
  SuitImpl suit2;
  SuitImpl suit3;
  SuitImpl suit4;

  @Before
  public void setUp() throws Exception {
    suit1 = new SuitImpl("hearts");
    suit2 = new SuitImpl("Clubs");
    suit3 = new SuitImpl("diamoNds");
    suit4 = new SuitImpl("SPADES");
  }

  @Test
  public void trySuits(){
    try {
      suit1 = new SuitImpl("he#rts");
    } catch (IllegalArgumentException e){
      e.getMessage();
    }
  }

  @Test
  public void getName() {
    assertEquals("hearts", suit1.getName());
    assertEquals("clubs", suit2.getName());
    assertEquals("diamonds", suit3.getName());
    assertEquals("spades", suit4.getName());
  }

  @Test
  public void getSymbol() {
    assertEquals('\u2665', suit1.getSymbol());
    assertEquals('\u2663', suit2.getSymbol());
    assertEquals('\u2666', suit3.getSymbol());
    assertEquals('\u2660', suit4.getSymbol());
  }


}