import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class OutputNumbersTest {

  private OutputNumbers a;

  @Before
  public void setUp() throws Exception {
    a = new OutputNumbers();
  }

  @Test
  public void outputNum() {
    String result1 = Arrays.toString(a.outputNum(0, 5).toArray());
    String result2 = Arrays.toString(a.outputNum(-1, 20).toArray());
    String result3 = Arrays.toString(a.outputNum(1000, 1001).toArray());
    String result4 = Arrays.toString(a.outputNum(300, 412).toArray());
    String result5 = Arrays.toString(a.outputNum('a', 'g').toArray());
    assertEquals("[1, 3]", result1);
    assertEquals("[1, 3, 5, 7, 9, 11, 13, 15, 17, 19]", result2);
    assertEquals("[]", result3);
    assertEquals("[301, 303, 305, 307, 309, 311, 313, 315, 317, 319, " +
            "321, 323, 325, 327, 329, 331, 333, 335, 337, 339, 341, 343, 345, " +
            "347, 349, 351, 353, 355, 357, 359, 361, 363, 365, 367, 369, 371, " +
            "373, 375, 377, 379, 381, 383, 385, 387, 389, 391, 393, 395, 397, " +
            "399, 401, 403, 405, 407, 409, 411]", result4);
    assertEquals("[99, 101]", result5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOutputNumException1(){
    String result = Arrays.toString(a.outputNum(0, 0).toArray());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOutputNumException2(){
    String result = Arrays.toString(a.outputNum(100, 5).toArray());
  }

}