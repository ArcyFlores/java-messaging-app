
/**
 * @author Owen Astrachan
 *
 */
import java.util.Arrays;

public class HasPair implements IHandPropertyTester {

    /**
     * Return true if hand has a pair, false otherwise,
     * relies on sorting putting equal-rank cards adjacent
     * @param hand specifies the hand being testede
     * @return true iff there's a pair in hand
     */
    public boolean hasProperty(ICard[] hand) {
        Arrays.sort(hand);
        for(int k=1; k < hand.length; k++){
            if (hand[k].getRank() == hand[k-1].getRank()){
                return true;
            }
        }
        return false;
    }
}
