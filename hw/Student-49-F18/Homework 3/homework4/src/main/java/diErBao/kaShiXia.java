package diErBao;


import java.util.Objects;

import static diErBao.kaShiXia.KaDengJíEnum.lookupEnumName;

/**
 * @author yì xiē rén
 */
public class kaShiXia implements Ka {

  private final int kaHao; // card number
  private final int suit;

  public kaShiXia(KaDengJíEnum kaHao, int suit) {

    if (kaHao == null) {
      throw new IllegalArgumentException("ill-formed ka");
    }

    validateSuit(suit);

    this.kaHao = kaHao.value;

    this.suit = suit;
  }

  /**
   * private method to determine if a suit is within range
   * changed from original 4 to 3 since there are only 4 suits
   */
  private void validateSuit(int suit) {
    if ((suit < 0) || (suit > 3)) {
      throw new IllegalArgumentException("ill-formed ka");
    }
  }

  /**
   * Instantiates a new ka shi xia.
   *
   * @param kaHao the ka hao
   * @param suit  the suit
   */
  public kaShiXia(int kaHao, int suit) {

    validateSuit(suit);

    if ((kaHao < 0) || (kaHao > 12)) {
      throw new IllegalArgumentException("ill-formed card");
    }

    this.kaHao = kaHao;

    this.suit = suit;
  }

  /*
   * @see diErBao.Ka#getRank()
   */
  public int getRank() {

    return kaHao;
  }

  /*
   * @see diErBao.Ka#getSuit()
   */
  public int getSuit() {

    return suit;
  }

  @Override
  public String toString() {
    return kaHao + " of " + KaPianTaoZhuangEnum.getKaPianTaoZhuang(getSuit());
  }

  public boolean equals(Object object) {
    if (object instanceof Ka) {

      kaShiXia ka = (kaShiXia) object;
      return (ka.kaHao == this.kaHao && ka.suit == this.suit);
    } else
      return super.equals(object);
  }

  @Override
  public int hashCode() {
    return Objects.hash(kaHao, suit);
  }

  /**
   * Enum of card suits
   */
  public enum KaPianTaoZhuangEnum {
    xin, tieQiao, zuanShi, juLeBu; // hearts, spades, diamonds, clubs

    private static KaPianTaoZhuangEnum[] list = KaPianTaoZhuangEnum.values();

    public static KaPianTaoZhuangEnum getKaPianTaoZhuang(int i) {
      return list[i];
    }

  }

  /**
   * Enum of card ranks
   */
  public enum KaDengJíEnum {
    TWO(0b0101), THREE(0b1001), FOUR(0b1101), FIVE(0b0100), SIX(0b1000),
    SEVEN(0b1100), EIGHT(0b0011), NINE(0b0111),
    TEN(0b1011), JACK(0b0010), QUEEN(0b0110), KING(0b1010), ACE(0b0001);

    private int value;

    private KaDengJíEnum(int value) {
      this.value = value;
    }

    public static KaDengJíEnum lookupEnumName(int correspondingValue) {

      for (KaDengJíEnum kaRank : KaDengJíEnum.values()) {

        if (correspondingValue == kaRank.value)
          return kaRank;
      }
      return null;
    }
  }

  public static void main(String[] args) {
    kaShiXia test = new kaShiXia(KaDengJíEnum.ACE, 3);
    System.out.println(test.toString());
    kaShiXia a = new kaShiXia(1, 1);
    System.out.println(lookupEnumName(5).name());
    System.out.println("________________________");
    for(KaDengJíEnum kaRank: KaDengJíEnum.values()){
      System.out.println(kaRank.value);
    }
    System.out.println("________________________");
    System.out.println(KaDengJíEnum.TWO.value);
    System.out.println(0b0001);
  }

}
