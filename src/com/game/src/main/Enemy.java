/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

// TODO: Auto-generated Javadoc
/**
 * The Class Enemy.
 */
public class Enemy extends GameObject implements EntityB{

	/** The barrier. */
	private boolean barrier = false;
	
	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The c. */
	private Controller c;
	
	/** The speed increase. */
	public double speedIncrease = 0.1;
	
	/** The goombais dead. */
	public boolean goombaisDead = false;
	
	/** The anim. */
	Animation anim;
	
	/** The anim explosion. */
	Animation animExplosion;
	
	/** The coin. */
	Animation coin;
	
	/** The goomba death sound loop. */
	SoundLoops goombaDeathSoundLoop;
	
	/** The coin sound loop. */
	SoundLoops coinSoundLoop;
	
	/**
	 * Instantiates a new enemy.
	 *
	 * @param x the x
	 * @param y the y
	 * @param tex the tex
	 * @param c the c
	 * @param game the game
	 */
	public Enemy(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		
		anim = new Animation(6, tex.enemy[0],tex.enemy[1]);
		animExplosion = new Animation(6, tex.enemyExplosion[0],tex.enemyExplosion[1],tex.enemyExplosion[2]);
		coin = new Animation(6, tex.coin[0],tex.coin[1],tex.coin[2]);
		
		String goombaDeathFile = "res/Sounds/SFX/smb3_kickspace.wav";
		String coinFile = "res/Sounds/SFX/smw_coin.wav";
		SoundLoops goombaDeathSoundLoop = new SoundLoops(goombaDeathFile);
		SoundLoops coinSoundLoop = new SoundLoops(coinFile);
		this.goombaDeathSoundLoop = goombaDeathSoundLoop;
		this.coinSoundLoop = coinSoundLoop;
		
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#tick()
	 */
	public void tick(){
		if(!goombaisDead){
			if (game.enemyHitRightBarrier == false){
				x+=game.enemySpeedIncrease; //x+=1;
			}
			
			if (x >= (Game.WIDTH * Game.SCALE) || game.enemyHitRightBarrier == true){
				/*
				for(int i = 0; i <= game.eb.size(); i++)
				{
					//game.eb.get(i)
					//if(game.ea.isEmpty())
					//	System.out.println("EMPTY");
					System.out.println("HIGH");
					//INCREASE ALL UP y+=16
				}
				*/
				//game.enemySpeedIncrease += 0.1;
				if (barrier == false)
					y += 16;
				barrier = true;
				game.enemyHitRightBarrier = true;
				//y +=16;
			}
			
			if (game.enemyHitRightBarrier == true){
				x-=game.enemySpeedIncrease; //x-= 1;
			}
			
			if (x <= 0 || game.enemyHitRightBarrier == false){
				//game.enemySpeedIncrease += 0.1;
				if (barrier == true)
					y +=16;
				barrier = false;
				game.enemyHitRightBarrier = false;
				//y +=16;
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
					if(this.goombaDeathSoundLoop.getSoundLoopBoolean() == false){
						for(int j = game.goombaDeathSoundLoop.size(); j > 0; j--){
							if(game.goombaDeathSoundLoop.get(j-1) != null && !game.goombaDeathSoundLoop.get(j-1).clipIsActive()){
								game.goombaDeathSoundLoop.remove(j-1);
							}
						}	
						for(int k = 0; k < game.goombaDeathSoundLoop.size() || k == 0; k++){
							if(game.goombaDeathSoundLoop.isEmpty())
								game.goombaDeathSoundLoop.add(this.goombaDeathSoundLoop);
							else if (game.goombaDeathSoundLoop.get(k) == game.goombaDeathSoundLoop.getLast()){
								game.goombaDeathSoundLoop.add(this.goombaDeathSoundLoop);
								k++;
							}else 
								this.goombaDeathSoundLoop.reduceSound(1.5f);
						}
						this.goombaDeathSoundLoop.setSoundLoopBoolean(true);
						game.goombaDeathSoundLoop.getLast().play();
					}
					c.removeEntity(tempEnt);
//if hit by item entity					game.setEnemyHitPauseTimer(System.currentTimeMillis() + 200);
					game.getHUD().setScore(200);
					goombaisDead = true;
				}
			}
			anim.runAnimation();
		}
		else if(goombaisDead){
			if(game.getGoombaDeathSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.goombaDeathSoundLoop.size()-1; l >= 0; l--){
					if(!game.goombaDeathSoundLoop.get(l).clipIsActive())
						game.goombaDeathSoundLoop.get(l).continuePlaying();
				}
				game.setGoombaDeathSoundPauseBoolean(false);
			}
			
			if(game.getCoinSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.coinSoundLoop.size()-1; l >= 0; l--){
					if(!game.coinSoundLoop.get(l).clipIsActive())
						game.coinSoundLoop.get(l).continuePlaying();
				}
				game.setCoinSoundPauseBoolean(false);
			}
			
