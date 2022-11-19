import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PitLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	View view;
	int ID; 
	int stones;
	
	public PitLabel(int ID, View view) {
		this.view = view;
		this.ID = ID;
		MouseListeners listener = new MouseListeners();
		addMouseListener(listener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		view.style.paintPit(g, stones);
	}

	public void update(){
		stones = view.controller.getStoneData().get(ID);
		repaint();
	}

	private class MouseListeners extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			view.controller.updateStones(ID);
		}
	}
}
