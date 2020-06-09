package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class LavaBubble extends GameObject implements EntityC {
	private Textures tex;
	private Game game;
	Animation up;
	Animation down;
	SoundLoops entranceSound;
	MiniBubblesFX bubbles;
	private long popOffTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private long flickerTimer1 = 0;									
	private long flickerTimer2 = 0;		
	private double velX = 0;
	private double velY = 0;
	private int bubbleCount = 0;
	private boolean hitIndicator = false;
	public LavaBubble(double x, double y,Textures tex, Game game, double velX){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.velX = velX;
		up = new Animation(6,tex.lavaUpDown[0],tex.lavaUpDown[1]);
		down = new Animation(6,tex.lavaUpDown[2],tex.lavaUpDown[3]);
		up.nextFrame();
		up.setCount(0);
		down.nextFrame();
		down.setCount(0);

		String entranceFile = "res/Sounds/SFX/mariolavabubblesfx.wav";
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
		bubbleCount++;
		if(bubbleCount > 2) {
			Random rand = new Random();
			switch(rand.nextInt(3)) {
			case 0:
				bubbles = new MiniBubblesFX(x-3,y,game,velX);
				break;
			case 1:
				bubbles = new MiniBubblesFX(x+11,y-1,game,velX);
				break;
			case 2:
				bubbles = new MiniBubblesFX(x+4+rand.nextInt(3)-rand.nextInt(3),y-4,game,velX);
				break;
			default:
				break;
			}
			game.getController().addEntity(bubbles);
			bubbleCount = 0;
			if(rand.nextInt(4) == 0)
				bubbleCount++;
		}
		if(System.currentTimeMillis() < popOffTimer) {
			if(velY > -2.5) {
				velY-=0.15;
			}
		}
		else {
			if(velY < 6)
				velY+=0.1;
		}
		if(velX < 0) {
			if(velX < -1)
				velX+=0.08;
		}
		else {
			if(1 < velX)
				velX-=0.08;
		}
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this,tempEnt)) {
				if(Game.currentlySelectedFireball != 3) {
					game.fireballSplash(tempEnt.getX(), tempEnt.getY());
					game.ea.removeLast();
					Game.soundFireballPop();
				}
				else {
					game.getHUD().setScore(200);
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"LavaBubble"));
					game.getController().removeEntity(this);
				}
				
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(Physics.Collision(this,tempEnt) && this != tempEnt && !tempEnt.entityName().equals("LavaBubble") && !tempEnt.getEntityCDead()) {
				if(tempEnt.entityName().equals("BulletBill") || tempEnt.entityName().equals("Mechakoopa")) {
					game.ec.get(i).setEntityCDead(true);
				}
				else if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp")) {
					game.getHUD().setScore(200);
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"LavaBubble"));
					game.getController().removeEntity(this);
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
					&& !tempEnt.entityName().equals("wiggler") && !tempEnt.entityName().equals("wigglerBody")) {
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
		}
		if (y-getBounds().height >= Game.HEIGHT * Game.SCALE){
			game.getController().removeEntity(this);
		}
		up.runAnimation();
		down.runAnimation();
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
		if(velY < 0) 
			up.drawAnimation(g, x, y, 0);
		else
			down.drawAnimation(g, x, y, 0);
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
			game.getController().addEntity(new ChompFX(game,x+4,y+2,"LavaBubble"));
			game.getController().removeEntity(this);
		}
	}

	public String entityName() {
		return "LavaBubble";
	}

	public void renderFlicker() {
		this.hitIndicator = true;
	}

	public void close() {
		
	}

}
