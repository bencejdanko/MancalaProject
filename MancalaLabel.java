import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaLabel extends JLabel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	int stones;
	Image image;
	
	public MancalaLabel(int stones) {
		this.stones = stones;
		
		String path = "images/mancalas/mancala" + stones + ".png";
		File file = new File(path);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image,0,0,this);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
