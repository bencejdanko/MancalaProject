import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {

	private Player A;
	private Player B;
	int startingStones;
	ArrayList<ChangeListener> listeners;
	ArrayList<Integer> pitStoneData; //from b6 -> a1
	int mancalaAStones;
	int mancalaBStones;
	int turn;
	
	public Model(int startingStones)  {
		turn = 0;
		mancalaAStones = 0;
		mancalaBStones = 0;
		this.startingStones = startingStones;
		A = new Player();
		B = new Player();
		listeners = new ArrayList<ChangeListener>();
		pitStoneData = new ArrayList<Integer>(Arrays.asList(new Integer[13]));
		Collections.fill(pitStoneData, startingStones);
	}

	
	public void attach(ChangeListener c) {
		listeners.add(c);
	}
	
	public ArrayList<Integer> getPitStones() {
		return (ArrayList<Integer>) (pitStoneData.clone()); 
    }
	
	
	
	//	13		12	11	10	9	8	7
	//	
	//	B									A
	//
	//			0	1	2	3	4	5		6
	
	public void update(int ID) { 
		int hand = pitStoneData.get(ID);
		pitStoneData.set(ID, 0);
		int playerTurn = turn%2; //if 0, player A's turn, if 1, player B's turn
		int IDcounter = ID;
		IDcounter++;
		
		while (hand > 0) {
			
			if (IDcounter == 6) {
				
				if (playerTurn == 0) {
					mancalaAStones++;
					hand--;
				}
				
				IDcounter++;
				
			} else if (IDcounter == 13) {
				
				if (playerTurn == 1) {
					mancalaBStones++;
					hand--;
				}
				IDcounter = 0;
				
			} else { 
				
				pitStoneData.set(IDcounter, pitStoneData.get(IDcounter)+1);
				IDcounter++;
				hand--;
				
			}
		}
		
		turn++;
		
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}

	public void updateStones(int id, int stones)
	{
		pitStoneData.set(id, stones);
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
