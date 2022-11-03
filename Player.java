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
        if (hand>=1){
            hand--;
        }
    }
}