import javax.swing.event.ChangeListener;
import java.util.ArrayList;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The Controller class represents controller in the MVC pattern and mutates the data for the
 * Mancala Game.
 */
public class Controller {
	private int startingHand, lastStoneID, oppositeID, oppositeStones;
	private boolean canUndo, goAgain, landEmpty;
	Model model;
    View view;
	final static String ALERT_NOT_PLAYER_TURN = "Not your turn!";
	final static String ALERT_PIT_EMPTY = "Pit is empty!";
	final static String ALERT_GO_AGAIN = "Go again!";
	final static String ALERT_GAME_OVER = "Game over! ";
	final static String ALERT_NO_UNDOS_LEFT_PLAYER_A = "No undos left For Player A";
	final static String ALERT_NO_UNDOS_LEFT_PLAYER_B = "No undos left For Player B";
	final static String ALERT_CANT_UNDO = "Can't undo";
	final static String ALERT_UNDOS_LEFT_PLAYER_A = "undos left for Player A";
	final static String ALERT_UNDOS_LEFT_PLAYER_B = "undos left for Player B";
	final static String ALERT_READY = "Ready to play";

	/**
	 * Constructs a Controller
	 */
    Controller() {
		canUndo = true;
    }

	/**
	 * Starts the game
	 */
	public void startGame() {
		view.initializeStyle();

		view.frameSetup();
		view.setupComponents();
		view.updateControllerListeners(this);

		view.visualize();
		view.initializeStoneChoice();
	}

	/**
	 * This method is called when the user clicks on a pit.
	 * It calls on the controller to update the model stones and alert.
	 * @param  ID The ID of the pit that was clicked.
	 */
    public void updateStones(int ID) {
		String alert = parseUserChoice(ID);
        model.setAlert(alert);
		updateListeners();
    }

	/**
	 * Notifies all the listeners of a change
	 */
	public void updateListeners() {
		model.updateListeners();
	}

	/**
	 * Determines if a Player's pit choice is valid or not, if valid
	 * continue calculations. Alerts include Not Player's turn and Empty pit.
	 * @param ID the pit number the Player chose
	 * @return a string detailing an alert or null if the pit choice was valid
	 */
    public String parseUserChoice(int ID) {
		if (!model.getCurrentPlayer().respectivePit(ID))    return ALERT_NOT_PLAYER_TURN; //if player chooses wrong pit
		if (model.getStoneData().get(ID) == 0) 				return ALERT_PIT_EMPTY; //if player chooses empty pit
		return dataParseAndAlert(ID);  //pit is valid
	}

	/**
	 * Produces alerts after the calculations for each pit is completed.
	 * Alerts include Capturing opposite stones, Go again, and Game Over
	 * @param ID the pit number of the player's selected pit (given that it is valid)
	 * @return a string detailing an alert or null if the there are no conditions passed
	 */
    public String dataParseAndAlert(int ID) {
		model.getCurrentPlayer().setHand(model.getStoneData().get(ID));
		startingHand = model.getCurrentPlayer().getHand();
		model.updateStones(ID, 0);
		lastStoneID = passStonesAlong(ID); //returns ID of last stone and updates stoneData
		landEmpty = false;
		//ends on an empty pit on the player's side of the board --> captures opposite stones
		if (landOnPlayerEmptyPit(lastStoneID) && model.getStoneData().get(model.getCurrentPlayer().getOppositePitID(lastStoneID)) > 0) {
			landEmpty = true;
			canUndo = true;
			return captureStonesAndAlert(lastStoneID);
		}
		if (gameIsOver()) return closeGame();
		//if the player's last stone is placed in their Mancala pit --> go again
		if (lastStoneID == model.getCurrentPlayer().getMancalaID()) {
			canUndo = true;
			goAgain = true;
			return ALERT_GO_AGAIN;
		}
		model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
		//resets the players undo count if it is 0 from a previous turn
		if (model.getCurrentPlayer().getUndoCount() == 0) {
			model.getCurrentPlayer().setUndoCount(3);
		}
		canUndo = true;
		goAgain = false;
		return null; //no alert
		
	}

