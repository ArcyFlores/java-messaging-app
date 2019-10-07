import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * OutputNumbers is a class that outputs a series of numbers
 * It implements all the methods dictated in the NumberSequence Interface.
 */
public class OutputNumbers implements NumberSequence {

  /**
   * Empty Constructor.
   */
  public OutputNumbers(){
    // left empty for future functionality
  }

  @Override
  public List<Integer> outputNum(int a, int b) throws IllegalArgumentException {
    List<Integer> output = new ArrayList<>();
    if (a >= b) {
      throw new IllegalArgumentException("The first integer must be smaller than the second" +
              "one inputted.");
    }
    while(a < b - 1){
      a = a + 1;
      if(a % 2 == 1)
      output.add(a);
    }
    return output;
  }

  public static void main(String[] args) {
    System.out.println("Enter an integer:");
    Scanner in = new Scanner(System.in);
    int a = in.nextInt();
    System.out.println("Enter another integer larger than first one:");
    int b = in.nextInt();

    OutputNumbers output = new OutputNumbers();
    System.out.println(output.outputNum(a, b));
  }
}
