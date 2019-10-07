import java.util.*;

public class TableauxPile implements IPile {

	private LinkedList myCards;
	private ICard myTopCard;
	
	public TableauxPile(Deck d, int count){
		myCards = new LinkedList();
		for(int k=0; k < count; k++){
			forceAdd((ICard) d.next());
		}
	}
	
	private void forceAdd(ICard card){
		myCards.add(card);
		myTopCard = card;
	}
	
	public ICard viewTopCard() {
		return myTopCard;
	}

	public void removeTopCard() {
		myCards.removeLast();
	}

	public boolean addCard(ICard card) {
		if (myTopCard == null){
			forceAdd(card);
			return true;
		}
		else if (myTopCard.getRank() == card.getRank()-1){
			int mysuit = myTopCard.getSuit();
			int othersuit = card.getSuit();
			if ( ((mysuit == ICard.DIAMONDS || mysuit == ICard.HEARTS) &&
				  (othersuit == ICard.CLUBS || othersuit == ICard.SPADES)) 
			||
				  ((mysuit == ICard.CLUBS || mysuit == ICard.SPADES) &&
				   (othersuit == ICard.DIAMONDS || othersuit == ICard.HEARTS))){
				forceAdd(card);
				return true;
			}
			return false;
	
		}
		else {
			return false;
		}
	}

	public void display(IDisplayer displayer) {
		Iterator it = myCards.iterator();
		while (it.hasNext()){
			displayer.show((ICard) it.next(),true);
		}
	}

}
