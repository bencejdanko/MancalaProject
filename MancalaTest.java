/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * MancalaTest holds the main method to test the Mancala game
 */
public class MancalaTest {
	/**
	 * Main method of MancalaTest
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller();
		View view = new View();

		controller.setModel(model);
		controller.setView(view);
		
		view.setController(controller);

		controller.startGame();
	}

}
