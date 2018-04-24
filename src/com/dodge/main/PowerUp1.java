package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PowerUp1 extends GameObject {
	
	public static final int POWERUP_WIDTH = 24, POWERUP_HEIGHT = 24;
	
	private Handler handler;

	public PowerUp1(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds0(){
		return new Rectangle((int)x, (int)y, POWERUP_WIDTH, POWERUP_HEIGHT);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		int [] xStar = {(int)x, (int)x+POWERUP_WIDTH, (int)x+POWERUP_WIDTH/2};
		int [] yStar = {(int)y, (int)y, (int)y+POWERUP_HEIGHT};
		
		g.setColor(new Color(255,165,0));
		g.fillPolygon(xStar, yStar, 3);
		//g.drawRect((int)x, (int)y, POWERUP_WIDTH, POWERUP_HEIGHT);
		g.setColor(new Color(255,165,0,44));
		g.fillOval((int)x-POWERUP_WIDTH/2, (int)y-POWERUP_HEIGHT/2, POWERUP_WIDTH*2, POWERUP_HEIGHT*2);
		g.setColor(new Color(255,165,0,33));
		g.fillOval((int)x-POWERUP_WIDTH, (int)y-POWERUP_HEIGHT, POWERUP_WIDTH*3, POWERUP_HEIGHT*3);
		g.setColor(new Color(255,165,0,22));
		g.fillOval((int)x-POWERUP_WIDTH*2, (int)y-POWERUP_HEIGHT*2, POWERUP_WIDTH*5, POWERUP_HEIGHT*5);
		g.setColor(new Color(255,165,0,11));
		g.fillOval((int)x-POWERUP_WIDTH*3, (int)y-POWERUP_HEIGHT*3, POWERUP_WIDTH*7, POWERUP_HEIGHT*7);		
	}
}
