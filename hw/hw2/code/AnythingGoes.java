
/**
 * @author Owen Astrachan
 *
 */
public class AnythingGoes implements IHandPropertyTester {
    
    /**
     * Always returns true
     */
    public boolean hasProperty(ICard[] hand) {
        return true;
    }
}
