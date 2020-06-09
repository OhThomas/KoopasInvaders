package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityE {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public double getVelX();
	public double getVelY();
	public void setVelX(double velX);
	public void setVelY(double velY);
	public void setScoreFollowMe(boolean b);
	public String entityName();
	public void setHitIndicator(boolean b);
	public boolean getHitIndicator();
	public boolean getEntityEDead();
	public void setEntityEDead(boolean dead);
	public void close();
}
