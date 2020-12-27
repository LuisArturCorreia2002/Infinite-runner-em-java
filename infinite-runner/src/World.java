import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

	public static int lastPos = 0;
	public static int lastWidth;
	
	public static int blockSize = 32;
	
	public static List<Tile> tiles = new ArrayList<Tile>();
	
	
	public void tick() {
		for(int i = 0; i < tiles.size(); i++) {
			tiles.get(i).tick();
		}
		if(Player.x >= lastPos) {
			//System.out.println("Gerar novo bloco de mundo!");
			int newWidth = new Random().nextInt(10-7) + 7;
			//Correcao!
			//Adicionamos o lastWidth para criar apenas uma vez sem espaço.
			if(lastPos+lastWidth == 0)
				lastPos = lastPos + (lastWidth*32);
			else
				lastPos = lastPos + new Random().nextInt(300-100)+100 +(lastWidth*32);
			generateWorldBlocks(newWidth);
			Game.pontos++;
			lastWidth = newWidth;
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < tiles.size(); i++) {
			tiles.get(i).render(g);
		}
	}
	
	public void generateWorldBlocks(int width) {
		for(int i =0; i < width; i++) {
			Tile tile = new Tile(lastPos + (i*blockSize),448);
			tiles.add(tile);
		}
	}
	
	public static boolean isFree(int nextx,int nexty) {
		Rectangle playerBox = new Rectangle(nextx,nexty,blockSize,blockSize);
		for(int i = 0; i < tiles.size(); i++) {
			Rectangle boxTile = new Rectangle(tiles.get(i).x,tiles.get(i).y,blockSize,blockSize);
			if(playerBox.intersects(boxTile))
				return false;
		}
		
		return true;
	}
	
	
}
