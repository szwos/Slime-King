package com.szwos.neon.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.szwos.neon.framework.KeyInput;
import com.szwos.neon.framework.ObjectId;
import com.szwos.neon.framework.Texture;



public class Game extends Canvas implements Runnable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4330447379051834729L;

	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	public static int LEVEL = 1;
	
	public BufferedImage level = null, bricks = null, tetris2 = null, tetris1 = null, tetris_trans = null;
	
	Handler handler;
	Camera cam;
	static Texture tex;
	
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		level = loader.loadImage("/level.png");
		bricks = loader.loadImage("/bkg_dungeon2.png");
		tetris2 = loader.loadImage("/bkg_tetris2.png");
		tetris1 = loader.loadImage("/bkg_tetris1.png");
		tetris_trans = loader.loadImage("/bkg_dungeon-tetris_transition.png");

		
		
		
		cam = new Camera(-32, 0);
		
		handler = new Handler(cam);
		
		handler.LoadImageLevel(level);
		
		
		//handler.addObject(new Player(100, 100, handler,  ObjectId.Player));
		
		//handler.CreateLevel();
	
		
		//handler.addObject(new Block(100, 100, ObjectId.Block));
		
		this.addKeyListener(new KeyInput(handler));
		
	}
	
	
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run()
	{
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	private void tick()
	{
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++)
		{
			if(handler.object.get(i).getId() == ObjectId.Player)
			{
				cam.tick(handler.object.get(i));
			}
		}
			
	}
	
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		///////////
		
		
		//render
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		
		g2d.translate(cam.getX(), cam.getY()); //begin of cam whatever that means
		
		
		 
		handler.drawBG(g);
		
		handler.render(g);
			
		
		g2d.translate(-cam.getX(), -cam.getY()); //end of cam
		
		
		///////////
		g.dispose();
		bs.show();
		
	}
	
	
	
	public static Texture getInstance()
	{
		return tex;
	}
	
	public static void main(String args[])
	{
		new Window(800, 600, "Slime King", new Game());
	}
}
