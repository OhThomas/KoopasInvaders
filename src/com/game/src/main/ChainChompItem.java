package com.game.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;
import com.game.src.main.libs.Animation;

public class ChainChompItem extends GameObject implements EntityE {

	private Textures tex;
	private Game game;
	private EntityF chainChompBall1;
	private EntityF chainChompBall2;
	private EntityF chainChompBall3;
	private EntityE chainChompNumber;
	private int xDestination = 10000;
	private int ecMarked = -1;
	private int ebMarked = -1;
	private int xDirection = -1;
	private int chain1X = 0;
	private int chain1Y = 0;
	private int chain2X = 0;
	private int chain2Y = 0;
	private int chain3X = 0;
	private int chain3Y = 0;
	private int silenceTracker = 0;
	private long movingTimer1 = 0;
	private long movingTimer2 = 0;
	private long movingTimer3 = 0;
	private boolean movingTimer2Boolean, movingTimer3Boolean = false;	
	private boolean chainChompisDead = false;
	private boolean keepChomping = false;
	private boolean keepMoving = false;
	private boolean chompingLeft = false;
	private boolean chompingRight = false;
	private boolean scoreFollowMe = false;
	protected double velX, velY;
	Animation anim;
	Animation animChompinL;
	Animation animChompinR;
	Animation animDisintegrate;
	SoundLoops itemSoundLoop;
	SoundLoops chainChompDeathSoundLoop;
	SoundLoops silenceSoundLoop;
	EntityB tempEntB = null;
	EntityC tempEntC = null;
	ChompFX chomped;
	Graphics g2d;
	public ChainChompItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.game = game;
		this.tex = tex;
		velX = 0;
		velY = 0;
		
