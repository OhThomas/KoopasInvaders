/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

// TODO: Auto-generated Javadoc
/**
 * The Class ChainChomp.
 */
public class ChainChomp extends GameObject implements EntityE {

	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The x destination. */
	private int xDestination = 10000;
	
	/** The ec marked. */
	private int ecMarked = -1;
	
	/** The x direction. */
	private int xDirection = -1;
	
	/** The moving timer 1. */
	private long movingTimer1 = 0;
	
	/** The moving timer 2. */
	private long movingTimer2 = 0;
	
	/** The moving timer 3. */
	private long movingTimer3 = 0;
	
	/** The moving timer 3 boolean. */
	private boolean movingTimer2Boolean, movingTimer3Boolean = false;
	
	/** The vel Y. */
	protected double velX, velY;
	
	/** The anim. */
	Animation anim;
	
	/** The item sound loop. */
	SoundLoops itemSoundLoop;
	
	/**
	 * Instantiates a new chain chomp.
	 *
	 * @param x the x
	 * @param y the y
	 * @param tex the tex
	 * @param game the game
	 */
	public ChainChomp(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.game = game;
		this.tex = tex;
		velX = 0;
		velY = 0;
		
		anim = new Animation(6,tex.chainChomp[0], tex.chainChomp[0],tex.chainChomp[1]);
		anim.nextFrame();
		String itemFile = "res/Sounds/SFX/Items/sm64_chain_chomp.wav";
		SoundLoops itemSoundLoop = new SoundLoops(itemFile);
		itemSoundLoop.reduceSound(10f);
		this.itemSoundLoop = itemSoundLoop;
		movingTimer1 = System.currentTimeMillis() + 2000;
		movingTimer2 = System.currentTimeMillis() + 2000;
		movingTimer3 = System.currentTimeMillis() + 2000;
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityE#tick()
	 */
	public void tick(){
		y += velY;
		if(velY != 0)
			x += velX;
		else
			velX = 0;
		if(movingTimer1 < System.currentTimeMillis()) {
			if(movingTimer2 >= movingTimer3) {
				if(movingTimer2Boolean == false) {
					movingTimer2 += 24;
					movingTimer2Boolean = true;
					movingTimer3Boolean = false;
				}
				if(velY> -3)
					velY--;
				if(xDirection == 0) {
					if(velX> -2)
						velX = velX - 0.2;
				}else if(xDirection == 1) {
					if(velX<2)
						velX = velX + 0.2;
				}
				movingTimer3++;
			}else {
				if(movingTimer3Boolean == false) {
					movingTimer3 += 72;
					movingTimer3Boolean = true;
					movingTimer2Boolean = false;
				}
				if(velY<0)
					velY++;
				if(xDirection == 0) {
					if(velX> -2)
						velX = velX - 0.2;
				}else if(xDirection == 1) {
					if(velX<2)
						velX = velX + 0.2;
				}
				movingTimer2++;
			}
		}
		//System.out.println("xDirection = " + xDirection + "\n velX = "+ velX);
		if(!game.ec.isEmpty()){
			if(ecMarked != -1 && ecMarked < game.ec.size()){
				if(game.ec.get(ecMarked).getX() < this.getX())
					xDirection = 0;//x--
				else
					xDirection = 1;
			}
			for(int i = 0; i < game.ec.size(); i++){

				EntityC tempEnt = game.ec.get(i);
				if((Math.abs((int)this.getX() - (int)game.ec.get(i).getX()) < xDestination) || game.ec.size() == 1){
						xDestination = Math.abs((int)this.getX() - (int)game.ec.get(i).getX());
						ecMarked = i;
				}
				if(Physics.Collision(this, tempEnt)){
					if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
						this.itemSoundLoop.play();
						this.itemSoundLoop.setSoundLoopBoolean(true);
					}
					game.ec.remove(i);
					game.setEnemyHitPauseTimer(System.currentTimeMillis() + 500);
					game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 300);
					ecMarked = -1;
				}
			}
		}
		else {
			xDirection = -1;
			velX = 0;
		}
		anim.runAnimation();
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityE#render(java.awt.Graphics)
	 */
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityE#getBounds()
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityE#getX()
	 */
	public double getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityE#getY()
	 */
	public double getY() {
		return y;
	}
}
