package main;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < objects.size(); i++){
			GameObject tempObject = objects.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics2D g){
		for(int i = 0; i < objects.size(); i++){
			GameObject tempObject = objects.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearEnemies(){
		for(int i = 0; i < objects.size(); i++){
			GameObject tempObject = objects.get(i);
			
			if (tempObject.getId() == ID.Player) {
				objects.clear();
				if (Game.gameState != Game.STATE.End) {
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
				}
				if (Game.gameState == Game.STATE.Game && Game.paused == true) {
					objects.clear();
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
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object){
		this.objects.remove(object);
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}
}
