import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model implements ChangeListener  {

	int A;
	int B;
	int startingStones;
	
	public Model(int startingStones)  {
		this.startingStones = startingStones;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
