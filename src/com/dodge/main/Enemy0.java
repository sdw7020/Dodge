package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy0 extends GameObject{
	
	public static final int ENEMY_WIDTH = 24, ENEMY_HEIGHT = 24;
	
	private Handler handler;

	public Enemy0(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds0(){
		return new Rectangle((int)x, (int)y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

	public void tick() {
		handler.addObject(new TrailFX((int)x, (int)y, ID.TrailFX, new Color((int)247, (int)172, (int)142), ENEMY_WIDTH, ENEMY_HEIGHT, 0.8f, handler));
		
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
		g.setColor(new Color(247, 62, 22));
		g.fillRect((int)x, (int)y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

}
