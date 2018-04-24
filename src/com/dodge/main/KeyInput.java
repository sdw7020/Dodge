package com.dodge.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dodge.main.Game.STATE;
import com.dodge.main.HUD.PHASE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private static boolean[] keyDown = new boolean[5];
	
	private int vel = 6; // replace individual numbers 
	
	Game game;
	
	public static int boost = 1;
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		this.game = game;
		
		getKeyDown()[0]=false; // W
		getKeyDown()[1]=false; // S
		getKeyDown()[2]=false; // A
		getKeyDown()[3]=false; // D
		getKeyDown()[4]=false; // SPACE
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
					tempObject.setVelY(-vel * boost);
					getKeyDown()[0]=true;
				}
				if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
					tempObject.setVelY(vel * boost);
					getKeyDown()[1]=true;
				}
				if(key == KeyEvent.VK_LEFT  || key == KeyEvent.VK_A){
					tempObject.setVelX(-vel * boost);
					getKeyDown()[2]=true;
				}
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
					tempObject.setVelX(vel * boost);
					getKeyDown()[3]=true;
				}
				if(key == KeyEvent.VK_SPACE){
					boost = 2;
					do{
						getKeyDown()[0]=true;
					}while (key == KeyEvent.VK_W && HUD.BOOST !=0);
					do{
						getKeyDown()[1]=true;
					} while (key == KeyEvent.VK_S && HUD.BOOST !=0);
					do{
						getKeyDown()[2]=true;
					}while(key == KeyEvent.VK_A && HUD.BOOST !=0);
					do{
						getKeyDown()[3]=true;
					}while(key == KeyEvent.VK_D && HUD.BOOST !=0);
					getKeyDown()[4]=true;
				}
			}
		}
		
		if(key == KeyEvent.VK_P){
			if(game.gameState == STATE.Game){
				if(Game.paused){
					Game.paused = false;
				}else{
					Game.paused = true;
				}
			}
		}
		if(key == KeyEvent.VK_SPACE){
			if(game.gameState == STATE.Game){
				if(Game.paused){
					Game.paused = false;
				}
			}
		}
		if(key == KeyEvent.VK_ENTER){
			if(game.gameState == STATE.Game){
				if(Game.paused){
					HUD.HEALTH = 120;
					HUD.MULTIPLIER = 240;
					HUD.BOOST = 120;
					Game.paused = false;
					Game.gameState = STATE.Menu;
					handler.object.clear();
					//handler.clearEnemies();
				}
			}
		}
		if(key == KeyEvent.VK_ESCAPE){
			if(game.gameState == STATE.Menu){
				System.exit(0);				
			}else if(game.gameState == STATE.End){
				HUD.HEALTH = 120;
				Game.paused = false;
				Game.gameState = STATE.Menu;
				handler.object.clear();	
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
					getKeyDown()[0]=false; // New way
				}
				if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
					getKeyDown()[1]=false;
				}
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
					getKeyDown()[2]=false;
				}
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
					getKeyDown()[3]=false;
				}
				if(key == KeyEvent.VK_SPACE){
					boost = 1;
					getKeyDown()[4]=false;
				}
				
				// Horizontal movement
				if (!getKeyDown()[2] && !getKeyDown()[3]){
					tempObject.setVelX(0);
				}
				// Vertical movement
				if (!getKeyDown()[0] && !getKeyDown()[1]){
					tempObject.setVelY(0);
				}
			}
		}
	}

	public static boolean[] getKeyDown() {
		return keyDown;
	}

	public void setKeyDown(boolean[] keyDown) {
		this.keyDown = keyDown;
	}

}
