import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The PitLabel class represents a stone pit and is a part of the view in the MVC pattern.
 * It is also a JLabel that will display the data of the stone pit.
 */
public class PitLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	View view;
	int ID; 
	int stones;

	/**
	 * Constructs a PitLabel with a given ID number and a View
	 * @param ID the ID number of the pit
	 * @param view the view it is a part of
	 */
	public PitLabel(int ID, View view) {
		this.view = view;
		this.ID = ID;
		MouseListeners listener = new MouseListeners();
		addMouseListener(listener);
	}

	/**
	 * Paints the PitLabel
	 * @param g the Graphics object
	 */
	@Override
	public void paintComponent(Graphics g) {
		view.style.paintPit(g, stones);
	}

	/**
	 * Repaints the PitLabel given there is an update
	 */
	public void update(){
		stones = view.controller.getStoneData().get(ID);
		repaint();
	}

	/**
	 * MouseListeners is a Mouseadapter that will perform
	 * actions when the mouse is clicked on the PitLabel
	 */
	private class MouseListeners extends MouseAdapter{
		/**
		 * Allows the controller to handle the data when the mouse is clicked
		 * on the PitLabel
		 * @param e the event to be processed
		 */
		public void mouseClicked(MouseEvent e) {
			view.controller.updateStones(ID);
		}
	}
}
