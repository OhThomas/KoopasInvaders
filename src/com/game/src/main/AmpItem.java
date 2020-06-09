package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class AmpItem extends GameObject implements EntityE {
	Textures tex;
	Game game;
	Zap zapper;
	Animation upAnimation;
	Animation leftAnimation;
	Animation rightAnimation;
	Animation leftHitAnimation;
	Animation rightHitAnimation;
	Animation deathAnimation;
	SoundLoops hitSound;
	SoundLoops deadSound;
	SoundLoops silenceSound;
	protected double velX = 0;
	protected double velY = 0;
	protected double pathwayLineX = 0;
	private boolean ampIsDead = false;
	private boolean velXMinus = false;
	private boolean velXPlus = false;
	private boolean scoreFollowMe = false;
	private long velYDecreaseTimer = 0;
	private long velXActivatedTimer = 0;
	private long hitTimer = 0;
	private int hitDirectionInt = 0;
	private int silenceTracker = 0;
	public AmpItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		upAnimation = new Animation(6, tex.ampItem[0], tex.ampItem[1], tex.ampItem[2], tex.ampItem[3]);
		rightAnimation = new Animation(6, tex.ampItem[4], tex.ampItem[5], tex.ampItem[6], tex.ampItem[7]);
		rightHitAnimation = new Animation(6, tex.ampItem[8], tex.ampItem[9], tex.ampItem[10], tex.ampItem[11]);
		leftAnimation = new Animation(6, tex.ampItem[12], tex.ampItem[13], tex.ampItem[14], tex.ampItem[15]);
		leftHitAnimation = new Animation(6, tex.ampItem[16], tex.ampItem[17], tex.ampItem[18], tex.ampItem[19]);
		deathAnimation = new Animation(4,tex.ampItemDeath[0],tex.ampItemDeath[1],tex.ampItemDeath[2],tex.ampItemDeath[3],
				tex.ampItemDeath[4],tex.ampItemDeath[5],tex.ampItemDeath[5]);
		pathwayLineX = x + 11;
		velX = -5;

		String deathFile = "res/Sounds/SFX/ampexplosionsfx.wav";
		SoundLoops deathSoundLoop = new SoundLoops(deathFile);
		VolumeSlider.adjustSFX(deathSoundLoop);
		this.deadSound = deathSoundLoop;
		String hitFile = "res/Sounds/SFX/ampzapsfx.wav";
		SoundLoops hitSoundLoop = new SoundLoops(hitFile);
		VolumeSlider.adjustSFX(hitSoundLoop);
		this.hitSound = hitSoundLoop;
		String silenceFile = "res/Sounds/Music/10secondsofsilencee.wav";//silence
		SoundLoops silenceSoundLoop = new SoundLoops(silenceFile);
		this.silenceSound = silenceSoundLoop;
		upAnimation.runAnimation();
		upAnimation.nextFrame();
		upAnimation.setCount(0);
		rightHitAnimation.runAnimation();
		rightHitAnimation.nextFrame();
		rightHitAnimation.setCount(0);
		leftHitAnimation.runAnimation();
		leftHitAnimation.nextFrame();
		leftHitAnimation.setCount(0);
		deathAnimation.runAnimation();
		deathAnimation.nextFrame();
		deathAnimation.setCount(0);
	}

	public void tick() {
		//System.out.println("velX = " + velX);
		//System.out.println("velY = " + velY);
		if(scoreFollowMe) {
			if(game.getHUD().getEnemyHitPauseTimer() < System.currentTimeMillis()) {
				scoreFollowMe = false;
				Game.scoreFollowingBoolean = false;
			}
			Game.currentEECollisionX = this.x-6;
			Game.currentEECollisionY = this.y-10;
			Game.currentEECollisionWidth = this.getBounds().getWidth();
		}
		if(ampIsDead) {
			if(!deadSound.getSoundLoopBoolean()) {
				deadSound.play();
				deadSound.setSoundLoopBoolean(true);
			}
			if(deathAnimation.getCount() != 7)
				deathAnimation.runAnimation();
			if(deathAnimation.getCount() == 7) {
				if(scoreFollowMe) {
					game.getHUD().setEnemyHitPauseTimer(0);
					scoreFollowMe = false;
					Game.scoreFollowingBoolean = false;
				}
				if(!deadSound.clipIsActive())
					game.ee.remove(this);//remove
			}
		}
		else {
			if(!game.ea.isEmpty()) {
				for(int i = 0; i < game.ea.size(); i++){
					EntityA tempEnt = game.ea.get(i);
					if(Physics.Collision(this, tempEnt)){
						zapper = new Zap(game.ea.get(i).getX(),game.ea.get(i).getY(),x,y,tex,game);
						game.getController().addEntity(zapper);
						if(Game.currentlySelectedFireball != 3)
							game.ea.remove(tempEnt);
						else
							ampIsDead = true;
						if(!hitSound.clipIsActive())
							hitSound.play();
						return;
					}
				}
			}
			if(!game.eb.isEmpty()) {
				for(int i = 0; i < game.eb.size(); i++){
					EntityB tempEnt = game.eb.get(i);
					if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){
						if(!tempEnt.enemyType().equals("Bowser")) {
							Game.currentEECollisionX = this.x;
							Game.currentEECollisionY = this.y;
							Game.currentEECollisionWidth = this.getBounds().getWidth();
							if(game.getWaitToPause() < System.currentTimeMillis()) {
								game.setEnemyHitPauseTimer(System.currentTimeMillis() + 500);
								game.setWaitToPause(System.currentTimeMillis() + 4000);
								if(!Game.itemPauseSoundLoop.clipIsActive()) {
									Game.itemPauseSoundLoop.setFramePosition(0);
									Game.itemPauseSoundLoop.play();
								}
							}
							else {
								setScoreFollowersFalse();
								game.setWaitToPause(System.currentTimeMillis() + 2000);
							}
							game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 300);
							hitTimer = System.currentTimeMillis() + 500;
							game.getHUD().setScore(200);
//							if(game.eb.get(i).getX()+game.eb.get(i).getWidth() < this.x)
//								hitDirectionInt = -1;
//							else if(game.eb.get(i).getX() > this.x+22)
//								hitDirectionInt = 1;
//							else
//								hitDirectionInt = 0;
							if(game.eb.get(i).getX()+(game.eb.get(i).getWidth()/3) < this.x+(this.getBounds().getWidth()/2))
								hitDirectionInt = -1;
							else if(game.eb.get(i).getX()-(game.eb.get(i).getWidth()/3) > this.x+(this.getBounds().getWidth()/2))
								hitDirectionInt = 1;
							else
								hitDirectionInt = 0;
							zapper = new Zap(game.eb.get(i).getX(),game.eb.get(i).getY(),x,y,tex,game);
							game.getController().addEntity(zapper);
							game.eb.remove(i);
							if(!hitSound.clipIsActive())
								hitSound.play();
							//add zap
						}
						else {
							HUD.HEALTH -= 4;
							ampIsDead = true;
							//spawn zaps
							//makesound
						}
					}
				}
			}
			if(!game.ec.isEmpty()){
				for(int i = 0; i < game.ec.size(); i++){
					EntityC tempEnt = game.ec.get(i);
					if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
						if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp")) {
							ampIsDead = true;
							//spawn zaps
							//makesound
						}
						else {
							Game.currentEECollisionX = this.x;
							Game.currentEECollisionY = this.y;
							Game.currentEECollisionWidth = this.getBounds().getWidth();
							if(game.getWaitToPause() < System.currentTimeMillis()) {
								game.setEnemyHitPauseTimer(System.currentTimeMillis() + 500);
								game.setWaitToPause(System.currentTimeMillis() + 4000);
								if(!Game.itemPauseSoundLoop.clipIsActive()) {
									Game.itemPauseSoundLoop.setFramePosition(0);
									Game.itemPauseSoundLoop.play();
								}
							}
							else {
								setScoreFollowersFalse();
								game.setWaitToPause(System.currentTimeMillis() + 2000);
							}
							game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 300);
							hitTimer = System.currentTimeMillis() + 500;
							game.getHUD().setScore(200);
//							if(game.ec.get(i).getX()+game.ec.get(i).getWidth() < this.x)
//								hitDirectionInt = -1;
//							else if(game.ec.get(i).getX() > this.x+22)
//								hitDirectionInt = 1;
//							else
//								hitDirectionInt = 0;
							if(game.ec.get(i).getX()+(game.ec.get(i).getWidth()/3) < this.x+(this.getBounds().getWidth()/2))
								hitDirectionInt = -1;
							else if(game.ec.get(i).getX()-(game.ec.get(i).getWidth()/3) > this.x+(this.getBounds().getWidth()/2))
								hitDirectionInt = 1;
							else
								hitDirectionInt = 0;

							zapper = new Zap(game.ec.get(i).getX(),game.ec.get(i).getY(),x,y,tex,game);
							game.getController().addEntity(zapper);
							game.ec.remove(i);
							//add zap
							if(!hitSound.clipIsActive())
								hitSound.play();
						}
					}
				}
			}

			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead() && tempEnt != this){
					if(i < game.ee.size()) {
						zapper = new Zap(game.ee.get(i).getX(),game.ee.get(i).getY(),x,y,tex,game);
						game.getController().addEntity(zapper);
					}
					if(tempEnt.entityName().equals("cheepCheeps") || tempEnt.entityName().equals("bombOmb") ||
							tempEnt.entityName().equals("bombOmbShrapnel1") || tempEnt.entityName().equals("bombOmbShrapnel2") ||
							tempEnt.entityName().equals("bulletBill")){
						game.ee.get(i).setEntityEDead(true);
					}
					
				}
			}
			
			if(velXActivatedTimer < System.currentTimeMillis()) {
				if(x+this.getBounds().width > this.pathwayLineX + 25) {
					velX -= 0.18;
					velXMinus = true;
					velXPlus = false;
				}
				if(x < this.pathwayLineX - 25) {
					velX += 0.18;
					velXMinus = false;
					velXPlus = true;
				}
				if(velXMinus)
					velX-=0.025;
				if(velXPlus)
					velX+=0.025;
//				if(velXActivatedTimer != 0) {
//					if(velX < 0)
//						velX = 5;
//					else
//						velX = -5;
//					velXActivatedTimer = 0;
//				}
//				if(velX > -0.1 && velX < 0.1) {
//					//velX = 0;
//					velXActivatedTimer = System.currentTimeMillis() + 1000;
//					velYDecreaseTimer = System.currentTimeMillis() + 500;
//					velY = 1.7;
//				}
//				else if(velX < 0-(velX/10)) {
//					velX += (-1 * (velX/10));
//				}
//				else if(velX > 0+(velX/10)) {
//					velX -= (1 * (velX/10));
//				}
				
//				else {
//					velXActivatedTimer = System.currentTimeMillis() + 1000;
//					velYDecreaseTimer = System.currentTimeMillis() + 500;
//					velY = 2;
//				}
			}
			if(velYDecreaseTimer > System.currentTimeMillis() && velY > 0) {
				velY-=(velY/10);
			}
			else if(velYDecreaseTimer != 0) {
				velY = 0;
				velYDecreaseTimer = 0;
			}
			//if(velX < 0-(velX/10) || velX > 0+(velX/10))
				x+=velX;
			y-=(2-velY);
			if(hitTimer < System.currentTimeMillis())
				upAnimation.runAnimation();
			else {
				if(hitDirectionInt == -1)
					leftHitAnimation.runAnimation();
				else if(hitDirectionInt == 1)
					rightHitAnimation.runAnimation();
				else
					upAnimation.runAnimation();
			}
			if(silenceSound.getSoundLoopBoolean() == true) {
				leftHitAnimation.setCount(0);
				rightHitAnimation.setCount(0);
				silenceSound.stop();
				silenceSound.setFramePosition(0);
				silenceSound.setSoundLoopBoolean(false);
				silenceTracker = 0;
			}
		}
		if(y < -118) 
			game.ee.remove(this);
	}

	public void render(Graphics g) {
		if(ampIsDead) {
			if(deathAnimation.getCount() != 7)
				deathAnimation.drawAnimation(g, x, y, 0);
		}
		else {
			if(hitTimer < System.currentTimeMillis()) {
				if(upAnimation.getCount() == 4)
					upAnimation.drawAnimation(g, x-4, y, 0);
				else
					upAnimation.drawAnimation(g, x, y, 0);
			}
			else {
				int i = 0;
				if(silenceSound.getLongFramePosition() != 0)
					i = (int)silenceSound.getLongFramePosition() / (int)(2368);//(4736);
				//if(System.currentTimeMillis() % 60 == 0) {
				if(i != silenceTracker ) {//&& (leftHitAnimation.getCount() != 4 || rightHitAnimation.getCount() != 4 || 
						//upAnimation.getCount() != 4))
//					leftHitAnimation.runAnimation();
//					rightHitAnimation.runAnimation();
//					upAnimation.runAnimation();
					upAnimation.runAnimation();
					leftHitAnimation.runAnimation();
					rightHitAnimation.runAnimation();
					upAnimation.runAnimation();
					leftHitAnimation.runAnimation();
					rightHitAnimation.runAnimation();
					upAnimation.runAnimation();
					leftHitAnimation.runAnimation();
					rightHitAnimation.runAnimation();
					upAnimation.runAnimation();
					leftHitAnimation.runAnimation();
					rightHitAnimation.runAnimation();
					leftHitAnimation.nextFrame();
					rightHitAnimation.nextFrame();
				}
				if(hitDirectionInt == -1) {
					if(leftHitAnimation.getCount() ==2)
						leftHitAnimation.drawAnimation(g, x-6, y, 0);
					else
						leftHitAnimation.drawAnimation(g, x, y, 0);
				}
				else if(hitDirectionInt == 1) {
					if(rightHitAnimation.getCount() == 1 || rightHitAnimation.getCount() == 0)
						rightHitAnimation.drawAnimation(g, x-6, y, 0);
					else if(rightHitAnimation.getCount() == 2)
						rightHitAnimation.drawAnimation(g, x-1, y, 0);
					else
						rightHitAnimation.drawAnimation(g, x, y, 0);
				}
				else {
					if(upAnimation.getCount() == 4)
						upAnimation.drawAnimation(g, x-4, y, 0);
					else
						upAnimation.drawAnimation(g, x, y, 0);
				}
				if(silenceTracker != i)
					silenceTracker = i;
			}
		}
	}

	public void setScoreFollowersFalse() {
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE)
				game.ee.get(e).setScoreFollowMe(true);
			else
				game.ee.get(e).setScoreFollowMe(false);
		}
		Game.scoreFollowingBoolean = true;
		if(hitSound.clipIsActive())
			hitSound.stop();
		hitSound.play();
	}

	public Rectangle getBounds() {
		if(hitTimer < System.currentTimeMillis()) {
			if(upAnimation.getCount() == 1)
				return new Rectangle((int)x, (int)y, 22, 21);
			else if(upAnimation.getCount() == 2)
				return new Rectangle((int)x, (int)y, 26, 21);
			else if(upAnimation.getCount() == 3)
				return new Rectangle((int)x, (int)y, 23, 22);
			else if(upAnimation.getCount() == 4)
				return new Rectangle((int)x, (int)y, 26, 21);
			else
				return new Rectangle((int)x, (int)y, 22, 21);
		}
		else {
			if(hitDirectionInt == -1) {
				if(leftHitAnimation.getCount() == 1)
					return new Rectangle((int)x, (int)y, 24, 24);
				else if(leftHitAnimation.getCount() == 2)
					return new Rectangle((int)x, (int)y, 30, 24);
				else if(leftHitAnimation.getCount() == 3)
					return new Rectangle((int)x, (int)y, 25, 27);
				else if(leftHitAnimation.getCount() == 4)
					return new Rectangle((int)x, (int)y, 30, 25);
				else
					return new Rectangle((int)x, (int)y, 24, 24);
			}
			else if(hitDirectionInt == 1) {
				if(rightHitAnimation.getCount() == 1)
					return new Rectangle((int)x, (int)y, 30, 25);
				else if(rightHitAnimation.getCount() == 2)
					return new Rectangle((int)x, (int)y, 25, 27);
				else if(rightHitAnimation.getCount() == 3)
					return new Rectangle((int)x, (int)y, 30, 24);
				else if(rightHitAnimation.getCount() == 4)
					return new Rectangle((int)x, (int)y, 24, 24);
				else
					return new Rectangle((int)x, (int)y, 30, 25);
			}
			else {
				if(upAnimation.getCount() == 1)
					return new Rectangle((int)x, (int)y, 22, 21);
				else if(upAnimation.getCount() == 2)
					return new Rectangle((int)x, (int)y, 26, 21);
				else if(upAnimation.getCount() == 3)
					return new Rectangle((int)x, (int)y, 23, 22);
				else if(upAnimation.getCount() == 4)
					return new Rectangle((int)x, (int)y, 26, 21);
				else
					return new Rectangle((int)x, (int)y, 22, 21);
					
			}
			
		}
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
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public void setScoreFollowMe(boolean b) {
		this.scoreFollowMe = b;
	}

	public String entityName() {
		return "amp";
	}
	
	public void setHitIndicator(boolean b) {
		return;
	}

	public boolean getHitIndicator() {
		return false;
	}

	public void setEntityEDead(boolean dead) {
		this.ampIsDead = dead;
	}
	
	public boolean getEntityEDead() {
		return ampIsDead;
	}
	
	public void close() {
		deadSound.close();
		hitSound.close();
	}

}
