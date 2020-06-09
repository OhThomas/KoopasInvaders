package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityD;
import com.game.src.main.libs.Animation;

public class LakituStar extends GameObject implements EntityD{
	
	private Textures tex;
	private Game game;
	private long popOffTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	protected double velX, velY;
	private String itemName = "lakituStar";
	
	Animation anim;
	
	SoundLoops itemSoundLoop;
	
	public LakituStar(double x, double y, Textures tex, Game game, double velX) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.velX = velX;
		velY = 0;
		
		anim = new Animation(6, tex.mario1Star[0], tex.mario1Star[1], tex.mario1Star[2], tex.mario1Star[3]);
		
		anim.nextFrame();
		anim.setCount(0);
		String itemFile = "res/Sounds/SFX/Items/powerstar.wav";
		SoundLoops itemSoundLoop = new SoundLoops(itemFile);
		itemSoundLoop.reduceSound(10f);
		VolumeSlider.adjustSFX(itemSoundLoop);
		this.itemSoundLoop = itemSoundLoop;
		popOffTimer = System.currentTimeMillis() + 200;
	}

	@Override
	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(popOffTimer != 0)
					popOffTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(System.currentTimeMillis() < popOffTimer) {
			if(velY > -2.5) {
				velY-=0.15;
			}
		}
		else {
			if(velY < 20)
				velY+=0.1;
			if(4 < velY)
				velY+=0.04;
			if(10 < velY)
				velY+=0.04;
		}
		x+=velX;
		y+=velY;
		
		for(int i = 0; i < game.ea.size()-1; i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				game.ed.remove(this);
				if(!game.ea.isEmpty() && Game.currentlySelectedFireball != 3)
					game.ea.remove(game.ea.getLast());
			}
		}
		/*
		if(Physics.Collision(this, game.ea.getFirst())){
			//MAKE PLAYER INVULNERABLE THROUGH BOOLEAN OR SOMETHIN(HEALTH + EXTRA INT MAYBE)
			game.ec.remove(this);
		}*/
		if(0 < velX) {
			if(velX < 1 && velX +0.4 < 1)
				velX+=0.4;
			else if(1 < velX)
				velX-=0.9;
		}
		else {
			if(-1 < velX && -1 < velX -0.4)
				velX-=0.4;
			else if(velX < -1)
				velX+=0.9;
		}
		if(((Game.HEIGHT * Game.SCALE) + 30 < y) || ((Game.WIDTH * Game.SCALE) + 30 < x) || (x < -30))
			game.ed.remove(this);
		anim.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < popOffTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		anim.drawAnimation(g, x, y, 0);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 14, 16);
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public String getItemName() {
		return itemName;
	}

	@Override
	public SoundLoops getItemSoundLoop() {
		return itemSoundLoop;
	}

	public void close() {
		itemSoundLoop.close();
	}

}
