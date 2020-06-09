package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class Enemy3 extends GameObject implements EntityB{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private Controller c;
	public double speedIncrease = 0.1;
	private long flickerTimer1 = 0;
	private long flickerTimer2 = 0;
	private boolean flicker = false;
	private boolean goombaisDead = false;
	private String enemyType = "Goomba3";
	
	Animation anim;
	Animation animDeath;
	Animation animExplosion;
	
	SoundLoops goomba3DeathSoundLoop;
	SoundLoops goomba3DeathSmokeSoundLoop;
	
	public Enemy3(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		
		anim = new Animation(6, tex.enemy3[0],tex.enemy3[1]);
		animDeath = new Animation(6, tex.enemy3Death[0],tex.enemy3Death[1],tex.enemy3Death[2]);
		animExplosion = new Animation(2, tex.enemy3Explosion[0],tex.enemy3Explosion[1],tex.enemy3Explosion[2],
				tex.enemy3Explosion[3],tex.enemy3Explosion[4],tex.enemy3Explosion[5],tex.enemy3Explosion[6],
				tex.enemy3Explosion[7],tex.enemy3Explosion[8],tex.enemy3Explosion[9],tex.enemy3Explosion[10],
				tex.enemy3Explosion[11],tex.enemy3Explosion[12]);
		
		String goomba3DeathSoundLoopFile = "res/Sounds/SFX/smb_stomp.wav";
		String goomba3DeathSmokeSoundLoopFile = "res/Sounds/SFX/mariogoomba3deathsmokesfx.wav";
		SoundLoops goomba3DeathSoundLoop = new SoundLoops(goomba3DeathSoundLoopFile);
		SoundLoops goomba3DeathSmokeSoundLoop = new SoundLoops(goomba3DeathSmokeSoundLoopFile);
		VolumeSlider.adjustSFX(goomba3DeathSoundLoop);
		VolumeSlider.adjustSFX(goomba3DeathSmokeSoundLoop);
		this.goomba3DeathSoundLoop = goomba3DeathSoundLoop;
		this.goomba3DeathSmokeSoundLoop = goomba3DeathSmokeSoundLoop;
	}
	
	public void tick(){
		if(!goombaisDead){
			if (game.enemyHitRightBarrier == false){
				if(x+8+game.enemySpeedIncrease >= Game.WIDTH * Game.SCALE) {
					boolean b = false;
					for(int i = 0; i <= c.getEntityB().size()-1; i++) {
						if(this == c.getEntityB().get(i))
							b = true;
						if(c.getEntityB().get(i).getX() + 8 + game.enemySpeedIncrease >= Game.WIDTH * Game.SCALE) {
							if(c.getEntityB().get(i).getY() < this.y)
								c.getEntityB().get(i).setX(c.getEntityB().get(i).getX()-game.enemySpeedIncrease);
							else
								c.getEntityB().get(i).setX(Game.WIDTH * Game.SCALE - 8);
						}
						else if(b == false)
							c.getEntityB().get(i).setX(c.getEntityB().get(i).getX()-game.enemySpeedIncrease*2);
					}
					x = Game.WIDTH * Game.SCALE - 8;
				}
				else
					x+=game.enemySpeedIncrease; //x+=1;
			}
			else if (game.enemyHitRightBarrier == true){
				if(x-game.enemySpeedIncrease <= 0) {
					boolean b = false;
					for(int i = 0; i <= c.getEntityB().size()-1; i++) {
						if(this == c.getEntityB().get(i))
							b = true;
						if(c.getEntityB().get(i).getX() -game.enemySpeedIncrease <= 0) {
							if(c.getEntityB().get(i).getY() < this.y)
								c.getEntityB().get(i).setX(c.getEntityB().get(i).getX()+game.enemySpeedIncrease);
							else
								c.getEntityB().get(i).setX(0);
						}
						else if(b == false)
							c.getEntityB().get(i).setX(c.getEntityB().get(i).getX()+game.enemySpeedIncrease*2);
					}
					x = 0;
				}
				else
					x-=game.enemySpeedIncrease; //x-= 1;
			}
			if (x + 8 >= (Game.WIDTH * Game.SCALE) || game.enemyHitRightBarrier == true){
				if (barrier == false) {
					y += 8;
					barrier = true;
				}
				if(x + 8 >= (Game.WIDTH * Game.SCALE) && game.enemyHitRightBarrier == false)
					x-=game.enemySpeedIncrease;
				else if(x + 8 >= (Game.WIDTH * Game.SCALE) && game.enemyHitRightBarrier == true)
					x-=game.enemySpeedIncrease*2;
				if(!game.enemyHitRightBarrier)
					game.enemyHitRightBarrier = true;
			}
			
			if (x <= 0 || game.enemyHitRightBarrier == false){
				if (barrier == true) {
					y +=16;
					barrier = false;
				}
				if(x <= 0 && game.enemyHitRightBarrier == true) 
					x+=game.enemySpeedIncrease;
				else if(x<=0 && game.enemyHitRightBarrier == false)
					x+=game.enemySpeedIncrease*2;
				if(game.enemyHitRightBarrier)
					game.enemyHitRightBarrier = false;
			}
			
			if (y + this.getHeight() >= game.getHeight()){
				game.Health -= 100;
			}
			
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if (game.eb.size() == 2)
						game.enemySpeedIncrease+= 0.9;
					game.enemySpeedIncrease+= 0.08; //0.7
					animDeath.nextFrame();
					animExplosion.nextFrame();
					if(Game.currentlySelectedFireball != 3)
						c.removeEntity(tempEnt);
					if(this.goomba3DeathSoundLoop.getSoundLoopBoolean() == false){
						for(int j = game.goomba3DeathSoundLoop.size(); j > 0; j--){
							if(game.goomba3DeathSoundLoop.get(j-1) != null && !game.goomba3DeathSoundLoop.get(j-1).clipIsActive()){
								//game.goomba3DeathSoundLoop.get(j-1).close();
								game.goomba3DeathSoundLoop.remove(j-1);
							}
						}	
						for(int k = 0; k < game.goomba3DeathSoundLoop.size() || k == 0; k++){
							if(game.goomba3DeathSoundLoop.isEmpty())
								game.goomba3DeathSoundLoop.add(this.goomba3DeathSoundLoop);
							else if (game.goomba3DeathSoundLoop.get(k) == game.goomba3DeathSoundLoop.getLast()){
								game.goomba3DeathSoundLoop.add(this.goomba3DeathSoundLoop);
								k++;
							}else if(this.goomba3DeathSoundLoop.getVolume() -1.5f >= this.goomba3DeathSoundLoop.minimumVolume())
								this.goomba3DeathSoundLoop.reduceSound(1.5f);
						}
						this.goomba3DeathSoundLoop.setSoundLoopBoolean(true);
						game.goomba3DeathSoundLoop.getLast().play();
					}
					game.getHUD().setScore(200);
					goombaisDead = true;
					if(game.eb.size() > 2) {
						for(int b = 0; b <= c.getEntityB().size()-1; b++) {
							if(this == c.getEntityB().get(b))
								break;
							if(game.enemyHitRightBarrier) {
								if(c.getEntityB().get(b).getX() - 0.08 <= 0)
									c.getEntityB().get(b).setX(0);
								else
									c.getEntityB().get(b).setX(c.getEntityB().get(b).getX() - 0.08);
							}
							else {
								if(c.getEntityB().get(b).getX() + 0.08 + 8 >= Game.WIDTH * Game.SCALE)
									c.getEntityB().get(b).setX(Game.WIDTH * Game.SCALE - 8);
								else
									c.getEntityB().get(b).setX(c.getEntityB().get(b).getX() + 0.08);
							}
						}
					}
				}
			}
			
			anim.runAnimation();
		}
		else if(goombaisDead){
			if(game.getGoomba3DeathSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.goomba3DeathSoundLoop.size()-1; l >= 0; l--){
					if(!game.goomba3DeathSoundLoop.get(l).clipIsActive())
						game.goomba3DeathSoundLoop.get(l).continuePlaying();
				}
				game.setGoomba3DeathSoundPauseBoolean(false);
			}

			if(game.getGoomba3DeathSmokeSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.goomba3DeathSmokeSoundLoop.size()-1; l >= 0; l--){
					if(!game.goomba3DeathSmokeSoundLoop.get(l).clipIsActive())
						game.goomba3DeathSmokeSoundLoop.get(l).continuePlaying();
				}
				game.setGoomba3DeathSmokeSoundPauseBoolean(false);
			}
			
			if(animDeath.getCount() == 3 && this.goomba3DeathSmokeSoundLoop.getSoundLoopBoolean() == false){
				for(int j = game.goomba3DeathSmokeSoundLoop.size(); j > 0; j--){
					if(game.goomba3DeathSmokeSoundLoop.get(j-1) != null && !game.goomba3DeathSmokeSoundLoop.get(j-1).clipIsActive()){
						//game.goomba3DeathSmokeSoundLoop.get(j-1).close();;
						game.goomba3DeathSmokeSoundLoop.remove(j-1);
					}
				}	
				for(int k = 0; k < game.goomba3DeathSmokeSoundLoop.size() || k == 0; k++){
					if(game.goomba3DeathSmokeSoundLoop.isEmpty())
						game.goomba3DeathSmokeSoundLoop.add(this.goomba3DeathSmokeSoundLoop);
					else if (game.goomba3DeathSmokeSoundLoop.get(k) == game.goomba3DeathSmokeSoundLoop.getLast()){
						game.goomba3DeathSmokeSoundLoop.add(this.goomba3DeathSmokeSoundLoop);
						k++;
					}else if(this.goomba3DeathSmokeSoundLoop.getVolume() -1.5f >= this.goomba3DeathSmokeSoundLoop.minimumVolume())
						this.goomba3DeathSmokeSoundLoop.reduceSound(1.5f);
				}
				this.goomba3DeathSmokeSoundLoop.setSoundLoopBoolean(true);
				game.goomba3DeathSmokeSoundLoop.getLast().play();
			}
			
			if(animDeath.getCount() != 3)
				animDeath.runAnimation();
			else if(animDeath.getCount() == 3 && animExplosion.getCount() != 13){
				animExplosion.runAnimation();
			}else if(animExplosion.getCount() == 13) {
				c.removeEntity(this);
				Game.bEntityRemoved = true;
			}
		}
	}
	
	public void render(Graphics g){
		if(flicker) {
			if(flickerTimer1 == 0 && flickerTimer2 == 0)
				flickerTimer1 = System.currentTimeMillis() + 250;
			if(flickerTimer1 < System.currentTimeMillis() && flickerTimer2 == 0) {
				flickerTimer1 = 0;
				flickerTimer2 = System.currentTimeMillis() + 250;
			}
			if(flickerTimer2 < System.currentTimeMillis() && flickerTimer1 == 0) {
				flickerTimer2 = 0;
				flickerTimer1 = System.currentTimeMillis() + 250;
			}
			if(flickerTimer1 < flickerTimer2) 
				return;
		}
		if(!goombaisDead)
			anim.drawAnimation(g, x, y, 0);
		else if(goombaisDead && animDeath.getCount() != 3)
			animDeath.drawAnimation(g, x, y, 0);
		else
			animExplosion.drawAnimation(g, x, y, 0);
		
		if(game.isPaused()){
			if(game.getGoomba3DeathSoundPauseBoolean() == false){
				for(int i = 0; i < game.goomba3DeathSoundLoop.size();i++){
					if(game.goomba3DeathSoundLoop.get(i).clipIsActive())
						game.goomba3DeathSoundLoop.get(i).stop();
				}
				game.setGoomba3DeathSoundPauseBoolean(true);
			}
			if(game.getGoomba3DeathSmokeSoundPauseBoolean() == false){
				for(int i = 0; i < game.goomba3DeathSmokeSoundLoop.size();i++){
					if(game.goomba3DeathSmokeSoundLoop.get(i).clipIsActive())
						game.goomba3DeathSmokeSoundLoop.get(i).stop();
				}
				game.setGoomba3DeathSmokeSoundPauseBoolean(true);
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

	public void setEntityBDead(boolean dead) {
		if(dead) {
			if (game.eb.size() == 2)
				game.enemySpeedIncrease+= 0.9;
			game.enemySpeedIncrease+= 0.08; //0.7
			animDeath.nextFrame();
			animExplosion.nextFrame();
			if(this.goomba3DeathSoundLoop.getSoundLoopBoolean() == false){
				for(int j = game.goomba3DeathSoundLoop.size(); j > 0; j--){
					if(game.goomba3DeathSoundLoop.get(j-1) != null && !game.goomba3DeathSoundLoop.get(j-1).clipIsActive()){
						//game.goomba3DeathSoundLoop.get(j-1).close();
						game.goomba3DeathSoundLoop.remove(j-1);
					}
				}	
				for(int k = 0; k < game.goomba3DeathSoundLoop.size() || k == 0; k++){
					if(game.goomba3DeathSoundLoop.isEmpty())
						game.goomba3DeathSoundLoop.add(this.goomba3DeathSoundLoop);
					else if (game.goomba3DeathSoundLoop.get(k) == game.goomba3DeathSoundLoop.getLast()){
						game.goomba3DeathSoundLoop.add(this.goomba3DeathSoundLoop);
						k++;
					}else if(this.goomba3DeathSoundLoop.getVolume() -1.5f >= this.goomba3DeathSoundLoop.minimumVolume())
						this.goomba3DeathSoundLoop.reduceSound(1.5f);
				}
				this.goomba3DeathSoundLoop.setSoundLoopBoolean(true);
				game.goomba3DeathSoundLoop.getLast().play();
			}
			game.getHUD().setScore(200);
		}
		this.goombaisDead = dead;
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
	
	public void setX(double x) {
		this.x = x;
	}

	public void renderFlicker() {
		flicker = true;
	}
	
	public void close() {
		goomba3DeathSoundLoop.close();
		goomba3DeathSmokeSoundLoop.close();
	}
}
