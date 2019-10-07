/*
 * Created on Oct 14, 2004
 *
 */

/**
 * @author Owen Astrachan
 *
 */
public class StatisticalCardTestMain {

    public static void main(String[] args) {
        IHandPropertyTester[] testers = {
                new FlushProperty(),
                new AllRed(),
                new HasPair(),
                new AnythingGoes()
            };
        int trials = 10000;
        int[] results = new int[testers.length];
        String[] labels = new String[testers.length];
        for(int k=0; k < testers.length; k++){
            labels[k] = testers[k].getClass().getName();
        }
        
        ICard[] hand = new ICard[5];
        for(int k=0; k < trials; k++){
            Deck d = new Deck();
            for(int j=0; j < 5; j++){
                hand[j] = (ICard) d.next();
            }
            for(int j=0; j < testers.length; j++){
                if (testers[j].hasProperty(hand)){
                    results[j]++;
                }
            }
        }
        System.out.println("*** results ***");
        for(int k=0; k < labels.length; k++){
            double percent = results[k]*100.0/trials;
            System.out.println(results[k] + "\t" + percent + "\t" + labels[k]);
        }
    }
}
