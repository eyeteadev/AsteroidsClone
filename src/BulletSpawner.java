import java.util.ArrayList;

public class BulletSpawner {
	
	MouseHandler mh;
	Bullet bullet;
	Cube cube;
	int amtOfBullet = 0;
	ArrayList<Bullet> bullets;
	
	BulletSpawner(MouseHandler mh, Cube cube){
		this.mh = mh;
		this.cube = cube;
		bullets = new ArrayList<Bullet>();
	}
	
	public void checkClick() {
		if(mh.click == true) {
			Bullet bullet = new Bullet(cube.x + cube.cubeWidth/2,cube.y + cube.cubeHeight/2, mh.mouseX, mh.mouseY);
			bullets.add(bullet);
			amtOfBullet++;
		}
	}
	
	
}
