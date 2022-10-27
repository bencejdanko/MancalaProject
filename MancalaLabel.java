import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaLabel extends JLabel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	int ID;
	int stones;
	Model model;
	
	public MancalaLabel(int stones, int ID, Model model) {
		this.model = model;
		this.ID = ID;
		
		if (ID == 6) this.stones = model.mancalaAStones;
		else this.stones = model.mancalaBStones;
	
		model.attach(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(0,0,40,280);
		
		int x = 10;
		int y = 20;
		
		for (int i = 1; i<= stones; i++) {
			g.drawLine(x, y, x, y);
			x+=10;
			
			if (x==30) {
				x = 10;
				y +=20;
			}
		}
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (ID == 6) this.stones = model.mancalaAStones;
		else this.stones = model.mancalaBStones;
		repaint();
	}
	
	

}
