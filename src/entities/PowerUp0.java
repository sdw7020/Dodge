package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GameObject;
import main.Handler;
import main.ID;

public class PowerUp0 extends GameObject {
	
	public static final int POWERUP_WIDTH = 36, POWERUP_HEIGHT = 36;
	private String color = "yellow";
	
	private Handler handler;

	public PowerUp0(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds0(){
		return new Rectangle((int)x, (int)y, POWERUP_WIDTH, POWERUP_HEIGHT);
	}
	
	public void tick(){
		if (color == "yellow"){
			handler.removeObject(this);
			color = "white";
			handler.addObject(this);
		}else if (color == "white"){
			handler.removeObject(this);
			color = "blue";
			handler.addObject(this);
		} else if (color == "blue"){
			handler.removeObject(this);
			color = "pink";
			handler.addObject(this);
		} else if (color == "pink"){
			handler.removeObject(this);
			color = "green";
			handler.addObject(this);
		}else if (color == "green"){
			handler.removeObject(this);
			color = "black";
			handler.addObject(this);
		} else if (color == "black"){
			handler.removeObject(this);
			color = "yellow";
			handler.addObject(this);
		}
	}
	
	public void render(Graphics2D g){
		int [] xStarS = {(int)x+3, (int)x+POWERUP_WIDTH+3, (int)x+3+POWERUP_WIDTH/2};
		int [] yStarS = {(int)y+POWERUP_HEIGHT+3, (int)y+3+POWERUP_HEIGHT, (int)y+3};
		
		int [] xStar = {(int)x, (int)x+POWERUP_WIDTH, (int)x+POWERUP_WIDTH/2};
		int [] yStar = {(int)y+POWERUP_HEIGHT, (int)y+POWERUP_HEIGHT, (int)y};
		
		g.setColor(Color.black);
		g.fillPolygon(xStarS, yStarS, 3);
		
		if (color == "yellow"){
			g.setColor(new Color(20,240,40));
		}
		if (color == "white"){
			g.setColor(Color.WHITE);
		}
		if (color == "blue"){
			g.setColor(new Color(20,250,40));
		}
		if (color == "pink") {
			g.setColor(new Color(20,240,40));
		}
		if (color == "green"){
			g.setColor(Color.black);
		}
		if (color == "black"){
			g.setColor(new Color(5,200,15));
		}
		g.fillPolygon(xStar, yStar, 3);
		//g.drawRect((int)x, (int)y, POWERUP_WIDTH, POWERUP_HEIGHT); // Hit box
		
	}
}
