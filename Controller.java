public class Controller {
	private int startingHand, lastStoneID;
	private boolean canUndo;
	Model model;
    View view;

	final static String ALERT_NOT_PLAYER_TURN = "Not your turn!";
	final static String ALERT_PIT_EMPTY = "Pit is empty!";
	final static String ALERT_GO_AGAIN = "Go again!";
	final static String ALERT_GAME_OVER = "Game over!";
	final static String ALERT_NO_UNDOS_LEFT_PLAYER_A = "No Undos Left For Player A";
	final static String ALERT_NO_UNDOS_LEFT_PLAYER_B = "No Undos Left For Player B";

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.addController(this);
		startingHand = 0;
		canUndo = true;
    }

    public void updateStones(int ID) {
        model.setDataAlert(parseUserChoice(ID));
        model.updateStones(-1, 0);
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
		
		if (model.gameIsOver()) return closeGame();
		if (landOnPlayerEmptyPit(lastStoneID)) 				            return captureStonesAndAlert(lastStoneID);
		if (lastStoneID == model.getCurrentPlayer().getMancalaID()) 	return ALERT_GO_AGAIN;

		model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
		if (model.getCurrentPlayer().getUndoCount() == 0) {
			model.getCurrentPlayer().setUndoCount(3);
		}
		return null; //no alert
		
	}

    public int passStonesAlong(int ID) {
		int IDcounter = ID;
		while (model.getCurrentPlayer().getHand() > 0) {
			IDcounter++;
			if (IDcounter >= 14) IDcounter = 0;

			if (IDcounter == model.getCurrentPlayer().getOppositeMancalaID()) IDcounter++;
			else if (IDcounter == model.getCurrentPlayer().getMancalaID()) {
				model.updateStones(IDcounter, model.getStoneData().get(IDcounter)+1);
				model.getCurrentPlayer().decreaseHand();
			}
			else {
				model.updateStones(IDcounter, model.getStoneData().get(IDcounter)+1);
				model.getCurrentPlayer().decreaseHand();
			}
		}
		return IDcounter; //last stone ID
	}


    public String captureStonesAndAlert(int ID) {
		int oppositeID = model.getCurrentPlayer().getOppositePitID(ID);
		int oppositeStones = model.getStoneData().get(oppositeID);
		model.updateStones(model.getCurrentPlayer().getMancalaID(), model.getStoneData().get(model.getCurrentPlayer().getMancalaID()) + oppositeStones + 1);
		model.updateStones(oppositeID, 0);
		model.updateStones(ID, 0);
		model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
		return "You captured " + oppositeStones + " stones from your opponent!";
	}

    public boolean landOnPlayerEmptyPit(int ID) {
		return model.getStoneData().get(ID) == 1 && model.getCurrentPlayer().respectivePit(ID);
	}

    public String closeGame() {
        int stonesLeft = model.getStoneData().subList(6, 13).stream().mapToInt(Integer::intValue).sum();
        model.stoneData.subList(0, 6).replaceAll(i -> 0);
		model.stoneData.subList(7, 13).replaceAll(i -> 0);
		model.updateStones(model.getCurrentPlayer().getMancalaID(), model.getStoneData().get(model.getCurrentPlayer().getMancalaID()) + stonesLeft);
		return ALERT_GAME_OVER;
	}

    public void removeAlert() {
        model.removeAlert();
    }

    public boolean detectAlert() {
        return (model.getAlert() != null);
    }

    public String getAlert() {
        return model.getAlert();
    }
	//undo needs to be worked on and debugged, possible fixes-->store previous moves in an arraylist
	//main issue right now is go again, otherwise undo works for single moves
    public void undo() {
		int IDcounter = lastStoneID;
		if (startingHand > 0){
			int i = startingHand;
			if (model.getPreviousAlert() != ALERT_GO_AGAIN){
				model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
			}
			if (model.getCurrentPlayer().getUndoCount() >= 1){
				while (i > 0) {
					model.updateStones(IDcounter, model.getStoneData().get(IDcounter)-1);
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
				model.updateStones(IDcounter, startingHand);
				startingHand = 0;
				model.getCurrentPlayer().setUndoCount(model.getCurrentPlayer().getUndoCount()-1);
				model.getCurrentPlayer().setHand(0);
//			if (model.getCurrentPlayer().getUndoCount() == 0){
//				canUndo = false;
//			}
			}
			else{
				if (model.getCurrentPlayer().getPlayerID() == 1){
					model.setDataAlert(ALERT_NO_UNDOS_LEFT_PLAYER_B);
				}
				else{
					model.setDataAlert(ALERT_NO_UNDOS_LEFT_PLAYER_A);
				}

				model.updateStones(-1, 0);
			}
		}
    }

    public void newGame() {
        model = new Model(0);
        view = new View(model);
    }

    public String getGameOverAlertCode()  {
        return ALERT_GAME_OVER;
    }
}
