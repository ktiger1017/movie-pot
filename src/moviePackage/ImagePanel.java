package moviePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -3896854509752663831L;
	private Image image;
	public ImagePanel(Image image) {
		super();
		this.image = image;
		setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
	
	public Dimension getDim() {
		return new Dimension(image.getWidth(null), image.getHeight(null)+40);
	}
	
}

