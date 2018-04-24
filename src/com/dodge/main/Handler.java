package com.dodge.main;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics2D g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearEnemies(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				object.clear();
				if (Game.gameState != Game.STATE.End) {
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
				}
				if (Game.gameState == Game.STATE.Game && Game.paused == true) {
					object.clear();
				}
			}
			if (tempObject.getId() == ID.DeadlyWalls) {
				if (Game.gameState != Game.STATE.End) {
					addObject(new DeadlyWalls(0,0, ID.DeadlyWalls, this));
				}
			}
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
}
