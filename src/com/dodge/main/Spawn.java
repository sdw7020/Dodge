package com.dodge.main;

import java.util.Random;

import com.dodge.main.HUD.PHASE;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private float scoreKeep = 0;
	int lastPhase = 0;
	public static long timer;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;

		if(System.currentTimeMillis() - timer > 20000){
			timer += 20000;
			
			// Remove phases
			if (hud.phase == PHASE.DeadlyWalls) {
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					
					if (tempObject.getId() == ID.DeadlyWalls){
						handler.removeObject(tempObject);						
					}
				}
			}
			if (hud.phase == PHASE.Icy) {
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					
					if (tempObject.getId() == ID.Icy){
						handler.removeObject(tempObject);						
					}
				}
			}
			
			// Select new phase
			int randomPhase =2;
			/*do {
				randomPhase = r.nextInt(3);
			} while (randomPhase == lastPhase);
			lastPhase = randomPhase;*/
			if (randomPhase == 0){
				hud.phase = PHASE.Default;
			}
			if (randomPhase == 1){
				hud.phase = PHASE.DeadlyWalls;
				handler.addObject(new DeadlyWalls(0,0,ID.DeadlyWalls, handler));
			}
			if (randomPhase == 2){
				hud.phase = PHASE.Icy;
				handler.addObject(new Icy(0,0,ID.Icy, handler));
			}	
		}
		
		if(scoreKeep >= 300){
			scoreKeep = 0;
			hud.setLevel((int)hud.getLevel() + 1);
			
			// ENEMIES
			if (hud.getLevel() % 4 == 0 && hud.getLevel() != 0){
				for(int i = 0; i < 2; i++){
					handler.addObject(new Enemy0(r.nextInt(Game.WIDTH - Enemy0.ENEMY_WIDTH), r.nextInt(Game.HEIGHT - Enemy0.ENEMY_HEIGHT), ID.Enemy0, handler));
				}
			}
			if (hud.getLevel() % 6 == 0){
				handler.addObject(new Enemy2(r.nextInt(Game.WIDTH - Enemy2.ENEMY_WIDTH), r.nextInt(Game.HEIGHT - Enemy0.ENEMY_HEIGHT), ID.Enemy2, handler));
			}
				
			/*if (hud.getLevel() % 7 == 0){
				for(int i = 0; i < 1; i++){
					handler.addObject(new Enemy1(r.nextInt(Game.WIDTH - Enemy1.ENEMY_WIDTH), r.nextInt(Game.HEIGHT - Enemy1.ENEMY_HEIGHT), ID.Enemy1, handler));
				}
			}*/
			if (hud.getLevel() % 10 == 0){
				for(int i = 0; i < 1; i++){
					handler.addObject(new Enemy2(r.nextInt(Game.WIDTH - Enemy2.ENEMY_WIDTH), r.nextInt(Game.HEIGHT - Enemy2.ENEMY_HEIGHT), ID.Enemy2, handler));
				}
			}
			
			//POWER UPS
			if (hud.getLevel() % 5 == 0 && hud.getLevel() >= 10){
				for(int i = 0; i < 1; i++){
					handler.addObject(new PowerUp0(r.nextInt(Game.WIDTH - PowerUp0.POWERUP_WIDTH), r.nextInt(Game.HEIGHT - PowerUp0.POWERUP_HEIGHT), ID.PowerUp0, handler));
				}
			}
			/*if (hud.getLevel() % 15 == 0){
				for(int i = 0; i < 1; i++){
					handler.addObject(new PowerUp1(r.nextInt(Game.WIDTH - PowerUp1.POWERUP_WIDTH), r.nextInt(Game.HEIGHT - PowerUp1.POWERUP_HEIGHT), ID.PowerUp1, handler));
				}
			} */ // NUKE OBJECT will replace this. EDIT: DONE!!!!
			for(int i = 0; i < 2; i++){
				handler.addObject(new PowerUp2(r.nextInt(Game.WIDTH - PowerUp2.POWERUP_WIDTH), r.nextInt(Game.HEIGHT - PowerUp2.POWERUP_HEIGHT), ID.PowerUp2, handler));
			}
		}		
	}
}
