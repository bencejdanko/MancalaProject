import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 1L;

	Model model;
	Controller controller;
	Icon board;
	JLabel background, turnIndicator;
	MancalaLabel mancalaA, mancalaB;
	ArrayList<JLabel> pitLabels = new ArrayList<JLabel>(12);
	JButton undoButton;
	
	Style style; // Strategy class for style
	
	
	public View(Model model) {
		this.model = model;
		this.controller = new Controller(model, this);
		initializeStoneChoiceAndStyle();
		frameSetup();
		setupComponents(style);
		visualize();
	}

	private void initializeStoneChoiceAndStyle() {
//todo: add a way to choose the style to the given options pane
		String[] options = {"Simple", "Cloudy", "Earthy"};
		int choice = JOptionPane.showOptionDialog(null, "Choose a style", "Style", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (choice == 0) style = new SimpleStyle();
		else if (choice == 1) style = new CloudyStyle();
		else if (choice == 2) style = new EarthyStyle();
		else style = new SimpleStyle();

		String[] choices = {"3", "4"};
		String input = (String) JOptionPane.showInputDialog(null, "Choose the number of stones per pit:", "Input", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if (input == null) System.exit(0);

		model = new Model(Integer.parseInt(input));
		model.attach(this);
		this.controller = new Controller(model, this);
	}

	public void frameSetup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(810,440);
		setLayout(null);
		setLocationRelativeTo(null);
	}
	
	public void setupComponents(Style style) {
		initializeBackground();
		initializePits(style);
		initializeUndoButton();
		initializeTurnIndicator();
	}
	
	public void initializeTurnIndicator() {
		turnIndicator = new TurnLabel(model);
		turnIndicator.setBounds(30,380,100,20);
	}
	
	public void initializePits(Style style) {
		int w = 80;
		int h = 120;
		int x = 110;
		int y = 220;
		
		int ID = 0;

		for (int stones : model.getStoneData()) {
			if (ID == 6) {
				mancalaA = new MancalaLabel(model.getMancalaAStones(),ID,model, this);

				mancalaA.setBounds(695,50,50,300);
				model.attach(mancalaA);
			}
			else if (ID == 13) {
				mancalaB = new MancalaLabel(model.getMancalaBStones(),ID,model,this);
				mancalaB.setBounds(55,50,50,300);
				model.attach(mancalaB);
			}
			else{
				PitLabel pit = new PitLabel(stones, ID, model, this, controller);
				pit.setBounds(x,y,w,h);
				if (ID < 6) {
					x+=100;
				}
				else{
					x-=100;
				}
				if (x>=650) {
					x-= 100;
					y= 50;
				}
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
		//todo undo button
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
	
		});

		 undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
	
	public void initializeBackground() {
		board = new BoardIcon(style);
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
		if (controller.detectAlert()) {
			if (controller.getAlert().equals(controller.getGameOverAlertCode())) endScreen();
			alert(controller.getAlert());
			controller.removeAlert();
		}
	}

	public void addController(Controller controller) {
		this.controller = controller;
	}

	public void alert(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void endScreen() {
		String message = "Game Over! ";
		if (model.getMancalaAStones() > model.getMancalaBStones()) {
			message += "Player A wins!";
		}
		else if (model.getMancalaAStones() < model.getMancalaBStones()) {
			message += "Player B wins!";
		}
		else {
			message += "It's a tie!";
		}
		JOptionPane.showMessageDialog(this, message);
		controller.newGame();
	}

}
