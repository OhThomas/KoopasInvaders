/*
 * 
 */
package com.game.src.main;

import java.awt.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Class GameObject.
 */
public class GameObject {

	/** The y. */
	public double x, y;
	
	/**
	 * Instantiates a new game object.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public GameObject(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the bound.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the bound
	 */
	public Rectangle getBound(int width, int height){
		return new Rectangle((int)x, (int)y, width, height);
	}
}
