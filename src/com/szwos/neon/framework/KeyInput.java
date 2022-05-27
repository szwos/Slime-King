package com.szwos.neon.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.szwos.neon.window.Handler;

public class KeyInput extends KeyAdapter
{
	Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT)
					{
						tempObject.right = true;
						tempObject.lastInput = 1;
						tempObject.lookingUp = false;
					}
				
				if(key == KeyEvent.VK_LEFT) 
					{
						tempObject.left = true;
						tempObject.lastInput = -1;
						tempObject.lookingUp = false;
					}
				
				if(key == KeyEvent.VK_UP)
				{
					tempObject.lookingUp = true;
				}
				
				if(key == KeyEvent.VK_SPACE) 
					tempObject.charging = true;
				
				if(key == KeyEvent.VK_W)
				{
					tempObject.flying = true;
					tempObject.velY = -10;
				}
				
			}
			
			
			
			
			
			/*
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT && key == KeyEvent.VK_LEFT)
				{
					tempObject.setVelX(lastInput);
				}else
				{
					if(key == KeyEvent.VK_RIGHT)
						{
						tempObject.setVelX(5);
						lastInput = 5;
						}
					if(key == KeyEvent.VK_LEFT)
						{
						tempObject.setVelX(-5);
						lastInput = -5;
						}
					
				}
							
				if((key == KeyEvent.VK_SPACE) && !tempObject.isJumping())
					{
						if(jumpForce > -15)
							jumpForce -= 1;
						
						System.out.println(jumpForce);
						
					}
				if(key == KeyEvent.VK_Z)
				{
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + 16, ObjectId.Bullet, tempObject.getFacing() * 10));
				}
				
			}
		}
		*/
		
		
		
			if(key == KeyEvent.VK_ESCAPE)
			{
				
				System.exit(1);
			}
			
		}
		
		
	}
	
	public void keyReleased(KeyEvent e)
	{
	
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++)
		{
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT)
				{
					tempObject.right = false;
				}
				
				if(key == KeyEvent.VK_LEFT) 
				{
					tempObject.left = false;
				}
				
				if(key == KeyEvent.VK_SPACE)
					{
					tempObject.charging = false;
					tempObject.jumping = true;
					}
				
				if(key == KeyEvent.VK_W)
				{
					tempObject.flying = false;
					tempObject.velY = 0;
				}
				
			}
		}
		
		
		/*
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
				
				if(key == KeyEvent.VK_SPACE) 
				{
					tempObject.setJumping(true);
					tempObject.setVelY(jumpForce);
					jumpForce = 0;
				
				}
			}
		}
		
		*/
		
	}
}
