import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class SpriteArc extends Sprite {
	public Color color;

	public SpriteArc() {
		super();
		this.color = Color.YELLOW;
	}

	public SpriteArc(Point position, Dimension size) {
		super(position, size);
		this.color = Color.YELLOW;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(this.color);
		g2.drawArc(-this.size.width/2, -this.size.height/2, this.size.width -1, this.size.height -1, 0, 360);
	}

}