import java.awt.Graphics;

public interface Style {
    /**
     * Paint the pits and their stones.
     * @param g the graphics context
     * @param stones the number of stones in the pit
     */
    void paintPit(Graphics g, int stones);

    /**
     * Paint the mancala and its stones.
     * @param g the graphics context
     * @param stones the number of stones in the mancala
     */
    void paintMancala(Graphics g, int stones);

    /**
     * Get the path to image file for the background.
     */
    String getBackgroundPath();

}
