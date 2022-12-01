import java.awt.Graphics;
import javax.swing.JLabel;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The MancalaLabel class represents a Mancala pit and is a part of the view in the MVC pattern.
 * It is also a JLabel that will display the data of the Mancala pit.
 */
public class MancalaLabel extends JLabel{
	private static final long serialVersionUID = 1L;

	int ID;
	int stones;
	View view;

	/**
	 * Constructs a MancalaLabel with a given ID number and View
	 * @param ID the ID number of the mancala pit
	 * @param view the View
	 */
	public MancalaLabel(int ID, View view) {
		this.view = view;
		this.ID = ID;
	}

	/**
	 * Paints the MancalaLabel
	 * @param g the Graphics object
	 */
	@Override
	public void paintComponent(Graphics g) {
		view.style.paintMancala(g, stones);
	}

	/**
	 * Repaints the MancalaLabel given there is an update
	 */
	public void update(){
		setStones(ID);
		repaint();
	}

	/**
	 * Sets the number of stones in the MancalaLabel to the number of stones in the
	 * Mancala pit with the given ID number
	 * @param ID the ID number of the Mancala pit
	 */
	public void setStones(int ID) {
		if (ID == 6) this.stones = view.controller.getMancalaAStones();
		else this.stones = view.controller.model.getMancalaBStones();
	}

}
