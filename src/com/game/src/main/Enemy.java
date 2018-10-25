package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class Enemy extends GameObject implements EntityB{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private Controller c;
	public double speedIncrease = 0.1;
	public boolean goombaisDead = false;
	public String enemyType = "Goomba1";
	
	Animation anim;
	Animation animExplosion;
	Animation coin;
	
	SoundLoops goombaDeathSoundLoop;
	SoundLoops coinSoundLoop;
	
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
		VolumeSlider.adjustSFX(goombaDeathSoundLoop);
		VolumeSlider.adjustSFX(coinSoundLoop);
		this.goombaDeathSoundLoop = goombaDeathSoundLoop;
		this.coinSoundLoop = coinSoundLoop;
		
	}
	
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
					y += 8;
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
			
			if (y+this.getHeight() >= game.getHeight()){
				game.Health -=100;
			}
		
		
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if (game.eb.size() == 2)
						game.enemySpeedIncrease+= 0.3;
					game.enemySpeedIncrease+= 0.06; //0.7
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
							}else if(this.goombaDeathSoundLoop.getVolume() -1.5f >= this.goombaDeathSoundLoop.minimumVolume())
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
					}else if(this.coinSoundLoop.getVolume() -1.5f >= this.coinSoundLoop.minimumVolume())
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
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setSpeed(double speed){
		this.speedIncrease = speed;
	}
	
	public double getX() {
		return x;
	}

	public double getY(){
		return y;
	}
	
	public SoundLoops getGoombaDeathSoundLoop() {
		return goombaDeathSoundLoop;
	}

	public void setGoombaDeathSoundLoop(SoundLoops goombaDeathSoundLoop) {
		this.goombaDeathSoundLoop = goombaDeathSoundLoop;
	}

	public boolean getEntityBDead() {
		return goombaisDead;
	}

	public int getWidth() {
		return 16;
	}

	public int getHeight() {
		return 16;
	}

	public String enemyType() {
		return enemyType;
	}
}
