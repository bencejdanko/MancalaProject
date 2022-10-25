import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 1L;
	Model model;
	Icon board;
	JLabel background, mancalaA, mancalaB;
	ArrayList<JLabel> pitLabels = new ArrayList<JLabel>(12);
	JButton undoButton;
	
	
	public View(Model model) {
		
		this.model = model;
		
		frameSetup();
		initializeBackground();
		initializeMancalas();
		initializePits();
		initializeUndoButton();
		visualize();
	}

	public void frameSetup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(810,440);
		setLayout(null);
		setLocationRelativeTo(null);
	}
	
	public void initializePits() {
		int w = 80;
		int h = 120;
		int x = 110;
		int y = 50;

		for (int i = 0; i <=12; i++) {
			//should be reading from model data here instead
			JLabel pit = new PitLabel(model.startingStones);
			pit.setBounds(x,y,w,h);
			
			x+=100;
			if (x>=650) {
				x = 110;
				y = 220;
			}
			
			pitLabels.add(pit);
		}
	}
	
	public void initializeUndoButton() {
		undoButton = new JButton("Undo");
		undoButton.setBounds(680,380,100,20);
	}
	
	public void initializeBackground() {
		board = new BoardIcon();
		background = new JLabel(board);
		background.setBounds(0,0,810,600);
	}
	
	public void initializeMancalas() {
		mancalaA = new MancalaLabel(0);
		mancalaA.setBounds(50,100,40,200);
		
		mancalaB = new MancalaLabel(0);
		mancalaB.setBounds(710,100,40,200);
	}
	
	public void visualize() {
		for (JLabel pit: pitLabels) add(pit);
		add(mancalaA);
		add(mancalaB);
		add(undoButton);
		add(background);
		setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