	/**
	 * Calculates and places stones in their proper pit based on the
	 * number of stones in the current Player's hand and mutates model
	 * data.
	 * @param ID the pit number of the starting pit
	 * @return the pit number of the last placed stone
	 */
    public int passStonesAlong(int ID) {
		int IDcounter = ID;
		while (model.getCurrentPlayer().getHand() > 0) {
			IDcounter++;
			if (IDcounter >= 14) IDcounter = 0;
			if (!(IDcounter == model.getCurrentPlayer().getOppositeMancalaID())) {
				model.updateStones(IDcounter, model.getStoneData().get(IDcounter)+1);
				model.getCurrentPlayer().decreaseHand();
			}
		}
		return IDcounter; //last stone ID
	}

	/**
	 * Captures the stones from the opposite pit given that the
	 * current player places the last stone in an empty pit on their side
	 * of the board. Procedes to add the captured stones and the
	 * stone in the empty pit to the player's Mancala pit.
	 * @param ID the pit number of the last placed stone.
	 * @return a string containing a message of how many stones were
	 * captured.
	 */
    public String captureStonesAndAlert(int ID) {
		oppositeID = model.getCurrentPlayer().getOppositePitID(ID);
		oppositeStones = model.getStoneData().get(oppositeID);
		model.updateStones(model.getCurrentPlayer().getMancalaID(), model.getStoneData().get(model.getCurrentPlayer().getMancalaID()) + oppositeStones + 1);
		model.updateStones(oppositeID, 0);
		model.updateStones(ID, 0);
		if (gameIsOver()) return closeGame();
		model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
		return "You captured " + oppositeStones + " stones from your opponent!";
	}

	/**
	 * Determines if the pit is an empty and is on the current player's side of the board.
	 * @param ID the pit number
	 * @return true if there was only one stone recently placed and it is on the
	 * current player's side of the board
	 */
    public boolean landOnPlayerEmptyPit(int ID) {
		return model.getStoneData().get(ID) == 1 && model.getCurrentPlayer().respectivePit(ID);
	}

	/**
	 * Closes the game
	 * @return a string of an alert for game over
	 */
    public String closeGame() {
        int stonesLeft = model.getStoneData().subList(7, 13).stream().mapToInt(Integer::intValue).sum();
		stonesLeft += model.getStoneData().subList(0, 6).stream().mapToInt(Integer::intValue).sum();
        model.stoneData.subList(0, 6).replaceAll(i -> 0);
		model.stoneData.subList(7, 13).replaceAll(i -> 0);
		model.updateStones(model.getCurrentPlayer().getMancalaID(), model.getStoneData().get(model.getCurrentPlayer().getMancalaID()) + stonesLeft);
		return ALERT_GAME_OVER;
	}

	/**
	 * Removes the current alert.
	 */
    public void removeAlert() {
		if (model == null || model.getAlert() == null) return;
        model.setAlert(null);
    }

	/**
	 * Determines if there is an alert. Returns true if there is an
	 * alert, else false.
	 * @return true if there is an alert, else false.
	 */
    public boolean detectAlert() {
        return (model != null && model.getAlert() != null);
    }

	/**
	 * Returns an alert
	 * @return a string containing an alert or null if the model is null
	 */
    public String getAlert() {
		if (model == null) return null;
		return model.getAlert();
    }

