import java.awt.Graphics2D;
import java.awt.Point;


public class WallLine {
	public Point pStart;
	public Point pEnd;
	public double pAlpha;

	WallLine(Point source, int x, int y) {
		this(source, new Point(x, y));
	}
	
	WallLine(Point source, Point poly) {
		int x = poly.x - source.x;
		int y = poly.y - source.y;
		
		this.pAlpha = Math.atan2(y, x);
		
		x = (int)(poly.x + Math.cos(this.pAlpha) * 500);
		y = (int)(poly.y + Math.sin(this.pAlpha) * 500);
		
		this.pStart = poly;
		this.pEnd = new Point(x, y);
	}
	
	public double distance(Point p) {
		return pEnd.distance(p);
	}

	public void paint(Graphics2D g2) {
		g2.drawLine((int)pStart.x, (int)pStart.y, (int)pEnd.x, (int)pEnd.y);
	}
}
