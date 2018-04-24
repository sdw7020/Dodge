package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import entities.Enemy0;
import entities.PowerUp2;
import gui.HUD.PHASE;
import main.Field;
import main.Game;
import main.Handler;
import main.ID;
import main.Player;
import main.Spawn;
import main.Game.STATE;

public class Menu extends KeyAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE){
			if (Game.gameState != STATE.Game){
				game.gameState = STATE.Game;
				hud.phase = PHASE.Default;
				Spawn.timer = System.currentTimeMillis();
				HUD.sw = System.currentTimeMillis();
				hud.setLevel(1);
				hud.setScore(0);
				HUD.ms = 0;
				HUD.sec = 0;
				HUD.min = 0;
				HUD.multiplier = 1;
				handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT/2, ID.Player, handler));

				handler.addObject(new Field(0,0,ID.Field, handler));
				for(int i = 0; i < 2; i++){
					handler.addObject(new Enemy0(r.nextInt(Game.WIDTH - 24), r.nextInt(Game.HEIGHT - 24), ID.Enemy0, handler));
					handler.addObject(new PowerUp2(r.nextInt(Game.WIDTH - PowerUp2.POWERUP_WIDTH), r.nextInt(Game.HEIGHT - PowerUp2.POWERUP_HEIGHT), ID.PowerUp2, handler));
				}
			}
		}
	}
	
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			g.setColor(new Color(0,122,222,180));
			g.fillRect(-1, -1, Game.WIDTH, Game.HEIGHT);
			
			g.setFont(new Font("ArtBrush", Font.PLAIN, 90)); 
			g.setColor(new Color(0,0,0,200));
			g.drawString("DODGE!", 203,253);
			g.setFont(new Font("ArtBrush", Font.PLAIN, 90)); 
			g.setColor(new Color(255,255,255));
			g.drawString("DODGE!", 200,250);
			
			g.setFont(new Font("VT323", Font.PLAIN, 15)); 
			g.setColor(new Color(222,222,222, 180));
			g.drawString("PRE-ALPHA 0.1.0", 225, 285);
			
			g.setFont(new Font("VT323", Font.BOLD, 30)); 
			g.setColor(new Color(0,0,0,200));
			g.drawString("PRESS SPACE TO PLAY", 202,402);
			g.setFont(new Font("VT323", Font.BOLD, 30)); 
			g.setColor(new Color(233,233,233));
			g.drawString("PRESS SPACE TO PLAY", 200,400);
			
			g.setFont(new Font("VT323", Font.PLAIN, 23)); 
			g.setColor(new Color(0,0,0,100));
			g.drawString("PRESS ESCAPE TO QUIT", 201,431);
			g.setFont(new Font("VT323", Font.PLAIN, 23)); 
			g.setColor(new Color(233,233,233, 111));
			g.drawString("PRESS ESCAPE TO QUIT", 200,430);
			
			// Made by
			g.setFont(new Font("VT323", Font.PLAIN, 18)); 
			g.setColor(new Color(222, 222, 222, 80));
			FontMetrics fm = g.getFontMetrics();
			g.drawString("(c) sdw7020 - 2016", Game.WINDOW_WIDTH - 24 - fm.stringWidth("(c) sdw7020 - 2016"), Game.WINDOW_HEIGHT - 24);
			
		}else if(game.gameState == STATE.End){
			g.setColor(new Color(200, 10, 30));
			g.fillRect(-1, -1, Game.WIDTH, Game.HEIGHT);
			
			g.setFont(new Font("ArtBrush", Font.PLAIN, 90)); 
			g.setColor(new Color(0,0,0));
			g.drawString("GAME OVER!", 253,253);
			g.setFont(new Font("ArtBrush", Font.PLAIN, 90)); 
			g.setColor(new Color(255,255,255));
			g.drawString("GAME OVER!", 250,250);
			
			g.setFont(new Font("VT323", Font.PLAIN, 30)); 
			g.setColor(new Color(0,0,0));
			g.drawString("HIGH SCORE: " + Game.highScore, 252,402);
			g.setFont(new Font("VT323", Font.PLAIN, 30)); 
			g.setColor(new Color(233,233,233));
			g.drawString("HIGH SCORE: " + Game.highScore, 250,400);
			
			g.setFont(new Font("VT323", Font.PLAIN, 30)); 
			g.setColor(new Color(0,0,0));
			g.drawString("SCORE: " + (int)hud.getScore(), 252,432);
			g.setFont(new Font("VT323", Font.PLAIN, 30)); 
			g.setColor(new Color(233,233,233));
			g.drawString("SCORE: " + (int)hud.getScore(), 250,430);
			
			g.setFont(new Font("VT323", Font.PLAIN, 30)); 
			g.setColor(new Color(0,0,0));
			g.drawString("LEVEL: " + (int)hud.getLevel(), 252,462);
			g.setFont(new Font("VT323", Font.PLAIN, 30)); 
			g.setColor(new Color(233,233,233));
			g.drawString("LEVEL: " + (int)hud.getLevel(), 250,460);
			
			g.setFont(new Font("VT323", Font.PLAIN, 23)); 
			g.setColor(new Color(0,0,0, 90));
			g.drawString("PRESS SPACE TO TRY AGAIN", 251,601);
			g.setFont(new Font("VT323", Font.PLAIN, 23)); 
			g.setColor(new Color(222,222,222, 90));
			g.drawString("PRESS SPACE TO TRY AGAIN", 250,600);
			
			// Stats
			/*g.setFont(new Font("VT323", Font.ITALIC, 80)); 
			g.setColor(new Color(40, 255, 180,150));
			g.drawString("HIGH SCORE", Game.WIDTH - 600,300);
			
			g.setFont(new Font("VT323", Font.ITALIC, 40)); 
			g.setColor(new Color(26, 255, 162,120));
			g.drawString("TESTNAAM        " + Game.highScore, Game.WIDTH - 600,600);*/
		}
	}
}
