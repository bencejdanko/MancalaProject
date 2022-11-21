import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The Model class represents model in the MVC pattern and holds the data for the
 * Mancala Game, such as the pit data and players.
 */
public class Model {

	private Player A, B, currentPlayer;
	int startingStones;
	ArrayList<ChangeListener> listeners;
	ArrayList<Integer> stoneData; //from a1 to mancala A, then from b1 to mancala B
	String alert;

	/**
	 * Constructor of the Model Class
	 */
	public Model() {
		A = new Player(0); //player A
		B = new Player(1); //player B
		currentPlayer = A;
		listeners = new ArrayList<ChangeListener>();
		stoneData = new ArrayList<Integer>(Arrays.asList(new Integer[14]));
		Collections.fill(stoneData, 0);
	}

	/**
	 * Constructor of the Model Class
	 * @param startingStones the number of stones to start with in each pit (excluding mancala pits)
	 * @param model a model
	 */
	public Model(int startingStones, Model model)  {
		this.startingStones = startingStones;
		A = model.A;
		B = model.B;
		currentPlayer = model.currentPlayer;
		listeners = model.listeners;
		stoneData = new ArrayList<Integer>(Arrays.asList(new Integer[14]));
		Collections.fill(stoneData, startingStones);
		stoneData.set(6, 0); //mancala A
		stoneData.set(13, 0); //mancala B
	}

	/**
	 * Attaches the views to the Model
	 * @param c a view
	 */
	public void attach(ChangeListener c) {
		listeners.add(c);
	}

	/**
	 * Returns the data of each pit
	 * @return the data of each pit
	 */
	public ArrayList<Integer> getStoneData() {
		return (ArrayList<Integer>) stoneData.clone(); 
    }

	/**
	 * Returns the current Player
	 * @return the current Player
	 */
	public Player getCurrentPlayer(){
		return currentPlayer;
	}

	/**
	 * Sets the current Player to the next Player
	 * @param i the current Player
	 */
	public void setCurrentPlayer(int i){
		if (i == 0){
			currentPlayer = B;
		}
		else {
			currentPlayer = A;
		}
	}

	/**
	 * Method to update the number of stones in a pit
	 * Also notifies listeners of the change
	 * @param ID the ID of the pit to update
	 * @param stones the number of stones to update to
	 */
	public void updateStones(int ID, int stones)
	{
		stoneData.set(ID, stones);
		updateListeners();
	}

	/**
	 * Notifies all the listeners of a change
	 */
	public void updateListeners() {
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * Sets the alert to a specific message
	 * @param alert
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	/**
	 * Returns the number of stones in Player A's Mancala
	 * @return the number of stones in Player A's Mancala
	 */
	public int getMancalaAStones(){
		return stoneData.get(6);
	}

	/**
	 * Returns the number of stones in Player B's Mancala
	 * @return the number of stones in Player B's Mancala
	 */
	public int getMancalaBStones() {
		return stoneData.get(13);
	}

	/**
	 * Returns the current alert message
	 * @return the current alert message
	 */
	public String getAlert() {
		return alert;
	}

}
