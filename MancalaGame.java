public class MancalaGame {
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
