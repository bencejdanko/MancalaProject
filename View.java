import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 1L;
	Model model;
	Icon board;
	JLabel background, mancalaA, mancalaB, turnIndicator;
	ArrayList<JLabel> pitLabels = new ArrayList<JLabel>(12);
	JButton undoButton;
	
	
	public View(Model model) {
		
		this.model = model;
		frameSetup();
		setupComponents();
		model.attach(this);
		visualize();
		initializeStoneChoice();
	}

	private void initializeStoneChoice() {
		//initializes the stone choice frame
		JFrame choiceFrame = new JFrame();
		choiceFrame.setSize(200, 100);
		choiceFrame.setLayout(new BorderLayout());
		JLabel label = new JLabel("Enter amount of initial stones (3 or 4)");
		label.setSize(200, 75);
		JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String stones = textField.getText();
				if (Integer.valueOf(stones) == 3 || Integer.valueOf(stones) == 4) {
					for (int i = 0; i < model.getPitStones().size(); i++) {
						model.updateStones(i, Integer.valueOf(stones));
					}
					choiceFrame.dispose();
				}
				else {
					label.setText("Invalid stone amount, choose 3 or 4");
					textField.setText("");
//					choiceFrame.pack();
				}
			}
		});
		textField.setSize(150,20);
		choiceFrame.add(label, BorderLayout.NORTH);
		choiceFrame.add(textField, BorderLayout.CENTER);
		choiceFrame.setLocationRelativeTo(null);
		choiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choiceFrame.pack();
		choiceFrame.setVisible(true);
	}

	public void frameSetup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(810,440);
		setLayout(null);
		setLocationRelativeTo(null);
	}
	
	public void setupComponents() {
		initializeBackground();
		initializeMancalas();
		initializePits();
		initializeUndoButton();
		initializeTurnIndicator();
	}
	
	public void initializeTurnIndicator() {
		turnIndicator = new TurnLabel(model);
		turnIndicator.setBounds(30,380,100,20);
	}
	
	public void initializePits() {
		int w = 80;
		int h = 120;
		int x = 110;
		int y = 50;
		
		int ID = 12;

		for (int stones : model.getPitStones()) {
			
			PitLabel pit = new PitLabel(stones, ID, model);
			pit.setBounds(x,y,w,h);
			
			x+=100;
			if (x>=650) {
				x = 110;
				y = 220;
			}
			
			pitLabels.add(pit);
			model.attach(pit);
			
			if (ID > 6) ID--;		//formats ID so it goes from a1-b6, mandala A is 6 and mandala B is 13
			if (ID < 6) ID++;
			if (ID == 6) ID=0;
			
			
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
		mancalaA = new MancalaLabel(model.mancalaAStones,13,model);
		mancalaA.setBounds(55,50,50,300);
		
		mancalaB = new MancalaLabel(model.mancalaBStones,6,model);
		mancalaB.setBounds(695,50,50,300);
	}
	
	public void visualize() {
		for (JLabel pit: pitLabels) add(pit);
		add(mancalaA);
		add(mancalaB);
		add(undoButton);
		add(turnIndicator);
		add(background);
		setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}

	
}
