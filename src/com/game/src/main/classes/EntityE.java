/*
 * 
 */
package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Interface EntityE.
 */
public interface EntityE {
	
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
	
}
