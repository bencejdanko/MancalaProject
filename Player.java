public class Player {
    private int hand;

    public Player() {
        hand = 0;
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

    public void decreaseHand() {
        if (hand>=1){
            hand--;
        }
    }
}
