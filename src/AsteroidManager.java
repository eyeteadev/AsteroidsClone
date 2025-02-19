import java.awt.Graphics2D;
import java.util.ArrayList;

public class AsteroidManager {
	
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	Bullet bullet;
	Panel panel;
	int spawnAmount;
	Cube cube;
	
	AsteroidManager(Panel panel, Cube cube){
		this.spawnAmount = 5;
		this.panel = panel;
		this.cube = cube;
	}
	
	public void spawnAsteroids() {
		for(int i = 0; i < spawnAmount; i++) {
			Asteroid asteroid = new Asteroid(cube);
			asteroids.add(asteroid);
		}
		
		spawnAmount++;
		
	}
	
	public void update() {
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update();
		}
	}
	
	
	// :( what the fuck
	public boolean checkCollision(Bullet bullet) {
		boolean toRet = false;
		for(int i = 0; i < asteroids.size(); i++) {
			if((bullet.x <= asteroids.get(i).x + Asteroid.asteroidWidth && bullet.x >= asteroids.get(i).x) && (bullet.y <= asteroids.get(i).y + Asteroid.asteroidHeight && bullet.y >= asteroids.get(i).y)) {
				asteroids.get(i).toDraw = false;
				
				toRet = true;
			}
		}
		return toRet;
	}
	
	
	public void checkCollision(Cube cube) {
		boolean isColliding = false;
		
		for(int i = 0; i < asteroids.size(); i++) {
			isColliding = (cube.x < asteroids.get(i).x + Asteroid.asteroidWidth && 
                    cube.x + cube.cubeWidth > asteroids.get(i).x &&
                    cube.y < asteroids.get(i).y  + Asteroid.asteroidHeight &&
                    cube.y + cube.cubeHeight > asteroids.get(i).y );
			
			if(cube.immunity <= 0 && isColliding) {
				cube.lives--;
				cube.immunity = 180;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(g2);
		}
	}
	
	public void removeNoDraw() {
		for(int i = 0; i < asteroids.size(); i++) {
			if(asteroids.get(i).toDraw == false) {
				asteroids.remove(i);
			}
		}
	}
	

}
