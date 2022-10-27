import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
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
		model.update(ID);
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
