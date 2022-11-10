import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TurnLabel extends JLabel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	View view;

	public TurnLabel(View view) {
		this.view = view;
		setText("Player A goes");
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (view.controller.getCurrentPlayer().getPlayerID() == 0) setText("Player A goes");
		else setText("Player B goes");
	}

}
