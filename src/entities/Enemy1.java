package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Game;
import main.GameObject;
import main.Handler;
import main.ID;
import main.TrailFX;

public class Enemy1 extends GameObject{
	
	public static final int ENEMY_WIDTH = 24, ENEMY_HEIGHT = 24;
	
	private Handler handler;

	public Enemy1(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 12;
		velY = 2;
	}
	
	public Rectangle getBounds0(){
		return new Rectangle((int)x, (int)y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

	public void tick() {
		handler.addObject(new TrailFX(x, y, ID.TrailFX, new Color((int)247, (int)172, (int)242), ENEMY_WIDTH, ENEMY_HEIGHT, 0.2f, handler));
		
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.WIDTH - ENEMY_WIDTH){
			velX *= -1;
		}
		if (y <= 0 || y >= Game.HEIGHT - ENEMY_WIDTH){
			velY *= -1;
		}
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(147, 62, 222));
		g.fillRect((int)x, (int)y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

}
