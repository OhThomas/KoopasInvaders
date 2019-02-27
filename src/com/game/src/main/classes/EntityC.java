package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityC {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	public int getWidth();
	public int getHeight();
	public boolean getEntityCDead();
	public void setEntityCDead(boolean dead);
	public String entityName();
	public void close();
}
