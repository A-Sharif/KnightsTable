import java.util.ArrayList;

public class Hand {
	
	int numCards;

	ArrayList<Card> hand = new ArrayList<Card>();

    
	//TODO: getters and setters for all attributes

	public int getNumCards() {
		return numCards; 
	}

	public void setNumCards(int n) {
		numCards = n; 
	}
	
	public int size() {
	    return hand.size();
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> h) {
		hand = h;
	}

	public void removeAll() {
		hand.removeAll(hand);
	}
	
	public void addCard(Card c) {
      numCards++;
      hand.add(c); 
	}
	
	public Hand(ArrayList<Card> h) {
		hand = h;
	}
	
}

	

