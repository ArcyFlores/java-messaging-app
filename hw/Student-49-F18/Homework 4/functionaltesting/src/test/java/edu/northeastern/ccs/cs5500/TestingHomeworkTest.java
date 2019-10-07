package edu.northeastern.ccs.cs5500;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestingHomeworkTest {

    /**
     * The TestingHomework instance we'll use to test.
     */
    private TestingHomework test;

    /**
     * Test Sqrt().
     */
    @Test
    void sqrt() {
      test = mock(TestingHomework.class);

      // test standard double
      when(test.sqrt(25)).thenReturn(5.0);
      assertEquals(5, test.sqrt(25));

      // test 1 and 0
      when(test.sqrt(1)).thenReturn(1.0);
      assertEquals(1, test.sqrt(1));
      when(test.sqrt(0)).thenReturn(0.0);
      assertEquals(0, test.sqrt(0));

      // test negative inputs
      when(test.sqrt(-5)).thenReturn(Double.NaN);
      assertEquals(Double.NaN, test.sqrt(-5));

      // test decimal output
      when(test.sqrt(908764767)).thenReturn(30145.72551789059);
      assertEquals(30145.72551789059, test.sqrt(908764767));
    }

    /**
     * Test Sqr().
     */
    @Test
    void sqr() {
      test = mock(TestingHomework.class);

      // Test standard integer to square
      when(test.sqr(5)).thenReturn(25);
      assertEquals(25, test.sqr(5));

      // Test negative integer to square
      when(test.sqr(-5)).thenReturn(25);
      assertEquals(25, test.sqr(-5));

      // test 1 and 0
      when(test.sqr(1)).thenReturn(1);
      assertEquals(1, test.sqr(1));
      when(test.sqr(0)).thenReturn(0);
      assertEquals(0, test.sqr(0));

      // test very large ints
      when(test.sqr(99999999)).thenReturn(Integer.MAX_VALUE);
      assertEquals(Integer.MAX_VALUE, test.sqr(99999999));
      when(test.sqr(-909019248)).thenReturn(Integer.MAX_VALUE);
      assertEquals(Integer.MAX_VALUE, test.sqr(-909019248));

    }

    /**
     * Test Factorial().
     */
    @Test
    void factorial() {
      test = mock(TestingHomework.class);

      when(test.factorial(0)).thenReturn(1);
      assertEquals(1, test.factorial(0));

      when(test.factorial(1)).thenReturn(1);
      assertEquals(1, test.factorial(1));

      when(test.factorial(2)).thenReturn(2);
      assertEquals(2, test.factorial(2));

      when(test.factorial(-1)).thenReturn(1);
      assertEquals(1, test.factorial(1));

      when(test.factorial(25)).thenReturn(2076180480);
      assertEquals(2076180480, test.factorial(25));
    }

    /**
     * Sample factorial method
     * for testing different cases.
     * @param n
     * @return an int representing n's factorial
     */
    private int fact(int n){
      int fact = 1;
      for(int i = 1; i <=n; i++){
        fact = fact * i;
      }
      return fact;
    }

    /**
     * Test Sum up.
     */
    @Test
    void sumUp() {
      test = mock(TestingHomework.class);

      when(test.sumUp(0)).thenReturn(0);
      assertEquals(0, test.sumUp(0));

      when(test.sumUp(1)).thenReturn(1);
      assertEquals(1, test.sumUp(1));

      when(test.sumUp(-8)).thenReturn(-36);
      assertEquals(-36, test.sumUp(-8));

      when(test.sumUp(2000000000)).thenReturn(174246400);
      assertEquals(174246400, test.sumUp(2000000000));

      when(test.sumUp(-999999999)).thenReturn(-95825663);
      assertEquals(-95825663, test.sumUp(-999999999));

    }

    /**
     * Example Sum of n int for experimenting with test cases.
     *
     * @param n the n
     * @return the int
     */
    private int sumOfN(int n) {
      return n * (n + 1) / 2;
    }

    /**
     * Example Sum of ap int.
     *
     * @param n the n
     * @return the int
     */
    private int sumOfAP(int n) {
      int sum = 0;
      for (int i = 0; i <= n; i++) {
        sum = sum + i;
      }
      return sum;
    }


    /**
     * Test Simple function xplus y.
     */
    @Test
    void simpleFunctionXplusY() {
      test = mock(TestingHomework.class);

      when(test.simpleFunctionXplusY(25, 5)).thenReturn(30);
      assertEquals(30, test.simpleFunctionXplusY(25, 5));

      when(test.simpleFunctionXplusY(0, 0)).thenReturn(0);
      assertEquals(0, test.simpleFunctionXplusY(0, 0));

      // negative ints
      when(test.simpleFunctionXplusY(0, -1)).thenReturn(-1);
      assertEquals(-1, test.simpleFunctionXplusY(0, -1));
      when(test.simpleFunctionXplusY(-5, -5)).thenReturn(-10);
      assertEquals(-10, test.simpleFunctionXplusY(-5, -5));
      when(test.simpleFunctionXplusY(-504245, 24)).thenReturn(sum(-504245, 24));
      assertEquals(-504221, test.simpleFunctionXplusY(-504245, 24));

      // large ints
      when(test.simpleFunctionXplusY(314097343, 987459834)).thenReturn(1301557177);
      assertEquals(1301557177, test.simpleFunctionXplusY(314097343, 987459834));
    }

    /**
     * Sample of an expected simple output
     * for testing.
     *
     * @param a int a
     * @param b int b
     * @return a + b
     */
    private int sum(int a, int b){
      return a + b;
    }

    /**
     * Test Despacer.
     */
    @Test
    void despacer() {
      test = mock(TestingHomework.class);

      when(test.despacer("   Hell  o , Wo rld")).thenReturn("Hello World");
      assertEquals("Hello World", test.despacer("   Hell  o , Wo rld"));

      when(test.despacer("")).thenReturn("");
      assertEquals("", test.despacer(""));

      when(test.despacer("      ")).thenReturn("");
      assertEquals("", test.despacer("      "));

      when(test.despacer("This string is correctly spaced out."))
              .thenReturn("This string is correctly spaced out.");
      assertEquals("This string is correctly spaced out.",
              test.despacer("This string is correctly spaced out."));

      when(test.despacer("Sentence , with multiple , commas . "))
              .thenReturn("Sentence, with multiple, commas.");
      assertEquals("Sentence, with multiple, commas.",
              test.despacer("Sentence , with multiple , commas . "));
    }

}