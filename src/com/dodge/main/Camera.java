package com.dodge.main;

public class Camera {
	private float x, y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject Player){
		x = -Player.getX() + Game.iWidth/2 - com.dodge.main.Player.PLAYER_WIDTH/2;
		y = -Player.getY() + Game.iHeight/2 - com.dodge.main.Player.PLAYER_HEIGHT/2;
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
