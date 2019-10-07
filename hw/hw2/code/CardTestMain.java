
/**
 * @author Owen Astrachan
 *
 */
public class CardTestMain {

    public static void main(String[] args) {
        IHandPropertyTester[] testers = {
            new FlushProperty(),
            new FlushProperty(),
            new AllRed(),
            new HasPair(),
            new AnythingGoes()
        };
        RealGuiTester rgt = new RealGuiTester(testers);
    }
}
