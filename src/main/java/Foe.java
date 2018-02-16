import javafx.scene.image.Image;
import java.util.ArrayList;

public class Foe extends Card{

    static int id = 0;
    ArrayList<Integer> battlepoint;
	
	public Foe(Image i, String n, int pt) {
		// TODO Auto-generated constructor stub
		super(i, n);
		id++;
		battlepoint = new ArrayList<Integer>();
		battlepoint.add(pt);
	}
	
	// constructor used when Foe has two battle points
	public Foe(Image i, String n, int pt1, int pt2) {
		// TODO Auto-generated constructor stub
		super(i, n);
		id++;
		battlepoint = new ArrayList<Integer>();
		battlepoint.add(pt1);
		battlepoint.add(pt2);
	}
	
	// TO DO: return higher points based on situation
	public int getBattlePoint() {
		return battlepoint.get(0);
	}

}
