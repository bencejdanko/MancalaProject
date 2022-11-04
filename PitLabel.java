import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PitLabel extends JLabel implements MouseListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	View view;
	Model model;
	Controller controller;
	int ID; 
	int stones;
	
	public PitLabel(int stones, int ID, Model model, View view, Controller controller) {
		this.model = model;
		this.view = view;
		this.controller = controller;
		this.ID = ID;
		this.stones = model.getStoneData().get(ID);
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		view.style.paintPit(g, stones);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		stones = model.getStoneData().get(ID);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		controller.updateStones(ID);
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
