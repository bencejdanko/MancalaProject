import java.awt.*;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The CloudyStyle class represents a concrete strategy of the Style interface in the strategy pattern and implements
 * all methods in the Style interface
 */
public class CloudyStyle implements Style {

	final static int PIT_WIDTH = 70;
	final static int PIT_HEIGHT = 110;
	final static int PIT_STROKE_WIDTH = 5;
	final static int PIT_STONE_STARTING_X = 15;
	final static int PIT_STONE_STARTING_Y = 20;
	final static int PIT_STONE_X_INCREMENT = 10;
	final static int PIT_STONE_MAX_X = 45;
	final static int PIT_STONE_Y_INCREMENT = 20;

	final static int MANCALA_WIDTH = 50;
	final static int MANCALA_HEIGHT = 280;
	final static int MANCALA_STROKE_WIDTH = 5;
	final static int MANCALA_STONE_STARTING_X = 5;
	final static int MANCALA_STONE_STARTING_Y = 15;
	final static int MANCALA_STONE_X_INCREMENT = 10;
	final static int MANCALA_STONE_MAX_X = 35;
	final static int MANCALA_STONE_Y_INCREMENT = 20;

    private Color primaryColour    = new Color(255,255,255); //white
    private Color secondaryColour  = new Color(0,0,255); //blue
    private String boardPath       	= "../MancalaProject/images/mancalaBoardCloudy.png";

	/**
	 * Paint the pits and their stones.
	 * @param g the graphics context
	 * @param stones the number of stones in the pit
	 */
    @Override
    public void paintPit(Graphics g, int stones) {
        
        Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(PIT_STROKE_WIDTH));
		g2.setColor(primaryColour);
		int cloud_y = 0;
		for (int i = 0; i < 5; i++) {
			g2.fillOval(0,cloud_y,PIT_WIDTH,PIT_HEIGHT/3);
			cloud_y += PIT_HEIGHT/6;
		}
		
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
		g2.setColor(primaryColour);
		int cloud_y = 0;
		for (int i = 0; i < 11; i++) {
			g2.fillOval(0,cloud_y,MANCALA_WIDTH,MANCALA_HEIGHT/6);
			cloud_y += MANCALA_HEIGHT/12;
		}
		
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
