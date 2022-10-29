//right now focusing on View, for that an AbstractStrategy class will be required to maintain is-a relationship

import javax.swing.*;
import java.awt.*;

//there are several assumptions in this class and after the creation of AbstractStrategy I need to sweep it.
public class viewTester extends AbstractStrategy {

	public viewTester() {
		super(frame, container);
	}

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
	//now for labels
	public void CreateLabels(){
		firstPlayer = new JLabel("Player 1");
		secondPlayer = new JLabel("Player 2");

		//deciding the color combinations for them
		firstPlayer.setForeground(Color.YELLOW);
		secondPlayer.setForeground(Color.RED);

		getPlayerLabel().add(firstPlayer);
		getPlayerLabel().add(secondPlayer);

		for(int i=0; i<getPlayerLabel().size(); i++){
			int x= getMancala().get(i).getX() + getMancala().get(i).getWidth() / 3;
			int y = getMancala().get(i).getY() - getMancala().get(i).getHeight() / 2;
			getPlayerLabel().get(i).setBounds(xCoord, yCoord, getMancala().get(i).getWidth() / 2, getMancala().get(i).getHeight() / 2);
			pnlMancala.add(getPlayerLabel().get(i));
		}

	}

}
