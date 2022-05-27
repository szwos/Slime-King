package com.szwos.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.szwos.neon.framework.GameObject;
import com.szwos.neon.framework.ObjectId;

public class Bullet extends GameObject 
{

	public Bullet(float x, float y, ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		
	}

	@Override
	public void render(Graphics g) 
	{		
		g.setColor(new Color(81, 255, 7));
		g.fillRect((int)x,  (int)y,  16, 16);
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
}
