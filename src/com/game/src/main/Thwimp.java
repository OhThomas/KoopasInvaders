package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class Thwimp extends GameObject implements EntityC {
	private Textures tex;
	private Game game;
	SoundLoops entranceSound;
	BufferedImage thwimp;
	private long popOffTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private long flickerTimer1 = 0;									
	private long flickerTimer2 = 0;		
	private double velX = 0;
	private double velY = 0;
	private boolean hitIndicator = false;
	public Thwimp(double x, double y,Textures tex, Game game, boolean velX){
		super(x,y);
		this.tex = tex;
		this.game = game;
		thwimp = tex.thwimp;
		if(velX) {
			this.velX = -2;
			this.x-=9;
		}
		else
			this.velX = 2;

		String entranceFile = "res/Sounds/SFX/thwimpentrancesfx.wav";
		SoundLoops entranceSoundLoop = new SoundLoops(entranceFile);
		VolumeSlider.adjustSFX(entranceSoundLoop);
		this.entranceSound = entranceSoundLoop;
		this.entranceSound.play();
		
		popOffTimer = System.currentTimeMillis() + 200;
	}
	
	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(popOffTimer != 0)
					popOffTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(System.currentTimeMillis() < popOffTimer) {
			if(velY > -2.5) {
				velY-=0.15;
			}
		}
		else {
			if(velY < 20)
				velY+=0.1;
		}
		if(velX < 0 && -6 < velX)
			velX-=0.001;
		else if(0 < velX && velX < 6)
			velX+=0.001;
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this,tempEnt)) {
				game.fireballSplash(tempEnt.getX(), tempEnt.getY());
				game.ea.removeLast();
				Game.soundFireballPop();
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(Physics.Collision(this,tempEnt) && this != tempEnt && !tempEnt.entityName().equals("Thwimp") && !tempEnt.getEntityCDead()) {
				if(tempEnt.entityName().equals("BulletBill")) {
					game.ec.get(i).setEntityCDead(true);
				}
				else if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
					game.getHUD().setScore(500);
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"BuzzyBeetleShell"));
					game.getController().removeEntity(tempEnt);
				}
				else if(!tempEnt.entityName().equals("Swoop")){
					game.getHUD().setScore(200);
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,tempEnt.entityName()));
					game.getController().removeEntity(tempEnt);
				}
			}
		}
		for(int i = 0; i < game.ee.size(); i++){
			EntityE tempEnt = game.ee.get(i);
			if(Physics.Collision(this,tempEnt) && !tempEnt.getEntityEDead() && !tempEnt.entityName().equals("chainChomp") && !tempEnt.entityName().equals("amp")
					&& !tempEnt.entityName().equals("wiggler") && !tempEnt.entityName().equals("wigglerBody") && !tempEnt.entityName().equals("lakitu")) {
				if(tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmb") || tempEnt.entityName().equals("lakituBombOmb")) {
					game.ee.get(i).setEntityEDead(true);
				}
				else {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,tempEnt.entityName()));
					game.getController().removeEntity(tempEnt);
				}
			}
		}
		
		x+=velX;
		y+=velY;
		
		if(Physics.Collision(this, game.getBb())){
			y -= velY/1.2;
			x-=velX/1.2;
//			game.getController().addEntity(new BlockBreakingFX(x,y+16,game,true));
//			game.getController().addEntity(new BlockBreakingFX(x+16,y+16,game,false));
		}
		if (y-getBounds().height >= Game.HEIGHT * Game.SCALE){
			game.getController().removeEntity(this);
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
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < popOffTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		g.drawImage(thwimp,(int)x,(int)y,null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
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
		return false;
	}

	public void setEntityCDead(boolean dead) {
		if(dead) {
			game.getHUD().setScore(200);
			game.getController().addEntity(new ChompFX(game,x+4,y+2,"Thwimp"));
			game.getController().removeEntity(this);
		}
	}

	public String entityName() {
		return "Thwimp";
	}

	public void renderFlicker() {
		this.hitIndicator = true;
	}

	public void close() {
		
	}

}
