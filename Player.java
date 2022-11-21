/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The Player Class represents a player in the game. A Player has a hand, id number, and
 * an undo count
 */
public class Player {
    private int hand, pid, undoCount;

    /**
     * contructs a Player with a given ID number
     * @param pid the ID number of the Player
     */
    public Player(int pid) {
        hand = 0;
        this.pid = pid;
        undoCount = 3;
    }

    /**
     * Returns the hand of the player
     * @return the number of stones in the player's hand
     */
    public int getHand() {
        return hand;
    }

    /**
     * Sets the player's hand to the given number
     * @param i the number of stones to be placed in the player's hand
     */
    public void setHand(int i) {
        hand = i;
    }

    /**
     * Returns the player's ID number
     * (Player A id = 0, Player B id = 1)
     * @return the player's ID number
     */
    public int getPlayerID(){
        return pid;
    }

    /**
     * Decreases the current players hand
     */
    public void decreaseHand() {
        hand--;
    }

    /**
     * Returns the pit number of the player's mancala pit
     * @return if Player A, 6, else 13 if Player B (Player A id = 0, Player B id = 1)
     */
    public int getMancalaID() {
        if (pid == 0) return 6;
        else return 13;
    }

    /**
     * Returns the pit number of the opposite player's mancala pit
     * @return if Player A, 13, else 6 if Player B (Player A id = 0, Player B id = 1)
     */
    public int getOppositeMancalaID() {
        if (pid == 0) return 13;
        else return 6;
    }

    /**
     * Returns an array of the respective pit range of the player
     * @return if Player A, 0-5, else 7-12 for Player B (Player A id = 0, Player B id = 1)
     */
    public int[] getRespectivePitsRange() {
        if (pid == 0) return new int[]{0,5};
        else return new int[]{7,12};
    }

    /**
     * Determines if the pit with the given id is within the player's
     * respective pit range.
     * @param ID the pit number.
     * @return true if it is within the player's respective pit range
     * else false.
     */
    public boolean respectivePit(int ID) {
        return ID >= getRespectivePitsRange()[0] && ID <= getRespectivePitsRange()[1];
    }

    /**
     * Returns the pit number of the pit opposite to the given pit number;
     * @param ID the given pit number
     * @return the pit number of the pit opposite to the given pit number
     */
    public int getOppositePitID(int ID) {
		return 12 - ID;
	}

    /**
     * Returns the number of remaining undos the player has (0-3).
     * @return the number of remaining undos the player has (0-3).
     */
    public int getUndoCount(){
        return undoCount;
    }

    /**
     * Sets the player's number of undos to the given number
     * @param i the given count
     */
    public void setUndoCount(int i){
        undoCount = i;
    }
}