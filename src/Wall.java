import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Wall {
	public Polygon p;

	Wall(Point2D.Float position) {
		this.p = new Polygon();
		p.addPoint((int) position.x - 10, (int) position.y - 10);
		p.addPoint((int) position.x + 10, (int) position.y - 10);
		p.addPoint((int) position.x + 10, (int) position.y + 10);
		p.addPoint((int) position.x - 10, (int) position.y + 10);
	}

	public Area getShadow(Point light) {
		if (p.contains(light)) {
			return new Area();

		} else {

			WallLine[] aWallLines = new WallLine[this.p.npoints];
			Point center = new Point(0, 0);
			for (int i = 0; i < this.p.npoints; i++) {
				WallLine nextWallline = new WallLine(light, this.p.xpoints[i],
						this.p.ypoints[i]);
				center.x += nextWallline.pEnd.x;
				center.y += nextWallline.pEnd.y;
				aWallLines[i] = nextWallline;
			}
			center.x /= this.p.npoints;
			center.y /= this.p.npoints;
			int x = center.x - light.x;
			int y = center.y - light.y;
			double a = Math.atan2(y, x);

			double[] borderDistance = { 0, 0 };
			int[] borderIndex = { 0, 0 };
			for (int i = 0; i < p.npoints; i++) {
				// double tmpDistance = aWallLines[i].distance(center);
				if (a > Math.PI / 2 && aWallLines[i].pAlpha < 0)
					aWallLines[i].pAlpha += Math.PI * 2;
				if (a < -Math.PI / 2 && aWallLines[i].pAlpha > 0)
					aWallLines[i].pAlpha -= Math.PI * 2;
				double tmpDistance = a - aWallLines[i].pAlpha;
				if (tmpDistance >= borderDistance[0]) {
					// borderDistance[1] = borderDistance[0];
					// borderIndex[1] = borderIndex[0];
					borderDistance[0] = tmpDistance;
					borderIndex[0] = i;
				} else if (tmpDistance <= borderDistance[1]) {
					borderDistance[1] = tmpDistance;
					borderIndex[1] = i;
				}
			}

			Polygon s = new Polygon();
			s.addPoint(aWallLines[borderIndex[0]].pStart.x,
					aWallLines[borderIndex[0]].pStart.y);
			s.addPoint(aWallLines[borderIndex[0]].pEnd.x,
					aWallLines[borderIndex[0]].pEnd.y);

			// Extend end of Shadow
			s.addPoint(aWallLines[borderIndex[0]].pEnd.x + (int)(Math.cos(a) * 500),
					aWallLines[borderIndex[0]].pEnd.y + (int)(Math.sin(a) * 500));
			s.addPoint(aWallLines[borderIndex[1]].pEnd.x + (int)(Math.cos(a) * 500),
					aWallLines[borderIndex[1]].pEnd.y + (int)(Math.sin(a) * 500));
			// END - Extend end of Shadow
			
			s.addPoint(aWallLines[borderIndex[1]].pEnd.x,
					aWallLines[borderIndex[1]].pEnd.y);
			s.addPoint(aWallLines[borderIndex[1]].pStart.x,
					aWallLines[borderIndex[1]].pStart.y);
			int i = borderIndex[1];
			while (i != borderIndex[0]) {
				s.addPoint(p.xpoints[i], p.ypoints[i]);
				if (++i >= p.npoints)
					i = 0;
			}

			return new Area(s);
		}
	}

	public void paint(Graphics2D g2) {
		g2.fill(p);
	}

}
