import javax.swing.JLabel;

public class TurnLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	View view;

	public TurnLabel(View view) {
		this.view = view;
		setText("Player A goes");
	}

	public void update(){
		if (view.controller.getCurrentPlayer().getPlayerID() == 0) setText("Player A goes");
		else setText("Player B goes");
	}

}
