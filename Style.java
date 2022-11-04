import java.awt.Graphics;

public interface Style {
    void paintPit(Graphics g, int stones);
    void paintMancala(Graphics g, int stones);
    String getBackgroundPath();

}
