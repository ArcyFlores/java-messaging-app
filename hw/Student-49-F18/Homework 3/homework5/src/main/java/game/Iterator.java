package game;

/**
 *
 * @param <T>
 */
public interface Iterator<T> {
  /**
   *
   */
  void first();     // set to first

  /**
   *
   */
  void next();      // advance

  /**
   *
   * @return
   */
  boolean isDone(); // is done

  /**
   *
   * @return
   */
  T current(); // get current
}
