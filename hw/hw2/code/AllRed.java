
/**
 * @author Owen Astrachan
 *
 */
public class AllRed implements IHandPropertyTester {

    /**
     * Returns true if and only if hand has all red cards,
     * e.g., diamonds or hearts.
     * @param hand specifies the cards
     * @return true if hand contains only red cards
     */
    public boolean hasProperty(ICard[] hand) {
        for(int k=0; k < hand.length; k++){
            if (hand[k].getSuit() == ICard.SPADES ||
                hand[k].getSuit() == ICard.CLUBS){
                return false;
            }
        }
        return true;
    }
}
