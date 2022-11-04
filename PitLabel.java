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
	int ID; 
	int stones;
	
	public PitLabel(int stones, int ID, Model model, View view) {
		this.model = model;
		this.ID = ID;
		this.stones = model.getStoneData().get(ID);
		this.view = view;
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
		model.updateStones(ID);
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
