package com.dodge.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.dodge.main.HUD.PHASE;

public class Icy extends GameObject {

	public Icy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
	}

	public void tick() {
		
	}

	public void render(Graphics2D g) {
		if (HUD.phase == PHASE.Icy){
			g.setColor(new Color(0,177,255,40));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		}
	}

	public Rectangle getBounds0() {
		return null;
	}

}
