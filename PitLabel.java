import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PitLabel extends JLabel implements MouseListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	int stones;
	Image image;
	
	public PitLabel(int stones) {
		this.stones = stones;
		
		String path = "images/pits/pit" + stones + ".png";
		File file = new File(path);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image,0,0,this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
