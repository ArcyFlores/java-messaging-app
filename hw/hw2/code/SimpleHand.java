
/**
 * @author Owen Astrachan
 *
 */
public class SimpleHand {
    
    public SimpleHand(){
        // currently nothing to do
    }
    
    public boolean allRed(ICard[] hand){
        for(int k=0; k < hand.length; k++){
            if (hand[k].getSuit() == ICard.SPADES ||
                hand[k].getSuit() == ICard.CLUBS){
                return false;
            }
        }
        return true;
    }
}
