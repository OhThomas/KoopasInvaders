/*
 * 
 */
package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.SoundLoops;

// TODO: Auto-generated Javadoc
/**
 * The Interface EntityD.
 */
public interface EntityD {
	
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
	 * Gets the item name.
	 *
	 * @return the item name
	 */
	public String getItemName();
	
	/**
	 * Gets the item sound loop.
	 *
	 * @return the item sound loop
	 */
	public SoundLoops getItemSoundLoop();
	
}
