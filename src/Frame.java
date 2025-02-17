import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public final int frameWidth = 400;
	public final int frameHeight = 400;
	public final int screenArea = frameWidth * frameHeight;
	Panel panel;
	
	Frame(){
		panel = new Panel();
		
		this.setSize(panel.frameWidth,panel.frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		
		this.add(panel);
		//this.pack();
		this.setVisible(true);
		
		panel.startGameThread();
	}
}
