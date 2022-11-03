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
	JLabel background, turnIndicator;
	MancalaLabel mancalaA, mancalaB;
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
//		initializeMancalas();
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
//		int y = 50;
		int y = 220;
		
		int ID = 0;

		for (int stones : model.getPitStones()) {
			if (ID == 6) {
				mancalaA = new MancalaLabel(model.mancalaAStones,ID,model);
				mancalaA.setBounds(695,50,50,300);
				model.attach(mancalaA);
			}
			else if (ID == 13) {
				mancalaB = new MancalaLabel(model.mancalaAStones,ID,model);
				mancalaB.setBounds(55,50,50,300);
				model.attach(mancalaB);
			}
			else{
				PitLabel pit = new PitLabel(stones, ID, model);
				pit.setBounds(x,y,w,h);
				if (ID < 6) {
					x+=100;
				}
				else{
					x-=100;
				}
				if (x>=650) {
					x-= 100;
//				y = 220;
					y= 50;
				}
//			System.out.println(ID);
				pitLabels.add(pit);
				model.attach(pit);
			}

			
			ID++;
			// Player A pits and mancala: 0-6
			// Player B pits and mancala: 7-13

			
			
		}
	}
	
	public void initializeUndoButton() {
		undoButton = new JButton("Undo");
		undoButton.setBounds(680,380,100,20);
		//todo
		 undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
	
	public void initializeBackground() {
		board = new BoardIcon();
		background = new JLabel(board);
		background.setBounds(0,0,810,600);
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
