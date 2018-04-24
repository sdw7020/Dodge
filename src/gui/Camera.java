package gui;

import main.Game;
import main.GameObject;
import main.Player;

public class Camera {
	private float x, y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject Player){
		x = -Player.getX() + Game.WINDOW_WIDTH/2 - main.Player.PLAYER_WIDTH/2;
		y = -Player.getY() + Game.WINDOW_HEIGHT/2 - main.Player.PLAYER_HEIGHT/2;
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
}
