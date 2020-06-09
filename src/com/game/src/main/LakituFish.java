package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;
import com.game.src.main.libs.Animation;

public class LakituFish  extends GameObject implements EntityE{
	private Textures tex;
	private Game game;
	private Animation fishL;
	private Animation fishR;
	private Animation fishDeadL;
	private Animation fishDeadR;
	private SoundLoops deadSound;
	private double velX = 0;
	private double velY = 0;
	private long popOffTimer = 0;
	private long deadPopOffTimer = 0;
	private long waitToDie = 0;
	private long bounceVelocityTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private long flickerTimer1 = 0;									
	private long flickerTimer2 = 0;									
	private boolean velXBoolean = false;
	private boolean dead = false;
	private boolean hitIndicator = false;
	public LakituFish(double x, double y,Textures tex, Game game, double velX) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.velX = velX;
		fishL = new Animation(6, tex.lakituFish[0],tex.lakituFish[1]);
		fishR = new Animation(6, tex.lakituFish[2],tex.lakituFish[3]);
		fishDeadL = new Animation(6, tex.lakituFish[4],tex.lakituFish[4]);
		fishDeadR = new Animation(6, tex.lakituFish[5],tex.lakituFish[5]);
		
		if(velX < 0) {
			
		}
		else {
		}

		fishL.nextFrame();
		fishL.setCount(0);
		fishR.nextFrame();
		fishR.setCount(0);
		fishDeadL.nextFrame();
		fishDeadL.setCount(0);
		fishDeadR.nextFrame();
		fishDeadR.setCount(0);

		String deathFile = "res/Sounds/SFX/smw_kick.wav";
		SoundLoops deathSoundLoop = new SoundLoops(deathFile);
		VolumeSlider.adjustSFX(deathSoundLoop);
		this.deadSound = deathSoundLoop;
		
		popOffTimer = System.currentTimeMillis() + 200;
		waitToDie = System.currentTimeMillis() + 600;
	}

	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(waitToDie != 0)
					waitToDie+=pausedTimerAddition2;
				if(popOffTimer != 0)
					popOffTimer+=pausedTimerAddition2;
				if(deadPopOffTimer != 0)
					deadPopOffTimer+=pausedTimerAddition2;
				if(bounceVelocityTimer != 0)
					bounceVelocityTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(System.currentTimeMillis() < popOffTimer || System.currentTimeMillis() < deadPopOffTimer) {
			if(velY > -2.5) {
				velY-=0.15;
			}
		}
		else {
			if(velY < 20)
				velY+=0.1;
		}
		
