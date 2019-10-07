package homework.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A Junit test for the Example class.
 */
class ExampleTest {

  /**
   * The Example to test.
   */
  Example example;

  /**
   * Sets up a new Example.
   */
  @BeforeEach
  void setUp() {
    example = new Example();
  }

  /**
   * Tests the Example method.
   */
  @Test
  void example() {
    int test = example.example(" This is a test.");
    assertEquals(0, test);

    // single space
    test = example.example(" ");
    assertEquals(0, test);

    // one space
    test = example.example("  2");
    assertEquals(1, test);

    // 2 spaces
    test = example.example("  a  7");
    assertEquals(2, test);


    // should output 3 according to comments
    test = example.example("  a,  b,  c  ");
    assertEquals(3, test);

    // should output 4
    test = example.example("  a,  b,  c,  d");
    assertEquals(4, test);
  }

}