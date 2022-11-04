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
	String dataAlert;

	final static String ALERT_NOT_PLAYER_TURN = "Not your turn!";
	final static String ALERT_PIT_EMPTY = "Pit is empty!";
	final static String ALERT_GO_AGAIN = "Go again!";
	final static String ALERT_GAME_OVER = "Game over!";

	public Model() { this(0); }	//default starting stones is 0
	
	public Model(int startingStones)  {
		this.startingStones = startingStones;
		A = new Player(0);
		B = new Player(1);
		currentPlayer = A;
		listeners = new ArrayList<ChangeListener>();
		stoneData = new ArrayList<Integer>(Arrays.asList(new Integer[14]));
		Collections.fill(stoneData, startingStones);
		stoneData.set(6, 0); //mancala A
		stoneData.set(13, 0); //mancala B
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

	public void updateStones(int ID)
	{
		dataAlert = parseUserChoice(ID);
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}

	public String parseUserChoice(int ID) {
		if (!currentPlayer.respectivePit(ID)) 			return ALERT_NOT_PLAYER_TURN;
		if (stoneData.get(ID) == 0) 					return ALERT_PIT_EMPTY;
		return dataParseAndAlert(ID); 
	}
	
	public String dataParseAndAlert(int ID) {
		currentPlayer.setHand(stoneData.get(ID));
		stoneData.set(ID, 0);
		int lastStoneID = passStonesAlong(ID); //returns ID of last stone and updates stoneData
		
		if (gameIsOver()) closeGame();
		if (landOnPlayerEmptyPit(lastStoneID)) 				return captureStonesAndAlert(lastStoneID);
		if (lastStoneID == currentPlayer.getMancalaID()) 	return ALERT_GO_AGAIN;

		setCurrentPlayer(currentPlayer.getPlayerID());
		return null; //no alert
		
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

	public int passStonesAlong(int ID) {
		int IDcounter = ID;
		while (currentPlayer.getHand() > 0) {
			IDcounter++;
			if (IDcounter >= 14) IDcounter = 0;

			if (IDcounter == currentPlayer.getOppositeMancalaID()) IDcounter++;
			else if (IDcounter == currentPlayer.getMancalaID()) {
				stoneData.set(IDcounter, stoneData.get(IDcounter)+1);
				currentPlayer.decreaseHand();
			}
			else {
				stoneData.set(IDcounter, stoneData.get(IDcounter)+1);
				currentPlayer.decreaseHand();
			}
		}
		return IDcounter; //last stone ID
	}

	public String captureStonesAndAlert(int ID) {
		int oppositeID = currentPlayer.getOppositePitID(ID);
		int oppositeStones = stoneData.get(oppositeID);
		stoneData.set(currentPlayer.getMancalaID(), stoneData.get(currentPlayer.getMancalaID()) + oppositeStones + 1);
		stoneData.set(oppositeID, 0);
		stoneData.set(ID, 0);
		setCurrentPlayer(currentPlayer.getPlayerID());
		return "You captured " + oppositeStones + " stones from your opponent!";
	}

	public boolean landOnPlayerEmptyPit(int ID) {
		return stoneData.get(ID) == 1 && currentPlayer.respectivePit(ID);
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
		dataAlert = null;
	}

	public String getAlert() {
		return dataAlert;
	}

	public String closeGame() {
		stoneData.set(currentPlayer.getMancalaID(), stoneData.subList(6, 13).stream().mapToInt(Integer::intValue).sum());
		stoneData.subList(0, 6).replaceAll(i -> 0);
		stoneData.subList(7, 13).replaceAll(i -> 0);
		return ALERT_GAME_OVER;
	}

	public void undo() {
		//TODO
		//Wise move: save the state of the game before each move
		//and then revert to that state when undo is called
		//save Player A and B, stoneData, currentPlayer
	}

}
