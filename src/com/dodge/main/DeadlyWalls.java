package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.dodge.main.HUD.PHASE;

public class DeadlyWalls extends GameObject {

	public DeadlyWalls(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
	}

	public Rectangle getBounds0() {
		return new Rectangle(20,0, Game.WIDTH-41, 20);						// Hit box N
	}
	
	public Rectangle getBounds1() {
		return new Rectangle(0,0,20,Game.HEIGHT-1);						// Hit box W
	}
	
	public Rectangle getBounds2() {
		return new Rectangle(20, Game.HEIGHT-21, Game.WIDTH-41, 20);		// Hit box S
	}
	
	public Rectangle getBounds3() {
		return new Rectangle(Game.WIDTH-21, 0, 20, Game.HEIGHT-1);		// Hit box E
	}
	
	public void tick() {
		
	}

	public void render(Graphics2D g) {
		// Deadly Walls
		if (HUD.phase == PHASE.DeadlyWalls) {
			g.setColor(new Color(200, 10, 30, 150));
			g.fillRect(20,0, Game.WIDTH-41, 20);	
			g.fillRect(0,0,20,Game.HEIGHT-1); 				
			g.fillRect(20, Game.HEIGHT-21, Game.WIDTH-41, 20); 	
			g.fillRect(Game.WIDTH-21, 0, 20, Game.HEIGHT-1);
		}
	}
}
