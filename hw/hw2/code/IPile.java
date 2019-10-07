
public interface IPile {
	public ICard viewTopCard();
	public void removeTopCard();
	public boolean addCard(ICard card);
	public void display(IDisplayer displayer);
}
