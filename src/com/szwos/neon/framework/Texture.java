package com.szwos.neon.framework;

import java.awt.image.BufferedImage;

import com.szwos.neon.window.BufferedImageLoader;


public class Texture 
{
	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;

	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[6];
	public BufferedImage[] player_jump = new BufferedImage[8];
	
	public Texture()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet_crown.png");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
				
	}
	
	private void getTextures()
	{
		block[0] = bs.grabImage(1, 1, 32, 32); //klocek
		block[1] = bs.grabImage(2, 1, 32, 32); //wierzc
		
		player[0] = ps.grabImage(1, 1, 32, 32);
		player[1] = ps.grabImage(2, 1, 32, 32);
		player[2] = ps.grabImage(3, 1, 32, 32);
		player[3] = ps.grabImage(4, 1, 32, 32);
		player[4] = ps.grabImage(5, 1, 32, 32);
		player[5] = ps.grabImage(6, 1, 32, 32);
		
		player_jump[0] = ps.grabImage(1, 2, 32, 32);
		player_jump[1] = ps.grabImage(2, 2, 32, 32);
		player_jump[2] = ps.grabImage(3, 2, 32, 32);
		player_jump[3] = ps.grabImage(4, 2, 32, 32);
		
		player_jump[4] = ps.grabImage(8, 2, 32, 32);
		player_jump[5] = ps.grabImage(7, 2, 32, 32);
		player_jump[6] = ps.grabImage(6, 2, 32, 32);
		player_jump[7] = ps.grabImage(5, 2, 32, 32);
		
	}
}
