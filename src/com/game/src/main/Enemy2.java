package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class Enemy2 extends GameObject implements EntityB{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private Controller c;
	public double speedIncrease = 0.1;
	private int soundLoopPosition = 0;
	private long goombaBounceDeathTimer = 0;
	private boolean goombaisDead = false;
	private String enemyType = "Goomba2";
	
	Animation anim;
	Animation animDeathBounceL;
	Animation animDeathBounceR;
	Animation animDeathL;
	Animation animDeathR;
	
	SoundLoops goomba2DeathSoundLoop;
	SoundLoops goomba2DeathQuiterSoundLoop;
	SoundLoops starDingSoundLoop;
	
	public Enemy2(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		
		anim = new Animation(6, tex.enemy2[0],tex.enemy2[1]);
		animDeathBounceL = new Animation(2, tex.enemy2DeathL[0],tex.enemy2DeathR[0]);
		animDeathBounceR = new Animation(2, tex.enemy2DeathR[0],tex.enemy2DeathL[0]);
		animDeathL = new Animation(1, tex.enemy2DeathL[0],tex.enemy2DeathL[1],tex.enemy2DeathL[2],
				tex.enemy2DeathL[3],tex.enemy2DeathL[4],tex.enemy2DeathL[5],tex.enemy2DeathL[6],
				tex.enemy2DeathL[7],tex.enemy2DeathL[8],tex.enemy2DeathL[9],tex.star[0],tex.star[1],
				tex.star[2],tex.star[3],tex.star[4],tex.star[4],tex.star[5],tex.star[5],tex.star[5]);
		animDeathR = new Animation(1, tex.enemy2DeathR[0],tex.enemy2DeathR[1],tex.enemy2DeathR[2],
				tex.enemy2DeathR[3],tex.enemy2DeathR[4],tex.enemy2DeathR[5],tex.enemy2DeathR[6],
				tex.enemy2DeathR[7],tex.enemy2DeathR[8],tex.enemy2DeathR[9],tex.star[0],tex.star[1],
				tex.star[2],tex.star[3],tex.star[4],tex.star[4],tex.star[5],tex.star[5],tex.star[5]);
		
		String goomba2DeathFile = "res/Sounds/SFX/smb3_kickspace.wav";
		String starDingFile = "res/Sounds/SFX/belldings3.wav";
		SoundLoops goomba2DeathSoundLoop = new SoundLoops(goomba2DeathFile);
		SoundLoops starDingSoundLoop = new SoundLoops(starDingFile);
		starDingSoundLoop.reduceSound(16.5f);
		VolumeSlider.adjustSFX(goomba2DeathSoundLoop);
		VolumeSlider.adjustSFX(starDingSoundLoop);
		if(starDingSoundLoop.getVolume() > -10f)
			starDingSoundLoop.reduceSound(6.5f);
		this.goomba2DeathSoundLoop = goomba2DeathSoundLoop;
		this.goomba2DeathQuiterSoundLoop = goomba2DeathSoundLoop;
		this.starDingSoundLoop = starDingSoundLoop;
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
			
			if (y+this.getHeight() >= game.getHeight()){
				game.Health -= 100;
			}
			
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if (game.eb.size() == 2)
						game.enemySpeedIncrease+= 0.4;
					game.enemySpeedIncrease+= 0.06; //0.7
					if(this.goomba2DeathSoundLoop.getSoundLoopBoolean() == false){
						for(int j = game.goombaDeathSoundLoop.size(); j > 0; j--){
							if(game.goombaDeathSoundLoop.get(j-1) != null && !game.goombaDeathSoundLoop.get(j-1).clipIsActive()){
								game.goombaDeathSoundLoop.remove(j-1);
							}
						}	
						for(int k = 0; k < game.goombaDeathSoundLoop.size() || k == 0; k++){
							if(game.goombaDeathSoundLoop.isEmpty())
								game.goombaDeathSoundLoop.add(this.goomba2DeathSoundLoop);
							else if (game.goombaDeathSoundLoop.get(k) == game.goombaDeathSoundLoop.getLast()){
								game.goombaDeathSoundLoop.add(this.goomba2DeathSoundLoop);
								k++;
							}else if(this.goomba2DeathSoundLoop.getVolume() -1.5f >= this.goomba2DeathSoundLoop.minimumVolume())
								this.goomba2DeathSoundLoop.reduceSound(1.5f);
						}
						this.goomba2DeathSoundLoop.setSoundLoopBoolean(true);
						game.goombaDeathSoundLoop.getLast().play();
					}
					if(Game.currentlySelectedFireball != 3)
						c.removeEntity(tempEnt);
					animDeathBounceL.nextFrame();
					animDeathBounceR.nextFrame();
					animDeathL.nextFrame();
					animDeathR.nextFrame();
					goombaBounceDeathTimer = System.currentTimeMillis() + 250;
					game.getHUD().setScore(200);
					goombaisDead = true;
					//c.removeEntity(this);
				}
			}
			anim.runAnimation();
		}
		else if (goombaisDead){
			if(System.currentTimeMillis() < goombaBounceDeathTimer){
				y-=0.4;
			}else if(System.currentTimeMillis() % 2 == 0 && animDeathL.getCount() < 14 && animDeathR.getCount() < 14)
				y +=2;
			else if(System.currentTimeMillis() % 2 == 0 && animDeathL.getCount() < 17 && animDeathR.getCount() < 17)
				y +=0.88;
			
			if(game.getGoombaDeathSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.goombaDeathSoundLoop.size()-1; l >= 0; l--){
					if(!game.goombaDeathSoundLoop.get(l).clipIsActive())
						game.goombaDeathSoundLoop.get(l).continuePlaying();
				}
				game.setGoombaDeathSoundPauseBoolean(false);
			}

			if(game.getStarDingPauseBoolean() == true && !game.isPaused()){
				for(int l = game.starDingSoundLoop.size()-1; l >= 0; l--){
					if(!game.starDingSoundLoop.get(l).clipIsActive())
						game.starDingSoundLoop.get(l).continuePlaying();
				}
				game.setStarDingPauseBoolean(false);
			}
			
			if(System.currentTimeMillis() < goombaBounceDeathTimer){
				if(anim.getCount() == 1)
					animDeathBounceL.runAnimation();
				else
					animDeathBounceR.runAnimation();
			}
			else if(animDeathL.getCount() != 19 && animDeathR.getCount() != 19){
				if(anim.getCount() == 1)
					animDeathL.runAnimation();
				else
					animDeathR.runAnimation();
			}
			else if(animDeathL.getCount() == 19 || animDeathR.getCount() == 19){
				c.removeEntity(this);
			}
			//DYNAMIC SOUND
			if((animDeathL.getCount() == 10 || animDeathR.getCount() == 10) && this.starDingSoundLoop.getSoundLoopBoolean() == false){
				for(int j = game.starDingSoundLoop.size(); j > 0; j--){
					if(game.starDingSoundLoop.get(j-1) != null && !game.starDingSoundLoop.get(j-1).clipIsActive()){
						game.starDingSoundLoop.remove(j-1);
					}
				}	
				for(int k = 0; k < game.starDingSoundLoop.size() || k == 0; k++){
					if(game.starDingSoundLoop.isEmpty())
						game.starDingSoundLoop.add(this.starDingSoundLoop);
					else if (game.starDingSoundLoop.get(k) == game.starDingSoundLoop.getLast()){
						game.starDingSoundLoop.add(this.starDingSoundLoop);
						k++;
					}else if(this.starDingSoundLoop.getVolume() -1.5f >= this.starDingSoundLoop.minimumVolume())
						this.starDingSoundLoop.reduceSound(1.5f);
					soundLoopPosition = k;
				}
				this.starDingSoundLoop.setSoundLoopBoolean(true);
				game.starDingSoundLoop.getLast().play();
			}//DYNAMIC SOUND
		}
	}
	
	public void render(Graphics g){
		if(animDeathBounceL.getCount() < 1 && animDeathBounceR.getCount() < 1)
			anim.drawAnimation(g, x, y, 0);
		else if(anim.getCount() == 1 && System.currentTimeMillis() < goombaBounceDeathTimer)
			animDeathBounceL.drawAnimation(g, x, y, 0);
		else if(anim.getCount() != 1 && System.currentTimeMillis() < goombaBounceDeathTimer)
			animDeathBounceR.drawAnimation(g, x, y, 0);
		else if(anim.getCount() == 1)
			animDeathL.drawAnimation(g, x, y, 0);
		else
			animDeathR.drawAnimation(g, x, y, 0);
		
		if(game.isPaused()){
			if(game.getGoombaDeathSoundPauseBoolean() == false){
				for(int i = 0; i < game.goombaDeathSoundLoop.size();i++){
					if(game.goombaDeathSoundLoop.get(i).clipIsActive())
						game.goombaDeathSoundLoop.get(i).stop();
				}
				game.setGoombaDeathSoundPauseBoolean(true);
			}
			if(game.getStarDingPauseBoolean() == false){
				for(int i = 0; i < game.starDingSoundLoop.size();i++){
					if(game.starDingSoundLoop.get(i).clipIsActive())
						game.starDingSoundLoop.get(i).stop();
				}
				game.setStarDingPauseBoolean(true);
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
