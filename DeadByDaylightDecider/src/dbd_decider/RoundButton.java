package dbd_decider;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RoundButton extends JButton {

	/*
	 * Source ideas for rounded button implementation:
	 * https://stackoverflow.com/questions/3439112/java-custom-shape-panels
	 * https://stackoverflow.com/questions/9361658/disable-jbutton-focus-border
	 * 
	 * Tweaked a bit to fit desired look and feel aspects.
	 */
	public RoundButton(ImageIcon imageIcon)
	{
		super(imageIcon);
		//Remove highlighted border when clicked
		setFocusPainted(false);

		// These statements shape the button so that it
		// becomes a circle rather than an oval.
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);

		// This call causes the JButton not to paint the background.
		// This allows us to paint a round background.
		setContentAreaFilled(false);
	}
	
	// Paint the border of the button using a simple stroke.
	@Override
	protected void paintBorder(Graphics g)
	{
		super.paintComponent(g);
	}

	// Hit detection.
	Shape shape;
	public boolean contains(int x, int y)
	{
		// If the button has changed size, make a new shape object.
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
}