import javafx.scene.image.Image;

public class Rank extends Card{
	static int id = 0;
	int battlepoint;
	
	public Rank(Image i, String n, int pt) {
		// TODO Auto-generated constructor stub
		super(i, n);
		battlepoint = pt;
		id++;
	}
	
	public Rank(String n, int pt) {
		// TODO Auto-generated constructor stub
		super(n);
		battlepoint = pt;
		id++;
	}
	
	public int getBattlePoint() {
		return battlepoint;
	}
	
	public void print() {
		System.out.println("Rank: " + getName() + " " + getBattlePoint());
	}

}
