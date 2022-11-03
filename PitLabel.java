import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PitLabel extends JLabel implements MouseListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	Model model;
	int ID; 
	int stones;
	
	public PitLabel(int stones, int ID, Model model) {
		this.model = model;
		this.ID = ID;
		this.stones = model.getPitStones().get(ID);
		
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(0,0,70,110);
		
		int x = 10;
		int y = 20;
		
		for (int i = 1; i<= stones; i++) {
			g.drawLine(x, y, x, y);
			x+=10;
			
			if (x==60) {
				x = 10;
				y+=20;
			}
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		stones = model.pitStoneData.get(ID);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Player currentPlayer = model.getCurrentPlayer();
		boolean oneMore = false;
		if (ID >= 0 && ID <= 5 && currentPlayer.getPlayerID() == 1) {
			JFrame wrongFrame = new JFrame();
			wrongFrame.setSize(200, 100);
			wrongFrame.setLayout(new BorderLayout());
			JLabel label = new JLabel("Player B must choose from B1 to B6");
			label.setSize(200, 75);
			JButton button = new JButton("Ok");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					wrongFrame.dispose();
				}
			});
			wrongFrame.add(label, BorderLayout.NORTH);
			wrongFrame.add(button, BorderLayout.CENTER);
			wrongFrame.setLocationRelativeTo(null);
			wrongFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			wrongFrame.pack();
			wrongFrame.setVisible(true);
		}
		else if (ID >= 7 && ID <= 12 && currentPlayer.getPlayerID() == 0){
			JFrame wrongFrame = new JFrame();
			wrongFrame.setSize(200, 100);
			wrongFrame.setLayout(new BorderLayout());
			JLabel label = new JLabel("Player A must choose from A1 to A6");
			label.setSize(200, 75);
			JButton button = new JButton("Ok");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					wrongFrame.dispose();
				}
			});
			wrongFrame.add(label, BorderLayout.NORTH);
			wrongFrame.add(button, BorderLayout.CENTER);
			wrongFrame.setLocationRelativeTo(null);
			wrongFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			wrongFrame.pack();
			wrongFrame.setVisible(true);
		}
		else if (model.getPitStones().get(ID) == 0)
		{
			JFrame wrongFrame = new JFrame();
			wrongFrame.setSize(200, 100);
			wrongFrame.setLayout(new BorderLayout());
			JLabel label = new JLabel("No stones --> Choose another pit");
			label.setSize(200, 75);
			JButton button = new JButton("Ok");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					wrongFrame.dispose();
				}
			});
			wrongFrame.add(label, BorderLayout.NORTH);
			wrongFrame.add(button, BorderLayout.CENTER);
			wrongFrame.setLocationRelativeTo(null);
			wrongFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			wrongFrame.pack();
			wrongFrame.setVisible(true);
		}
		else{
			currentPlayer.setHand(stones);
			model.updateStones(ID, 0);
			int IDcounter = ID+1;
			while (currentPlayer.getHand() > 0)
			{
				if (IDcounter == 6){
					if (currentPlayer.getPlayerID() == 0){
						currentPlayer.decreaseHand();
						model.updateStones(IDcounter, model.mancalaAStones++);
						if (currentPlayer.getHand() == 0)
						{
							oneMore = true;
							JFrame oneMoreFrame = new JFrame();
							oneMoreFrame.setSize(200, 100);
							oneMoreFrame.setLayout(new FlowLayout());
							JLabel label = new JLabel("Player A gets one more turn");
							label.setSize(200, 75);
							JButton button = new JButton("Ok");
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									oneMoreFrame.dispose();
								}
							});
							oneMoreFrame.add(label, BorderLayout.NORTH);
							oneMoreFrame.add(button, BorderLayout.CENTER);
							oneMoreFrame.setLocationRelativeTo(null);
							oneMoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							oneMoreFrame.pack();
							oneMoreFrame.setVisible(true);
						}
					}
					IDcounter++;
				}
				else if (IDcounter == 13){
					if (currentPlayer.getPlayerID() == 1){
						currentPlayer.decreaseHand();
						model.updateStones(IDcounter, model.mancalaBStones++);
						if (currentPlayer.getHand() == 0)
						{
							oneMore = true;
							JFrame oneMoreFrame = new JFrame();
							oneMoreFrame.setSize(200, 100);
							oneMoreFrame.setLayout(new FlowLayout());
							JLabel label = new JLabel("Player B gets one more turn");
							label.setSize(200, 75);
							JButton button = new JButton("Ok");
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									oneMoreFrame.dispose();
								}
							});
							oneMoreFrame.add(label, BorderLayout.NORTH);
							oneMoreFrame.add(button, BorderLayout.CENTER);
							oneMoreFrame.setLocationRelativeTo(null);
							oneMoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							oneMoreFrame.pack();
							oneMoreFrame.setVisible(true);
						}
					}
					IDcounter = 0;
				}
				else {
					model.updateStones(IDcounter, model.getPitStones().get(IDcounter) + 1);
					IDcounter++;
					currentPlayer.decreaseHand();
				}
			}
			IDcounter-=1;
			if (IDcounter == -1)
			{
				IDcounter = 13;
			}
			if(model.pitStoneData.get(IDcounter) == 1 && IDcounter < 6 && currentPlayer.getPlayerID() == 0) { //if the last stone is dropped into an empty pit on player A's side
				model.mancalaAStones += model.pitStoneData.get(12-IDcounter) + 1;
				model.updateStones(6 , model.mancalaAStones); //take the stones from the opposite pit and add them to mancala A
				model.updateStones(12-IDcounter, 0);
				model.updateStones(IDcounter, 0);
			}
			else if (model.pitStoneData.get(IDcounter) == 1 && IDcounter > 6 && IDcounter < 13 && currentPlayer.getPlayerID() == 1) { //if the last stone is dropped into an empty pit on player B's side
				model.mancalaBStones += model.pitStoneData.get(12-IDcounter) + 1;
				model.updateStones(13 , model.mancalaBStones); //take the stones from the opposite pit and add them to mancala B
				model.updateStones(12-IDcounter, 0);
				model.updateStones(IDcounter, 0);
			}
			//This needs to be worked on
			if (model.pitStoneData.subList(0, 5).stream().mapToInt(Integer::intValue).sum() == 0) {
				model.mancalaBStones += model.pitStoneData.subList(7, 12).stream().mapToInt(Integer::intValue).sum();
				model.pitStoneData.subList(7, 12).clear();
				Collections.fill(model.pitStoneData.subList(0, 5), 0);
			} else if (model.pitStoneData.subList(7, 12).stream().mapToInt(Integer::intValue).sum() == 0) {
				model.mancalaAStones += model.pitStoneData.subList(0, 5).stream().mapToInt(Integer::intValue).sum();
				model.pitStoneData.subList(0, 5).clear();
				Collections.fill(model.pitStoneData.subList(7, 12), 0);
			}
			if (!oneMore)
			{
				model.setCurrentPlayer(currentPlayer.getPlayerID());
				model.updateStones(IDcounter, model.getPitStones().get(IDcounter));
			}
		}


//		model.update(ID);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
