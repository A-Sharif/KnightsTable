import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 

public class RankDeck extends Deck{
	String[] rank = new String[] {"Squire", "Knight", "Champion Knight"};
	int[] pt = new int[] {5, 10, 20};

	public RankDeck() throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		
		Image image; 
		Card aCard;
		String path;
		
		
		for (int i = 0; i < 3; i++) {
			//path = "src/main/resources/R " + rank[i] + ".jpg";
			//image = new Image(new FileInputStream(path)); 
			for (int j=0; j < 4; j++) {
				aCard = new Rank(rank[i], pt[i]);
				aDeck.add(aCard);
				//aCard.print();
			}
		}
	}
	
	
	/*
	public static void main(String[] args) {
		try {
			Deck rank = new RankDeck();
			rank.printDeck();
			rank.shuffle();
			rank.printDeck();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

}
