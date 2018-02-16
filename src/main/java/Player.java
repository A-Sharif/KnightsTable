import javafx.scene.image.Image;

public class Player {
	String playerName; 
	int numShields = 0; 
	Hand cards; 
	Image rank;
	
	//TODO: ALLY SLOT
	//TODO:add the shield symbol attribute

	public String getName() {
		return playerName; 
	}

	public void setName(String n) {
		playerName = n; 
	}

	public int getShields() {
		return numShields; 
	}

	public void setShields(int s) {
		numShields = s; 
	}

	public Hand getHand() {
		return cards; 
	}
	
	public void setHand(Hand h) {
		cards = h; 
	}
	
	public Image getRankImage() {
		return rank;
	}
	
	public void setRankImage(Image r) {
		rank = r;
	}

	public Player(String n, int s) {
		playerName = n; 
		numShields = s; 
	}

	
}
