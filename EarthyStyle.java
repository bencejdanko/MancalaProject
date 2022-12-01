import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Color;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The EarthyStyle class represents a concrete strategy of the Style interface in the strategy pattern and implements
 * all methods in the Style interface
 */
public class EarthyStyle implements Style {

    final static int PIT_WIDTH = 70;
	final static int PIT_HEIGHT = 110;
	final static int PIT_STROKE_WIDTH = 5;
	final static int PIT_STONE_STARTING_X = 10;
	final static int PIT_STONE_STARTING_Y = 20;
	final static int PIT_STONE_X_INCREMENT = 10;
	final static int PIT_STONE_MAX_X = 60;
	final static int PIT_STONE_Y_INCREMENT = 20;

	final static int MANCALA_WIDTH = 40;
	final static int MANCALA_HEIGHT = 280;
	final static int MANCALA_STROKE_WIDTH = 5;
	final static int MANCALA_STONE_STARTING_X = 5;
	final static int MANCALA_STONE_STARTING_Y = 7;
	final static int MANCALA_STONE_X_INCREMENT = 10;
	final static int MANCALA_STONE_MAX_X = 45;
	final static int MANCALA_STONE_Y_INCREMENT = 10;

    private Color primaryColour    = new Color(255, 255, 204); //light brown
    private Color secondaryColour  = new Color(0,0,0); //black
    private String boardPath        = "../MancalaProject/images/mancalaBoardEarthy.png";

	/**
	 * Paint the pits and their stones.
	 * @param g the graphics context
	 * @param stones the number of stones in the pit
	 */
    @Override
    public void paintPit(Graphics g, int stones) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(PIT_STROKE_WIDTH));
		g2.drawRect(0,0,PIT_WIDTH,PIT_HEIGHT);
        g2.setColor(primaryColour);
        g2.fillRect(0,0,PIT_WIDTH,PIT_HEIGHT);

		
		int x = PIT_STONE_STARTING_X;
		int y = PIT_STONE_STARTING_Y;

		g.setColor(secondaryColour);
		for (int i = 1; i<= stones; i++) {
			g.drawLine(x, y, x, y);
			x+=PIT_STONE_X_INCREMENT;
			if (x==PIT_STONE_MAX_X) {
				x = PIT_STONE_STARTING_X;
				y += PIT_STONE_Y_INCREMENT;
			}
		}
    }


	/**
	 * Paint the mancala and its stones.
	 * @param g the graphics context
	 * @param stones the number of stones in the mancala
	 */
	@Override
	public void paintMancala(Graphics g, int stones) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(MANCALA_STROKE_WIDTH));
		g2.drawRect(0,0,MANCALA_WIDTH, MANCALA_HEIGHT);
		g2.setColor(primaryColour);
        g2.fillRect(0,0,MANCALA_WIDTH,MANCALA_HEIGHT);
		
		int x = MANCALA_STONE_STARTING_X;
		int y = MANCALA_STONE_STARTING_Y;
		
		g.setColor(secondaryColour);
		for (int i = 1; i<= stones; i++) {
			g.drawLine(x, y, x, y);
			x+=MANCALA_STONE_X_INCREMENT;
			
			if (x==MANCALA_STONE_MAX_X) {
				x = MANCALA_STONE_STARTING_X;
				y += MANCALA_STONE_Y_INCREMENT;
			}
		}
	}

	/**
	 * Get the path to image file for the background.
	 * @return a string containing the path
	 */
    @Override
    public String getBackgroundPath() {
        return boardPath;
    }

    
    
}
