import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class shadowMouseInputListener implements MouseInputListener {
	private shadowPaint parent;

	shadowMouseInputListener(shadowPaint parent) {
		this.parent = parent;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getButton() == 1 && this.parent.lLight.size() < 10) {
			this.parent.lLight.add(new SpriteFoV(new Point(arg0.getX(), arg0.getY()), new Dimension(400, 400)));
			//this.parent.repaint();
		}
		if(arg0.getButton() == 2) {
			this.parent.moveLight =! this.parent.moveLight;
		}
		if(arg0.getButton() == 3 && this.parent.lLight.size() > 1) {
			this.parent.lLight.remove(this.parent.lLight.size() -1);
			//this.parent.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(this.parent.moveLight) {
			this.parent.lLight.get(0).setPosition(arg0.getX(), arg0.getY());
			//this.parent.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if(this.parent.moveLight) {
			this.parent.lLight.get(0).setPosition(arg0.getX(), arg0.getY());
			//this.parent.repaint();
		}
	}

}
