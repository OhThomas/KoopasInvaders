/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

// TODO: Auto-generated Javadoc
/**
 * The Class Bowser.
 */
public class Bowser extends GameObject implements EntityB{

	/** The barrier. */
	private boolean barrier = false;
	
	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The c. */
	private Controller c;
	
	/** The timer 1. */
	private double timer1 = 100;
	
	/** The timer 2. */
	private double timer2 = 0;
	
	/** The hit timer. */
	private double hitTimer = 0;
	
	/** The death timer. */
	private double deathTimer = 0;
	
	/** The speed increase. */
	public double speedIncrease = 0.1;
	
	/** The death timer set. */
	private boolean deathTimerSet = false;
	
	/** The bowseris dead. */
	private boolean bowserisDead = false;
	
	/** The vel Y. */
	protected double velX, velY;
	
	/** The r. */
	Random r = new Random();
	
	/** The anim. */
	Animation anim;
	
	/** The anim entrance. */
	Animation animEntrance;
	
	/** The anim hit. */
	Animation animHit;
	
	/**
	 * Instantiates a new bowser.
	 *
	 * @param x the x
	 * @param y the y
	 * @param tex the tex
	 * @param c the c
	 * @param game the game
	 */
	public Bowser(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		velX = 0;
		velY = 2;
		
		animEntrance = new Animation(20, tex.bowserEntrance[0],tex.bowserEntrance[1],
				tex.bowserEntrance[2],tex.bowserEntrance[3],
				tex.bowserEntrance[4],tex.bowserEntrance[5],
				tex.bowserEntrance[6],tex.bowserEntrance[7]);
		
		animHit = new Animation(6,tex.bowserHit[0], tex.bowserHit[1]);
		anim = new Animation(38, tex.bowser[0],tex.bowser[1]);
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#tick()
	 */
	public void tick(){
		if(bowserisDead) {
			animHit.runAnimation();
			if(HUD.getTimer2() <= 0)
				c.removeEntity(this);
			
		}
		else {
			if(timer1 > 0){
				x += 1;
				//y += velY;
				timer1 = timer1 - 1;
				animEntrance.runAnimation();
			}
			if(timer2 > 0){
				//y += velY;
				timer2--;
				animEntrance.runAnimation();
			}
			if(hitTimer > 0){
				hitTimer--;
				animHit.runAnimation();
			}
			if(HUD.HEALTH <= 0) {
				bowserisDead = true;
			}
			if (game.enemyHitRightBarrier == false){
				x+=game.enemySpeedIncrease; //x+=1;
			}
			
			if (x >= (Game.WIDTH * Game.SCALE)- 55 || game.enemyHitRightBarrier == true){
				int i = r.nextInt(10);
				if (barrier == false && i == 0 && y < ((Game.HEIGHT * Game.SCALE)/2) - 16)
					y += 16;
				barrier = true;
				game.enemyHitRightBarrier = true;
				//y +=16;
			}
			
			if (game.enemyHitRightBarrier == true){
				x-=game.enemySpeedIncrease; //x-= 1;
			}
			
			if (x <= 0 || game.enemyHitRightBarrier == false){
				int i = r.nextInt(10);
				if (barrier == true && i == 0 && y < ((Game.HEIGHT * Game.SCALE)/2) - 16)
					y +=16;
				barrier = false;
				game.enemyHitRightBarrier = false;
			}
			
			if (y >= game.getHeight()){
				game.gameOverBoolean = true;
			}
			
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if (game.eb.size() == 2)
						game.enemySpeedIncrease+= 0.9;
					game.enemySpeedIncrease+= 0.2; //0.7
					HUD.HEALTH -= 100;//4
					hitTimer = 80;
					if(game.ea.size() >= 1)
						game.ea.removeLast();
					//c.removeEntity(tempEnt);
					//c.removeEntity(this);
				}
			}
		}
		
		anim.runAnimation();
		animHit.runAnimation();
		animEntrance.runAnimation();
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#render(java.awt.Graphics)
	 */
	public void render(Graphics g){
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				timer2 = 50;
			}
		}
		if(timer1 > 0)
			animEntrance.drawAnimation(g, x, y, 0);
		else if(hitTimer > 0){
			animHit.drawAnimation(g, x, y, 0);
		}
		else
			anim.drawAnimation(g, x, y, 0);
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getBounds()
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 64, 50);
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(double speed){
		this.speedIncrease = speed;
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getX()
	 */
	public double getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getY()
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Gets the timer 1.
	 *
	 * @return the timer 1
	 */
	public double getTimer1(){
		return timer1;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getEntityBDead()
	 */
	public boolean getEntityBDead() {
		return bowserisDead;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getWidth()
	 */
	public int getWidth() {
		return 64;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getHeight()
	 */
	public int getHeight() {
		return 50;
	}
}
