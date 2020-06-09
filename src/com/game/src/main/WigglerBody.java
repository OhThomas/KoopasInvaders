package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class WigglerBody extends GameObject implements EntityE   {
	
	Textures tex;
	Game game;
	Animation bodyNormalL;
	Animation bodyNormalR;
	Animation bodyMadL;
	Animation bodyMadR;
	private int bodyNumber = 0;
	private double velX = 0;
	private double velY = 0;
	private double velYDead = 0;
	private long velYTimer = 0;
	private long wigglerHitEnemyPauseTimer = 0;
	private long wigglerDeadTimer = 0;
	private long wigglerDeadDelayTimer = 0;
	private long wigglerVelXSwitchTimer1 = 0;
	private long wigglerVelXSwitchTimer2 = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private boolean velYBoolean = false;
	private boolean angry = false;
	private boolean dead = false;
	private boolean nowInScreen = false;
	private boolean angryDeathAnimation = false;
	private boolean scoreFollowMe = false;
	private boolean xCheck = false;
	
	public WigglerBody(double x, double y, Textures tex, Game game, int bodyNumber, int setup) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.bodyNumber = bodyNumber;
		if(setup == 0) {
			velX = 0.7;
		}
		else if(setup == 1) {
			velX = -0.7;
		}
		y+=15;
		this.x = x;
		this.y = y;
		Random rand = new Random();
		bodyNormalL = new Animation(6, tex.wigglerItemBody[0], tex.wigglerItemBody[1], tex.wigglerItemBody[2], tex.wigglerItemBody[3]);
		bodyNormalR = new Animation(6, tex.wigglerItemBody[4], tex.wigglerItemBody[5], tex.wigglerItemBody[6], tex.wigglerItemBody[7]);
		bodyMadL = new Animation(6, tex.wigglerItemBodyMad[0], tex.wigglerItemBodyMad[1], tex.wigglerItemBodyMad[2], tex.wigglerItemBodyMad[3]);
		bodyMadR = new Animation(6, tex.wigglerItemBodyMad[4], tex.wigglerItemBodyMad[5], tex.wigglerItemBodyMad[6], tex.wigglerItemBodyMad[7]);
		bodyNormalL.nextFrame();
		bodyNormalL.setCount(0);
		bodyNormalR.nextFrame();
		bodyNormalR.setCount(0);
		bodyMadL.nextFrame();
		bodyMadL.setCount(0);
		bodyMadR.nextFrame();
		bodyMadR.setCount(0);
		if(bodyNumber == 1) {
			velYTimer = 0;
		}
		else if(bodyNumber == 2) {
			velYTimer = System.currentTimeMillis() + 100;
			bodyNormalL.setCount(1);
			bodyNormalR.setCount(1);
			bodyMadL.setCount(1);
			bodyMadR.setCount(1);
		}
		else if(bodyNumber == 3) {
			velYTimer = System.currentTimeMillis() + 200;
			bodyNormalL.setCount(2);
			bodyNormalR.setCount(2);
			bodyMadL.setCount(2);
			bodyMadR.setCount(2);
		}else if(bodyNumber == 4) {
			velYTimer = System.currentTimeMillis() + 300;
			bodyNormalL.setCount(1);
			bodyNormalR.setCount(1);
			bodyMadL.setCount(1);
			bodyMadR.setCount(1);
		}
	}

	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(wigglerDeadDelayTimer != 0)
					wigglerDeadDelayTimer+=pausedTimerAddition2;
				if(wigglerDeadTimer != 0)
					wigglerDeadTimer+=pausedTimerAddition2;
				if(velYTimer != 0)
					velYTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(scoreFollowMe) {
			if(game.getHUD().getEnemyHitPauseTimer() < System.currentTimeMillis()) {
				scoreFollowMe = false;
				Game.scoreFollowingBoolean = false;
			}
			if(velX > 0)
				Game.currentEECollisionX = this.x-6+(7*bodyNumber);
			else
				Game.currentEECollisionX = this.x-6-(7*bodyNumber);
			Game.currentEECollisionY = this.y-10;
			Game.currentEECollisionWidth = this.getBounds().getWidth();
		}
		if(dead) {
			if(wigglerVelXSwitchTimer1 == 0) {
				wigglerVelXSwitchTimer1 = System.currentTimeMillis() + 200;
			}
			else if(wigglerVelXSwitchTimer1 < System.currentTimeMillis()) {
				velX *= -1;
				wigglerVelXSwitchTimer1 = 0;
			}
			if(System.currentTimeMillis() < wigglerDeadDelayTimer) {
				return;
			}
			else if(System.currentTimeMillis() < wigglerDeadTimer) {
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
			if(!angryDeathAnimation)
				angryDeathAnimation = true;
			if(xCheck)
				xCheck = false;
			if((x+8 >= Game.WIDTH * Game.SCALE || x <= 0)) {
				if((getHeadVelX() < 0 && velX > 0) || (getHeadVelX() > 0 && velX < 0))
					velX *= -1;
			}
//			if(nowInScreen == true && (x+8 >= Game.WIDTH * Game.SCALE || x <= 0)) {
//				velX *= -1;
//				y-=8;
//			}
//			else if(nowInScreen == false && (x+8 <= Game.WIDTH * Game.SCALE && x > 0))
//				nowInScreen = true;
			if(velYTimer < System.currentTimeMillis()) {
				if(velYBoolean == false) {
					velY-=0.05;
					if(velY <= -0.4)
						velYBoolean = true;
				}
				else {
					velY+=0.05;
					if(velY >= 0.4)
						velYBoolean = false;
				}
				if(velYTimer != 0)
					velYTimer = 0;
			}
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					if(Game.currentlySelectedFireball != 3) {
						game.ea.remove(tempEnt);
						Game.soundFireballPop();
					}
					else if(!dead){
						angry = true;
						dead = true;
						setHeadDead();
					}
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityBDead() == false){
					if(!tempEnt.enemyType().equals("Bowser")) {
						HUD.currentScoreFromChainChomp = "";
						if(velX > 0)
							Game.currentEECollisionX = this.x-6+(7*bodyNumber);
						else
							Game.currentEECollisionX = this.x-6-(7*bodyNumber);
						Game.currentEECollisionY = this.y-10;
						if(velX > 0) 
							Game.currentEECollisionWidth = this.getBounds().getWidth();
						else
							Game.currentEECollisionWidth = this.getBounds().getWidth()+20;
						game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
						game.setWaitToPause(System.currentTimeMillis() + 2000);
						setScoreFollowersFalse();
						game.eb.get(i).setEntityBDead(true);
					}
					else {
						HUD.HEALTH -=8;
						angry = true;
						dead = true;
						setHeadDead();
					}
				}
			}

			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityCDead() == false){
					if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp")
							|| tempEnt.entityName().equals("Mechakoopa")) {
						angry = true;
						dead = true;
						setHeadDead();
					}
					else {
						HUD.currentScoreFromChainChomp = "";
						if(velX > 0)
							Game.currentEECollisionX = this.x-6+(7*bodyNumber);
						else
							Game.currentEECollisionX = this.x-6-(7*bodyNumber);
						Game.currentEECollisionY = this.y-10;
						if(velX > 0) 
							Game.currentEECollisionWidth = this.getBounds().getWidth();
						else
							Game.currentEECollisionWidth = this.getBounds().getWidth()+20;
						game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 1300);
						game.setWaitToPause(System.currentTimeMillis() + 2000);
						setScoreFollowersFalse();
						game.ec.get(i).setEntityCDead(true);
					}
				}
			}
			
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(this == tempEnt) {
					if(i+bodyNumber < game.ee.size() && i+bodyNumber >= 0 && game.ee.get(i+bodyNumber).entityName().equals("wiggler") && game.ee.get(i+bodyNumber).getVelX() > 0 && velX > 0) {
						x = game.ee.get(i+bodyNumber).getX()-(7*bodyNumber);
						xCheck = true;
					}
					if(i+bodyNumber < game.ee.size() && i+bodyNumber >= 0 && game.ee.get(i+bodyNumber).entityName().equals("wiggler") && game.ee.get(i+bodyNumber).getVelX() < 0 && velX < 0) {
						x = game.ee.get(i+bodyNumber).getX()+(7*bodyNumber);
						xCheck = true;
					}
				}
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead()) { 
					if((tempEnt.entityName().equals("bombOmbShrapnel2") || tempEnt.entityName().equals("bombOmbShrapnel1"))){
						game.ee.remove(tempEnt);
					}
					else if(tempEnt.entityName().equals("chainChomp")) {
						//turn around
						angry = true;
						dead = true;
						setHeadDead();
					}
					else if(tempEnt.entityName().equals("bulletBill")) {
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("bombOmb")) {
						game.ee.get(i).setEntityEDead(true);
						angry = true;
						dead = true;
						setHeadDead();
					}
					else if(tempEnt.entityName().equals("amp")) {
						angry = true;
						dead = true;
						setHeadDead();
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
			bodyMadL.runAnimation();
			bodyMadR.runAnimation();
			if(xCheck == false)
				x+=velX*3;
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
		else {
			if(xCheck)
				xCheck = false;
			if(nowInScreen == true && (x+8 >= Game.WIDTH * Game.SCALE || x <= 0)) 
				velX *= -1;
			else if(nowInScreen == false && (x+8 <= Game.WIDTH * Game.SCALE && x > 0))
				nowInScreen = true;
			if(velYTimer < System.currentTimeMillis()) {
				if(velYBoolean == false) {
					velY-=0.05;
					if(velY <= -0.4)
						velYBoolean = true;
				}
				else {
					velY+=0.05;
					if(velY >= 0.4)
						velYBoolean = false;
				}
				if(velYTimer != 0)
					velYTimer = 0;
			}
			
//			if(velY < 2 && velY != 0)
//				velY+=0.5;
//			else if(velY == 2)
//				velY = 0;
//			else if(velY <= 0)
//				velY-=0.5;
//			else if(velY == -2)
//				velY = 0.5;
			
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					setHeadDead();
					if(Game.currentlySelectedFireball != 3) {
						game.ea.remove(tempEnt);
						Game.soundFireballPop();
					}
					else if(!dead){
						angry = true;
						dead = true;
						setHeadDead();
					}
				}
			}
			
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityBDead() == false){
					if(!tempEnt.enemyType().equals("Bowser")) {
						game.eb.get(i).setEntityBDead(true);
						setHeadDead();
					}
					else {
						HUD.HEALTH -=4;
						angry = true;
						dead = true;
						setHeadDead();
					}
				}
			}
			
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityCDead() == false){
					if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp")
							|| tempEnt.entityName().equals("Mechakoopa")) {
						angry = true;
						dead = true;
						setHeadDead();
					}
					else {
						game.ec.get(i).setEntityCDead(true);
						setHeadDead();
					}
				}
			}
			
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(this == tempEnt) {
					if(i+bodyNumber < game.ee.size() && i+bodyNumber >= 0 && game.ee.get(i+bodyNumber).entityName().equals("wiggler") && game.ee.get(i+bodyNumber).getVelX() > 0 && velX > 0) {
						x = game.ee.get(i+bodyNumber).getX()-(7*bodyNumber);
						xCheck = true;
					}
					if(i+bodyNumber < game.ee.size() && i+bodyNumber >= 0 && game.ee.get(i+bodyNumber).entityName().equals("wiggler") && game.ee.get(i+bodyNumber).getVelX() < 0 && velX < 0) {
						x = game.ee.get(i+bodyNumber).getX()+(7*bodyNumber);
						xCheck = true;
					}
				}
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead()) { 
					if((tempEnt.entityName().equals("bombOmbShrapnel2") || tempEnt.entityName().equals("bombOmbShrapnel1"))){
						setHeadDead();
						game.ee.remove(tempEnt);
					}
					else if(tempEnt.entityName().equals("chainChomp")) {
						//turn around
						//set dead for now
						setHeadDead();
					}
					else if(tempEnt.entityName().equals("bulletBill")) {
						setHeadDead();
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("bombOmb")) {
						game.ee.get(i).setEntityEDead(true);
						angry = true;
						dead = true;
						setHeadDead();
					}
					else if(tempEnt.entityName().equals("amp")) {
						setHeadDead();
					}
					else if(tempEnt.entityName().equals("cheepCheeps")) {
						setHeadDead();
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("lakituFish")) {
						setHeadDead();
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("lakituSpike")) {
						setHeadDead();
						game.ee.get(i).setEntityEDead(true);
					}else if(tempEnt.entityName().equals("lakituRedShell")) {
						setHeadDead();
						game.ee.get(i).setEntityEDead(true);
					}
				}
			}
			bodyNormalL.runAnimation();
			bodyNormalR.runAnimation();
			if(xCheck == false)
				x+=velX;
			y+=velY;
			if(y > Game.HEIGHT * Game.SCALE || y < -16 || x+50 < 0 || x-50 > Game.WIDTH * Game.SCALE) {
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
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < this.wigglerDeadDelayTimer || System.currentTimeMillis() < this.wigglerDeadTimer || System.currentTimeMillis() < velYTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() < this.wigglerHitEnemyPauseTimer && !dead) {
			return;
		}
		else if((angry && !dead) || angryDeathAnimation) {
			if(velX > 0)
				bodyMadR.drawAnimation(g, x, y, 0);
			else
				bodyMadL.drawAnimation(g, x, y, 0);
		}
		else {
			if(velX > 0)
				bodyNormalR.drawAnimation(g, x, y, 0);
			else
				bodyNormalL.drawAnimation(g, x, y, 0);
		}
