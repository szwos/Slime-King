package com.szwos.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.szwos.neon.framework.GameObject;
import com.szwos.neon.framework.ObjectId;
import com.szwos.neon.framework.Texture;
import com.szwos.neon.window.Animation;
import com.szwos.neon.window.Camera;
import com.szwos.neon.window.Game;
import com.szwos.neon.window.Handler;

public class Player extends GameObject{

	
	private float width = 48, height = 48;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 15;
	private boolean isGrounded = false;
	//1 - right
	//-1 left
	
	
	private Handler handler;
	private Camera cam;
	Texture tex = Game.getInstance();
	
	private Animation playerWalk;
	private Animation playerWalkBackwards;
	
	
	public Player(float x, float y, Handler handler,Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		
		playerWalk = new Animation(2, tex.player[0], tex.player[1], tex.player[2]);
		playerWalkBackwards = new Animation(2, tex.player[3], tex.player[4], tex.player[5]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) 
	{
		
		Controls();
		
		x += velX;
		y += velY;
		
		if(isGrounded)
		{	
		if(left)
			facing = -1;
		else if(right)
			facing = 1;
		}else
		{
			if(velX < 0)
				facing = -1;
			else if(velX > 0)
				facing = 1;
		}
		
		if(falling || jumping)
		{
			if(!flying)
				velY += gravity;
				
			if(velY > MAX_SPEED)
			{
				velY = MAX_SPEED;
			}
		}
		
		Collision(object);
		
		playerWalk.runAnimation();
		playerWalkBackwards.runAnimation();
		//if (y > 1000) handler.reloadLevel();
	}

	private void Controls()
	{
		//System.out.println("left: "  + left + " right: " + right + " lastInput: " + lastInput);
		
		//System.out.println("charging: " + charging + " charge: " + charge + " jumping: " + jumping + " falling: " + falling);
		System.out.println(y);
		
		if(charging && isGrounded)
		{ 
			setVelX(0);
			if(charge < 15)
				charge += 1;
		}
		
		if(jumping && isGrounded)
		{
			setVelY(-charge);
			
			if(!lookingUp)
				setVelX(facing * 5);
			
			charge = 0;
			jumping = false;
 			isGrounded = false;
 			lookingUp = false;
		}
		
		
		if(!charging && isGrounded)
		{
			if(left == true && right == true)
			{
				setVelX(lastInput * 4) ;
			}
			else if(this.left == true)
			{
				this.setVelX(-4);
			}else if(this.right == true)
			{	
				this.setVelX(4);
			}else if(left == false && right == false)
			{
				setVelX(0);
			}
		}
					
	}
	
	private void Collision(LinkedList<GameObject> object)
	{
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			{
				
				
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() + 22;
					velY = 0;
				}
				
				
				if(getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() - height +1;
					velY = 0;
					falling = false;
					//jumping = false;
					isGrounded = true;
				}else
				{
					falling = true;
				}
				
				
				
				//RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					
					x = tempObject.getX() - width + 10;
					this.velX = -this.velX;
				}
				
				
				
				//LEFT
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					
					x = tempObject.getX() + 22;
					this.velX = -this.velX;
				}
				

				
			}else if(tempObject.getId() == ObjectId.Flag)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					handler.nextLevel();
				}
				
			}
		}
	}
	
	
	@Override
	public void render(Graphics g) 
	{
		
		
		g.setColor(Color.red);
		
		if(!isGrounded)
		{
			if(facing == 1)
				g.drawImage(tex.player_jump[2],(int)x, (int)y, 48, 48, null);
			else if(facing == -1)
				g.drawImage(tex.player_jump[6],(int)x, (int)y, 48, 48, null);
			
		}else
		{		
			if(velX != 0)
			{
				if(facing == 1)
				{
					playerWalk.drawAnimation(g, (int)x, (int)y, 48, 48);
				}else
				{
					playerWalkBackwards.drawAnimation(g, (int)x, (int)y, 48, 48);
				}
				
			}else
			{
				if(facing == 1)
				{
					g.drawImage(tex.player[0], (int)x, (int)y, 48, 48, null);
				}else
				{
					g.drawImage(tex.player[5], (int)x, (int)y, 48, 48, null);
				}
			}
		}	
			
		
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.blue);
		//g2d.draw(getBounds());
		//g2d.draw(getBoundsRight());
		//g2d.draw(getBoundsLeft());
		//g2d.draw(getBoundsTop());
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) ((int)x + (width/2) - (width/2)/2) + 5, (int) ((int)y + (height/2)) + 10, (int)width/2 - 10, (int)height/2 - 10);
	}
	
	public Rectangle getBoundsTop() {
		
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)) + 5, (int)y + 20, (int)width/2 - 10, (int)height/2 - 10);
	}
	
	public Rectangle getBoundsRight() {
		
		return new Rectangle((int) ((int)x + width-5) - 9, (int)y + 15 + 7, (int)5, (int)height-10 - 10 - 5);
	}

	public Rectangle getBoundsLeft() {
	
	return new Rectangle((int)x + 5 + 4, (int)y+5 + 10 + 7, (int)5, (int)height-10 - 10 - 5);
}

	

}
