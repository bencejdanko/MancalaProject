import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The View class represents View in the MVC pattern and displays the data for the
 * Mancala Game, such as the pit data.
 */
public class View extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 1L;

	Controller controller;
	Icon board;
	JLabel background;
	TurnLabel turnIndicator;
	MancalaLabel mancalaA, mancalaB;
	ArrayList<PitLabel> pitLabels = new ArrayList<PitLabel>(12);
	JButton undoButton;
	
	Style style; // Strategy class for style

	/**
	 * Prompts the user for what style they want to use. Uses a SimpleStyle by default.
	 */
	public void initializeStyle() {
		String[] options = {"Simple", "Cloudy", "Earthy"};
		int choice = JOptionPane.showOptionDialog(null, "Choose a style", "Style", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (choice == 0) style = new SimpleStyle();
		else if (choice == 1) style = new CloudyStyle();
		else if (choice == 2) style = new EarthyStyle();
		else style = new SimpleStyle();
	}

	/**
	 * Prompts the user to select the number of stones to start with in each pit (excluding mancala pits)
	 */
	public void initializeStoneChoice(){
		String[] choices = {"3", "4"};
		String input = (String) JOptionPane.showInputDialog(null, "Choose the number of stones per pit:", "Input", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if (input == null) System.exit(0);
		int i = Integer.parseInt(input);
		controller.setModelWithStartingStones(i);
		controller.updateListeners();
	}

	/**
	 * Sets up the view frame
	 */
	public void frameSetup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(810,440);
		setLayout(null);
		setLocationRelativeTo(null);
	}

	/**
	 * Sets up the components of the view
	 */
	public void setupComponents() {
		initializeBackground();
		initializePits();
		initializeUndoButton();
		initializeTurnIndicator();
	}

	/**
	 * Sets up the TurnLabel
	 */
	public void initializeTurnIndicator() {
		turnIndicator = new TurnLabel(this);
		turnIndicator.setBounds(30,380,100,20);
	}

	/**
	 * Sets up all the pits, including mancala pits
	 */
	public void initializePits() {
		int w = 80;
		int h = 120;
		int x = 110;
		int y = 220;

		for (int ID = 0; ID < 14; ID++) {
			if (ID == 6) {
				mancalaA = new MancalaLabel(ID,this);
				mancalaA.setBounds(695,50,50,300);
			}
			else if (ID == 13) {
				mancalaB = new MancalaLabel(ID,this);
				mancalaB.setBounds(55,50,50,300);
			}
			else{
				PitLabel pit = new PitLabel(ID, this);
				pit.setBounds(x,y,w,h);
				if (ID < 6) x+=100;
				else x-=100;
				if (x>=650) {
					x-= 100;
					y= 50;
				}
				pitLabels.add(pit);
			}
			// Player A pits and mancala: 0-6
			// Player B pits and mancala: 7-13
			
		}
	}

	/**
	 * Sets up the Undo button
	 */
	public void initializeUndoButton() {
		undoButton = new JButton("Undo");
		undoButton.setBounds(680,380,100,20);
		undoButton.addActionListener(event -> controller.undo());
	}

	/**
	 * Sets up the background for the board
	 */
	public void initializeBackground() {
		board = new BoardIcon(this);
		background = new JLabel(board);
		background.setBounds(0,0,810,600);
	}

	/**
	 * Adds all the components to the View frame
	 */
	public void visualize() {
		for (JLabel pit: pitLabels) add(pit);
		add(mancalaA);
		add(mancalaB);
		add(undoButton);
		add(turnIndicator);
		add(background);
		controller.updateListeners();
		setVisible(true);
	}
	
	/**
	 * Updates the view if it detects an alert from the controller. 
	 * It displays the alert to the user, or, if the game is over, 
	 * it displays the winner and calls on the controller to reset the game.
	 * @param e ChangeEvent from the model
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if (controller.detectAlert()) {
			String alert = controller.getAlert();
			if (alert.equals(controller.getGameOverAlertCode())) {
				endScreen();
				return;
			}
			alert(alert);
			controller.removeAlert();
			}

		for (PitLabel pit: pitLabels) pit.update();
		mancalaA.update();
		mancalaB.update();
		turnIndicator.update();
	}

	/**
	 * Sets the controller of the view to the given controller
	 * @param controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Attaches the view to the model with a given controller that holds the model
	 * @param controller the controller holding the model
	 */
	public void updateControllerListeners(Controller controller) {
		controller.attachListener(this);
	}

	/**
	 * Prompts an alert to the Player's when given a message that is not null
	 * @param message the message containing an alert
	 */
	public void alert(String message) {
		if (message == null) return;
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Produces an end screen when the game is over and displays the outcome of the game.
	 */
	public void endScreen() {
		String message = "Game Over! ";
		if (controller.getMancalaAStones() > controller.getMancalaBStones()) {
			message += "Player A wins! They had " + controller.getMancalaAStones() + " stones in their mancala, while Player B had " + controller.getMancalaBStones() + " stones.";
		}
		else if (controller.getMancalaAStones() < controller.getMancalaBStones()) {
			message += "Player B wins! They had " + controller.getMancalaBStones() + " stones in their mancala, while Player A had " + controller.getMancalaAStones() + " stones.";
		}
		else {
			message += "It's a tie! Both players had " + controller.getMancalaAStones() + " stones in their mancala.";
		}
		JOptionPane.showMessageDialog(this, message);
		controller.newGame();
	}

}
