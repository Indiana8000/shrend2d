import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;


public class SpriteFoV extends Sprite {
	private RadialGradientPaint rgp;
	private float[] dist = { 0.85f, 0.95f, 1.0f };
	private Color[] colors = { new Color(0,0,0,0), new Color(0,0,0,0.5f),   Color.BLACK };

	public SpriteFoV() {
		super();
		this.rgp = new RadialGradientPaint(new Point(0, 0), this.size.width/2, this.dist, this.colors);
	}
	
	public SpriteFoV(Point position, Dimension size) {
		super(position, size);
		this.rgp = new RadialGradientPaint(new Point(0, 0), this.size.width/2, this.dist, this.colors);
	}

	public void draw(Graphics2D g2) {
		g2.setPaint(this.rgp);
		g2.fillOval(-this.size.width/2, -this.size.height/2, this.size.width, this.size.height);

	}

}
