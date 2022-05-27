package com.szwos.neon.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.szwos.neon.framework.GameObject;
import com.szwos.neon.framework.ObjectId;
import com.szwos.neon.objects.Block;
import com.szwos.neon.objects.Flag;
import com.szwos.neon.objects.Player;

public class Handler 
{

	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private Camera cam;
	
	private BufferedImage level2 = null;
	private BufferedImage level1 = null;
	private BufferedImage bricks = null, tetris2 = null, tetris1 = null, tetris_trans = null;
	
	
	
	
	public Handler(Camera cam)
	{
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/level2.png");
		level1 = loader.loadImage("/level.png");
		bricks = loader.loadImage("/bkg_dungeon2.png");
		tetris2 = loader.loadImage("/bkg_tetris2.png");
		tetris1 = loader.loadImage("/bkg_tetris1.png");
		tetris_trans = loader.loadImage("/bkg_dungeon-tetris_transition.png");

		
	}
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void LoadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width: " + w + ", height: "  + h);
		
		for(int xx = 0; xx < h; xx++)
		{
			for(int yy = 0; yy < w; yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) &0xff;
				
				if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
				if(red == 128 && green == 128 && blue == 128) addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
				if(red == 0 && green == 0 && blue == 255) addObject(new Player(xx*32, yy*32, this, cam ,ObjectId.Player));
				if(red == 255 && green == 255 && blue == 0) addObject(new Flag(xx*32, yy*32, ObjectId.Flag));
				
			}
		}
	}
	
	public void drawBG(Graphics g)
	{
	
		int xx = 32;
		int yy = 32;
		
		
			for(yy = 0; yy < Game.HEIGHT * 21; yy += Game.HEIGHT)
				g.drawImage(bricks, xx, yy, Game.WIDTH, Game.HEIGHT, null);
		
			
			g.drawImage(tetris2, xx, yy, Game.WIDTH, Game.HEIGHT, null);//tetris2
			 yy += Game.HEIGHT;
			
			g.drawImage(tetris1, xx, yy, Game.WIDTH, Game.HEIGHT, null);//tetris1
			 yy += Game.HEIGHT;
			 
			g.drawImage(tetris_trans, xx, yy, Game.WIDTH, Game.HEIGHT, null);//transition
			 yy += Game.HEIGHT;
			
			 
			for(int i = 0; i < 6; i++)
			{
				g.drawImage(bricks, xx, yy, Game.WIDTH, Game.HEIGHT, null);//transition
				 yy += Game.HEIGHT;
				 
			}
			
 
			
	}

	
	public void reloadLevel() {
		switchLevel(Game.LEVEL);
	}
	
	public void nextLevel()
	{
		Game.LEVEL++;
		switchLevel(Game.LEVEL);
		
	}
	
	public void switchLevel(int lvl)
	{
		clearLevel();
		cam.setX(0);
		
		switch(lvl)
		{
		case 1:
			LoadImageLevel(level1);
			break;
		case 2:
			LoadImageLevel(level2);
			break;
		}
		Game.LEVEL = lvl;
	}
	
	
	
	private void clearLevel()
	{
		object.clear();
	}
	
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
}
