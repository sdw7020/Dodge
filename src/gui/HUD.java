package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import main.Game;
import main.KeyInput;

public class HUD {
	public static float HEALTH = 120;
	public static float MULTIPLIER = 240;
	public static float BOOST = 120;
	private float greenValue = 182;
	private int opacity = 255;
	
	public static long sw;
	public static long ms = 0;
	public static long sec = 0;
	public static long min = 0;
	String sms = String.format("%01d", ms);
	String ssec = String.format("%02d", sec);
	String smin = String.format("%02d", min);
	
	private int score = 0;
	private int level = 1;
	public static int multiplier = 1;
	
	public enum PHASE{
		Default,
		DeadlyWalls,
		Icy;
	}
	
	public static PHASE phase = PHASE.Default;
	
	public void tick(){		
		ms = (System.currentTimeMillis() - sw)/100 - sec*10;
		sec = (System.currentTimeMillis() - sw)/1000;
		if (sec > 59){
			HUD.sw = System.currentTimeMillis();
			min++;
		}
		sms = String.format("%01d", ms);
		ssec = String.format("%02d", sec);
		smin = String.format("%02d", min);
		
		HEALTH = Game.clamp(HEALTH, 0, 120);
		BOOST = Game.clamp(BOOST, 0, 120);
		greenValue = Game.clamp(greenValue, 0, 182);
		greenValue = HEALTH*2;
		
		MULTIPLIER = Game.clamp(MULTIPLIER, 1, 240);
		
		MULTIPLIER--;
		score = score + multiplier;
		
		if(MULTIPLIER == 0){
			HEALTH = (float) (HEALTH - 0.1);
			multiplier = 1;
		}
		
		if(KeyInput.getKeyDown()[4] == true){
			if (BOOST > 0) {
				BOOST--;
			} else{
				KeyInput.boost = 1;
			}
		}
	}
	
	public void render(Graphics g){
		// Health bar
		g.setColor(new Color (0,0,0,opacity));
		g.fillRect(Game.WINDOW_WIDTH - 264+3, 24+3, 240, 36);
		g.setColor(new Color (211,211,211,opacity));
		g.fillRect(Game.WINDOW_WIDTH - 264, 24, 240, 36);
		g.setColor(new Color(62, (int)greenValue, 42, opacity));
		g.fillRect((int)Game.WINDOW_WIDTH - 264, 24, (int)HEALTH*2, 36);
		
		// Multiplier bar
		g.setColor(new Color (0,0,0,opacity));
		g.fillRect(Game.WINDOW_WIDTH - 264+3, 68+3, 240, 18);
		g.setColor(new Color (211,211,211,opacity));
		g.fillRect(Game.WINDOW_WIDTH - 264, 68, 240, 18);
		g.setColor(new Color(255,255,42,opacity));
		g.fillRect((int)Game.WINDOW_WIDTH - 264, 68, (int)MULTIPLIER, 18);
		
		// Speed boost bar
		g.setColor(new Color (0,0,0,opacity));
		g.fillRect(Game.WINDOW_WIDTH - 264+3, 94+3, 240, 18);
		g.setColor(new Color (211,211,211,opacity));
		g.fillRect(Game.WINDOW_WIDTH - 264, 94, 240, 18);
		g.setColor(new Color(55,155,255,opacity));
		g.fillRect((int)Game.WINDOW_WIDTH - 264, 94, (int)BOOST*2, 18);
		
		// Text
		g.setFont(new Font("Ubuntu Mono", Font.PLAIN, 20)); 
		g.setColor(new Color(0,0,0, 100));
		//g.drawString("TIME : "+smin+":"+ssec+"."+sms, 24+2, (Game.iHeight/2-200)+24+2);
		g.drawString("PHASE: " + phase, 24+2, (Game.WINDOW_HEIGHT/2-200)+44+2);
		g.drawString("LEVEL: " + level, 24+2, (Game.WINDOW_HEIGHT/2-200)+64+2);
		g.drawString("MLTPL: " + multiplier, 24+2, (Game.WINDOW_HEIGHT/2-200)+84+2);
		g.drawString("SCORE: " + score, 24+2, (Game.WINDOW_HEIGHT/2-200)+104+2);
		
		g.setColor(new Color(122,122,122, 100));
		//g.drawString("TIME : "+smin+":"+ssec+"."+sms, 24, (Game.iHeight/2-200)+24);
		g.drawString("PHASE: " + phase, 24, (Game.WINDOW_HEIGHT/2-200)+44);
		g.drawString("LEVEL: " + level, 24, (Game.WINDOW_HEIGHT/2-200)+64);
		g.drawString("MLTPL: " + multiplier, 24, (Game.WINDOW_HEIGHT/2-200)+84);
		g.drawString("SCORE: " + score, 24, (Game.WINDOW_HEIGHT/2-200)+104);
		
		// Time
		g.setFont(new Font("ArtBrush", Font.PLAIN, 80)); 
		g.setColor(new Color(0,0,0));
		g.drawString(""+smin+":"+ssec+"."+sms, 24+2, 84+2);
		
		g.setFont(new Font("ArtBrush", Font.PLAIN, 80)); 
		g.setColor(new Color(255,255,255));
		g.drawString(""+smin+":"+ssec+"."+sms, 24, 84);
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public float getScore(){
		return score;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public float getLevel(){
		return level;
	}
}
