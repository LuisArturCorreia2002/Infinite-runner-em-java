import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
	
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	
	public static World world;
	
	public static Player player;
	
	public static int pontos  = 0;
	
	public static BufferedImage playerSprite;
	public static BufferedImage floorSprite;
	
	public Game() {
		world = new World();
		player = new Player(0,448-32);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.addKeyListener(this);
		
		try {
			playerSprite = ImageIO.read(getClass().getResource("/player.png"));
			floorSprite = ImageIO.read(getClass().getResource("/sprite.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick() {
		world.tick();
		player.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0,148,255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		world.render(g);
		
		player.render(g);
		
		g.setFont(new Font("arial",Font.BOLD,18));
		g.setColor(Color.white);
		g.drawString("Pontos: "+(pontos-2), 10, 20);
		
		bs.show();
		
	}

	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Game game = new Game();
		frame.setTitle("Infinite Runner");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		new Thread(game).start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			this.player.jump = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