//		if(velXBoolean == false) {
//			velX-=0.05;
//			if(velX <= -0.4)
//				velXBoolean = true;
//		}
//		else {
//			velX+=0.05;
//			if(velX >= 0.4)
//				velXBoolean = false;
//		}
		if(game.getPlayer().getBounds().intersects(this.getBounds())) {
			//ADD SFX
			if(dead && deadPopOffTimer < System.currentTimeMillis()) {
				Game.soundPop();
				game.getController().addEntity(new ChompFX(game,x+4,y,"Fish"));
				game.getController().removeEntity(this);
			}
			else {
				//kill player
			}
		}
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this,tempEnt)) {
				if(dead && deadPopOffTimer < System.currentTimeMillis()) {
					Game.soundPop();
					if(Game.currentlySelectedFireball != 3) {
						game.ea.removeLast();
						Game.soundFireballPop();
					}
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Fish"));
					game.getController().removeEntity(this);
				}
				else {
					//ADD SFX
					deadBounce(tempEnt.getX());
					this.setEntityEDead(true);
				}
			}
		}
		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
			if(Physics.Collision(this,tempEnt) && !tempEnt.getEntityBDead()) {
				if(dead && deadPopOffTimer < System.currentTimeMillis()) {
					if(!tempEnt.enemyType().equals("Bowser")) {
						tempEnt.setEntityBDead(true);
					}
					else {
						Game.soundPop();
						HUD.HEALTH -= 8;
						game.getController().removeEntity(this);
					}
				}
				else {
					//ADD SFX
					deadBounce(tempEnt.getX());
					if(!tempEnt.enemyType().equals("Bowser")) {
						tempEnt.setEntityBDead(true);
					}
					else if(!dead && deadPopOffTimer < System.currentTimeMillis()){
						HUD.HEALTH -= 8;
					}
					this.setEntityEDead(true);
				}
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(Physics.Collision(this,tempEnt)) {
				if(dead && deadPopOffTimer < System.currentTimeMillis()) {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Fish"));
					if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
						Game.soundPop();
						game.getController().removeEntity(this);
					}
				}
				else {
					//ADD SFX
					deadBounce(tempEnt.getX());
					if(!tempEnt.entityName().equals("BuzzyBeetleShell")) {
						game.ec.get(i).setEntityCDead(true);
					}
					this.setEntityEDead(true);
				}
			}
		}
		for(int i = 0; i < game.ee.size(); i++) {
			EntityE tempEnt = game.ee.get(i);
			if(game.ee.get(i).getBounds().intersects(this.getBounds())) {
				if(tempEnt.entityName().equals("lakitu")){
					liveBounce(tempEnt.getX(),tempEnt.getY(),tempEnt.getBounds().getWidth());
					//game.getController().addEntity(new ChompFX(game,x+4,y,"Fish"));
				}
				else if(dead && (tempEnt.entityName().equals("wiggler") || tempEnt.entityName().equals("wigglerBody"))) {
					game.getController().addEntity(new ChompFX(game,x+4,y,"Fish"));
					if(dead && deadPopOffTimer < System.currentTimeMillis()) {
						Game.soundPop();
						game.getController().removeEntity(this);
					}
					else {
						//ADD SFX
						deadBounce(tempEnt.getX());
					}
				}
				else if(tempEnt.getEntityEDead() == false && (tempEnt.entityName().equals("amp") || tempEnt.entityName().equals("chainChomp")
						 || (tempEnt.entityName().equals("lakituSpike") && popOffTimer < System.currentTimeMillis())) ){
					game.getController().addEntity(new ChompFX(game,x+4,y,"Fish"));
					if(dead && deadPopOffTimer < System.currentTimeMillis()) {
						Game.soundPop();
						game.getController().removeEntity(this);
					}
					else {
						//ADD SFX
						deadBounce(tempEnt.getX());
						this.setEntityEDead(true);
					}
					
				}else if(tempEnt.getEntityEDead() == false && (tempEnt.entityName().equals("bombOmb") || tempEnt.entityName().equals("bulletBill") ||
						tempEnt.entityName().equals("cheepCheeps") || (tempEnt.entityName().equals("lakituRedShell") && popOffTimer < System.currentTimeMillis())) ){
					game.ee.get(i).setEntityEDead(true);
					game.getController().addEntity(new ChompFX(game,x+4,y,"Fish"));
					if(dead && deadPopOffTimer < System.currentTimeMillis()) {
						Game.soundPop();
						game.getController().removeEntity(this);
					}
					else {
						//ADD SFX
						deadBounce(tempEnt.getX());
						this.setEntityEDead(true);
					}
				}else if(tempEnt.getEntityEDead() == true && (tempEnt.entityName().equals("bombOmb") || tempEnt.entityName().equals("bulletBill")) ){
					Game.soundPop();
					game.getController().addEntity(new ChompFX(game,x+4,y,"Fish"));
					game.getController().removeEntity(this);
				}else if(tempEnt.getEntityEDead() == true && (tempEnt.entityName().equals("cheepCheeps"))){
					if(!dead) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"CheepCheeps"));
						game.ee.remove(i);
					}
				}
			}
			
		}
		if(dead) {
			if(!deadSound.getSoundLoopBoolean()) {
				if(!deadSound.clipIsActive())
					deadSound.play();
				deadSound.setSoundLoopBoolean(true);
			}
			if(velX < 0)
				fishDeadL.runAnimation();
			else
				fishDeadR.runAnimation();
		}
		else {
			if(velX < 0)
				fishL.runAnimation();
			else
				fishR.runAnimation();
		}
		y+=velY;
		x+=velX;

		if(Physics.Collision(this, game.getBb())){
			if(velY >= 12)
				this.setEntityEDead(true);
			if(!dead) {
				liveBounce(x,y,getBounds().width);
			}
			else if(System.currentTimeMillis() < deadPopOffTimer) {
				deadBounce(x);
			}
			else {
				game.getController().addEntity(new ChompFX(game,x+4,y+2,"Fish"));
				game.getController().removeEntity(this);
			}
			y -= velY/1.2;
			x-=velX/1.2;
			/*
			if(Physics.Collision(this, game.eb)){
				System.out.println("Collision detected!");
			}
			*/
		}
		if (y-getBounds().height >= Game.HEIGHT * Game.SCALE){
			game.getController().removeEntity(this);
		}
	}
	
	public void deadBounce(double tempEntX) {
		if(this.x + (this.getBounds().width/2) <= tempEntX) {
			if(velX < 0 && velX +0.4 < 0)
				velX+=0.4;
			else if(0 < velX)
				velX*=-1;
		}
		else {
			if(0 < velX && 0 < velX -0.4)
				velX-=0.4;
			else if(velX < 0)
				velX*=-1;
		}
	}
	
	public void liveBounce(double tempEntX, double tempEntY, double tempEntWidth) {
		if(bounceVelocityTimer < System.currentTimeMillis()) {
			if(this.y-5 < tempEntY) {
				if(0 < velY) {
					if(2 < velY)
						velY = -2;
					else
						velY*=-1;
				}
				y--;
				//velY--;
			}
			if(x < tempEntX+(tempEntWidth/2)) {
				x--;
			}
			else {
				x++;
			}
			bounceVelocityTimer = System.currentTimeMillis() + 100;
		}
	}
	
	public void render(Graphics g) {
		if(hitIndicator) {
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
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < popOffTimer || System.currentTimeMillis() < deadPopOffTimer || System.currentTimeMillis() < waitToDie || System.currentTimeMillis() < bounceVelocityTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		if(dead && !hitIndicator) {
			if(velX < 0)
				fishDeadL.drawAnimation(g, x, y, 0);
			else
				fishDeadR.drawAnimation(g, x, y, 0);
		}
		else {
			if(velX < 0)
				fishL.drawAnimation(g, x, y, 0);
			else
				fishR.drawAnimation(g, x, y, 0);
		}
	}

	public Rectangle getBounds() {
		if(dead) {
			return new Rectangle((int)x, (int)y, 15, 16);
		}
		else {
			if(velX < 0) {
				if(fishL.getCount() == 0)
					return new Rectangle((int)x, (int)y, 15, 16);
				else
					return new Rectangle((int)x, (int)y, 16, 16);
			}
			else{
				if(fishR.getCount() == 0)
					return new Rectangle((int)x, (int)y, 15, 16);
				else
					return new Rectangle((int)x, (int)y, 16, 16);
			}
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String entityName() {
		return "lakituFish";
	}

	public void close() {
		deadSound.close();
		//g2d.dispose();
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
		return;
	}

	public void setHitIndicator(boolean b) {
		this.hitIndicator = b;
	}

	public boolean getHitIndicator() {
		return hitIndicator;
	}

	public boolean getEntityEDead() {
		return dead;
	}

	public void setEntityEDead(boolean dead) {
		if(this.dead && deadPopOffTimer < System.currentTimeMillis()) {
			game.getController().addEntity(new ChompFX(game,x+(getBounds().width/2),y+(getBounds().height/2),"Fish"));
			game.getController().removeEntity(this);
			return;
		}
		if(dead) {
			velY = -1;
			deadPopOffTimer = System.currentTimeMillis() + 150;
			this.dead = dead;
		}
	}

}
