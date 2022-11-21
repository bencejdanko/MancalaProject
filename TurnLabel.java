import javax.swing.JLabel;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The TurnLabel class represents a label displaying the current player on a given turn
 * and is a part of the View.
 */
public class TurnLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	View view;

	/**
	 * Constructs a TurnLabel with a given view that it is part of
	 * @param view
	 */
	public TurnLabel(View view) {
		this.view = view;
		setText("Player A goes");
	}

	/**
	 * Repaints the TurnLabel given there is an update
	 */
	public void update(){
		if (view.controller.getCurrentPlayer().getPlayerID() == 0) setText("Player A goes");
		else setText("Player B goes");
	}

}