			if(animExplosion.getCount() == 3 && this.coinSoundLoop.getSoundLoopBoolean() == false){
				for(int j = game.coinSoundLoop.size(); j > 0; j--){
					if(game.coinSoundLoop.get(j-1) != null && !game.coinSoundLoop.get(j-1).clipIsActive()){
						game.coinSoundLoop.remove(j-1);
					}
				}	
				for(int k = 0; k < game.coinSoundLoop.size() || k == 0; k++){
					if(game.coinSoundLoop.isEmpty())
						game.coinSoundLoop.add(this.coinSoundLoop);
					else if (game.coinSoundLoop.get(k) == game.coinSoundLoop.getLast()){
						game.coinSoundLoop.add(this.coinSoundLoop);
						k++;
					}else 
						this.coinSoundLoop.reduceSound(1.5f);
				}
				this.coinSoundLoop.setSoundLoopBoolean(true);
				game.coinSoundLoop.getLast().play();
			}
			
			if(animExplosion.getCount() != 3)
				animExplosion.runAnimation();
			else if(animExplosion.getCount() == 3 && coin.getCount() != 3)
				coin.runAnimation();
			else if(coin.getCount() == 3)
				c.removeEntity(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#render(java.awt.Graphics)
	 */
	public void render(Graphics g){
		if(goombaisDead && animExplosion.getCount() != 3)
			animExplosion.drawAnimation(g, x, y, 0);
		else if(animExplosion.getCount() == 3)
			if(y<14)
				coin.drawAnimation(g, x, y, 0);
			else
				coin.drawAnimation(g, x, y-14, 0);
		else
			anim.drawAnimation(g, x, y, 0);
		
		if(game.isPaused()){
			if(game.getGoombaDeathSoundPauseBoolean() == false){
				for(int i = 0; i < game.goombaDeathSoundLoop.size();i++){
					if(game.goombaDeathSoundLoop.get(i).clipIsActive())
						game.goombaDeathSoundLoop.get(i).stop();
				}
				game.setGoombaDeathSoundPauseBoolean(true);
			}
			if(game.getCoinSoundPauseBoolean() == false){
				for(int i = 0; i < game.coinSoundLoop.size();i++){
					if(game.coinSoundLoop.get(i).clipIsActive())
						game.coinSoundLoop.get(i).stop();
				}
				game.setCoinSoundPauseBoolean(true);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getBounds()
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
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
	 * Gets the goomba death sound loop.
	 *
	 * @return the goomba death sound loop
	 */
	public SoundLoops getGoombaDeathSoundLoop() {
		return goombaDeathSoundLoop;
	}

	/**
	 * Sets the goomba death sound loop.
	 *
	 * @param goombaDeathSoundLoop the new goomba death sound loop
	 */
	public void setGoombaDeathSoundLoop(SoundLoops goombaDeathSoundLoop) {
		this.goombaDeathSoundLoop = goombaDeathSoundLoop;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getEntityBDead()
	 */
	public boolean getEntityBDead() {
		return goombaisDead;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getWidth()
	 */
	public int getWidth() {
		return 16;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityB#getHeight()
	 */
	public int getHeight() {
		return 16;
	}
}
