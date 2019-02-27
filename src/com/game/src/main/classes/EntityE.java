package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityE {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	public String entityName();
	public void setHitIndicator(boolean b);
	public boolean getHitIndicator();
	public boolean getEntityEDead();
	public void setEntityEDead(boolean dead);
	public void close();
}
