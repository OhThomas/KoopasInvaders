/*
 * 
 */
package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Interface EntityB.
 */
public interface EntityB {
	
	/**
	 * Tick.
	 */
	public void tick();
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g);
	
	/**
	 * Gets the bounds.
	 *
	 * @return the bounds
	 */
	public Rectangle getBounds();
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX();
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY();
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth();
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight();
	
	/**
	 * Gets the entity B dead.
	 *
	 * @return the entity B dead
	 */
	public boolean getEntityBDead();
}
