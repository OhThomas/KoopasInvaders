package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class CheepCheepsItemSpawner extends GameObject implements EntityE   {
	
	private Textures tex;
	private Game game;
	private long timer = 0;
	private long spawnTimer = 0;
	
	public CheepCheepsItemSpawner(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		timer = System.currentTimeMillis() + 15000;
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			game.getController().addEntity(new CheepCheepsItem(0,370, tex, game,0));
		else
			game.getController().addEntity(new CheepCheepsItem(Game.WIDTH * Game.SCALE,370, tex, game,1));
		spawnTimer = System.currentTimeMillis() + 750;
	}

	public void tick() {
		Random rand = new Random();
		if(spawnTimer < System.currentTimeMillis() && rand.nextInt(50) == 0) {
			//Spawn CheepCheeps
			if(rand.nextInt(2) == 0)
				game.getController().addEntity(new CheepCheepsItem(0,370, tex, game,0));
			else
				game.getController().addEntity(new CheepCheepsItem(Game.WIDTH * Game.SCALE,370, tex, game,1));
			spawnTimer = System.currentTimeMillis() + 750;
		}
		if(timer < System.currentTimeMillis())
			game.ee.remove(this);
	}

	public void render(Graphics g) {
		return;
	}

	public Rectangle getBounds() {
		return new Rectangle(0,0,0,0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String entityName() {
		return "cheepCheepsSpawner";
	}

	public void setHitIndicator(boolean b) {
		return;
	}

	public boolean getHitIndicator() {
		return false;
	}

	public void setEntityEDead(boolean dead) {
		close();
		game.ee.remove(this);
	}
	
	public boolean getEntityEDead() {
		return false;
	}
	
	public void close() {
		
	}

}
