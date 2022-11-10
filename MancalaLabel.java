import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaLabel extends JLabel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	final static int WIDTH = 50;
	final static int HEIGHT = 280;
	final static int STROKE_WIDTH = 5;
	final static int STONE_STARTING_X = 7;
	final static int STONE_STARTING_Y = 20;
	final static int STONE_X_INCREMENT = 10;
	final static int STONE_Y_INCREMENT = 10;


	int ID;
	int stones;
	View view;
	
	public MancalaLabel(int ID, View view) {
		this.view = view;
		this.ID = ID;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		view.style.paintMancala(g, stones);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		setStones(ID);
		repaint();
	}
	
	public void setStones(int ID) {
		if (ID == 6) this.stones = view.controller.getMancalaAStones();
		else this.stones = view.controller.model.getMancalaBStones();
	}

}
