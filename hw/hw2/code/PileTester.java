import java.util.*;

public class PileTester {
	private IDisplayer myDisplayer;
	private Deck myDeck;
	
	public void showPile(IPile pile){
		pile.display(myDisplayer);
		System.out.println();
	}
	
	public PileTester(){
		myDisplayer = new TextDisplayer();
		myDeck = new Deck();
	}
	
	public void testTableauxPile(){
		IPile tp = new TableauxPile(myDeck,0);
		ICard[] cards = new ICard[52];
		int index = 0;
		myDeck = new Deck();

		while (myDeck.hasNext()){
			cards[index++] = (ICard) myDeck.next();
		}
		Arrays.sort(cards,0,cards.length,
				new Comparator(){
					public int compare(Object a, Object b){
						ICard ac = (ICard) a;
						ICard bc = (ICard) b;
						if (ac.getRank() == bc.getRank()){
							return ac.getSuit() - bc.getSuit();
						}
						else{
							return ac.getRank() - bc.getRank();
						}
					}
		});
		for(int k=0; k < cards.length; k++){
			if(tp.addCard(cards[k])){
				showPile(tp);
			}
		}
	}
	
	public void testAcePile(){
		ICard[] spades = new ICard[13];
		int index = 0;
		myDeck = new Deck();
		while (myDeck.hasNext()){
			ICard card = (ICard) myDeck.next();
			if(card.getSuit() == ICard.SPADES){
				spades[index++] = card;
			}
		}
		Arrays.sort(spades);
		IPile ap = new AcePile();
		for(int k=0; k < spades.length; k++){
			ap.addCard(spades[k]);
			showPile(ap);
		}
	}
	
	public static void main(String[] args){
		PileTester tester = new PileTester();
		tester.testAcePile();
		tester.testTableauxPile();
	}
}
