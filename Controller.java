import javax.swing.event.ChangeListener;
import java.util.ArrayList;
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

    Controller() {
		canUndo = true;
    }

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

	public void updateListeners() {
		model.updateListeners();
	}

    public String parseUserChoice(int ID) {
		if (!model.getCurrentPlayer().respectivePit(ID))    return ALERT_NOT_PLAYER_TURN;
		if (model.getStoneData().get(ID) == 0) 				return ALERT_PIT_EMPTY;
		return dataParseAndAlert(ID); 
	}

    public String dataParseAndAlert(int ID) {
		model.getCurrentPlayer().setHand(model.getStoneData().get(ID));
		startingHand = model.getCurrentPlayer().getHand();
		model.updateStones(ID, 0);
		lastStoneID = passStonesAlong(ID); //returns ID of last stone and updates stoneData
		landEmpty = false;
		if (landOnPlayerEmptyPit(lastStoneID)) {
			landEmpty = true;
			canUndo = true;
//			System.out.println("here");
			return captureStonesAndAlert(lastStoneID);
		}
		if (gameIsOver()) return closeGame();
		if (lastStoneID == model.getCurrentPlayer().getMancalaID()) {
			canUndo = true;
			goAgain = true;
			return ALERT_GO_AGAIN;
		}
		model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
		if (model.getCurrentPlayer().getUndoCount() == 0) {
			model.getCurrentPlayer().setUndoCount(3);
		}
		canUndo = true;
		goAgain = false;
		return null; //no alert
		
	}

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

    public boolean landOnPlayerEmptyPit(int ID) {
		return model.getStoneData().get(ID) == 1 && model.getCurrentPlayer().respectivePit(ID);
	}

    public String closeGame() {
        int stonesLeft = model.getStoneData().subList(7, 13).stream().mapToInt(Integer::intValue).sum();
		stonesLeft += model.getStoneData().subList(0, 6).stream().mapToInt(Integer::intValue).sum();
        model.stoneData.subList(0, 6).replaceAll(i -> 0);
		model.stoneData.subList(7, 13).replaceAll(i -> 0);
		model.updateStones(model.getCurrentPlayer().getMancalaID(), model.getStoneData().get(model.getCurrentPlayer().getMancalaID()) + stonesLeft);
		return ALERT_GAME_OVER;
	}

    public void removeAlert() {
		if (model == null || model.getAlert() == null) return;
        model.setAlert(null);
    }

    public boolean detectAlert() {
        return (model != null && model.getAlert() != null);
    }

    public String getAlert() {
		if (model == null) return null;
		return model.getAlert();
    }
	//undo needs to be worked on and debugged, possible fixes-->store previous moves in an arraylist
	//main issue right now is go again, otherwise undo works for single moves
    public void undo() {
		int IDcounter = lastStoneID;
		if (startingHand > 0 && canUndo){
			int i = startingHand;
			if (!goAgain){
				model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
			}
			if (model.getCurrentPlayer().getUndoCount() >= 1){
				while (i > 0) {
					if (model.getStoneData().get(IDcounter) != 0) {
						model.updateStones(IDcounter, model.getStoneData().get(IDcounter) - 1);
					}
					IDcounter--;
					if (IDcounter == -1){
						if (model.getCurrentPlayer().getPlayerID() == 1){
							IDcounter = 13;
						}
						else{
							IDcounter = 12;
						}
					}
					if (IDcounter == 6 && model.getCurrentPlayer().getPlayerID() == 1){
						IDcounter = 5;
					}
					i--;
				}
				if (landEmpty){
					model.updateStones(oppositeID, oppositeStones);
					if (model.getCurrentPlayer().getPlayerID() == 1){
						model.updateStones(13, model.getStoneData().get(13) - oppositeStones);
					}
					else{
						model.updateStones(6, model.getStoneData().get(6) - oppositeStones);
					}
					landEmpty = false;
				}
				model.updateStones(IDcounter, startingHand);
				startingHand = 0;
				model.getCurrentPlayer().setUndoCount(model.getCurrentPlayer().getUndoCount()-1);
				model.getCurrentPlayer().setHand(0);
				canUndo = false;
//			if (model.getCurrentPlayer().getUndoCount() == 0){
//				canUndo = false;
//			}
			}
			else{
				if (model.getCurrentPlayer().getPlayerID() == 1){
					model.setAlert(ALERT_NO_UNDOS_LEFT_PLAYER_B);
				}
				else{
					model.setAlert(ALERT_NO_UNDOS_LEFT_PLAYER_A);
				}
				updateListeners();
			}
		}
		else{
			model.setAlert(ALERT_CANT_UNDO);
			updateListeners();
		}
    }

    public void newGame() {
		model = null;
		view.dispose();
        MancalaTest.main(null);
    }

    public String getGameOverAlertCode()  {
        return ALERT_GAME_OVER;
    }

	public int getMancalaAStones() {
		return model.getMancalaAStones();
	}

	public int getMancalaBStones() {
		return model.getMancalaBStones();
	}

	public void attachListener(ChangeListener listener) {
		model.attach(listener);
	}

	public void setModelWithStartingStones(int startingStones) {
		model = new Model(startingStones);
	}

	public Player getCurrentPlayer() {
		return model.getCurrentPlayer();
	}

	public ArrayList<Integer> getStoneData() {
		return model.getStoneData();
	}

	public void setPreviousAlert(String alert) {
		model.prevAlert = alert;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
		view.setController(this);
	}

	public boolean gameIsOver() {
		return (model.stoneData.subList(0, 6).stream().mapToInt(Integer::intValue).sum()) == 0 || (model.stoneData.subList(7, 13).stream().mapToInt(Integer::intValue).sum() == 0);
	}

}
