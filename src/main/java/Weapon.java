import javafx.scene.image.Image;

public class Weapon extends Card{

   static int id = 0;
   int battlepoint;
	
	public Weapon(Image i, String n, int pt) {
		// TODO Auto-generated constructor stub
		super(i, n);
		battlepoint = pt;
		id++;
	}
	
	public int getBattlepoint() {
		return battlepoint;
	}

}