		anim = new Animation(6,tex.chainChompItem[0], tex.chainChompItem[0],tex.chainChompItem[1], tex.chainChompItem[2],
				tex.chainChompItem[3],tex.chainChompItem[4],tex.chainChompItem[3],tex.chainChompItem[2],tex.chainChompItem[1]);
		animChompinL = new Animation(6,tex.chainChompChompin[0],tex.chainChompChompin[1],tex.chainChompChompin[2],
				tex.chainChompChompin[3],tex.chainChompChompin[4],tex.chainChompChompin[5],tex.chainChompChompin[4],tex.chainChompChompin[2],
				tex.chainChompChompin[1],tex.chainChompChompin[0]);
		animChompinR = new Animation(6,tex.chainChompChompin[6],tex.chainChompChompin[7],tex.chainChompChompin[8],
				tex.chainChompChompin[9],tex.chainChompChompin[10],tex.chainChompChompin[11],tex.chainChompChompin[10],tex.chainChompChompin[8],
				tex.chainChompChompin[7],tex.chainChompChompin[6]);
		animDisintegrate = new Animation(6,tex.chainChompDisintegrate[0],tex.chainChompDisintegrate[1],
				tex.chainChompDisintegrate[2],tex.chainChompDisintegrate[3],tex.chainChompDisintegrate[4],
				tex.chainChompDisintegrate[5],tex.chainChompDisintegrate[6],tex.chainChompDisintegrate[7]);
		anim.nextFrame();
		animDisintegrate.setCount(0);
		animDisintegrate.nextFrame();
		animChompinL.nextFrame();
		animChompinL.setCount(0);
		animChompinR.nextFrame();
		animChompinR.setCount(0);
		String itemFile = "res/Sounds/SFX/Items/sm64_chain_chomp.wav";
		String deathFile = "res/Sounds/SFX/Items/sm64_chainchompdisintegrating.wav";
		String silenceFile = "res/Sounds/Music/10secondsofsilencee.wav";//silence
		SoundLoops itemSoundLoop = new SoundLoops(itemFile);
		SoundLoops deathSoundLoop = new SoundLoops(deathFile);
		SoundLoops silenceSoundLoop = new SoundLoops(silenceFile);
		itemSoundLoop.reduceSound(10f);
		deathSoundLoop.reduceSound(7f);
		VolumeSlider.adjustSFX(itemSoundLoop);
		VolumeSlider.adjustSFX(deathSoundLoop);
		this.itemSoundLoop = itemSoundLoop;
		this.chainChompDeathSoundLoop = deathSoundLoop;
		this.silenceSoundLoop = silenceSoundLoop;//Game.menuSoundLoops.get(Game.menuSoundLoops.size()-1);
		movingTimer1 = System.currentTimeMillis() + 2000;
		movingTimer2 = System.currentTimeMillis() + 2000;
		movingTimer3 = System.currentTimeMillis() + 2000;
		chain1X = (int)x-5;
		chain1Y = (int)y+30;
		chain2X = (int)x-5;
		chain2Y = (int)y+30;
		chain3X = (int)x-5;
		chain3Y = (int)y+30;
		EntityE chainChompNumber;
//		for(int i = 0; i < game.ee.size(); i++){
//			EntityE tempEnt = game.ee.get(i);
//			if(tempEnt == this) 
//				break;
//			if(tempEnt.entityName().equals("chainChomp")) 
//				chainChompNumber++;
//		}
		chainChompNumber = this;
		this.chainChompNumber = chainChompNumber;
		chainChompBall1 = new ChainChompChainFX(chain1X+this.getBounds().width/2,chain1Y, tex, game,chainChompNumber);
		chainChompBall2 = new ChainChompChainFX(chain2X+this.getBounds().width/2,chain2Y, tex, game,chainChompNumber,chainChompBall1,0);
		chainChompBall3 = new ChainChompChainFX(chain3X+this.getBounds().width/2,chain3Y, tex, game,chainChompNumber,chainChompBall2,1);
		game.getController().addEntity(chainChompBall1);//,0+(3*chainChompNumber)));
		game.getController().addEntity(chainChompBall2);//,1+(3*chainChompNumber)));
		game.getController().addEntity(chainChompBall3);//,2+(3*chainChompNumber)));
		
//		game.getController().addEntity(new ChainChompChainFX(chain1X+this.getBounds().width/2,chain1Y, tex, game,chainChompNumber));//,0+(3*chainChompNumber)));
//		game.getController().addEntity(new ChainChompChainFX(chain2X+this.getBounds().width/2,chain2Y, tex, game,chainChompNumber));//,1+(3*chainChompNumber)));
//		game.getController().addEntity(new ChainChompChainFX(chain3X+this.getBounds().width/2,chain3Y, tex, game,chainChompNumber));//,2+(3*chainChompNumber)));
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
		if(keepChomping) {
			animChompinL.setCount(0);
			animChompinR.setCount(0);
			silenceSoundLoop.stop();
			silenceSoundLoop.setFramePosition(0);
			silenceSoundLoop.setSoundLoopBoolean(false);
			silenceTracker = 0;
			keepChomping = false;
			chompingLeft = false;
			chompingRight = false;
			HUD.currentScoreFromChainChomp = "";
		}
		if(tempEntB != null) {
			game.getHUD().setScore(200);
			game.getController().removeEntity(tempEntB);
			tempEntB = null;
			chompingLeft = false;
			chompingRight = false;
			HUD.currentScoreFromChainChomp = "";
		}
		if(tempEntC != null) {
			if(tempEntC.entityName().equals("BuzzyBeetleShell")) 
				game.getHUD().setScore(500);
			else //if(tempEntC.entityName().equals("BulletBill")) 
				game.getHUD().setScore(200);
			game.getController().removeEntity(tempEntC);
			tempEntC = null;
			chompingLeft = false;
			chompingRight = false;
			//HUD.currentScoreFromChainChomp = "";
		}
		if(animDisintegrate.getCount() == 8) {
			if(scoreFollowMe) {
				game.getHUD().setEnemyHitPauseTimer(0);
				scoreFollowMe = false;
				Game.scoreFollowingBoolean = false;
			}
			removeChain();
			game.ee.remove(this);
		}
		else if(chainChompisDead) {
			animDisintegrate.runAnimation();

			if(movingTimer2 >= movingTimer3 && !keepMoving) {
				keepMoving = true;
				velX = 0;
				velY = 0;
			}
			if(xDirection == 1) {
				if(velX> -0.8)
					velX = velX - 0.009;
			}else if(xDirection == 0) {
				if(velX<0.8)
					velX = velX + 0.009;
			}
			if(velY < 0.9)
				velY = velY + 0.009;
			if(keepMoving) {
				x += velX;
				y += velY;
			}
			if(game.getChainChompDeathSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.chainChompDeathSoundLoop.size()-1; l >= 0; l--){
					if(!game.chainChompDeathSoundLoop.get(l).clipIsActive())
						game.chainChompDeathSoundLoop.get(l).continuePlaying();
				}
				game.setChainChompDeathSoundPauseBoolean(false);
			}
		}
		else {
			y += velY;
			if(velY != 0)
				x += velX;
			else
				velX = 0;
			if(y < -118) {
				removeChain();
				game.ee.remove(this);
			}
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
			if(!game.ea.isEmpty()) {
				for(int i = 0; i < game.ea.size(); i++){
					EntityA tempEnt = game.ea.get(i);
					if(Physics.Collision(this, tempEnt)){
						game.fireballSplash(tempEnt.getX(), tempEnt.getY());
						game.ea.remove(tempEnt);
						Game.soundFireballPop();
						//MAKESOUND
						return;
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
					if((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1){
							xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
							ebMarked = i;
					}
					if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){
						if(!tempEnt.enemyType().equals("Bowser")) {
//							if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
//								this.itemSoundLoop.play();
//								this.itemSoundLoop.setSoundLoopBoolean(true);
//							}
							//game.eb.remove(i);
							tempEntB = game.eb.get(i);
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
							if(game.getWaitToPause() < System.currentTimeMillis()) {
								keepChomping = true;
								if(game.eb.get(i).getX() >= this.x + 15)
									chompingRight = true;
								else
									chompingLeft = true;
								HUD.currentScoreFromChainChomp = "null0";
								Game.currentEECollisionX = this.x-6;
								Game.currentEECollisionY = this.y-10;
								Game.currentEECollisionWidth = this.getBounds().getWidth();
								game.setEnemyHitPauseTimer(System.currentTimeMillis() + 1500);
								game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
								game.setWaitToPause(System.currentTimeMillis() + 4000);
							}else {
								HUD.currentScoreFromChainChomp = "200";
								Game.currentEECollisionX = this.x-6;
								Game.currentEECollisionY = this.y-10;
								Game.currentEECollisionWidth = this.getBounds().getWidth();
								game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
								game.setWaitToPause(System.currentTimeMillis() + 2000);
								setScoreFollowersFalse();
							}
							ebMarked = -1;
						}
						else {
							HUD.HEALTH -= 4;
							chainChompisDead = true;
							//MAKE EXPLOSION SOUND
							if(this.chainChompDeathSoundLoop.getSoundLoopBoolean() == false){
								for(int j = game.chainChompDeathSoundLoop.size(); j > 0; j--){
									if(game.chainChompDeathSoundLoop.get(j-1) != null && !game.chainChompDeathSoundLoop.get(j-1).clipIsActive()){
										game.chainChompDeathSoundLoop.remove(j-1);
										//j--;
									}
								}	
								for(int k = 0; k < game.chainChompDeathSoundLoop.size() || k == 0; k++){
									if(game.chainChompDeathSoundLoop.isEmpty())
										game.chainChompDeathSoundLoop.add(this.chainChompDeathSoundLoop);
									else if (game.chainChompDeathSoundLoop.get(k) == game.chainChompDeathSoundLoop.getLast()){
										game.chainChompDeathSoundLoop.add(this.chainChompDeathSoundLoop);
										k++;
									}else if(this.chainChompDeathSoundLoop.getVolume() -1.5f >= this.chainChompDeathSoundLoop.minimumVolume())
										this.chainChompDeathSoundLoop.reduceSound(1.5f);
								}
								this.chainChompDeathSoundLoop.setSoundLoopBoolean(true);
								game.chainChompDeathSoundLoop.getLast().play();
							}
						}
					}
				}
			}
			if(!game.ec.isEmpty()){
				if(ecMarked != -1 && ecMarked < game.ec.size() && game.ec.get(ecMarked).getY() < this.getY()){
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
					if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
						tempEntC = game.ec.get(i);
//						if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
//							this.itemSoundLoop.play();
//							this.itemSoundLoop.setSoundLoopBoolean(true);
//						}
//						if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
//							HUD.currentScoreFromChainChomp = "500";
//							game.getHUD().setScore(500);
//						}
//						else if(tempEnt.entityName().equals("BulletBill")) {
//							HUD.currentScoreFromChainChomp = "200";
//							game.getHUD().setScore(500);
//						}
						if(game.getWaitToPause() < System.currentTimeMillis()) {
							keepChomping = true;
							if(game.ec.get(i).getX() >= this.x + 15)//+20
								chompingRight = true;
							else// if(game.ec.get(i).getX() <= this.x + 10
								chompingLeft = true;
							HUD.currentScoreFromChainChomp = "null0";
							//game.ec.remove(i);
							Game.currentEECollisionX = this.x-6;
							Game.currentEECollisionY = this.y-10;
							Game.currentEECollisionWidth = this.getBounds().getWidth();
							game.setEnemyHitPauseTimer(System.currentTimeMillis() + 1500);
							game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
							game.setWaitToPause(System.currentTimeMillis() + 4000);
						}else {
							Game.currentEECollisionX = this.x-6;
							Game.currentEECollisionY = this.y-10;
							Game.currentEECollisionWidth = this.getBounds().getWidth();
							game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
							game.setWaitToPause(System.currentTimeMillis() + 2000);
							setScoreFollowersFalse();
							if(tempEntC.entityName().equals("BuzzyBeetleShell")) 
								HUD.currentScoreFromChainChomp = "500";
							else if(tempEntC.entityName().equals("BulletBill")) 
								HUD.currentScoreFromChainChomp = "200";
							else 
								HUD.currentScoreFromChainChomp = "200";
							
						}
						ecMarked = -1;
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
					if(tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmbShrapnel1") ||
							tempEnt.entityName().equals("bombOmbShrapnel2") ||tempEnt.entityName().equals("bombOmb")	) {
						game.ee.get(i).setEntityEDead(true);
					}
				}
			}
			anim.runAnimation();
		}
	}
	
	public void render(Graphics g){
		if(g2d == null)
			g2d = (Graphics2D)g.create();
		if(keepChomping && Game.isPaused()) {
			if(!silenceSoundLoop.getSoundLoopBoolean() && !silenceSoundLoop.clipIsActive()) {
				silenceSoundLoop.setSoundLoopBoolean(true);
				silenceSoundLoop.play();
				if(!Game.itemPauseSoundLoop.clipIsActive()) {
					Game.itemPauseSoundLoop.setFramePosition(0);
					Game.itemPauseSoundLoop.play();
				}
			}
			int i = 0;
			if(silenceSoundLoop.getLongFramePosition() != 0)
				i = (int)silenceSoundLoop.getLongFramePosition() / (int)(2368);//(4736);
			if((animChompinL.getCount() == 5 || animChompinR.getCount() == 5) && (tempEntC != null || tempEntB != null)) {
				if(tempEntB != null) {
					if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
						this.itemSoundLoop.play();
						this.itemSoundLoop.setSoundLoopBoolean(true);
					}
					HUD.currentScoreFromChainChomp = "";
					game.getHUD().setScore(200);
					chomped = new ChompFX(game,tempEntB.getX(),tempEntB.getY(),tempEntB.enemyType());
					game.getController().addEntity(chomped);
					game.getController().removeEntity(tempEntB);
				}
				if(tempEntC != null) {
					if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
						this.itemSoundLoop.play();
						this.itemSoundLoop.setSoundLoopBoolean(true);
					}
					if(tempEntC.entityName().equals("BuzzyBeetleShell")) {
						HUD.currentScoreFromChainChomp = "500";
						game.getHUD().setScore(500);
					}
					else if(tempEntC.entityName().equals("BulletBill")) {
						HUD.currentScoreFromChainChomp = "200";
						game.getHUD().setScore(200);
					}
					else {
						HUD.currentScoreFromChainChomp = "200";
						game.getHUD().setScore(200);
					}
					chomped = new ChompFX(game,tempEntC.getX(),tempEntC.getY(),tempEntC.entityName());
					game.getController().addEntity(chomped);
					game.getController().removeEntity(tempEntC);
				}
				tempEntB = null;
				tempEntC = null;
			}
