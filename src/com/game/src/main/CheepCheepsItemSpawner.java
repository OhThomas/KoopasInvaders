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
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	
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
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(timer != 0)
					timer+=pausedTimerAddition2;
				if(spawnTimer != 0)
					spawnTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		Random rand = new Random();
		int yAdd = rand.nextInt(200);
		if(spawnTimer < System.currentTimeMillis() && rand.nextInt(50) == 0) {
			//Spawn CheepCheeps
			if(rand.nextInt(2) == 0)
				game.getController().addEntity(new CheepCheepsItem(0,370-yAdd, tex, game,0));
			else
				game.getController().addEntity(new CheepCheepsItem(Game.WIDTH * Game.SCALE,370-yAdd, tex, game,1));
			spawnTimer = System.currentTimeMillis() + 750;
		}
		if(timer < System.currentTimeMillis())
			game.ee.remove(this);
	}

	public void render(Graphics g) {
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < timer || System.currentTimeMillis() < spawnTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
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
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return 0;
	}

	public double getVelY() {
		return 0;
	}

	public void setVelX(double velX) {
	}

	public void setVelY(double velY) {
	}
	
	public void setScoreFollowMe(boolean b) {
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
