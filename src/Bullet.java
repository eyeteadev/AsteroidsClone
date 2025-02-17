import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet{
	
	boolean isAlive;
	double toX, toY;
	double x, y;
	int mx, my;
	
	Bullet(double x, double y, int mx, int my){
		this.x = x;
		this.y = y;
		this.mx = mx;
		this.my = my;
		calcTrajectory();
	}
	
	public void draw(Graphics2D g2) {
		g2.drawOval((int)x,(int) y, 4, 4);
		
	}
	
	public void update() {
		x += toX;
		y += toY;
		
	}
	
	public void calcTrajectory() {
		double dirX = mx - x;
		double dirY = my - y;
    
		double pyth = Math.sqrt(dirX * dirX + dirY * dirY);
		
		if (pyth > 0) {
			toX = (dirX / pyth) * 5;
			toY = (dirY / pyth) * 5;
		}
		
	}
}
