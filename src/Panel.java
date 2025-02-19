import java.awt.Color;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
	
	public static final int frameWidth = 600;
	public static final int frameHeight = 500;
	public static final int screenArea = frameWidth * frameHeight;
	public static final int tileSize = 25;
	
	final int FPS = 60;
	
	Cube cube;
	Thread gameThread;
	KeyManager km;
	MouseHandler mh;
	BulletSpawner bs;
	AsteroidManager asteroidManager;
	Asteroid asteroid;
	LifeCounter counter;
	
	
	Panel(){
		this.setSize(frameWidth,frameHeight);
		mh = new MouseHandler();
		km = new KeyManager();
		cube = new Cube(km,mh);
		asteroidManager = new AsteroidManager(this,cube);
		
		counter = new LifeCounter(cube);
		bs = new BulletSpawner(mh,cube);
		this.setPreferredSize(new Dimension(frameWidth,frameHeight));
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
		this.addKeyListener(km);
		this.addMouseListener(mh);
		this.addMouseMotionListener(mh);
		this.setLayout(null);
		this.add(counter);
		
		asteroidManager.spawnAsteroids();
		
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		cube.update();
		bs.checkClick();
		asteroidManager.update();
		
		asteroidManager.checkCollision(cube);
		bulletAsteroidCollisionUpdate();
		if(asteroidManager.asteroids.size() < 1) {
			asteroidManager.spawnAsteroids();
		}
		
		if(cube.lives <= 0) {
			counter.over = true;
		}
		
		counter.update(cube);
	}
	
	
	//the reason  i lump in the collision with the bullet drawing is that collision shouldnt be checked if no bullets exist. 
	public void bulletAsteroidCollisionUpdate() {
		if(bs.bullets.size() > 0) {
			for(int i = 0; i < bs.bullets.size(); i++) {
				//This is to update the bullet.
				bs.bullets.get(i).update();
				
				//this then calls checkCollision, which inturn handles most of the collision checking.
				//if its a collision, it sets the individual asteroids's toDraw boolean to false, which is then checked in the AstManager update function.
				//it calles remove after to then remove the objects not being drawn from the array.
				if(asteroidManager.checkCollision(bs.bullets.get(i))) {
					cube.score += 100;
					asteroidManager.removeNoDraw();
				}
				if(bs.bullets.get(i).x < 0 || bs.bullets.get(i).x > Panel.frameWidth || bs.bullets.get(i).y < 0 || bs.bullets.get(i).y > Panel.frameHeight) {
					bs.bullets.remove(i);
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		
		cube.draw(g2);
		asteroidManager.draw(g2);
		if(bs.bullets.size() > 0) {
			for(int i = 0; i < bs.bullets.size(); i++) {
				bs.bullets.get(i).draw(g2);
			}
		}
		
		counter.draw(g2);
		
		g2.dispose();
	}
	
	@Override
	public void run() {
		//everything commented out is for FPS counter.
		
		double interval = 1_000_000_000 / FPS;
		double delta = 0;
		long currentTime;
		long previousTime = System.nanoTime();
		/*
		long timer = 0;
		int drawCount = 0;
		*/
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - previousTime) / interval;
			//timer = timer + ( currentTime - previousTime);
			previousTime = currentTime;
			
			if(delta >= 1) {	
				update();
				repaint();
				if(counter.over) {
					gameThread = null;
				}
				delta--;
				
				//drawCount++;
				
				//this is what makes it so you can only have 1 bullet per click
				if(mh.click == true) {
					mh.click = false;
				}

			}
			/*
			if(timer >= 1000000000) {
				//System.out.println("FPS: " + drawCount); keep this here for the future.
				drawCount = 0;
				timer = 0;
			}
			*/
		}
	}
}
