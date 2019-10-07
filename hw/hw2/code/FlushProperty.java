
/**
 * @author Owen Astrachan
 *
 */
public class FlushProperty implements IHandPropertyTester {

    /**
     * Returns true iff hand is a flush: all the same suit
     * @param hand specifies the hand
     * @return true if all cards in hand have the same suit
     */
    
    public boolean hasProperty(ICard[] hand) {
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
}