//			if(System.currentTimeMillis() % 60 == 0 && (animChompinL.getCount() != 10 || animChompinR.getCount() != 10)) {
//				this.animChompinL.runAnimation();
//				this.animChompinR.runAnimation();
//			}
			if(i != silenceTracker && (animChompinL.getCount() != 10 || animChompinR.getCount() != 10)) {
				this.animChompinL.runAnimation();
				this.animChompinR.runAnimation();
				this.animChompinL.nextFrame();
				this.animChompinR.nextFrame();
				this.animChompinL.setCount(i);
				this.animChompinR.setCount(i);
			}
			if(animChompinL.getCount() == 10 || animChompinR.getCount() == 10) {
				anim.drawAnimation(g, x, y, 0);
				if(chompingLeft)
					chompingLeft = false;
				if(chompingRight)
					chompingRight = false;
			}
			else {
				if(chompingLeft)
					this.animChompinL.drawAnimation(g, x, y, 0);
				else if(chompingRight)
					this.animChompinR.drawAnimation(g, x, y, 0);
				else
					anim.drawAnimation(g, x, y, 0);
			}
			if(silenceTracker != i)
				silenceTracker = i;
		}
		else {
			if(chainChompisDead)
				animDisintegrate.drawAnimation(g, x, y, 0);
			else
				anim.drawAnimation(g, x, y, 0);
		}
		if(game.isPaused()){
			if(game.getChainChompDeathSoundPauseBoolean() == false){
				for(int i = 0; i < game.chainChompDeathSoundLoop.size();i++){
					if(game.chainChompDeathSoundLoop.get(i).clipIsActive())
						game.chainChompDeathSoundLoop.get(i).stop();
				}
				game.setChainChompDeathSoundPauseBoolean(true);
			}
		}
	}
	
	private void removeChain() {
		int tracker = 0;
		for(int i = 0; i < game.ef.size(); i++) {
			EntityF tempEnt = game.ef.get(i);
			if(tempEnt.entityName().equals("chainChompChainFX")) {
				tracker++;
				//if(chainChompNumber - (int)Math.floor(tracker/3) == 0) {
				if(chainChompBall1 == tempEnt) {
					game.ef.remove(i);
					if(!game.ef.isEmpty()) {
						if(game.ef.get(i).entityName().equals("chainChompChainFX")) {
							game.ef.remove(i);
							if(!game.ef.isEmpty()) {
								if(game.ef.get(i).entityName().equals("chainChompChainFX")) {
									game.ef.remove(i);
								}
							}
						}
					}
					break;
				}
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
		return new Rectangle((int)x, (int)y, 30, 28);
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
		return "chainChomp";
	}
	
	public void setHitIndicator(boolean b) {
		return;
	}
	
	public boolean getHitIndicator() {
		return false;
	}
	
	public void setEntityEDead(boolean dead) {
		if(dead) 
			removeChain();
		this.chainChompisDead = dead;
	}
	
	public boolean getEntityEDead() {
		return chainChompisDead;
	}
	
	public void close() {
		itemSoundLoop.close();
		chainChompDeathSoundLoop.close();
	}
}
