import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	public boolean upPress, downPress, leftPress, rightPress;
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A) {
			leftPress = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			rightPress = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			upPress = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			downPress = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A) {
			leftPress = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			rightPress = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			upPress = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			downPress = false;
		}
	}

}
