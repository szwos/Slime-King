package com.szwos.neon.window;

import com.szwos.neon.framework.GameObject;

public class Camera 
{

	private float x, y;

	
	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player)
	{
		y = ((int)(-player.getY())/480) * 480  +55 /*- Game.HEIGHT/2*/;
	}
	
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
