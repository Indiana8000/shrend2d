import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public abstract class Sprite {
	protected Point position;
	protected Dimension size;
	protected float rotation;
	protected float scale;

	public Sprite() {
		this.position = new Point(-50, -50);
		this.size = new Dimension(50, 50);
		this.rotation = 0;
		this.scale = 1.0f;
	}

	public Sprite(Point position, Dimension size) {
		this.size = size;
		this.position = position;
		this.rotation = 0;
		this.scale = 1.0f;
	}

	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setPosition(Point position, int offsetX, int offsetY) {
		this.position.x = position.x + offsetX;
		this.position.y = position.y + offsetY;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void paint(Graphics2D g2) {
		// Save original Transformation
		AffineTransform saveAT = g2.getTransform();

		// Transform Painting Area
		AffineTransform newAT = new AffineTransform();
		newAT.translate(this.position.x, this.position.y);
		newAT.scale(this.scale, this.scale);
		newAT.rotate((this.rotation) / 180.0d * Math.PI);
		g2.transform(newAT);

		// Draw this Sprite (and/or Child)
		this.draw(g2);

		// DEBUG: Draw frame around Sprite
		g2.setColor(Color.gray);
		//g2.drawRect(-this.size.width / 2, -this.size.height / 2, this.size.width -1, this.size.height - 1);

		// Restore original Transformation
		g2.setTransform(saveAT);
	}

	public abstract void draw(Graphics2D g2);
}
