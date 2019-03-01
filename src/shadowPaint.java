import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class shadowPaint extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public boolean moveLight = true;
	public List<SpriteFoV> lLight;
	public List<Wall> wWall;
	
	private double angel = 0;

	public shadowPaint() {
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(640, 480));
		this.setFocusable(true); // required for KeyListener to work
		this.setFocusTraversalKeysEnabled(false); // required to get the TAB-Key

		this.addMouseListener(new shadowMouseInputListener(this));
		this.addMouseMotionListener(new shadowMouseInputListener(this));

		lLight = new ArrayList<SpriteFoV>();
		lLight.add(new SpriteFoV(new Point(0, 0), new Dimension(400, 400)));
		lLight.add(new SpriteFoV(new Point(0, 0), new Dimension(400, 400)));

		wWall = new ArrayList<Wall>();
		wWall.add(new Wall(new Point2D.Float(100, 60)));
		wWall.add(new Wall(new Point2D.Float(100, 80)));
		wWall.add(new Wall(new Point2D.Float(100, 100)));
		wWall.add(new Wall(new Point2D.Float(100, 120)));
		wWall.add(new Wall(new Point2D.Float(100, 140)));
		wWall.add(new Wall(new Point2D.Float(120, 60)));
		wWall.add(new Wall(new Point2D.Float(120, 100)));
		wWall.add(new Wall(new Point2D.Float(140, 80)));
		wWall.add(new Wall(new Point2D.Float(140, 80)));
		wWall.add(new Wall(new Point2D.Float(140, 100)));
		wWall.add(new Wall(new Point2D.Float(140, 120)));
		wWall.add(new Wall(new Point2D.Float(140, 140)));

		wWall.add(new Wall(new Point2D.Float(180, 60)));
		wWall.add(new Wall(new Point2D.Float(180, 80)));
		wWall.add(new Wall(new Point2D.Float(180, 100)));
		wWall.add(new Wall(new Point2D.Float(180, 120)));
		wWall.add(new Wall(new Point2D.Float(180, 140)));
		wWall.add(new Wall(new Point2D.Float(200, 100)));
		wWall.add(new Wall(new Point2D.Float(200, 80)));
		wWall.add(new Wall(new Point2D.Float(220, 60)));
		wWall.add(new Wall(new Point2D.Float(220, 80)));
		wWall.add(new Wall(new Point2D.Float(220, 100)));
		wWall.add(new Wall(new Point2D.Float(220, 120)));
		wWall.add(new Wall(new Point2D.Float(220, 140)));

		wWall.add(new Wall(new Point2D.Float(260, 60)));
		wWall.add(new Wall(new Point2D.Float(260, 80)));
		wWall.add(new Wall(new Point2D.Float(260, 100)));
		wWall.add(new Wall(new Point2D.Float(260, 120)));
		wWall.add(new Wall(new Point2D.Float(260, 140)));
		wWall.add(new Wall(new Point2D.Float(280, 60)));
		wWall.add(new Wall(new Point2D.Float(280, 140)));
		wWall.add(new Wall(new Point2D.Float(300, 80)));
		wWall.add(new Wall(new Point2D.Float(300, 100)));
		wWall.add(new Wall(new Point2D.Float(300, 120)));

		wWall.add(new Wall(new Point2D.Float(340, 60)));
		wWall.add(new Wall(new Point2D.Float(340, 80)));
		wWall.add(new Wall(new Point2D.Float(340, 100)));
		wWall.add(new Wall(new Point2D.Float(360, 100)));
		wWall.add(new Wall(new Point2D.Float(360, 120)));
		wWall.add(new Wall(new Point2D.Float(360, 140)));
		wWall.add(new Wall(new Point2D.Float(380, 60)));
		wWall.add(new Wall(new Point2D.Float(380, 80)));
		wWall.add(new Wall(new Point2D.Float(380, 100)));
		
		Polygon tmpP = new Polygon();
		double iMax = 16;
		for (double i = 0; i < iMax; i++) {
			double a = 360 * i / iMax / 180.0d * Math.PI;
			wWall.add(new Wall(new Point2D.Float(
					400 + (float) Math.cos(a) * 140,
					300 + (float) Math.sin(a) * 80)));
			tmpP.addPoint((int)(500 +  Math.cos(a) * 20),
					(int)(100 +  Math.sin(a) * 20));
		}
		Wall tmpW = new Wall(new Point2D.Float(500, 100));
		tmpW.p = tmpP;
		wWall.add(tmpW);
		
		
		Timer x = new Timer(33, this);
		x.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Shadows
		float[] dist = { 0.0f, 0.1f, 0.3f, 1.0f };
		Color[] colors = { new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 0.65f), new Color(1.0f, 1.0f, 1.0f, 0.0f) };
		for (int i = 0; i < lLight.size(); i++) {
			Area areaShadow = new Area();
			for (int j = 0; j < wWall.size(); j++) {
				Area areaTmp = wWall.get(j).getShadow(
						lLight.get(i).position);
				if(areaTmp.isEmpty()) {
					areaShadow = new Area();
					break;
				} else {
					areaShadow.add(areaTmp);
				}
			}
			if(areaShadow.isEmpty()) continue;

			Area areaLight = new Area(new Arc2D.Float(lLight.get(i).position.x
					- lLight.get(i).size.width / 2, lLight.get(i).position.y
					- lLight.get(i).size.height / 2, lLight.get(i).size.width,
					lLight.get(i).size.height, 0, 360, Arc2D.PIE));
			areaShadow.intersect(areaLight);
			areaShadow.exclusiveOr(areaLight);
			RadialGradientPaint rgp = new RadialGradientPaint(lLight.get(i).position, lLight.get(i).size.width/2, dist, colors);
			g2.setPaint(rgp);
			g2.fill(areaShadow);
		}

		// Objects
		g2.setColor(Color.BLUE);
		for (int i = 0; i < wWall.size(); i++) {
			wWall.get(i).paint(g2);
		}

/*		// Field of View
		Rectangle2D.Float shapeScreen = new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight());
		Area areaScreen = new Area(shapeScreen);
		for (int i = 0; i < lLight.size(); i++) {
			//if (lLight.size() == 1) lLight.get(i).paint(g2);
			Area areaLight = new Area(new Arc2D.Float(lLight.get(i).position.x
					- lLight.get(i).size.width / 2, lLight.get(i).position.y
					- lLight.get(i).size.height / 2, lLight.get(i).size.width,
					lLight.get(i).size.height, 0, 360, Arc2D.PIE));
			areaScreen.subtract(areaLight);
			g2.setColor(Color.YELLOW);
			g2.drawArc(lLight.get(i).position.x - 3, lLight.get(i).position.y - 3, 6, 6, 0, 360);
		}
		g2.setColor(Color.BLACK);
		g2.fill(areaScreen);*/
		
		g2.setColor(Color.GREEN);
		g2.drawString("Maus: Left = add light / right = remove last / middle = stick first", 10, 15);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(this.lLight.size() > 1) {
			this.angel += 0.003;
			int x = (int)(400 + Math.cos(this.angel) * 170);
			int y = (int)(300 + Math.sin(this.angel) * 110);
			this.lLight.get(1).setPosition(x, y);
		}
		
		repaint();
	}
}
