import java.util.*;

public class AcePile implements IPile {

	private ArrayList myCards;
	private ICard myTopCard;
	
	public AcePile(){
		myCards = new ArrayList();
	}
	
	public ICard viewTopCard() {
		return myTopCard;
	}

	public void removeTopCard() {
		// cannot remove top card, do nothing
	}

	public boolean addCard(ICard card) {
		if (myCards.size() == 0 && card.getRank() == 1){
			myCards.add(card);
			myTopCard = card;
			return true;
		}
		else{
			if (myTopCard.getSuit() == card.getSuit() &&
				myTopCard.getRank() == card.getRank()-1){
				myCards.add(card);
				myTopCard = card;
				return true;
			}
		}
		return false;
	}

	public void display(IDisplayer displayer) {
		for(int k=0; k <myCards.size(); k++){
			displayer.show((ICard) myCards.get(k), true);
		}
	}

}
