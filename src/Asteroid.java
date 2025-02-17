import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Random;

public class Asteroid {
	double x, y;
	double boxX, boxY;
	BufferedImage image;
	Random random;
	double toX, toY;
	double randX, randY;
	double randtoX, randtoY;
	static int asteroidWidth, asteroidHeight;
	boolean toDraw;
	
	Asteroid(){
		toDraw = true;
		boxX = 30;
		boxY = 30;
		asteroidWidth = 40;
		asteroidHeight = 34;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("pixil-frame-0.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		random = new Random();
		randX = random.nextDouble() * Panel.frameWidth;
		randY = random.nextDouble() * Panel.frameHeight;		
		randtoX = random.nextDouble() * Panel.frameWidth;
		randtoY = random.nextDouble() * Panel.frameHeight;	
		
		x = randtoX;
		y = randtoY;
		
		calcTrajectory();
		
	}
	
	public void calcTrajectory() {
		double dirX = randX - x;
		double dirY = randY - y;
    
		double pyth = Math.sqrt(dirX * dirX + dirY * dirY);
		
		if (pyth > 0) {
			toX = (dirX / pyth) * 2;
			toY = (dirY / pyth) * 2;
		}
		
	}
	
	public void update() {
		x += toX;
		y += toY;
		
		if(x > Panel.frameWidth) {
            x = 0 - asteroidWidth;
        }
        
        if(x < 0 - asteroidWidth) {
            x = Panel.frameWidth;
        }
        
        if(y > Panel.frameHeight) {
            y = 0 - asteroidHeight;
        }
        
        if(y < 0 - asteroidHeight) {
            y = Panel.frameHeight;
        }
		
	}
	
	
	
	
	
	public void draw(Graphics2D g2) {
		if(toDraw) {
			g2.drawImage(image,(int) x,(int) y,  asteroidWidth, asteroidHeight,null);
		}
	}
	
}
