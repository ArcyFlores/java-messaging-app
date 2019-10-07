package adapters;

import cards.Card;
import cards.Rank;
import cards.RankImpl;
import cards.Suit;
import cards.SuitImpl;
import diErBao.Ka;
import diErBao.kaShiXia;

/**
 *
 */
public class KaAdapter implements Card {

  /**
   * Adapted Ka object.
   */
  private kaShiXia adaptee;
  private kaShiXia.KaDengJíEnum enumRank;
  private Rank rank;
  private Suit suit;

  /**
   * The KaAdapter constructor.
   *
   * @param rank kind of rank
   * @param suit kind of suit
   */
  public KaAdapter(String rank, String suit) {
    int kaSuit = setKaSuit(suit);
    kaShiXia.KaDengJíEnum kaRank = mapKaRank(rank);
    adaptee = new kaShiXia(kaRank, kaSuit);
    this.rank = new RankImpl(rank);
    this.suit = new SuitImpl(suit);
  }

  /**
   * Method that assigns an enum depending on the given rank.
   *
   * @param rank the string rank
   * @return an enum
   */
  private kaShiXia.KaDengJíEnum mapKaRank(String rank) {
    switch (rank.toLowerCase()) {
      case "ace":
        return kaShiXia.KaDengJíEnum.ACE;
      case "2":
        return kaShiXia.KaDengJíEnum.TWO;
      case "3":
        return kaShiXia.KaDengJíEnum.THREE;
      case "4":
        return kaShiXia.KaDengJíEnum.FOUR;
      case "5":
        return kaShiXia.KaDengJíEnum.FIVE;
      case "6":
        return kaShiXia.KaDengJíEnum.SIX;
      case "7":
        return kaShiXia.KaDengJíEnum.SEVEN;
      case "8":
        return kaShiXia.KaDengJíEnum.EIGHT;
      case "9":
        return kaShiXia.KaDengJíEnum.NINE;
      case "10":
        return kaShiXia.KaDengJíEnum.TEN;
      case "jack":
        return kaShiXia.KaDengJíEnum.JACK;
      case "queen":
        return kaShiXia.KaDengJíEnum.QUEEN;
      case "king":
        return kaShiXia.KaDengJíEnum.KING;
      default:
        return null;
    }
  }

  /**
   * Retrieves a suits value to map with KaShiXia
   *
   * @param suit the given suit
   * @return an int representing a Ka suit
   */
  private int setKaSuit(String suit) {
    switch (suit.toLowerCase()) {
      case "hearts":
        return 0;
      case "spades":
        return 1;
      case "diamonds":
        return 2;
      default:
        return 3;
    }
  }

  /**
   * The KaAdapter constructor.
   *
   * @param rank kind of rank
   * @param suit kind of suit
   */
  public KaAdapter(int rank, int suit) {
    adaptee = new kaShiXia(rank, suit);
    this.rank = setRank(rank);
    this.suit = setSuit(suit);
  }

  /**
   * Maps a suit to an integer.
   */
  private Suit setSuit(int suit) {
    switch (suit) {
      case 0:
        return new SuitImpl("hearts");
      case 1:
        return new SuitImpl("spades");
      case 2:
        return new SuitImpl("diamonds");
      default:
        return new SuitImpl("clubs");
    }
  }

  /**
   * Method to return a Card's rank.
   *
   * @return a rank
   */
  @Override
  public Rank getRank() {
    return this.rank;
  }

  /**
   * Method to return a Card's rank.
   *
   * @param rank an integer rank
   * @return the string name of that rank
   */
  private Rank setRank(int rank) {
    String rankName = "";
    switch (rank) {
      case 0b0001:
        return new RankImpl("ace");
      case 0b0101:
        return new RankImpl("2");
      case 0b1001:
        return new RankImpl("3");
      case 0b1101:
        return new RankImpl("4");
      case 0b0100:
        return new RankImpl("5");
      case 0b1000:
        return new RankImpl("6");
      case 0b1100:
        return new RankImpl("7");
      case 0b0011:
        return new RankImpl("8");
      case 0b0111:
        return new RankImpl("9");
      case 0b1011:
        return new RankImpl("10");
      case 0b0010:
        return new RankImpl("jack");
      case 0b0110:
        return new RankImpl("queen");
      case 0b1010:
        return new RankImpl("king");
      default:
        return null;
    }

  }

  /**
   * Method that returns a string representation of a Card.
   *
   * @return a string
   */
  public String toString() {
    return "" + this.rank.getShortName() + " " + this.suit.getSymbol();
  }

  /**
   * Method to return a Card's Suit.
   *
   * @return the Card's suit
   */
  @Override
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Returns the Ka.
   *
   * @return this Ka to return.
   */
  public Ka getKa() {
    return this.adaptee;
  }


}
