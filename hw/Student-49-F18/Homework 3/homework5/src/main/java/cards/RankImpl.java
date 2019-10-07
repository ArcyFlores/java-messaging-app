package cards;

/**
 * The type Rank.
 *
 * @author aflores
 * A RankImpl is the class that represents a Card's rank and
 * will represent one of the following values:
 * Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King.
 */
public class RankImpl implements Rank {

  /**
   * All Possible Ranks in RankImpl.
   */
  private static final String ACE = "Ace";
  private static final String TWO = "2";
  private static final String THREE = "3";
  private static final String FOUR = "4";
  private static final String FIVE = "5";
  private static final String SIX = "6";
  private static final String SEVEN = "7";
  private static final String EIGHT = "8";
  private static final String NINE = "9";
  private static final String TEN = "10";
  private static final String JACK = "Jack";
  private static final String QUEEN = "Queen";
  private static final String KING = "King";


  /**
   * A RankImpl's properties.
   */
  private String name;
  private int pip;
  private char shortName;

  /**
   * Constructor for a RankImpl.
   *
   * @param rank the rank
   */
  RankImpl(String rank) {
    if (validateRank(rank)) {
      this.name = rank.toLowerCase();
      this.pip = createPip(rank);
      this.shortName = createShortName(rank);
    } else {
      throw new IllegalArgumentException("Incorrect string entered. Rank must be one of: \n" +
              "Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, or King.");
    }
  }

  /**
   * Helper function to assign a Rank a short name depending on its rank.
   *
   * @param rank the rank to classify
   * @return a char representing a a Rank
   */
  private char createShortName(String rank) {
    char rankShortName = '0';
    if (ACE.equalsIgnoreCase(rank)) {
      rankShortName = 'A';
    } else if (TWO.equals(rank)) {
      rankShortName = '2';

    } else if (THREE.equals(rank)) {
      rankShortName = '3';

    } else if (FOUR.equals(rank)) {
      rankShortName = '4';

    } else if (FIVE.equals(rank)) {
      rankShortName = '5';

    } else if (SIX.equals(rank)) {
      rankShortName = '6';

    } else if (SEVEN.equals(rank)) {
      rankShortName = '7';

    } else if (EIGHT.equals(rank)) {
      rankShortName = '8';

    } else if (NINE.equals(rank)) {
      rankShortName = '9';

    } else if (TEN.equalsIgnoreCase(rank)) {
      rankShortName = 'T';

    } else if (JACK.equalsIgnoreCase(rank)) {
      rankShortName = 'J';

    } else if (QUEEN.equalsIgnoreCase(rank)) {
      rankShortName = 'Q';

    } else if (KING.equalsIgnoreCase(rank)) {
      rankShortName = 'K';
    }
    return rankShortName;
  }

  /**
   * Helper function to assign a pip value to a given Rank.
   *
   * @param rank the rank to classify
   * @return an integer representing a Rank's pip value
   */
  private int createPip(String rank) {
    int rankPip = 0;
    if (ACE.equals(rank)) {
      rankPip = 1;
    } else if (TWO.equals(rank)) {
      rankPip = 2;
    } else if (THREE.equals(rank)) {
      rankPip = 3;
    } else if (FOUR.equals(rank)) {
      rankPip = 4;
    } else if (FIVE.equals(rank)) {
      rankPip = 5;
    } else if (SIX.equals(rank)) {
      rankPip = 6;
    } else if (SEVEN.equals(rank)) {
      rankPip = 7;
    } else if (EIGHT.equals(rank)) {
      rankPip = 8;
    } else if (NINE.equals(rank)) {
      rankPip = 9;
    } else if (TEN.equals(rank)) {
      rankPip = 10;
    }
    return rankPip;
  }

  /**
   * Method to validate that valid string has been inserted.
   *
   * @return a boolean if valid string, false otherwise.
   */
  private boolean validateRank(String suit) {
    return ACE.equalsIgnoreCase(suit) || TWO.equals(suit) || THREE.equals(suit)
            || FOUR.equals(suit) || FIVE.equals(suit) || SIX.equals(suit)
            || SEVEN.equals(suit) || EIGHT.equals(suit) || NINE.equals(suit)
            || TEN.equals(suit) || JACK.equalsIgnoreCase(suit) || QUEEN.equalsIgnoreCase(suit)
            || KING.equalsIgnoreCase(suit);
  }

  /**
   * Returns the Rank as a String.
   *
   * @return the name of the rank
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the Pip of a Card. Pips are like ranks expect that face cards all have
   * pips of 0, where as the other cards pips correspond to their name.
   * I.e. Ace has pip of 1, 2 has pip of 2 and so on.
   *
   * @return a rank's pip
   */
  public int getPips() {
    return this.pip;
  }

  /**
   * Returns a char representing the Card.
   *
   * @return a char
   */
  public char getShortName() {
    return this.shortName;
  }
}
