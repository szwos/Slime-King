package com.szwos.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.szwos.neon.framework.GameObject;
import com.szwos.neon.framework.ObjectId;

public class Flag extends GameObject 
{

	public Flag(float x, float y, ObjectId id) {
		super(x, y, id);
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
	
		
	}

	@Override
	public void render(Graphics g) 
	{		
		g.setColor(Color.yellow);
		g.fillRect((int)x,  (int)y,  32, 32);
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
