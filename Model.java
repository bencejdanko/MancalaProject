import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class Model{

	private Player A;
	private Player B;
	int startingStones;
	private ArrayList<Integer> data;
	private ArrayList<ChangeListener> listeners;
	
	public Model(int startingStones)  {
		this.startingStones = startingStones;
		A = new Player();
		B = new Player();
		data = new ArrayList<Integer>();
		listeners = new ArrayList<ChangeListener>();
	}
	public ArrayList<Integer> getData() {
		return (ArrayList<Integer>) (data.clone());
	}
	public void attach(ChangeListener c) {
		listeners.add(c);
	}
	public void update(int pit, int count){
		data.set(pit, count);
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}
	
}