	/**
	 * Returns the board state to the previous turn if a player has a valid number
	 * of undos and hasn't performed undo successively.
	 */
    public void undo() {
		int IDcounter = lastStoneID;
		if (startingHand > 0 && canUndo){
			int i = startingHand;
			//Don't change TurnLabel if player has a Go Again
			if (!goAgain){
				model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
			}
			Player currPlayer = model.getCurrentPlayer();
			// undoCount > 0
			if (currPlayer.getUndoCount() >= 1){
				//removes stones starting from the pit of the last placed stone
				while (i > 0) {
					if (model.getStoneData().get(IDcounter) != 0) {
						model.updateStones(IDcounter, model.getStoneData().get(IDcounter) - 1);
					}
					IDcounter--;
					if (IDcounter == -1){
						if (currPlayer.getPlayerID() == 1){
							IDcounter = 13;
						}
						else{
							IDcounter = 12;
						}
					}
					if (IDcounter == 6 && currPlayer.getPlayerID() == 1){
						IDcounter = 5;
					}
					i--;
				}
				//if Player previously captured stones in the previous turn put them back
				if (landEmpty){
					model.updateStones(oppositeID, oppositeStones);
					if (currPlayer.getPlayerID() == 1){
						model.updateStones(13, model.getStoneData().get(13) - oppositeStones);
					}
					else{
						model.updateStones(6, model.getStoneData().get(6) - oppositeStones);
					}
					landEmpty = false;
				}
				model.updateStones(IDcounter, startingHand);
				startingHand = 0;
				currPlayer.setUndoCount(model.getCurrentPlayer().getUndoCount()-1);
				currPlayer.setHand(0);
				canUndo = false;
				//display number of undos left for the current player
				if (currPlayer.getPlayerID() == 1){
					model.setAlert(currPlayer.getUndoCount() + " " + ALERT_UNDOS_LEFT_PLAYER_B);
				}
				else{
					model.setAlert(currPlayer.getUndoCount() + " " + ALERT_UNDOS_LEFT_PLAYER_A);
				}
				updateListeners();
			}
			// no undos left for the player
			else{
				if (currPlayer.getPlayerID() == 1){
					model.setAlert(ALERT_NO_UNDOS_LEFT_PLAYER_B);
				}
				else{
					model.setAlert(ALERT_NO_UNDOS_LEFT_PLAYER_A);
				}
				if (!goAgain){
					model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
				}
				updateListeners();
			}
		}
		// Can't undo on first turn and can't undo twice
		else{
			model.setAlert(ALERT_CANT_UNDO);
			updateListeners();
		}
    }

	/**
	 * Creates a new game
	 */
    public void newGame() {
		model = null;
		view.dispose();
        MancalaTest.main(null);
    }

	/**
	 * Returns the game over alert
	 * @return the game over alert
	 */
    public String getGameOverAlertCode()  {
        return ALERT_GAME_OVER;
    }

	/**
	 * Returns the number of stones in Player A's mancala pit
	 * @return the number of stones in Player A's mancala pit
	 */
	public int getMancalaAStones() {
		return model.getMancalaAStones();
	}

	/**
	 * Returns the number of stones in Player B's mancala pit
	 * @return the number of stones in Player B's mancala pit
	 */
	public int getMancalaBStones() {
		return model.getMancalaBStones();
	}

	/**
	 * Attaches a view to the model
	 * @param listener
	 */
	public void attachListener(ChangeListener listener) {
		model.attach(listener);
	}

	/**
	 * Sets the model with starting stones for each pit (excluding
	 * the mancala pits)
	 * @param startingStones the number of starting stones
	 */
	public void setModelWithStartingStones(int startingStones) {
		model = new Model(startingStones, model);
//		model.setAlert(ALERT_READY);
	}

	/**
	 * Returns the current Player
	 * @return the current Player
	 */
	public Player getCurrentPlayer() {
		return model.getCurrentPlayer();
	}

	/**
	 * Returns an arraylist containing the data of the model
	 * @return an arraylist containing the data of the model
	 */
	public ArrayList<Integer> getStoneData() {
		return model.getStoneData();
	}

	/**
	 * Sets the current model to the given model
	 * @param model a model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Sets the view to the givern view
	 * @param view
	 */
	public void setView(View view) {
		this.view = view;
		view.setController(this);
	}

	/**
	 * Determines if the game is over.
	 * @return true if the game is over
	 */
	public boolean gameIsOver() {
		return (model.stoneData.subList(0, 6).stream().mapToInt(Integer::intValue).sum()) == 0 || (model.stoneData.subList(7, 13).stream().mapToInt(Integer::intValue).sum() == 0);
	}

}
