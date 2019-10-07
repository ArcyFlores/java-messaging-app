
/**
 * This code may be used in any way provided that
 * the work is attributed to the author. The license
 * for use allows modification, use-for-profit, but requires
 * distribution with similar qualifications --- see
 * the license at http://creativecommons.org/licenses/by-sa/1.0
 *
 *  @author Owen Astrachan
 *
 */
public class CardAnswers {
    public boolean isRed(ICard card){
        return card.getSuit() == ICard.HEARTS ||
               card.getSuit() == ICard.DIAMONDS;
    }
    
    public boolean isPair(ICard a, ICard b) {
        return a.getSuit() == b.getSuit();
    }
    
    public boolean isFlush(ICard[] hand){
         if (hand.length == 0 || hand.length == 1){
             return true;
         }
         int suit = hand[0].getSuit();
         for(int k=1; k < hand.length; k++){
             if (hand[k].getSuit() != suit){
                 return false;
             }
         }
         return true;
    }
    
    public int handTotal(ICard[] hand){
        int total = 0;
        int aceTotal = 0;
        for(int k=0; k < hand.length; k++){
            int value = hand[k].getRank();
            if (value >= 10){
                value = 10;
            }
            if (value == 1){
                aceTotal++;
            }
            else {
                total += value;
            }
        }
        // now determine how to count aces
        if (aceTotal > 0){
            if (total <= 10){  // can ace count as 11?
                total += 11;   // ace is high, one used
                aceTotal--;
            }
            total += aceTotal; // only one can count as 11
        }
        return total;
    }
    
}
