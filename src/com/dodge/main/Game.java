package com.dodge.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -6349073816761171019L;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int iWidth = (int) screenSize.getWidth();
	static int iHeight = (int) screenSize.getHeight();
	static int fSize = 2400;
	public static final int WIDTH = fSize, HEIGHT = fSize - 1000;

	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;

	public static String highScore = "";
	String username = System.getProperty("user.name");

	private Random r;
	private HUD hud;
	private Handler handler;
	private Camera cam;
	private Spawn spawner;
	private Menu menu;

	public enum STATE {
		Menu, Game, End;
	};

	public static STATE gameState = STATE.Menu;

	public Game() {

		try {
			InputStream is = getClass().getResourceAsStream("res/ArtBrush.ttf");
			Font ArtBrush = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		handler = new Handler();
		cam = new Camera(0, 0);
		hud = new HUD();
		menu = new Menu(this, handler, hud);

		this.addKeyListener(new KeyInput(handler, this));
		this.addKeyListener(menu);

		new Window(WIDTH, HEIGHT, "GameTest1", this);

		spawner = new Spawn(handler, hud);
		r = new Random();

		if (highScore.equals("")) {
			highScore = this.getHighScore();
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		// Basic game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ms = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ms;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("DODGE - " + frames + " frames per second, " + amountOfTicks + " ticks per second");
				frames = 0;
			}
		}
		stop();
	}

	public String getHighScore() {
		FileReader readFile = null;
		BufferedReader bReader = null;
		try {
			readFile = new FileReader("C:\\Users\\" + username + "\\Documents\\Games\\sdw7020\\Dodge\\highscore.dat");
			bReader = new BufferedReader(readFile);
			return bReader.readLine();
		} catch (Exception e) {
			return "-1";
		} finally {
			try {
				if (bReader != null) {
					bReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void tick() {
		highScore = this.getHighScore();
		if (gameState == STATE.Game) {
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				for (int i = 0; i < handler.object.size(); i++) {
					if (handler.object.get(i).getId() == ID.Player) {
						cam.tick(handler.object.get(i));
					}
				}

				// Game over
				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 120;
					HUD.MULTIPLIER = 240;
					HUD.BOOST = 120;
					gameState = STATE.End;
					handler.clearEnemies();
					PrintWriter writeFile;
					try {
						File file = new File("C:\\Users\\" + username + "\\Documents\\Games\\sdw7020\\Dodge",
								"highscore.dat");
						file.getParentFile().mkdirs();
						try {
							FileWriter writer = new FileWriter(file);
						} catch (IOException e) {
							e.printStackTrace();
						}

						writeFile = new PrintWriter(
								"C:\\Users\\" + username + "\\Documents\\Games\\sdw7020\\Dodge\\highscore.dat");
						if (Game.highScore == null) {
							Game.highScore = "0";
						}
						if (hud.getScore() > Integer.parseInt(Game.highScore)) {
							writeFile.println((int) hud.getScore());
						} else {
							writeFile.println(Integer.parseInt(Game.highScore));
						}
						writeFile.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(new Color((int) 11, (int) 11, (int) 11));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Camera movement
		g.translate(cam.getX(), cam.getY());

		handler.render(g);

		g.translate(-cam.getX(), -cam.getY());

		if (gameState == STATE.Game) {
			if (!paused) {
				hud.render(g);
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.render(g);
		}

		if (paused) {
			g.setColor(new Color(55, 55, 55, 120));
			g.fillRect(-1, -1, WIDTH + 1, HEIGHT + 1);

			g.setFont(new Font("ArtBrush", Font.PLAIN, 90));
			g.setColor(new Color(0, 0, 0));
			g.drawString("GAME PAUSED", 253, 253);
			g.setFont(new Font("ArtBrush", Font.PLAIN, 90));
			g.setColor(new Color(242, 242, 242));
			g.drawString("GAME PAUSED", 250, 250);

			g.setFont(new Font("VT323", Font.PLAIN, 30));
			g.setColor(new Color(0, 0, 0));
			g.drawString("PRESS SPACE / P TO CONTINUE", 252, 402);
			g.setFont(new Font("VT323", Font.PLAIN, 30));
			g.setColor(new Color(222, 222, 222));
			g.drawString("PRESS SPACE / P TO CONTINUE", 250, 400);

			g.setFont(new Font("VT323", Font.PLAIN, 23));
			g.setColor(new Color(0, 0, 0, 90));
			g.drawString("PRESS ENTER TO GO TO MAIN MENU", 251, 431);
			g.setFont(new Font("VT323", Font.PLAIN, 23));
			g.setColor(new Color(222, 222, 222, 90));
			g.drawString("PRESS ENTER TO GO TO MAIN MENU", 250, 430);
		}

		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String args[]) {
		new Game();
	}

}
