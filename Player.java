public class Player {
    private int hand, pid;

    public Player(int pid) {
        hand = 0;
        this.pid = pid;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int i) {
        hand = i;
    }

    public void increaseHand() {
        hand++;
    }

    public int getPlayerID(){
        return pid;
    }

    public void decreaseHand() {
        hand--;
    }

    public int getMancalaID() {
        if (pid == 0) return 6;
        else return 13;
    }

    public int getOppositeMancalaID() {
        if (pid == 0) return 13;
        else return 6;
    }

    public int[] getRespectivePitsRange() {
        if (pid == 0) return new int[]{0,5};
        else return new int[]{7,12};
    }

    public boolean respectivePit(int ID) {
        return ID >= getRespectivePitsRange()[0] && ID <= getRespectivePitsRange()[1];
    }

    public int getOppositePitID(int ID) {
		return 12 - ID;
	}

}