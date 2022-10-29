//right now focusing on View, for that an AbstractStrategy class will be required to maintain is-a relationship

import javax.swing.*;

public class viewTester extends AbstractStrategy {

	public static void main(String[] args) {
		Model model = new Model(4);
		new View(model);


	}
	private JLabel firstPlayer;
	private JLabel secondPlayer;

	public void createMancala(){
		String player = "A";
		int y =80;

		for(int i=0; i<2; i++){

			//need to adda  getMancala method in AbstractStrategy
			getMancala().add(new JButton());
			getMancala().get(i).setBounds(320, y, 320, 40);
			y= y+320;
			getMancala().get(i).setName("Player" + player);
			pnlMancala.add(getMancala().get(i));
			player = "B";
		}
	}

}
