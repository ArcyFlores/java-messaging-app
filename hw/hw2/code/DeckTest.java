/*
 * @author Owen Astrachan
 * 
 * Created on Apr 15, 2004
 *
 */
import java.util.*;
public class DeckTest {
	
	public static boolean isSorted(ICard[] list){
		for(int k=1; k < list.length; k++){
			if (list[k-1].compareTo(list[k]) > 0){
				return false;
			}
		}
		return true;
	}
	public static void printCards(ArrayList cards){
		for(int k=0; k < cards.size(); k++){
			System.out.println(cards.get(k));
		}
	}
	public static void main(String[] args){
		Deck d = new Deck();
		ArrayList list = new ArrayList();
		while (d.hasNext()){
			list.add(d.next());
		}
		printCards(list);
		Collections.sort(list);
		printCards(list);
		ICard[] clist = (ICard[]) list.toArray(new ICard[0]);
		System.out.println("sorted? "+isSorted(clist));
	}	
}
