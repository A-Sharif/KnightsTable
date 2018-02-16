import javafx.scene.image.Image;

public class Quest extends Card{
	
	static int id=0;
	int stage;
	String Foe="";

	public Quest(Image i, String n, int stg) {
		// TODO Auto-generated constructor stub
		super(i, n);
		stage = stg;
		id++;
	}
	
	public Quest(Image i, String n, int stg, String f) {
		// TODO Auto-generated constructor stub
		super(i, n);
		stage = stg;
		Foe = f;
		id++;
	}
	
	public int getStage() {
		return stage;
	}
	
	public String getFoe() {
		return Foe;
	}

}
