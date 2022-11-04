public class Controller {
    Model model;
    View view;

	final static String ALERT_NOT_PLAYER_TURN = "Not your turn!";
	final static String ALERT_PIT_EMPTY = "Pit is empty!";
	final static String ALERT_GO_AGAIN = "Go again!";
	final static String ALERT_GAME_OVER = "Game over!";

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.addController(this);
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
		model.updateStones(ID, 0);
		int lastStoneID = passStonesAlong(ID); //returns ID of last stone and updates stoneData
		
		if (model.gameIsOver()) closeGame();
		if (landOnPlayerEmptyPit(lastStoneID)) 				            return captureStonesAndAlert(lastStoneID);
		if (lastStoneID == model.getCurrentPlayer().getMancalaID()) 	return ALERT_GO_AGAIN;

		model.setCurrentPlayer(model.getCurrentPlayer().getPlayerID());
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

    public void undo() {
        //model.undo();
    }

    public void newGame() {
        model = new Model();
        view = new View(model);
    }

    public String getGameOverAlertCode()  {
        return ALERT_GAME_OVER;
    }
}
