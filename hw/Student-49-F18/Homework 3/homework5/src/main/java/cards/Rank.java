package cards;

/**
 * @author aflores
 * The interface Rank.
 *
 * A Rank is a classification for a Card. It can be one of the following:
 * Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, or King.
 */
public interface Rank {

  /**
   * Returns the Rank as a String.
   *
   * @return the name of the rank
   */
  String getName();

  /**
   * Returns the Pip of a Card. Pips are like ranks expect that face cards all have
   * pips of 0, where as the other cards pips correspond to their name.
   * I.e. Ace has pip of 1, 2 has pip of 2 and so on.
   *
   * @return a rank's pip
   */
  int getPips();

  /**
   * Returns a char representing the Card.
   *
   * @return a char
   */
  char getShortName();
}
