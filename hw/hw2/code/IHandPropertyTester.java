/**
 * An interface for testing properties
 * of a hand of cards, where the hand is
 * an array of ICard objects, presumably obtained
 * from a Deck and then passed to an object that
 * implements this interface.
 * 
 * @author Owen Astrachan
 *
 */
public interface IHandPropertyTester {
    /**
     * Returns true if and only if hand satisfies this property
     * @param hand is the hand tested
     * @return true if hand satisfies property being tested
     */
    public boolean hasProperty(ICard[] hand);
}
