import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Cube {
	
	KeyManager km;
	MouseHandler mh;
	double x , y;
	double velX = 0, velY = 0;
	double acceleration = 0.05;
	double friction = 0.98;
	int mx, my;
	double angleToMouse;
	
	int lives = 3;
	int score = 0;
	int immunity = 0;
	
	public final int cubeWidth = Panel.tileSize;
	public final int cubeHeight = Panel.tileSize;
	
	Cube(KeyManager km, MouseHandler mh){
		this.km = km;
		this.mh = mh;
		x = Panel.frameWidth / 2 - (cubeWidth - 2);
		y = Panel.frameHeight / 2 - (cubeHeight - 2);
	}
	
	
	public void update() {
		
		mx = mh.mouseX;
		my = mh.mouseY;
		
		angleToMouse = calculateAngle((int)x + cubeWidth / 2,(int) y + cubeHeight / 2,mx,my);

		if(km.leftPress == true) { 
			velX = velX - acceleration;
		}else{
			velX = velX * friction;
		}
		
		if(km.rightPress == true) {
		

			velX = velX + acceleration;
		}else {
			
		}
		if(km.upPress == true) {
			velY = velY - acceleration;
			
		}else {
			velY = velY * friction;
		}
		if(km.downPress == true) {
			velY = velY + acceleration;
		}else {
			velY = velY * friction;
		}
		
		x = x + velX;
		y = y + velY;
		
		if(x > Panel.frameWidth) {
            x = 0 - cubeWidth;
        }
        
        if(x < 0 - cubeWidth) {
            x = Panel.frameWidth;
        }
        
        if(y > Panel.frameHeight) {
            y = 0 - cubeHeight;
        }
        
        if(y < 0 - cubeHeight) {
            y = Panel.frameHeight;
        }
		
        immune();
		//System.out.println(x);
	}
	
	public double calculateAngle(int x, int y, int mx, int my) {
		double toReturn;
		double deltaX = mx - x;
		double deltaY = my - y;
		
		toReturn = Math.atan2(deltaX, deltaY);
		//toReturn = Math.toDegrees(toReturn);
		
		return toReturn;
	}
	
	public void immune() {
		if(immunity > 0) {
			immunity--;
		}
	}
	
	
	public void draw(Graphics2D g2) {
		AffineTransform originalTransform = g2.getTransform();
		g2.setColor(Color.blue);
		g2.rotate(-angleToMouse,x + cubeWidth/2,y + cubeHeight/2);
		g2.fillRect((int)x , (int)y, cubeWidth, cubeHeight); //changed from drawRect to have a full cube
		g2.rotate(+angleToMouse,x + cubeWidth/2,y + cubeHeight/2);

		g2.setTransform(originalTransform);
	}
}
