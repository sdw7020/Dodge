package com.dodge.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TrailFX extends GameObject{

	private float alpha = 1;
	private float life;
	private Handler handler;
	private Color color;
	private float width, height;
	
	public TrailFX(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	public void tick() {
		if (alpha > life){
			alpha -= life - 0.0001;
		} else {
			handler.removeObject(this);
		}
	}

	public void render(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int)x, (int)y, (int)width, (int)height);		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds0() {
		return null;
	}

}
