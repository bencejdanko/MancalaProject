import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Color;

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
	final static int MANCALA_STONE_MAX_X = 35;
	final static int MANCALA_STONE_Y_INCREMENT = 20;

    private Color primaryColour    = new Color(255, 255, 204); //light brown
    private Color secondaryColour  = new Color(0,0,0); //black
    private String boardPath        = "../MancalaProject/images/mancalaBoardEarthy.png";
    
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

    @Override
    public String getBackgroundPath() {
        return boardPath;
    }

    
    
}
