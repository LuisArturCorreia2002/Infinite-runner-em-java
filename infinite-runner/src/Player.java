import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	
	public static int x;
	public static double y;
	
	public double vspd = 0;
	public double gravity = 0.4;
	
	public boolean jump = false;
	
	public int jumpHeight = -12;
	
	public int speedHorizontal = 5;
	
	public Player(int x, int y) {
		Player.x = x;
		Player.y = y;
	}
	
	public void tick() {
		vspd+=gravity;
		if(!World.isFree((int)x,(int)(y+1)) && jump)
		{
			vspd = jumpHeight;
			jump = false;
		}else {
			jump = false;
		}
		
		if(!World.isFree((int)x,(int)(y+vspd))) {
			
			int signVsp = 0;
			if(vspd >= 0)
			{
				signVsp = 1;
			}else  {
				signVsp = -1;
			}
			while(World.isFree((int)x,(int)(y+signVsp))) {
				y = y+signVsp;
			}
			vspd = 0;
		}
		
		y = y + vspd;
		if(World.isFree(x+speedHorizontal, (int)y)) {
		x+=speedHorizontal;
		}
		
		Camera.x = x - Game.WIDTH / 2;
		
		
		if(y >= Game.HEIGHT + 300) {
			//Game Over!
			gameOver();
			return;
		}
		
		speedHorizontal = 4 + ((Game.pontos-2) / 3) + 1;
		
		System.out.println(speedHorizontal);
		
	}
	
	public void gameOver() {
		x = 0;
		Game.world = new World();
		World.lastPos = 0;
		World.lastWidth = 0;
		World.tiles = new ArrayList<Tile>();
		Game.player = new Player(0,448-32);
		Game.pontos = 0;
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.red);
		g.drawImage(Game.playerSprite,x - Camera.x, (int)y - Camera.y, 32, 32,null);
	}
	
	
}
