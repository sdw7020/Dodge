package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import gui.HUD;

public class Player extends GameObject{
	
	Handler handler;
	
	public static final int PLAYER_WIDTH = 24, PLAYER_HEIGHT = 24;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds0(){
		return new Rectangle((int)x, (int)y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	public void tick() {
		handler.addObject(new TrailFX((int)x, (int)y, ID.TrailFX, new Color(222,222,222), PLAYER_HEIGHT, PLAYER_HEIGHT, 0.15f, handler));
		
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - PLAYER_WIDTH);
		y = Game.clamp(y, 0, Game.HEIGHT - PLAYER_HEIGHT);
		
		collision();
	}
	
	private void collision(){
		for(int i = 0; i < handler.objects.size(); i++){
			GameObject tempObject = handler.objects.get(i);
			
			if(tempObject.getId() == ID.Enemy0 || tempObject.getId() == ID.Enemy1 || tempObject.getId() == ID.Enemy2){
				if(getBounds0().intersects(tempObject.getBounds0())){
					// Collision code
					HUD.HEALTH -= 2;
				}
			}
			if(tempObject.getId() == ID.PowerUp0){
				if(getBounds0().intersects(tempObject.getBounds0())){
					// Collision code
					HUD.MULTIPLIER = 240;
					HUD.BOOST += 5;
					HUD.HEALTH += 40;
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.PowerUp1){
				if(getBounds0().intersects(tempObject.getBounds0())){
					// Collision code
					HUD.MULTIPLIER = 240;
					handler.clearEnemies();
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.PowerUp2){
				if(getBounds0().intersects(tempObject.getBounds0())){
					// Collision code
					HUD.MULTIPLIER = 240;
					HUD.multiplier++;
					HUD.BOOST += 5;
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.DeadlyWalls){
				if(getBounds0().intersects(tempObject.getBounds0())){
					HUD.HEALTH = 0;
				}
				if(getBounds0().intersects(tempObject.getBounds1())){
					HUD.HEALTH = 0;
				}
				if(getBounds0().intersects(tempObject.getBounds2())){
					HUD.HEALTH = 0;
				}
				if(getBounds0().intersects(tempObject.getBounds3())){
					HUD.HEALTH = 0;
				}
			}
		}
	}

	public void render(Graphics2D g) {		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}

}
