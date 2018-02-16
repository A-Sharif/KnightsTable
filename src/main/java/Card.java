import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	//card class attributes

		//name of the card
		String name; 
		Image img;
		//- getters and setters for all attributes
		public String getName() {
			return name; 
		}
		public void setName(String n) {
			name = n; 
		}
		
		public Image getImg() {
			return img; 
		}
		
		public void setImg(Image i) {
			img = i;
		}

		
		//TODO: 
		//- add ability to specify image to constructor so we can have some kind of deckInitializer that
		//goes and builds the decks at the beginning of the game based on card frequency table provided in rules
		//- add ability to specify the name of the card to the constructor
		public Card(Image i, String n) {
            img = i;
            name = n;
		}
		
		// for test purpose
		public Card(String n) {
			name = n;
		}
		
		public void print() {
			System.out.println(getName());
		}
}
