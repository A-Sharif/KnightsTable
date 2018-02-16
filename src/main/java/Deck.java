import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Deck {
	int numCards;

	ArrayList<Card> aDeck = new ArrayList<Card>();

    
	public Deck() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ArrayList<Card> getDeck() {
		return aDeck;
	}
	
	public void shuffle() {
		Collections.shuffle(aDeck);
	}
	
	public void add(Card c) {
		aDeck.add(c);
	}

	public int size() {
		return aDeck.size();
	}
	
	public void printDeck() {
		System.out.println(aDeck.size());
		for (int i = 0; i < aDeck.size(); i++) {
			aDeck.get(i).print();
		}
	}

}
