import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {

	private Player A, B, currentPlayer;
	int startingStones;
	ArrayList<ChangeListener> listeners;
	ArrayList<Integer> stoneData; //from a1 to mancala A, then from b1 to mancala B
	String alert, prevAlert;


	public Model() {
		startingStones = 0;
		A = new Player(0);
		B = new Player(1);
		currentPlayer = A;
		listeners = new ArrayList<ChangeListener>();
		stoneData = new ArrayList<Integer>(Arrays.asList(new Integer[14]));
		Collections.fill(stoneData, startingStones); }	//default starting stones is 0
	
	public Model(int startingStones)  {
//		System.out.println(startingStones);
		this.startingStones = startingStones;
		A = new Player(0);
		B = new Player(1);
		currentPlayer = A;
		listeners = new ArrayList<ChangeListener>();
		stoneData = new ArrayList<Integer>(Arrays.asList(new Integer[14]));
		Collections.fill(stoneData, startingStones);
//		stoneData.set(6, 0); //mancala A
//		stoneData.set(13, 0); //mancala B
	}

	
	public void attach(ChangeListener c) {
		listeners.add(c);
	}
	
	public ArrayList<Integer> getStoneData() {
		return (ArrayList<Integer>) stoneData.clone(); 
    }

	public Player getCurrentPlayer(){
		return currentPlayer;
	}

	public void setCurrentPlayer(int i){
		if (i == 0){
			currentPlayer = B;
		}
		else {
			currentPlayer = A;
		}
	}

	public void updateStones(int ID, int stones)
	{
		if (ID != -1) stoneData.set(ID, stones);
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}


	public boolean gameIsOver() {
		return (stoneData.subList(0, 6).stream().mapToInt(Integer::intValue).sum()) == 0 || (stoneData.subList(7, 13).stream().mapToInt(Integer::intValue).sum() == 0);
	}

	public int sumOfPits(Player p) {
		int sum = 0;
		for (int i = p.getRespectivePitsRange()[0]; i <= p.getRespectivePitsRange()[1]; i++) {
			sum += stoneData.get(i);
		}
		return sum;
	}

	public int getMancalaAStones(){
		return stoneData.get(6);
	}

	public int getMancalaBStones() {
		return stoneData.get(13);
	}

	public int setMancalaAStones(int stones) {
		return stoneData.set(6, stones);
	}

	public int setMancalaBStones(int stones) {
		return stoneData.set(13, stones);
	}

	public void removeAlert() {
		alert = null;
	}

	public String getAlert() {
		return alert;
	}
	public String getPreviousAlert(){
		return prevAlert;};

}
