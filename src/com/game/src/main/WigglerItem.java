package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class WigglerItem extends GameObject implements EntityE   {
	
	Textures tex;
	Game game;
	Animation headNormalL;
	Animation headNormalR;
	Animation headMadL;
	Animation headMadR;
	Animation madColorChangeL;
	Animation madColorChangeR;
	Animation headWithoutFlowerL;
	Animation headWithoutFlowerR;
	SoundLoops deathSound;
	private int xDestination = 10000;
	private int ebMarked = -1;
	private int setup = 0;
	private double velX = 0;
	private double velY = 0;
	private double velYDead = 0;
	private long wigglerHitEnemyPauseTimer = 0;
	private long wigglerDeadTimer = 0;
	private long wigglerVelXSwitchTimer1 = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private boolean flipAround = false;
	private boolean angry = false;
	private boolean dead = false;
	private boolean angryDeathAnimation = false;
	private boolean scoreFollowMe = false;
	private boolean angryAndNotDead = false;
	
	FlowerFX flower;
	
	public WigglerItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		y = 0;
		if(!game.eb.isEmpty()) {
			for(int i = 0; i < game.eb.size(); i ++){
				EntityB tempEnt = game.eb.get(i);
				if(tempEnt.getY() > y)
					y = tempEnt.getY();
			}
		}
		if(y == 0 && !game.ec.isEmpty()) {
			for(int i = 0; i < game.ec.size(); i ++){
				EntityC tempEnt = game.ec.get(i);
				if(tempEnt.getY() > y)
					y = tempEnt.getY();
			}
		}
		if(y == 0)
			y = 100;
		else
			y+=30;
		Random rand = new Random();
		setup = rand.nextInt(2);
		if(setup == 0) {
			x = 1;
			velX = 0.7;
			game.getController().addEntity(new WigglerBody(0-28,y, tex, game,4,setup));
			game.getController().addEntity(new WigglerBody(0-21,y, tex, game,3,setup));
			game.getController().addEntity(new WigglerBody(0-14,y, tex, game,2,setup));
			game.getController().addEntity(new WigglerBody(0-7,y, tex, game,1,setup));
		}
		else if(setup == 1) {
			x = (Game.WIDTH * Game.SCALE) -16;
			velX = -0.7;
			game.getController().addEntity(new WigglerBody((Game.WIDTH * Game.SCALE) + 28-15,y, tex, game,4,setup));
			game.getController().addEntity(new WigglerBody((Game.WIDTH * Game.SCALE) + 21-15,y, tex, game,3,setup));
			game.getController().addEntity(new WigglerBody((Game.WIDTH * Game.SCALE) + 14-15,y, tex, game,2,setup));
			game.getController().addEntity(new WigglerBody((Game.WIDTH * Game.SCALE) + 7-15,y, tex, game,1,setup));
		}
		this.x = x;
		this.y = y;
		headNormalL = new Animation(7, tex.wigglerItemHead[0], tex.wigglerItemHead[1], tex.wigglerItemHead[2]);
		headNormalR = new Animation(7, tex.wigglerItemHead[3],tex.wigglerItemHead[4], tex.wigglerItemHead[5]);
		headMadL = new Animation(7, tex.wigglerItemHeadMad[0], tex.wigglerItemHeadMad[1], tex.wigglerItemHeadMad[2]);
		headMadR = new Animation(7, tex.wigglerItemHeadMad[3],tex.wigglerItemHeadMad[4], tex.wigglerItemHeadMad[5]);
		madColorChangeL = new Animation(6,tex.wigglerItemMadColorChangeL[0],tex.wigglerItemMadColorChangeL[1],tex.wigglerItemMadColorChangeL[2],
				tex.wigglerItemMadColorChangeL[3],tex.wigglerItemMadColorChangeL[4]);
		madColorChangeR = new Animation(6,tex.wigglerItemMadColorChangeR[0],tex.wigglerItemMadColorChangeR[1],tex.wigglerItemMadColorChangeR[2],
				tex.wigglerItemMadColorChangeR[3],tex.wigglerItemMadColorChangeR[4]);
		headWithoutFlowerL = new Animation(6,tex.wigglerItemHeadWithoutFlower[0],tex.wigglerItemHeadWithoutFlower[1],
				tex.wigglerItemHeadWithoutFlower[2]);
		headWithoutFlowerR = new Animation(6,tex.wigglerItemHeadWithoutFlower[3],tex.wigglerItemHeadWithoutFlower[4],
				tex.wigglerItemHeadWithoutFlower[5]);
		headNormalL.nextFrame();
		headNormalL.setCount(0);
		headNormalR.nextFrame();
		headNormalR.setCount(0);
		headMadL.nextFrame();
		headMadL.setCount(0);
		headMadR.nextFrame();
		headMadR.setCount(0);
		madColorChangeL.nextFrame();
		madColorChangeL.setCount(0);
		madColorChangeR.nextFrame();
		madColorChangeR.setCount(0);
		headWithoutFlowerL.nextFrame();
		headWithoutFlowerL.setCount(0);
		headWithoutFlowerR.nextFrame();
		headWithoutFlowerR.setCount(0);
		
		String itemEnemyFile = "res/Sounds/SFX/sm64_wiggler_hit.wav";
		SoundLoops itemEnemySoundLoops = new SoundLoops(itemEnemyFile);
		VolumeSlider.adjustSFX(itemEnemySoundLoops);
		this.deathSound = itemEnemySoundLoops;
	}

	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(wigglerDeadTimer != 0)
					wigglerDeadTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(scoreFollowMe) {
			if(game.getHUD().getEnemyHitPauseTimer() < System.currentTimeMillis()) {
				scoreFollowMe = false;
				Game.scoreFollowingBoolean = false;
			}
			Game.currentEECollisionX = this.x-6;
			Game.currentEECollisionY = this.y-10;
			Game.currentEECollisionWidth = this.getBounds().getWidth();
		}
		if(angryAndNotDead) {
			if(!dead) {
				game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
				game.setWaitToPause(System.currentTimeMillis() + 2000);
				game.setEnemyHitPauseTimer(System.currentTimeMillis() + 1000);
				wigglerHitEnemyPauseTimer = System.currentTimeMillis() + 1000;
				angryAndNotDead = false;
			}
			else
				angryAndNotDead = false;
		}
		if(dead) {
			if(!deathSound.getSoundLoopBoolean()) {
				if(!deathSound.clipIsActive())
					deathSound.play();
				deathSound.setSoundLoopBoolean(true);
			}
			if(wigglerVelXSwitchTimer1 == 0) {
				wigglerVelXSwitchTimer1 = System.currentTimeMillis() + 200;
			}
			else if(wigglerVelXSwitchTimer1 < System.currentTimeMillis()) {
				velX *= -1;
				wigglerVelXSwitchTimer1 = 0;
			}
			headWithoutFlowerR.runAnimation();
			headWithoutFlowerL.runAnimation();
			if(System.currentTimeMillis() < wigglerDeadTimer) {
				y-=2;
			}
			else {
				if(velYDead < 4)
					velYDead+=0.1;
				else if(velYDead > 4)
					velYDead = 4;
				y+=velYDead;
			}
			if(game.getPlayer().getBounds().intersects(this.getBounds())) {
				Game.soundPop();
				game.getController().addEntity(new ChompFX(game,x+4,y,"Wiggler"));
				game.getController().removeEntity(this);
			}
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					Game.soundPop();
					game.getController().addEntity(new ChompFX(game,x+4,y,"Wiggler"));
					game.getController().removeEntity(this);
				}
			}
			for(int i = 0; i < game.ec.size(); i++) {
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					Game.soundPop();
					game.getController().addEntity(new ChompFX(game,x+4,y,"Wiggler"));
					game.getController().removeEntity(this);
				}
			}
			for(int i = 0; i < game.ee.size(); i++) {
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead() && !tempEnt.entityName().equals("wiggler") && !tempEnt.entityName().equals("wigglerBody")){
					Game.soundPop();
					game.getController().addEntity(new ChompFX(game,x+4,y,"Wiggler"));
					game.getController().removeEntity(this);
				}
			}
			if(y > Game.HEIGHT * Game.SCALE || y < -16 || x+16 < 0 || x > Game.WIDTH * Game.SCALE) {
				if(scoreFollowMe) {
					game.getHUD().setEnemyHitPauseTimer(0);
					scoreFollowMe = false;
					Game.scoreFollowingBoolean = false;
				}
				game.ee.remove(this);
			}
		}
		else if(angry) {
			if(!angryDeathAnimation) {
				if(velX < 0 && x < Game.WIDTH-50)
					flipAround = true;
				if(velX > 0 && x > Game.WIDTH+50)
					flipAround = true;
				angryDeathAnimation = true;
			}
			if(x+8 >= Game.WIDTH * Game.SCALE || x <= 0) {
				if(flipAround) {
					velX *= -1;
					flipAround = false;
				}
			}
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					if(Game.currentlySelectedFireball != 3) {
						game.ea.remove(tempEnt);
						Game.soundFireballPop();
					}
					else if(!dead){
						dead = true;
						setBodyAngryOrDead();
					}
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(!tempEnt.getEntityBDead() && ((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1)){
						xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
						ebMarked = i;
				}
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityBDead() == false){
					if(!tempEnt.enemyType().equals("Bowser")) {
						game.eb.get(i).setEntityBDead(true);
						xDestination = 10000;
						ebMarked = -1;
						HUD.currentScoreFromChainChomp = "";
						Game.currentEECollisionX = this.x-6;
						Game.currentEECollisionY = this.y-10;
						if(velX > 0) 
							Game.currentEECollisionWidth = this.getBounds().getWidth();
						else
							Game.currentEECollisionWidth = this.getBounds().getWidth()+20;
						game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
						game.setWaitToPause(System.currentTimeMillis() + 2000);
						setScoreFollowersFalse();
					}
					else {
						HUD.HEALTH -=8;
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
				}
			}

			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityCDead() == false){
					if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp")
							 || tempEnt.entityName().equals("Mechakoopa")) {
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else {
						game.ec.get(i).setEntityCDead(true);
						HUD.currentScoreFromChainChomp = "";
						HUD.currentScoreFromChainChompInt = 200;
						Game.currentEECollisionX = this.x-6;
						Game.currentEECollisionY = this.y-10;
						if(velX > 0) 
							Game.currentEECollisionWidth = this.getBounds().getWidth();
						else
							Game.currentEECollisionWidth = this.getBounds().getWidth()+20;
						game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
						game.setWaitToPause(System.currentTimeMillis() + 2000);
						setScoreFollowersFalse();
					}
				}
			}
			
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead()) { 
					if((tempEnt.entityName().equals("bombOmbShrapnel2") || tempEnt.entityName().equals("bombOmbShrapnel1"))){
						game.ee.remove(tempEnt);
					}
					else if(tempEnt.entityName().equals("chainChomp")) {
						//turn around
						//kill for now
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else if(tempEnt.entityName().equals("bulletBill")) {
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("bombOmb")) {
						game.ee.get(i).setEntityEDead(true);
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else if(tempEnt.entityName().equals("amp")) {
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else if(tempEnt.entityName().equals("cheepCheeps")) {
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("lakituFish")) {
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("lakituSpike")) {
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("lakituRedShell")) {
						game.ee.get(i).setEntityEDead(true);
					}
				}
			}
			headMadL.runAnimation();
			headMadR.runAnimation();
			x+=velX*3;
			y+=velY;
			/*
			if(ebMarked != -1 && ebMarked < game.eb.size() && game.eb.get(ebMarked).getY() < this.getY()){
				if(game.eb.get(ebMarked).getX() < this.getX())
					setBodyX(-2);
				else
					setBodyX(2);
				if(game.eb.get(ebMarked).getY() < this.getY())
					setBodyY(-2);
			}
			else if(!game.eb.isEmpty() && this.y < game.eb.get(0).getX()){
				Random rand = new Random();
				if(rand.nextInt(50) == 0)
					setBodyY(-4);
			}*/
			if(y > Game.HEIGHT * Game.SCALE || y < -16 || x+30 < 0 || x-30 > Game.WIDTH * Game.SCALE) {
				if(scoreFollowMe) {
					game.getHUD().setEnemyHitPauseTimer(0);
					scoreFollowMe = false;
					Game.scoreFollowingBoolean = false;
				}
				game.ee.remove(this);
			}
		}
		else {
			if(x+8 >= Game.WIDTH * Game.SCALE || x <= 0) {
				velX *= -1;
			}
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					addFlower();
					angry = true;
					HUD.currentScoreFromChainChomp = "null0";
					if(Game.currentlySelectedFireball != 3) {
						game.ea.remove(tempEnt);
						Game.soundFireballPop();
					}
					else if(!dead)
						dead = true;
					setBodyAngryOrDead();
				}
			}
			
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityBDead() == false){
					if(!tempEnt.enemyType().equals("Bowser")) {
						addFlower();
						angry = true;
						if(velX > 0) 
							Game.currentEECollisionWidth = this.getBounds().getWidth();
						else
							Game.currentEECollisionWidth = this.getBounds().getWidth()+20;
						Game.currentEECollisionX = this.x-6;
						Game.currentEECollisionY = this.y-10;
						HUD.currentScoreFromChainChomp = "200";
						setBodyAngryOrDead();
						game.eb.get(i).setEntityBDead(true);
					}
					else {
						HUD.HEALTH -=4;
						addFlower();
						angry = true;
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
				}
			}
			
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityCDead() == false){
					if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp")
							|| tempEnt.entityName().equals("Mechakoopa")) {
						addFlower();
						angry = true;
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else {
						game.ec.get(i).setEntityCDead(true);
						addFlower();
						angry = true;
						if(velX > 0) 
							Game.currentEECollisionWidth = this.getBounds().getWidth();
						else
							Game.currentEECollisionWidth = this.getBounds().getWidth()+20;
						Game.currentEECollisionX = this.x-6;
						Game.currentEECollisionY = this.y-10;
						HUD.currentScoreFromChainChomp = "200";
						setBodyAngryOrDead();
					}
				}
			}
			
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead()) { 
					if((tempEnt.entityName().equals("bombOmbShrapnel2") || tempEnt.entityName().equals("bombOmbShrapnel1"))){
						addFlower();
						angry = true;
						setBodyAngryOrDead();
						game.ee.remove(tempEnt);
					}
					else if(tempEnt.entityName().equals("chainChomp")) {
						//turn around
						//kill for now
						addFlower();
						angry = true;
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else if(tempEnt.entityName().equals("bulletBill")) {
						addFlower();
						angry = true;
						setBodyAngryOrDead();
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("bombOmb")) {
						game.ee.get(i).setEntityEDead(true);
						addFlower();
						angry = true;
						dead = true;
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else if(tempEnt.entityName().equals("cheepCheeps")) {
						addFlower();
						angry = true;
						setBodyAngryOrDead();
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("amp")) {
						addFlower();
						angry = true;
						dead = true;//new
						setBodyAngryOrDead();
						wigglerDeadTimer = System.currentTimeMillis() + 500;
					}
					else if(tempEnt.entityName().equals("lakituFish")) {
						addFlower();
						angry = true;
						setBodyAngryOrDead();
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("lakituSpike")) {
						addFlower();
						angry = true;
						setBodyAngryOrDead();
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("lakituRedShell")) {
						addFlower();
						angry = true;
						setBodyAngryOrDead();
						game.ee.get(i).setEntityEDead(true);
					}
				}
			}
			headNormalL.runAnimation();
			headNormalR.runAnimation();
			x+=velX;
			y+=velY;
			if(y > Game.HEIGHT * Game.SCALE || y < -16 || x+30 < 0 || x-30 > Game.WIDTH * Game.SCALE) {
				if(scoreFollowMe) {
					game.getHUD().setEnemyHitPauseTimer(0);
					scoreFollowMe = false;
					Game.scoreFollowingBoolean = false;
				}
				game.ee.remove(this);
			}
		}
	}

	public void render(Graphics g) {
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && System.currentTimeMillis() < this.wigglerDeadTimer){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() < wigglerHitEnemyPauseTimer) {
			if(velX > 0)
				madColorChangeR.drawAnimation(g, x-12, y, 0);
			else
				madColorChangeL.drawAnimation(g, x, y, 0);
			madColorChangeL.runAnimation();
			madColorChangeR.runAnimation();
			if(velX > 0) 
				Game.currentEECollisionWidth = this.getBounds().getWidth();
			else
				Game.currentEECollisionWidth = this.getBounds().getWidth()+20;
			Game.currentEECollisionX = this.x-6;
			Game.currentEECollisionY = this.y-10;
			HUD.currentScoreFromChainChomp = "200";
		}
		else if((angry && !dead) || angryDeathAnimation) {
			if(velX > 0)
				headMadR.drawAnimation(g, x, y, 0);
			else
				headMadL.drawAnimation(g, x, y, 0);
		}
		else if(!dead) {
			if(velX > 0)
				headNormalR.drawAnimation(g, x, y, 0);
			else
				headNormalL.drawAnimation(g, x, y, 0);
		}
		else {
			if(velX > 0)
				headWithoutFlowerR.drawAnimation(g, x, y, 0);
			else
				headWithoutFlowerL.drawAnimation(g, x, y, 0);
		}
