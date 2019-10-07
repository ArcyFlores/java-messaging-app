package edu.northeastern.ccs.cs5500;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestingHomeworkTest {
  /**
   * The TestingHomework instance we'll use to test.
   */
  private TestingHomework test = new TestingHomeworkImpl();

  /**
   * Test Sqrt().
   */
  @Test
  void sqrt() {

    // test standard double
    assertEquals(5.0, test.sqrt(25));
    assertEquals(7.0, test.sqrt(49));
    assertEquals(6.0, test.sqrt(36));

    // test 1 and 0
    assertEquals(1.0, test.sqrt(1));
    assertEquals(0, test.sqrt(0));

    // test negative inputs
    assertEquals(Double.NaN, test.sqrt(-5));
    assertEquals(Double.NaN, test.sqrt(-1));
    assertEquals(Double.NaN, test.sqrt(-3854325));

    // should throw error for invalid input
    assertThrows(Exception.class, () -> {
      test.sqrt(Integer.MIN_VALUE);
    });

    // should throw error for invalid input
    assertThrows(Exception.class, () -> {
      test.sqrt(Integer.MAX_VALUE);
    });

    // test large decimal output
    assertEquals(30145.72, test.sqrt(908764767), 2);
    assertEquals(1.4, test.sqrt(2), 2);
    assertEquals(7.42, test.sqrt(55), 2);
    assertEquals(12.44, test.sqrt(155), 2);

  }

  /**
   * Test Sqr().
   */
  @Test
  void sqr() {

    // test 1 and 0
    assertEquals(1, test.sqr(1));
    assertEquals(0, test.sqr(0));
    assertEquals(1, test.sqr(-1));

    // Test standard integer to square
    assertEquals(4, test.sqr(2));
    assertEquals(9, test.sqr(3));
    assertEquals(16, test.sqr(4));
    assertEquals(25, test.sqr(5));
    assertEquals(291600, test.sqr(540));
    assertEquals(2147395600, test.sqr(46340));
    assertEquals(100000000, test.sqr(10000));

    // Test negative integer to square
    assertEquals(4, test.sqr(-2));
    assertEquals(9, test.sqr(-3));
    assertEquals(16, test.sqr(-4));
    assertEquals(25, test.sqr(-5));
    assertEquals(291600, test.sqr(-540));
    assertEquals(2147395600, test.sqr(-46340));
    assertEquals(100000000, test.sqr(-10000));


    // test very large ints
    assertEquals(12.44, test.sqrt(155), 2);

    // Should throw exceptions or expect to return errors
    // due to overflow
    assertThrows(Exception.class, () -> {
      test.sqr(46341);
    });
    assertThrows(Exception.class, () -> {
      test.sqr(-46341);
    });
    assertThrows(Exception.class, () -> {
      test.sqr(-909019248);
    });
    assertThrows(Exception.class, () -> {
      test.sqr(909019248);
    });


}

  /**
   * Test Factorial().
   */
  @Test
  void factorial() {
    //test positive integers
    assertEquals(1, test.factorial(0));
    assertEquals(1, test.factorial(1));
    assertEquals(2, test.factorial(2));
    assertEquals(6, test.factorial(3));
    assertEquals(24, test.factorial(4));
    assertEquals(120, test.factorial(5));
    assertEquals(720, test.factorial(6));
    assertEquals(5040, test.factorial(7));
    assertEquals(40320, test.factorial(8));
    assertEquals(362880, test.factorial(9));
    assertEquals(3628800, test.factorial(10));
    assertEquals(39916800, test.factorial(11));
    assertEquals(479001600, test.factorial(12));


    // negative numbers return error  -1, but should
    // throw an Illegal Argument Exception instead
    try{
      assertEquals(-1, test.factorial(-1));
    } catch (IllegalArgumentException e){
      e.getMessage();
    }

    try{
      assertEquals(-1, test.factorial(-5));
    } catch (IllegalArgumentException e){
      e.getMessage();
    }

    // error because of int overflow, should expect to
    // throw Exceptions
    try{
      assertEquals(-1, test.factorial(13));
    } catch (Exception e){
      e.getMessage();
    }
    try{
      assertEquals(-1, test.factorial(25));
    }catch (Exception e){
      e.getMessage();
    }

  }

  /**
   * Test Sum up.
   * Positive Integers only.
   */
  @Test
  void sumUp() {
    // test positive integers
    assertEquals(0, test.sumUp(0));
    assertEquals(1, test.sumUp(1));
    assertEquals(3, test.sumUp(2));
    assertEquals(15, test.sumUp(5));
    assertEquals(55, test.sumUp(10));
    assertEquals(630, test.sumUp(35));
    assertEquals(2415, test.sumUp(69));
    assertEquals(5050, test.sumUp(100));
    assertEquals(11325, test.sumUp(150));
    assertEquals(1125750, test.sumUp(1500));
    assertEquals(200010000, test.sumUp(20000));
    assertEquals(2147385345, test.sumUp(65534));


    // Following tests should expect an error due to int overflow,
    // code should throw exception or handle overflow efficiently
    // -1 is inputted as error but it should throw Runtime Exception
    try{
      assertEquals(-1, test.sumUp(6559350));
    } catch (Exception e){
      e.getMessage();
    }
    try{
      assertEquals(-1, test.sumUp(2000000000));
    } catch (Exception e){
      e.getMessage();
    }
    try{
      assertEquals(-1, test.sumUp(999999999));
    } catch (Exception e){
      e.getMessage();
    }
    try{
      assertEquals(-1, test.sumUp(759535));
    } catch (Exception e){
      e.getMessage();
    }


    // Code should throw Illegal argument exception for negative ints
    assertThrows(IllegalArgumentException.class, () -> {
      test.sumUp(-6559350);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.sumUp(-1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.sumUp(-2000000000);
    });
    assertThrows(IllegalArgumentException.class, () -> {
    test.sumUp(-8);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.sumUp(-999999999);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.sumUp(-759535);
    });

  }

  /**
   * Test Simple function x plus y.
   */
  @Test
  void simpleFunctionXplusY() {
    assertEquals(0, test.simpleFunctionXplusY(0, 0));

    assertEquals(1, test.simpleFunctionXplusY(0, 1));
    assertEquals(1, test.simpleFunctionXplusY(1, 0));
    assertEquals(6, test.simpleFunctionXplusY(4, 2));
    assertEquals(6, test.simpleFunctionXplusY(2, 4));
    assertEquals(8, test.simpleFunctionXplusY(4, 4));
    assertEquals(30, test.simpleFunctionXplusY(10, 20));
    assertEquals(30, test.simpleFunctionXplusY(20, 10));
    assertEquals(10, test.simpleFunctionXplusY(5, 5));
    assertEquals(30, test.simpleFunctionXplusY(25, 5));
    assertEquals(30, test.simpleFunctionXplusY(5, 25));
    assertEquals(22139, test.simpleFunctionXplusY(22134, 5));
    assertEquals(22139, test.simpleFunctionXplusY(5, 22134));
    assertEquals(23267, test.simpleFunctionXplusY(23142, 125));
    assertEquals(23267, test.simpleFunctionXplusY(125, 23142));
    assertEquals(9841239, test.simpleFunctionXplusY(9841234, 5));
    assertEquals(9841239, test.simpleFunctionXplusY(5, 9841234));
    assertEquals(337747, test.simpleFunctionXplusY(314324, 23423));
    assertEquals(337747, test.simpleFunctionXplusY(23423, 314324));
    assertEquals(288532165, test.simpleFunctionXplusY(43207945, 245324220));
    assertEquals(288532165, test.simpleFunctionXplusY(245324220, 43207945));


    // negative ints
    assertEquals(-2, test.simpleFunctionXplusY(-1, -1));
    assertEquals(-10, test.simpleFunctionXplusY(-5, -5));
    assertEquals(-30, test.simpleFunctionXplusY(-20, -10));
    assertEquals(-30, test.simpleFunctionXplusY(-10, -20));
    assertEquals(-30, test.simpleFunctionXplusY(-25, -5));
    assertEquals(-30, test.simpleFunctionXplusY(-5, -25));
    assertEquals(-150, test.simpleFunctionXplusY(-100, -50));
    assertEquals(-150, test.simpleFunctionXplusY(-50, -100));
    assertEquals(-900, test.simpleFunctionXplusY(-500, -400));
    assertEquals(-900, test.simpleFunctionXplusY(-400, -500));
    assertEquals(-474, test.simpleFunctionXplusY(-50, -424));
    assertEquals(-474, test.simpleFunctionXplusY(-424, -50));
    assertEquals(-42858, test.simpleFunctionXplusY(-42834, -24));
    assertEquals(-42858, test.simpleFunctionXplusY(-24,-42834));
    assertEquals(-22139, test.simpleFunctionXplusY(-22134, -5));
    assertEquals(-22139, test.simpleFunctionXplusY(-5, -22134));
    assertEquals(-43253848, test.simpleFunctionXplusY(-423, -43253425));
    assertEquals(-1284655, test.simpleFunctionXplusY(-41234, -1243421));
    assertEquals(-911759, test.simpleFunctionXplusY(-908354, -3405));
    assertEquals(-284685366, test.simpleFunctionXplusY(-41234123, -243451243));
    assertEquals(-284685366, test.simpleFunctionXplusY(-243451243, -41234123));


    // positive and negative
    assertEquals(-1, test.simpleFunctionXplusY(0, -1));
    assertEquals(-1, test.simpleFunctionXplusY(-1, 0));
    assertEquals(0, test.simpleFunctionXplusY(-5, 5));
    assertEquals(0, test.simpleFunctionXplusY(5, -5));
    assertEquals(-15, test.simpleFunctionXplusY(-20, 5));
    assertEquals(15, test.simpleFunctionXplusY(20, -5));
    assertEquals(-55, test.simpleFunctionXplusY(0, -55));
    assertEquals(10, test.simpleFunctionXplusY(-10, 20));
    assertEquals(-10, test.simpleFunctionXplusY(-20, 10));
    assertEquals(-20, test.simpleFunctionXplusY(-25, 5));
    assertEquals(20, test.simpleFunctionXplusY(25, -5));
    assertEquals(-20, test.simpleFunctionXplusY(5, -25));
    assertEquals(20, test.simpleFunctionXplusY(-5, 25));
    assertEquals(-229, test.simpleFunctionXplusY(-234, 5));
    assertEquals(-229, test.simpleFunctionXplusY(5, -234));
    assertEquals(22129, test.simpleFunctionXplusY(22134, -5));
    assertEquals(-22129, test.simpleFunctionXplusY(-22134, 5));
    assertEquals(-22129, test.simpleFunctionXplusY(5, -22134));
    assertEquals(22129, test.simpleFunctionXplusY(-5, 22134));
    assertEquals(83492, test.simpleFunctionXplusY(-3242, 86734));
    assertEquals(56343, test.simpleFunctionXplusY(56348, -5));
    assertEquals(-23110, test.simpleFunctionXplusY(345, -23455));

    // large ints should throw RunTimeException due to overflow
    assertThrows(RuntimeException.class, () -> {
      test.simpleFunctionXplusY(314097343, 987459834);
    });
    assertThrows(RuntimeException.class, () -> {
      test.simpleFunctionXplusY(-945289645, -942432840);
    });
    assertThrows(RuntimeException.class, () -> {
      test.simpleFunctionXplusY(933458734, 930557177);
    });
    // should throw IllegalArgument Exception for MAX and MIN INT
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY(Integer.MAX_VALUE, 0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY (0, Integer.MAX_VALUE);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY(Integer.MAX_VALUE, 0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY( 1,Integer.MAX_VALUE);
    });
    // should throw IllegalArgument Exception for MAX and MIN INT
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY(Integer.MAX_VALUE, 1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY (1, Integer.MAX_VALUE);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY(Integer.MAX_VALUE, 1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.simpleFunctionXplusY( 1,Integer.MAX_VALUE);
    });
  }

  /**
   * Test Despacer.
   */
  @Test
  void despacer() {
    //testing empty string and only spaces
    assertEquals("", test.despacer(""));
    assertEquals("", test.despacer("      "));

    // testing various strings with varying degrees of spaces to test
    assertEquals("HelloWorld", test.despacer("HelloWorld"));
    assertEquals("Hello World", test.despacer("Hello    World"));
    assertEquals("Hell o , Wo rld", test.despacer("   Hell  o , Wo  rld"));
    assertEquals("Hell o, World", test.despacer("   Hell o, World"));
    assertEquals("Hell o, World", test.despacer("Hell  o, World"));

    assertEquals("This string just has one trailing space.",
            test.despacer("This string just has one trailing space.  "));
    assertEquals("This string is correctly spaced out.",
            test.despacer("This string is correctly spaced out."));
    assertEquals("Sentence, with multiple, commas.",
            test.despacer("Sentence,   with  multiple,  commas.  "));

  }

}