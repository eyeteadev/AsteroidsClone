import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cube {
	
	KeyManager km;
	double x , y;
	double velX = 0, velY = 0;
	double acceleration = 0.05;
	double friction = 0.98;
	
	int lives = 3;
	int score = 0;
	int immunity = 0;
	
	public final int cubeWidth = Panel.tileSize;
	public final int cubeHeight = Panel.tileSize;
	
	Cube(KeyManager km){
		this.km = km;
		x = Panel.frameWidth / 2 - (cubeWidth - 2);
		y = Panel.frameHeight / 2 - (cubeHeight - 2);
	}
	
	
	public void update() {

		if(km.leftPress == true) {
			//x -= 2;
			velX = velX - acceleration;
		}else{
			velX = velX * friction;
		}
		
		if(km.rightPress == true) {
		
			//x += 2;
			velX = velX + acceleration;
		}else {
			
		}
		if(km.upPress == true) {
			//y -= 2;
			velY = velY - acceleration;
			
		}else {
			velY = velY * friction;
		}
		if(km.downPress == true) {
			//y += 2;
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
	
	public void immune() {
		if(immunity > 0) {
			immunity--;
		}
	}
	
	
	public void draw(Graphics2D g2) {
		//g2.setBackground(Color.BLACK);
		g2.setColor(Color.blue);
		g2.drawRect((int)x, (int)y,cubeWidth, cubeHeight);
		//g2.dispose();
	}
}
