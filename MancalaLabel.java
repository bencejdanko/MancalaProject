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
	Model model;
	View view;
	
	public MancalaLabel(int stones, int ID, Model model, View view) {
		this.model = model;
		this.view = view;
		this.ID = ID;
		setStones(ID);
		model.attach(this);
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
		if (ID == 6) this.stones = model.getMancalaAStones();
		else this.stones = model.getMancalaBStones();
	}

}
