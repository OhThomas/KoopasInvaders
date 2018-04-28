/*
 * 
 */
package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyInput.
 */
public class KeyInput extends KeyAdapter {

	/** The game. */
	Game game;
	
	/**
	 * Instantiates a new key input.
	 *
	 * @param game the game
	 */
	public KeyInput(Game game){
		this.game = game;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}
}
