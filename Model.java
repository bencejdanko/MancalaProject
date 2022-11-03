import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {

	private Player A, B, currentPlayer;
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
		A = new Player(0);
		B = new Player(1);
		currentPlayer = A;
		listeners = new ArrayList<ChangeListener>();
		pitStoneData = new ArrayList<Integer>(Arrays.asList(new Integer[14]));
		Collections.fill(pitStoneData, startingStones);
	}

	
	public void attach(ChangeListener c) {
		listeners.add(c);
	}
	
	public ArrayList<Integer> getPitStones() {
		return pitStoneData; 
    }

	public Player getCurrentPlayer(){
		return currentPlayer;
	}

	public void setCurrentPlayer(int i){
		if (i == 0){
			currentPlayer = B;
		}
		else{
			currentPlayer = A;
		}
	}
	
	//	13		12	11	10	9	8	7
	//	
	//	B									A
	//
	//			0	1	2	3	4	5		6
	
//	public void update(int ID) {
//		int hand = pitStoneData.get(ID);
//		pitStoneData.set(ID, 0);
//		int playerTurn = turn%2; //if 0, player A's turn, if 1, player B's turn
//		int IDcounter = ID;
//		IDcounter++;
//
//		while (hand > 0) {
//			if (IDcounter == 6) { //if the stone is dropped into mancala A
//
//				if (playerTurn == 0) { //if it is player A's turn
//					mancalaAStones++;
//					hand--;
//					if (hand == 0) turn++; //if the last stone is dropped in mancala A as player A, then they get an extra turn
//				}
//
//				IDcounter++;
//
//			} else if (IDcounter == 13) { //if the stone is dropped into mancala B
//
//				if (playerTurn == 1) { //if it is player B's turn
//					mancalaBStones++;
//					hand--;
//					if (hand == 0) turn++; //if the last stone is dropped into mancala B as player B, then they get an extra turn
//				}
//				IDcounter = 0;
//
//			} else {
//
//				pitStoneData.set(IDcounter, pitStoneData.get(IDcounter)+1);
//				IDcounter++;
//				hand--;
//				if(hand == 0 && pitStoneData.get(IDcounter)==1 && IDcounter < 6 && playerTurn == 0) { //if the last stone is dropped into an empty pit on player A's side
//					mancalaAStones += pitStoneData.get(12-IDcounter) + 1; //take the stones from the opposite pit and add them to mancala A
//					pitStoneData.set(12-IDcounter, 0);
//					pitStoneData.set(IDcounter, 0);
//
//				} else if (hand == 0 && pitStoneData.get(IDcounter)==1 && IDcounter > 6 && playerTurn == 1) { //if the last stone is dropped into an empty pit on player B's side
//					mancalaBStones += pitStoneData.get(12-IDcounter) + 1; //take the stones from the opposite pit and add them to mancala B
//					pitStoneData.set(12-IDcounter, 0);
//					pitStoneData.set(IDcounter, 0);
//				}
//			}
//		}
//
//		//check if game is over. Keep in mind, indexes 0-5 are player A's pits, 7-12 are player B's pits. 6 and 13 are mancala A and B respectively.
//		if (pitStoneData.subList(0, 5).stream().mapToInt(Integer::intValue).sum() == 0) {
//			mancalaBStones += pitStoneData.subList(7, 12).stream().mapToInt(Integer::intValue).sum();
//			pitStoneData.subList(7, 12).clear();
//			Collections.fill(pitStoneData.subList(0, 5), 0);
//		} else if (pitStoneData.subList(7, 12).stream().mapToInt(Integer::intValue).sum() == 0) {
//			mancalaAStones += pitStoneData.subList(0, 5).stream().mapToInt(Integer::intValue).sum();
//			pitStoneData.subList(0, 5).clear();
//			Collections.fill(pitStoneData.subList(7, 12), 0);
//		}
//
//		turn++;
//
//		for (ChangeListener l : listeners){
//			l.stateChanged(new ChangeEvent(this));
//		}
//	}

	public void updateStones(int id, int stones)
	{
		pitStoneData.set(id, stones);
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}

}