//		if(angry) 
//			floppyDead.drawAnimation(g, x, y, 0);
//		else
//			floppy.drawAnimation(g, x, y, 0);
	}
	
	public void addFlower() {
		if(!angry) {
			if(velX < 0)
				flower = new FlowerFX(x,y, tex, game,0);
			else
				flower = new FlowerFX(x,y, tex, game,1);
			game.getController().addEntity(flower);
			y+=8;
		}
	}
	
	public void setBodyAngryOrDead() {
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE) {
				if(e-1 < game.ee.size() && e-1 >= 0 && game.ee.get(e-1).entityName().equals("wigglerBody"))
					game.ee.get(e-1).setEntityEDead(true);
				if(e-2 < game.ee.size() && e-2 >= 0 && game.ee.get(e-2).entityName().equals("wigglerBody"))
					game.ee.get(e-2).setEntityEDead(true);
				if(e-3 < game.ee.size() && e-3 >= 0 && game.ee.get(e-3).entityName().equals("wigglerBody"))
					game.ee.get(e-3).setEntityEDead(true);
				if(e-4 < game.ee.size() && e-4 >= 0 && game.ee.get(e-4).entityName().equals("wigglerBody"))
					game.ee.get(e-4).setEntityEDead(true);
			}
		}
		if(angry && !dead && !angryAndNotDead) {
			angryAndNotDead = true;
//			game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
//			game.setWaitToPause(System.currentTimeMillis() + 2000);
//			game.setEnemyHitPauseTimer(System.currentTimeMillis() + 1000);
//			wigglerHitEnemyPauseTimer = System.currentTimeMillis() + 1000;
		}
	}
	
	public void setBodyX(int x) {
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE) {
				if(e-1 < game.ee.size() && e-1 >= 0 && game.ee.get(e-1).entityName().equals("wigglerBody"))
					game.ee.get(e-1).setX(game.ee.get(e-1).getX()+x);
				if(e-2 < game.ee.size() && e-2 >= 0 && game.ee.get(e-2).entityName().equals("wigglerBody"))
					game.ee.get(e-2).setX(game.ee.get(e-2).getX()+x);
				if(e-3 < game.ee.size() && e-3 >= 0 && game.ee.get(e-3).entityName().equals("wigglerBody"))
					game.ee.get(e-3).setX(game.ee.get(e-3).getX()+x);
				if(e-4 < game.ee.size() && e-4 >= 0 && game.ee.get(e-4).entityName().equals("wigglerBody"))
					game.ee.get(e-4).setX(game.ee.get(e-4).getX()+x);
			}
		}
		this.x+=x;
	}
	
	public void setBodyY(int y) {
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE) {
				if(e-1 < game.ee.size() && e-1 >= 0 && game.ee.get(e-1).entityName().equals("wigglerBody"))
					game.ee.get(e-1).setY(game.ee.get(e-1).getY()+y);
				if(e-2 < game.ee.size() && e-2 >= 0 && game.ee.get(e-2).entityName().equals("wigglerBody"))
					game.ee.get(e-2).setY(game.ee.get(e-2).getY()+y);
				if(e-3 < game.ee.size() && e-3 >= 0 && game.ee.get(e-3).entityName().equals("wigglerBody"))
					game.ee.get(e-3).setY(game.ee.get(e-3).getY()+y);
				if(e-4 < game.ee.size() && e-4 >= 0 && game.ee.get(e-4).entityName().equals("wigglerBody"))
					game.ee.get(e-4).setY(game.ee.get(e-4).getY()+y);
			}
		}
		this.y+=y;
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
	
	public void checkForDead() {
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE) {
				if(e-1 < game.ee.size() && e-1 >= 0 && game.ee.get(e-1).entityName().equals("wigglerBody")) {
					if(game.ee.get(e-1).getEntityEDead() == true)
						this.dead = true;
				}
				if(e-2 < game.ee.size() && e-2 >= 0 && game.ee.get(e-2).entityName().equals("wigglerBody")) {
					if(game.ee.get(e-2).getEntityEDead() == true)
						this.dead = true;
				}
				if(e-3 < game.ee.size() && e-3 >= 0 && game.ee.get(e-3).entityName().equals("wigglerBody")) {
					if(game.ee.get(e-3).getEntityEDead() == true)
						this.dead = true;
				}
				if(e-4 < game.ee.size() && e-4 >= 0 && game.ee.get(e-4).entityName().equals("wigglerBody")) {
					if(game.ee.get(e-4).getEntityEDead() == true)
						this.dead = true;
				}
			}
		}
	}
	
	public Rectangle getBounds() {
//		if(floppy.getCount() == 1)
//			return new Rectangle((int)x, (int)y, 16, 15);
//		else
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
		return "wiggler";
	}

	public void setHitIndicator(boolean b) {
		return;
	}

	public boolean getHitIndicator() {
		return false;
	}
	
	public void setEntityEDead(boolean dead) {
		if(!this.angry) {
			addFlower();
			this.angry = dead;
			checkForDead();
		}
		else {
			this.dead = dead;
		}
		setBodyAngryOrDead();
	}
	
	public boolean getEntityEDead() {
		return dead;
	}
	
	public void close() {
		deathSound.close();
	}

}
