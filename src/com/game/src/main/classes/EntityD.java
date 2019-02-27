package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.SoundLoops;

public interface EntityD {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	public String getItemName();
	public SoundLoops getItemSoundLoop();
	public void close();
}
