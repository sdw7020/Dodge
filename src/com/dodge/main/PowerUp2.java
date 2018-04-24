package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class PowerUp2 extends GameObject {
	
	public static final int POWERUP_WIDTH = 36, POWERUP_HEIGHT = 36;
	private int color = 0;
	
	private Random r;
	private Handler handler;

	public PowerUp2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		r = new Random();
		
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds0(){
		return new Rectangle((int)x, (int)y, POWERUP_WIDTH, POWERUP_HEIGHT);
	}
	
	public void tick(){
		handler.removeObject(this);
		int colorKeep = color;
		color = r.nextInt(6);
		do {
			color = r.nextInt(6);
		} while (color == colorKeep);
		handler.addObject(this);
	}
	
	public void render(Graphics2D g){
		int [] xStarS = {(int)x+3, (int)x+3+POWERUP_WIDTH/2, (int)x+3+POWERUP_WIDTH, (int)x+3+POWERUP_WIDTH/2};
		int [] yStarS = {(int)y+3+POWERUP_HEIGHT/2, (int)y+3, (int)y+3+POWERUP_HEIGHT/2, (int)y+3+POWERUP_HEIGHT};
		
		int [] xStar = {(int)x, (int)x+POWERUP_WIDTH/2, (int)x+POWERUP_WIDTH, (int)x+POWERUP_WIDTH/2};
		int [] yStar = {(int)y+POWERUP_HEIGHT/2, (int)y, (int)y+POWERUP_HEIGHT/2, (int)y+POWERUP_HEIGHT};

		g.setColor(Color.black);
		g.fillPolygon(xStarS, yStarS, 4);
		
		g.setColor(new Color(255,255,42));
		if (color == 0){
			g.setColor(new Color(255,255,42));	// YELLOW
		}
		if (color == 1){
			g.setColor(Color.WHITE);			// WHITE
		}
		if (color == 2){
			g.setColor(new Color(0,122,255)); 	// BLUE
		}
		if (color == 3) {
			g.setColor(new Color(148,0,211));	// PURPLE
		}
		if (color == 4){
			g.setColor(new Color(50,250,50));	// GREEN
		}
		if (color == 5){
			g.setColor(new Color(200, 10, 30));	// RED
		}
		g.fillPolygon(xStar, yStar, 4);
		//g.drawRect((int)x, (int)y, POWERUP_WIDTH, POWERUP_HEIGHT); // HITBOX
	}
}
