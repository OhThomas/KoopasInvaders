package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class Swoop extends GameObject implements EntityC {
	private Textures tex;
	private Game game;
	Animation batL;
	Animation batDeadL;
	Animation batR;
	Animation batDeadR;
	SoundLoops swoopSound;
	SoundLoops swoopHitSound;
	private long popOffTimer = 0;
	private long deadPopOffTimer = 0;
	private long bounceVelocityTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private long flickerTimer1 = 0;									
	private long flickerTimer2 = 0;		
	private double velX = 0;
	private double velY = 0;
	private boolean dead = false;
	private boolean bouncy = false;
	private boolean bouncySet = false;
	private boolean hitIndicator = false;
	public Swoop(double x, double y,Textures tex, Game game, double velX){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.velX = velX;
		Random rand = new Random();
		if (rand.nextInt(3) == 0)
			bouncy = true;
		batL = new Animation(6,tex.lakituBat[0],tex.lakituBat[1]);
		batR = new Animation(6,tex.lakituBat[3],tex.lakituBat[2]);
		batDeadL = new Animation(6,tex.lakituBat[4],tex.lakituBat[4]);
		batDeadR = new Animation(6,tex.lakituBat[7],tex.lakituBat[7]);

		String swoopFile = "res/Sounds/SFX/smw_swooper_no_echo.wav";
		SoundLoops swoopSoundLoop = new SoundLoops(swoopFile);
		VolumeSlider.adjustSFX(swoopSoundLoop);
		this.swoopSound = swoopSoundLoop;
		
		String swoopHitFile = "res/Sounds/SFX/swoophitsfx.wav";
		SoundLoops swoopHitSoundLoop = new SoundLoops(swoopHitFile);
		VolumeSlider.adjustSFX(swoopHitSoundLoop);
		this.swoopHitSound = swoopHitSoundLoop;
		
		swoopSound.play();
		popOffTimer = System.currentTimeMillis() + 200;
	}
	
	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(popOffTimer != 0)
					popOffTimer+=pausedTimerAddition2;
				if(deadPopOffTimer != 0)
					deadPopOffTimer+=pausedTimerAddition2;
				if(bounceVelocityTimer != 0)
					bounceVelocityTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(System.currentTimeMillis() < popOffTimer) {
			if(velY > -2.5) {
				velY-=0.15;
			}
		}
		else {
			if (game.getPlayer().y < y+80 && !dead) {
				if(game.getPlayer().y < y+10 && bouncy) {
					liveBounce(x,y,getBounds().width);
					bouncy = false;
					bouncySet = true;
				}
				if(!bouncySet) {
					if(3 < velY)
						velY-=0.5;
					else if(velY < 3)
						velY = 3;
				}
				else {
					if(3 < velY)
						velY-=0.005;
					else if(velY < 3)
						velY += .2;
				}
			}
			else {
				if(velY < 9)
					velY+=0.1;
			}
			if(!dead) {
				if(game.getPlayer().y < y+80) {
					if(game.getPlayer().x + (game.getPlayer().MARIO_WIDTH/2) < this.x+(this.getBounds().width/2)) {
						if(-5 < velX)
							velX-=0.3;
						else
							velX = -5;
					}
					else {
						if(velX < 5)
							velX+=0.3;
						else
							velX = 5;
					}
				}
				else {
					if(game.getPlayer().x + (game.getPlayer().MARIO_WIDTH/2) < this.x+(this.getBounds().width/2)) {
						if(-1 < velX)
							velX-=0.1;
						else
							velX = -1;
					}
					else {
						if(velX < 1)
							velX+=0.1;
						else
							velX = 1;
					}
				}
				for(int i = 0; i < game.ea.size(); i++){
					EntityA tempEnt = game.ea.get(i);
					if(Physics.Collision(this,tempEnt)) {
						if(dead && deadPopOffTimer < System.currentTimeMillis()) {
							Game.soundPop();
							if(Game.currentlySelectedFireball != 3)
								game.ea.removeLast();
							game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Swoop"));
							game.getController().removeEntity(this);
						}
						else if(!dead){
							//ADD SFX
							this.setEntityCDead(true);
						}
					}
				}
				for(int i = 0; i < game.ec.size(); i++){
					EntityC tempEnt = game.ec.get(i);
					if(Physics.Collision(this,tempEnt) && this != tempEnt && !tempEnt.entityName().equals("Swoop") && !tempEnt.getEntityCDead()) {
						if(tempEnt.entityName().equals("BulletBill")) {
							game.ec.get(i).setEntityCDead(true);
						}
						if(dead && deadPopOffTimer < System.currentTimeMillis()) {
							Game.soundPop();
							game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Swoop"));
							game.getController().removeEntity(this);
						}
						else if(!dead){
							//ADD SFX
							this.setEntityCDead(true);
						}
						
					}
				}
			}
		}
		if(game.getPlayer().getBounds().intersects(this.getBounds())) {
			//ADD SFX
			if(dead && deadPopOffTimer < System.currentTimeMillis()) {
				Game.soundPop();
				game.getController().addEntity(new ChompFX(game,x+4,y,"Swoop"));
				game.getController().removeEntity(this);
			}
			else {
				//kill player
			}
		}
		if(dead) {
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this,tempEnt)) {
					if(deadPopOffTimer < System.currentTimeMillis()) {
						Game.soundPop();
						if(Game.currentlySelectedFireball != 3) {
							Game.soundFireballPop();
							game.ea.removeLast();
						}
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Swoop"));
						game.getController().removeEntity(this);
					}
				}
			}
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this,tempEnt) && this != tempEnt && !tempEnt.entityName().equals("Swoop") && !tempEnt.getEntityCDead()) {
					if(tempEnt.entityName().equals("BulletBill")) {
						game.ec.get(i).setEntityCDead(true);
					}
					if(dead && deadPopOffTimer < System.currentTimeMillis()) {
						Game.soundPop();
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Swoop"));
						game.getController().removeEntity(this);
					}
					else if(!dead){
						//ADD SFX
						this.setEntityCDead(true);
					}
					
				}
			}
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this,tempEnt) && !tempEnt.getEntityEDead()) {
					if(tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmb") || tempEnt.entityName().equals("lakituBombOmb")) {
						game.ee.get(i).setEntityEDead(true);
					}
					if(deadPopOffTimer < System.currentTimeMillis()) {
						Game.soundPop();
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Swoop"));
						game.getController().removeEntity(this);
					}
				}
			}
		}
		x+=velX;
		y+=velY;
		
		if(Physics.Collision(this, game.getBb())){
			if(velY >= 12)
				this.setEntityCDead(true);
			if(!dead) {
				liveBounce(x,y,getBounds().width);
			}
			else if(System.currentTimeMillis() < deadPopOffTimer) {
				deadBounce(x);
			}
			else {
				Game.soundPop();
				game.getController().addEntity(new ChompFX(game,x+4,y+2,"Swoop"));
				game.getController().removeEntity(this);
			}
			y -= velY/1.2;
			x-=velX/1.2;
		}
		if (y-getBounds().height >= Game.HEIGHT * Game.SCALE){
			game.getController().removeEntity(this);
		}
		batL.runAnimation();
		batR.runAnimation();
		batDeadL.runAnimation();
		batDeadR.runAnimation();
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
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < popOffTimer || System.currentTimeMillis() < deadPopOffTimer 
				|| System.currentTimeMillis() < bounceVelocityTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		if(!dead || hitIndicator) {
			if(velX < 0)
				batL.drawAnimation(g, x, y, 0);
			else
				batR.drawAnimation(g, x, y, 0);
		}
		else {
			if(velX < 0)
				batDeadL.drawAnimation(g, x, y, 0);
			else
				batDeadR.drawAnimation(g, x, y, 0);
		}
	}

	public Rectangle getBounds() {
		if(dead) {
			return new Rectangle((int)x, (int)y, 13, 16);
		}
		else {
			if(velX < 0) {
				if(batL.getCount() == 0)
					return new Rectangle((int)x, (int)y, 13, 16);
				else
					return new Rectangle((int)x, (int)y, 16, 16);
			}
			else {
				if(batR.getCount() == 0)
					return new Rectangle((int)x, (int)y, 13, 16);
				else
					return new Rectangle((int)x, (int)y, 16, 16);
			}
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
		velY*=-1;
		if(velY < -3)
			velY = -3;
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
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return getBounds().width;
	}

	public int getHeight() {
		return getBounds().height;
	}

	public boolean getEntityCDead() {
		return dead;
	}

	public void setEntityCDead(boolean dead) {
		if(this.dead && deadPopOffTimer < System.currentTimeMillis()) {
			game.getController().addEntity(new ChompFX(game,x+4,y+2,"Swoop"));
			game.getController().removeEntity(this);
		}
		else {
			if(!swoopHitSound.getSoundLoopBoolean()) {
				swoopHitSound.play();
				swoopHitSound.setSoundLoopBoolean(true);
			}
			deadBounce(x+(getBounds().width/2));
			this.dead = dead;
			game.getHUD().setScore(200);
			deadPopOffTimer = System.currentTimeMillis() + 150;
		}
	}

	public String entityName() {
		return "Swoop";
	}

	public void renderFlicker() {
		this.hitIndicator = true;
	}

	public void close() {
		
	}

}
