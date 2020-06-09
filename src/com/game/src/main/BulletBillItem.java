package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class BulletBillItem extends GameObject implements EntityE {

	private Textures tex;
	private Game game;
	private int xDestination = 10000;
	private int ecMarked = -1;
	private int ebMarked = -1;
	private int xDirection = -1;
	private int hitCount = 0;
	private int smokeCount = 0;
	private long timer1 = 150;
	private boolean bulletBillisDead = false;
	private boolean scoreFollowMe = false;
	protected double velX, velY;
	Animation animUItem;
	Animation animULItem;
	Animation animURItem;
	Animation animUDisintegrate;
	SoundLoops itemSoundLoop;
	SoundLoops bulletBillDeathSoundLoop;
	BulletBillSmokeFX smoke;
	
	public BulletBillItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.game = game;
		this.tex = tex;
		velX = 0;
		velY = -1;
		
		animUItem = new Animation(6, tex.bulletBillUItem[0], tex.bulletBillUItem[1], tex.bulletBillUItem[2], tex.bulletBillUItem[3],
				 tex.bulletBillUItem[4], tex.bulletBillUItem[5], tex.bulletBillUItem[6], tex.bulletBillUItem[7], tex.bulletBillUItem[8],
				 tex.bulletBillUItem[9], tex.bulletBillUItem[10], tex.bulletBillUItem[11], tex.bulletBillUItem[12], tex.bulletBillUItem[13],
				 tex.bulletBillUItem[14], tex.bulletBillUItem[15], tex.bulletBillUItem[16]);
		
		animULItem = new Animation(6, tex.bulletBillULItem[0], tex.bulletBillULItem[1], tex.bulletBillULItem[2], tex.bulletBillULItem[3],
				 tex.bulletBillULItem[4], tex.bulletBillULItem[5], tex.bulletBillULItem[6], tex.bulletBillULItem[7],
				 tex.bulletBillULItem[8], tex.bulletBillULItem[9]);
		
		animURItem = new Animation(6, tex.bulletBillURItem[0], tex.bulletBillURItem[1], tex.bulletBillURItem[2], tex.bulletBillURItem[3],
				 tex.bulletBillURItem[4], tex.bulletBillURItem[5], tex.bulletBillURItem[6], tex.bulletBillURItem[7],
				 tex.bulletBillURItem[8], tex.bulletBillURItem[9]);
		
		animUDisintegrate = new Animation(5, tex.bulletBillExplosionItem[0],tex.bulletBillExplosionItem[1],
				tex.bulletBillExplosionItem[2],tex.bulletBillExplosionItem[3],tex.bulletBillExplosionItem[4],
				tex.bulletBillExplosionItem[5],tex.bulletBillExplosionItem[6]);
		animUItem.nextFrame();
		animUItem.setCount(0);
		animULItem.nextFrame();
		animULItem.setCount(0);
		animURItem.nextFrame();
		animURItem.setCount(0);
		animUDisintegrate.nextFrame();
		animUDisintegrate.setCount(0);
		String itemFile = "res/Sounds/SFX/nsmbwiiBulletBillCannon2.wav";
		String deathFile = "res/Sounds/SFX/smb2_bbdeathsfx.wav";
		SoundLoops itemSoundLoop = new SoundLoops(itemFile);
		SoundLoops deathSoundLoop = new SoundLoops(deathFile);
		VolumeSlider.adjustSFX(itemSoundLoop);
		VolumeSlider.adjustSFX(deathSoundLoop);
		this.itemSoundLoop = itemSoundLoop;
		this.bulletBillDeathSoundLoop = deathSoundLoop;
	}

	public void tick(){
		if(scoreFollowMe) {
			if(game.getHUD().getEnemyHitPauseTimer() < System.currentTimeMillis()) {
				scoreFollowMe = false;
				Game.scoreFollowingBoolean = false;
			}
			Game.currentEECollisionX = this.x-6;
			Game.currentEECollisionY = this.y-10;
			Game.currentEECollisionWidth = this.getBounds().getWidth();
		}
		if(animUDisintegrate.getCount() == 7) {
			if(scoreFollowMe) {
				game.getHUD().setEnemyHitPauseTimer(0);
				scoreFollowMe = false;
				Game.scoreFollowingBoolean = false;
			}
			game.ee.remove(this);
		}
		else if(bulletBillisDead) {
			animUDisintegrate.runAnimation();

			if(xDirection == 1) {
				if(velX> -0.8)
					velX = velX - 0.009;
			}else if(xDirection == 0) {
				if(velX<0.8)
					velX = velX + 0.009;
			}
			if(velY < 0.9)
				velY = velY + 0.009;
			if(game.getBulletBillDeathSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.bulletBillDeathSoundLoop.size()-1; l >= 0; l--){
					if(!game.bulletBillDeathSoundLoop.get(l).clipIsActive())
						game.bulletBillDeathSoundLoop.get(l).continuePlaying();
				}
				game.setBulletBillDeathSoundPauseBoolean(false);
			}
			x += velX/12;
			y += velY/3;
		}
		else {
			if (this.getX()-30 >= (Game.WIDTH * Game.SCALE)){			//Removing BulletBill from Game
				game.ee.remove(this);
			}
			if (x+30 <= 0){
				game.ee.remove(this);
			}
			if(y < -16)
				game.ee.remove(this);
			if(hitCount == 2) {
				bulletBillisDead = true;
				return;
			}
			//System.out.println("xDirection = " + xDirection + "\n velX = "+ velX);

			if((int)timer1 > 0){									//Chases Enemy
				if(ebMarked >= game.eb.size() || (game.eb.isEmpty() && ebMarked >= 0))
					ebMarked = -1;
				if(ecMarked >= game.ec.size() || (game.ec.isEmpty() && ecMarked >= 0))
					ecMarked = -1;
				if(!game.ea.isEmpty()) {
					for(int i = 0; i < game.ea.size(); i++){
						EntityA tempEnt = game.ea.get(i);
						if(Physics.Collision(this, tempEnt)){
							if(Game.currentlySelectedFireball != 3)
								game.ea.remove(tempEnt);
							bulletBillisDead = true;
							//MAKE EXPLOSION SOUND
							if(this.bulletBillDeathSoundLoop.getSoundLoopBoolean() == false){
								for(int j = game.bulletBillDeathSoundLoop.size(); j > 0; j--){
									if(game.bulletBillDeathSoundLoop.get(j-1) != null && !game.bulletBillDeathSoundLoop.get(j-1).clipIsActive()){
										game.bulletBillDeathSoundLoop.remove(j-1);
										//j--;
									}
								}	
								for(int k = 0; k < game.bulletBillDeathSoundLoop.size() || k == 0; k++){
									if(game.bulletBillDeathSoundLoop.isEmpty())
										game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
									else if (game.bulletBillDeathSoundLoop.get(k) == game.bulletBillDeathSoundLoop.getLast()){
										game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
										k++;
									}else if(this.bulletBillDeathSoundLoop.getVolume() -1.5f >= this.bulletBillDeathSoundLoop.minimumVolume())
										this.bulletBillDeathSoundLoop.reduceSound(1.5f);
								}
								this.bulletBillDeathSoundLoop.setSoundLoopBoolean(true);
								game.bulletBillDeathSoundLoop.getLast().play();
							}
						}
					}
				}
				if(!game.eb.isEmpty()){
					if(ebMarked != -1 && ebMarked < game.eb.size()){
						if(game.eb.get(ebMarked).getX() < this.getX())
							xDirection = 0;//x--
						else
							xDirection = 1;
					}
					for(int i = 0; i < game.eb.size(); i++){
		
						EntityB tempEnt = game.eb.get(i);
						if((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1 || ebMarked == i) {
								//(ebMarked == i && (Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) > xDestination))){
								xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
								ebMarked = i;
						}
						if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){
							if(!tempEnt.enemyType().equals("Bowser")) {
								if(hitCount == 1) {
									game.eb.remove(i);
									//Game.bEntityRemoved = true;
									if(!game.eb.isEmpty() && game.getSpawnDone2() == false) {
										if (game.eb.size() == 2)
											game.enemySpeedIncrease+= 0.3;
										game.enemySpeedIncrease+= 0.06;
									}else if(!game.eb.isEmpty() && game.getSpawnDone3() == false) {
										if (game.eb.size() == 2)
											game.enemySpeedIncrease+= 0.4;
										game.enemySpeedIncrease+= 0.06; //0.7
									}else if(!game.eb.isEmpty() && game.getSpawnDone4() == false) {
										if (game.eb.size() == 2)
											game.enemySpeedIncrease+= 0.9;
										game.enemySpeedIncrease+= 0.08; //0.7
									}
									ebMarked = -1;
									hitCount++;
									bulletBillisDead = true;
									game.getHUD().setScore(200);
									return;
								}
								if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
									this.itemSoundLoop.play();
									this.itemSoundLoop.setSoundLoopBoolean(true);
								}
								game.eb.remove(i);
								//Game.bEntityRemoved = true;
								if(!game.eb.isEmpty() && game.getSpawnDone2() == false) {
									if (game.eb.size() == 2)
										game.enemySpeedIncrease+= 0.3;
									game.enemySpeedIncrease+= 0.06;
								}else if(!game.eb.isEmpty() && game.getSpawnDone3() == false) {
									if (game.eb.size() == 2)
										game.enemySpeedIncrease+= 0.4;
									game.enemySpeedIncrease+= 0.06; //0.7
								}else if(!game.eb.isEmpty() && game.getSpawnDone4() == false) {
									if (game.eb.size() == 2)
										game.enemySpeedIncrease+= 0.9;
									game.enemySpeedIncrease+= 0.08; //0.7
								}
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
								ebMarked = -1;
								hitCount++;
								game.getHUD().setScore(200);
								xDestination = 10000;
							}
							else {
								HUD.HEALTH -= 4;
								bulletBillisDead = true;
								//MAKE EXPLOSION SOUND
								if(this.bulletBillDeathSoundLoop.getSoundLoopBoolean() == false){
									for(int j = game.bulletBillDeathSoundLoop.size(); j > 0; j--){
										if(game.bulletBillDeathSoundLoop.get(j-1) != null && !game.bulletBillDeathSoundLoop.get(j-1).clipIsActive()){
											game.bulletBillDeathSoundLoop.remove(j-1);
											//j--;
										}
									}	
									for(int k = 0; k < game.bulletBillDeathSoundLoop.size() || k == 0; k++){
										if(game.bulletBillDeathSoundLoop.isEmpty())
											game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
										else if (game.bulletBillDeathSoundLoop.get(k) == game.bulletBillDeathSoundLoop.getLast()){
											game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
											k++;
										}else if(this.bulletBillDeathSoundLoop.getVolume() -1.5f >= this.bulletBillDeathSoundLoop.minimumVolume())
											this.bulletBillDeathSoundLoop.reduceSound(1.5f);
									}
									this.bulletBillDeathSoundLoop.setSoundLoopBoolean(true);
									game.bulletBillDeathSoundLoop.getLast().play();
								}
							}
						}
					}
				}
				if(!game.ec.isEmpty()){
					if(ecMarked != -1 && ecMarked < game.ec.size() && game.ec.get(ecMarked).getY() < this.getY()){
						if(game.ec.get(ecMarked).getX() < this.getX()+6.4)
							xDirection = 0;//x--
						else if(game.ec.get(ecMarked).getX() > this.getX()-6.4)
							xDirection = 1;
					}
					for(int i = 0; i < game.ec.size(); i++){
		
						EntityC tempEnt = game.ec.get(i);
						if((Math.abs((int)this.getX() - (int)game.ec.get(i).getX()) < xDestination) || game.ec.size() == 1 || ecMarked == i) {
								//(ecMarked == i && (Math.abs((int)this.getX() - (int)game.ec.get(i).getX()) > xDestination))){
								xDestination = Math.abs((int)this.getX() - (int)game.ec.get(i).getX());
								ecMarked = i;
						}
						if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
							if(hitCount == 1 || tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("BulletBill") ||
									tempEnt.entityName().equals("Mechakoopa")) {
								if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
									//game.getHUD().setScore(500);
									ecMarked = -1;
									hitCount++;
									bulletBillisDead = true;
									return;
								}
								else if(tempEnt.entityName().equals("BulletBill")) {
									game.ec.get(i).setEntityCDead(true);
									ecMarked = -1;
									hitCount++;
									bulletBillisDead = true;
									return;
								}
								else
									game.getHUD().setScore(200);
								game.ec.remove(i);
								ecMarked = -1;
								hitCount++;
								bulletBillisDead = true;
								return;
							}
							if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
								this.itemSoundLoop.play();
								this.itemSoundLoop.setSoundLoopBoolean(true);
							}
							if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
								HUD.currentScoreFromChainChomp = "500";
								game.getHUD().setScore(500);
							}
							else if(tempEnt.entityName().equals("BulletBill")) {
								HUD.currentScoreFromChainChomp = "200";
								game.getHUD().setScore(200);
							}
							else {
								HUD.currentScoreFromChainChomp = "200";
								game.getHUD().setScore(200);
							}
							game.ec.remove(i);
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
							ecMarked = -1;
							hitCount++;
							xDestination = 10000;
						}
					}
				}
				else if(game.eb.isEmpty()){
					xDirection = -1;
					velX = 0;
				}
				for(int i = 0; i < game.ee.size(); i++){
					
					EntityE tempEnt = game.ee.get(i);
					if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead()){
						if(tempEnt.entityName().equals("bombOmbShrapnel1") ||tempEnt.entityName().equals("bombOmbShrapnel2") ||
								tempEnt.entityName().equals("bombOmb")) {
							game.ee.get(i).setEntityEDead(true);
							this.bulletBillisDead = true;
							//MAKE EXPLOSION SOUND
							if(this.bulletBillDeathSoundLoop.getSoundLoopBoolean() == false){
								for(int j = game.bulletBillDeathSoundLoop.size(); j > 0; j--){
									if(game.bulletBillDeathSoundLoop.get(j-1) != null && !game.bulletBillDeathSoundLoop.get(j-1).clipIsActive()){
										game.bulletBillDeathSoundLoop.remove(j-1);
										//j--;
									}
								}	
								for(int k = 0; k < game.bulletBillDeathSoundLoop.size() || k == 0; k++){
									if(game.bulletBillDeathSoundLoop.isEmpty())
										game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
									else if (game.bulletBillDeathSoundLoop.get(k) == game.bulletBillDeathSoundLoop.getLast()){
										game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
										k++;
									}else if(this.bulletBillDeathSoundLoop.getVolume() -1.5f >= this.bulletBillDeathSoundLoop.minimumVolume())
										this.bulletBillDeathSoundLoop.reduceSound(1.5f);
								}
								this.bulletBillDeathSoundLoop.setSoundLoopBoolean(true);
								game.bulletBillDeathSoundLoop.getLast().play();
							}
						}
					}
				}
			}
			else {
				Random rand = new Random();
				int i = rand.nextInt(130);
				y++;
				if (i == 18){										//Resets timer1 so he chases Enemies
					timer1 = 150;
				}
			}
			animUItem.runAnimation();
			animULItem.runAnimation();
			animURItem.runAnimation();
			if((ebMarked != -1 && !game.eb.isEmpty() && game.eb.getFirst().getY() <= this.y) ||
				(ecMarked != -1 && !game.ec.isEmpty() && game.ec.getLast().getY() <= this.y)) {
				if(game.getSpawnDone2() == false) {
					if(xDirection == 0)
						velX = -0.3; //-1 * (game.enemySpeedIncrease - 0.2);
					else if(xDirection == 1)
						velX = 0.3;//game.enemySpeedIncrease - 0.2;
					else
						velX = 0;
				}else if(game.getSpawnDone3() == false) {
					if(xDirection == 0)
						velX = -0.7; //-1 * (game.enemySpeedIncrease - 0.2);
					else if(xDirection == 1)
						velX = 0.7;//game.enemySpeedIncrease - 0.2;
					else
						velX = 0;
				}else if(game.getSpawnDone4() == false) {
					if(xDirection == 0)
						velX = -1; //-1 * (game.enemySpeedIncrease - 0.2);
					else if(xDirection == 1)
						velX = 1;//game.enemySpeedIncrease - 0.2;
					else
						velX = 0;
				}else {
					if(xDirection == 0)
						velX = -1; //-1 * (game.enemySpeedIncrease - 0.2);
					else if(xDirection == 1)
						velX = 1;//game.enemySpeedIncrease - 0.2;
					else
						velX = 0;
				}
			}
			else
				velX = 0;
			//CREATE SMOKE
			smokeCount++;
			if(smokeCount > 1) {
				if(xDestination <= 7 && xDestination >= 0) 
					smoke = new BulletBillSmokeFX(x+4,y+9,game);
				else if(velX > 0 && ((ebMarked != -1 && game.eb.get(ebMarked).getX() > this.getX() +5.8) ||
						(ebMarked != -1 && game.eb.get(ebMarked).getX() > this.getX() + 5.8)))
					smoke = new BulletBillSmokeFX(x-3,y+8,game);
				else if(velX < 0 && ((ebMarked != -1 && game.eb.get(ebMarked).getX() < this.getX() -5.8) ||
						(ebMarked != -1 && game.eb.get(ebMarked).getX() < this.getX() - 5.8)))
					smoke = new BulletBillSmokeFX(x+11,y+8,game);
				else
					smoke = new BulletBillSmokeFX(x+4,y+9,game);
				game.getController().addEntity(smoke);
				smokeCount = 0;
			}
			x += velX;
			y += velY;
			//System.out.println(xDestination);
		}
	}
	
	public void render(Graphics g){
		if(ebMarked >= game.eb.size() || (game.eb.isEmpty() && ebMarked >= 0))
			ebMarked = -1;
		if(ecMarked >= game.ec.size() || (game.ec.isEmpty() && ecMarked >= 0))
			ecMarked = -1;
		if(bulletBillisDead)
			animUDisintegrate.drawAnimation(g, x, y, 0);
		//else if(xDirection == 1 && (ebMarked != -1 && game.eb.get(ebMarked).getX() > this.getX() +6.4) ||
		//		(ebMarked != -1 && game.eb.get(ebMarked).getX() > this.getX() + 6.4))
		else if(xDestination <= 7 && xDestination >= 0) {
			animUItem.drawAnimation(g, x, y, 0);
		}
		else if(velX > 0 && ((ebMarked != -1 && game.eb.get(ebMarked).getX() > this.getX() +5.8) ||
						(ebMarked != -1 && game.eb.get(ebMarked).getX() > this.getX() + 5.8)))
			animURItem.drawAnimation(g, x, y, 0);
		//else if(xDirection == 0 && (ebMarked != -1 && game.eb.get(ebMarked).getX() < this.getX() -6.4) ||
		//		(ebMarked != -1 && game.eb.get(ebMarked).getX() < this.getX() - 6.4))
		else if(velX < 0 && ((ebMarked != -1 && game.eb.get(ebMarked).getX() < this.getX() -5.8) ||
						(ebMarked != -1 && game.eb.get(ebMarked).getX() < this.getX() - 5.8)))
			animULItem.drawAnimation(g, x, y, 0);
		else
			animUItem.drawAnimation(g, x, y, 0);
		
		if(game.isPaused()){
			if(game.getBulletBillDeathSoundPauseBoolean() == false){
				for(int i = 0; i < game.bulletBillDeathSoundLoop.size();i++){
					if(game.bulletBillDeathSoundLoop.get(i).clipIsActive())
						game.bulletBillDeathSoundLoop.get(i).stop();
				}
				game.setBulletBillDeathSoundPauseBoolean(true);
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
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
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
		return "bulletBill";
	}
	
	public void setHitIndicator(boolean b) {
		return;
	}
	
	public boolean getHitIndicator() {
		return false;
	}

	public void setEntityEDead(boolean dead) {
		this.bulletBillisDead = dead;
	}
	
	public boolean getEntityEDead() {
		return bulletBillisDead;
	}
	
	public void close() {
		itemSoundLoop.close();
		bulletBillDeathSoundLoop.close();
	}
}
