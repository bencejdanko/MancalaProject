import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
/**
 * Fall 2022 CS151 Project Submission
 * @author Bence Danko & Ryan Yee
 * @version 1.0 11/20/22
 *
 * The BoardIcon class represents an icon that is responsible for displaying background
 * of the board in the View in certain Style supported by the View.
 */
public class BoardIcon implements Icon, ImageObserver {
	View view;

	/**
	 * Constructs a BoardIcon with a given View
	 * @param view
	 */
	public BoardIcon(View view) {
		this.view = view;
	}

	/**
	 * Paints the BoardIcon with the given Style
	 * @param c  a Component to get properties useful for painting
	 * @param g  the graphics context
	 * @param x  the X coordinate of the icon's top-left corner
	 * @param y  the Y coordinate of the icon's top-left corner
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		String path = view. style.getBackgroundPath();
		File file = new File(path);
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.drawImage(image,0,0,this);
	}

	/**
	 * Returns the width of the Icon
	 * @return 0
	 */
	@Override
	public int getIconWidth() {
		// never used
		return 0;
	}

	/**
	 * Returns the height of the Icon
	 * @return 0
	 */
	@Override
	public int getIconHeight() {
		//never used
		return 0;
	}

	/**
	 * Updates the BoardIcon image
	 * @param img   the image being observed.
	 * @param infoflags   the bitwise inclusive OR of the following
	 * @param x   the x coordinate.
	 * @param y   the y coordinate.
	 * @param width    the width.
	 * @param height   the height.
	 * @return
	 */
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		//board background icon is never updated
		return true;
	}


}
