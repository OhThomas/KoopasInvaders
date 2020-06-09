package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public int getWidth();
	public int getHeight();
	public boolean getEntityBDead();
	public void setEntityBDead(boolean dead);
	public String enemyType();
	public void renderFlicker();
	public void close();
}
