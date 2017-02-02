

import java.awt.Color;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {
	private Color color;
	
	public ColorPanel(Color color) {
		this.color = color.darker();
		reset();
	}
	
	public void pressed() {
		setBackground(getBackground().brighter());
		update(getGraphics());
	}
	public void released() {
		setBackground(getBackground().darker());
		update(getGraphics());
	}
	public void reset() {
		setBackground(this.color);
		update(getGraphics());
	}
}
