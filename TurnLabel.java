import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TurnLabel extends JLabel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	Model model;

	public TurnLabel(Model model) {
		this.model = model;
		setText("Player A goes");
		model.attach(this);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		int turn = model.turn%2;
		if (turn == 0) setText("Player A goes");
		else setText("Player B goes");
	}

}
