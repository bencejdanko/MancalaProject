import java.awt.Graphics;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The Style interface represents strategy in the strategy pattern and holds common methods
 * to paint the Mancala board in a certain style
 */
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
     * @return a string containing the path
     */
    String getBackgroundPath();

}
