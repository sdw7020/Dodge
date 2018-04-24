package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy2 extends GameObject{
	
	public static final int ENEMY_WIDTH = 18, ENEMY_HEIGHT = 18;
	
	private Handler handler;
	private GameObject player;

	public Enemy2(float x, float y, ID id, Handler handler/*, int rVelX, int rVelY*/) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player){
				player = handler.object.get(i);
			}
		}
	}
	
	public Rectangle getBounds0(){
		return new Rectangle((int)x, (int)y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

	public void tick() {
		handler.addObject(new TrailFX(x, y, ID.TrailFX, new Color((int)117, (int)252, (int)62), ENEMY_WIDTH, ENEMY_HEIGHT, 0.03f, handler));
		
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - Player.PLAYER_WIDTH/2;
		float diffY = y - player.getY() - Player.PLAYER_HEIGHT/2;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX = ((-1/distance) * diffX) * 3;
		velY = ((-1/distance) * diffY) * 3;
		/*
		if (x <= 0 || x >= Game.WIDTH - ENEMY_WIDTH){
			velX *= -1;
		}
		if (y <= 0 || y >= Game.HEIGHT - ENEMY_WIDTH){
			velY *= -1;
		}*/
		
		
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(69, 102, 22));
		g.fillRect((int)x, (int)y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

}
