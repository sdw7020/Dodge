package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Field extends GameObject{
	
	private Handler handler;

	public Field(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds0(){
		return null;
	}

	public void tick() {
		
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(8,8,8));
		g.fillRect((int)x+16, (int)y+16, Game.WIDTH, Game.HEIGHT);
		g.setColor(new Color((int)22,(int)22,(int)22));
		g.fillRect((int)x, (int)y, Game.WIDTH, Game.HEIGHT);			// optional background
		g.drawImage(Toolkit.getDefaultToolkit().getImage("res/FieldBackground2.png"), 0, 0, null);
	}

}