//		if(angry) 
//			floppyDead.drawAnimation(g, x, y, 0);
//		else
//			floppy.drawAnimation(g, x, y, 0);
	}
	
	public void setHeadDead() {
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE) {
				if(e+bodyNumber < game.ee.size() && e+bodyNumber >= 0 && game.ee.get(e+bodyNumber).entityName().equals("wiggler")) {
					game.ee.get(e+bodyNumber).setEntityEDead(true);
					return;
				}
			}
		}
		return;
	}
	
	public double getHeadVelX() {
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE) {
				if(e+bodyNumber < game.ee.size() && e+bodyNumber >= 0 && game.ee.get(e+bodyNumber).entityName().equals("wiggler"))
					return game.ee.get(e+bodyNumber).getVelX();
			}
		}
		return 0;
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

	public Rectangle getBounds() {
		if(bodyNormalL.getCount() == 1)
			return new Rectangle((int)x, (int)y, 16, 15);
		else
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
		return "wigglerBody";
	}

	public void setHitIndicator(boolean b) {
		return;
	}

	public boolean getHitIndicator() {
		return false;
	}
	
	public void setEntityEDead(boolean dead) {
//		for(int i = 0; i < game.ee.size(); i++){
//			EntityE tempEnt = game.ee.get(i);
//		}
		for(int e = 0; e < game.ee.size(); e++){
			EntityE tempEntE = game.ee.get(e);
			if(this == tempEntE) {
				if(e+bodyNumber < game.ee.size() && e+bodyNumber >= 0 && game.ee.get(e+bodyNumber).entityName().equals("wiggler"))
					if(game.ee.get(e+bodyNumber).getEntityEDead() == true) {
						dead = true;
						this.wigglerDeadTimer = System.currentTimeMillis() + 500 + (this.bodyNumber * 250);
						this.wigglerDeadDelayTimer = System.currentTimeMillis() + (this.bodyNumber * 250);
					}
					else {
						dead = false;
						this.wigglerHitEnemyPauseTimer = System.currentTimeMillis() + 1000;
					}
			}
		}
		this.angry = true;
		this.dead = dead;
//		if(dead)
//			floppyDead.setCount(floppy.getCount());
	}
	
	public boolean getEntityEDead() {
//		return angry;
		return dead;
	}
	
	public void close() {
		
	}

}
