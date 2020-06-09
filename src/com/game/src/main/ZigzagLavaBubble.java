package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class ZigzagLavaBubble extends GameObject implements EntityC{

	
	private Textures tex;
	private Game game;
	private boolean topHitOnce = false;
	private boolean xDirectionSet = false;
	private boolean yDirectionSet = false;
	private boolean pauseSoundLoop = false;
	private boolean flicker = false;
	private double velX = 0;
	private double velY = 0;
	private long flickerTimer1 = 0;
	private long flickerTimer2 = 0;
	private String entityName = "ZigzagLavaBubble";
	Animation upL;
	Animation upR;
	Animation downL;
	Animation downR;
	SoundLoops shellHit;
	SoundLoops shellHitShell;
	SoundLoops shellHitBowser;
	
	public ZigzagLavaBubble(double x, double y, Textures tex, Game game, double velX){
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.velX = velX;
		upL = new Animation(1,tex.lavaDiagonal[0],tex.lavaDiagonal[1]);
		upR = new Animation(1,tex.lavaDiagonal[2],tex.lavaDiagonal[3]);
		downL = new Animation(1,tex.lavaDiagonal[4],tex.lavaDiagonal[5]);
		downR = new Animation(1,tex.lavaDiagonal[6],tex.lavaDiagonal[7]);
		String shellHitFile = "res/Sounds/SFX/smw_lava_bubble.wav";
		String shellHitShellFile = "res/Sounds/SFX/nsmbwiiBump.wav";
		String shellHitBowserFile = "res/Sounds/SFX/nsmbwiiClassicBump.wav";
		SoundLoops shellHitSoundLoop = new SoundLoops(shellHitFile);
		SoundLoops shellHitShellSoundLoop = new SoundLoops(shellHitShellFile);
		SoundLoops shellHitBowserSoundLoop = new SoundLoops(shellHitBowserFile);
		VolumeSlider.adjustSFX(shellHitSoundLoop);
		VolumeSlider.adjustSFX(shellHitShellSoundLoop);
		VolumeSlider.adjustSFX(shellHitBowserSoundLoop);
		shellHitSoundLoop.reduceSound(1.4f);
		this.shellHit = shellHitSoundLoop;
		this.shellHitShell = shellHitShellSoundLoop;
		this.shellHitBowser = shellHitBowserSoundLoop;
		if(this.velX == 0) {
			Random rand = new Random();
			if (rand.nextInt(2) == 0)
				this.velX = 2;
			else
				this.velX = -2;
		}
		upL.nextFrame();
		upL.setCount(0);
		upR.nextFrame();
		upR.setCount(0);
		downL.nextFrame();
		downL.setCount(0);
		downR.nextFrame();
		downR.setCount(0);
	}
	
	public void tick(){
		if(!topHitOnce) {
			//if(!xDirectionSet) {
			//	xDirectionSet = true;
			//}
			if(y <= 0)
				topHitOnce = true;
//			if(Game.WIDTH * Game.SCALE <= x+14)
//				velX = -2;
//			if(x <= 0)
//				velX = 2;
			
			velY = -1;
			y += velY;
			x += velX;
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if(Game.currentlySelectedFireball != 3) {
						game.fireballSplash(tempEnt.getX(), tempEnt.getY());
						game.ea.removeLast();
						Game.soundFireballPop();
					}
					else {
						shellHit.play();
						game.getHUD().setScore(200);
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"ZigzagLavaBubble"));
						game.getController().removeEntity(this);
					}
				}
			}
			for(int i = 0; i < game.eb.size(); i ++){
				EntityB tempEnt = game.eb.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if(tempEnt.enemyType().equals("Bowser") ) {
//						if(this.getX() <= tempEnt.getX() && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
//							velX = -2;
//						else if(tempEnt.getX() <= this.getX()  && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
//							velX = 2;
//						if(tempEnt.getY()<=this.getY())
//							velY = 3;
//						else if(this.getY()<= tempEnt.getY())
//							velY = -3;
						if(!shellHitBowser.clipIsActive())
							shellHitBowser.play();
					}
				}
			}
			for(int i = 0; i < game.ec.size(); i ++){
				EntityC tempEnt = game.ec.get(i);
				if(tempEnt == this || tempEnt.entityName().equals("ZigzagLavaBubble")) {}
				else if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					if(tempEnt.entityName().equals("GreenShell") || tempEnt.entityName().equals("GreenShellCircle")) {
						topHitOnce = true;
						tempEnt.setEntityCDead(true);
						shellHitShell.play();
					}
					else if(tempEnt.entityName().equals("BulletBill")) {
						topHitOnce = true;
						tempEnt.setEntityCDead(true);
					}else if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp")) {
						this.setEntityCDead(true);
					}
				}
			}
		}
		else if(!Physics.Collision(this, game.getBb())){
			if(!yDirectionSet) {
				velY = 3;
				yDirectionSet = true;
			}
			x += velX;
			y += velY;
			if(Game.WIDTH * Game.SCALE <= x+14)
				velX = -2;
			else if(x <= 0)
				velX = 2;
			if(Game.HEIGHT * Game.SCALE <= y + 14)
				velY = -3;
			else if(y + 14 <= 0)
				game.ec.remove(this);
			/*
			if(Physics.Collision(this, game.eb)){
				System.out.println("Collision detected!");
			}
			*/
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if(Game.currentlySelectedFireball != 3) {
						game.fireballSplash(tempEnt.getX(), tempEnt.getY());
						game.ea.removeLast();
						Game.soundFireballPop();
					}
					else {
						shellHit.play();
						game.getHUD().setScore(200);
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"ZigzagLavaBubble"));
						game.getController().removeEntity(this);
					}
				}
			}

			for(int i = 0; i < game.eb.size(); i ++){
				EntityB tempEnt = game.eb.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if(tempEnt.enemyType().equals("Bowser") ) {
						if(this.getX() <= tempEnt.getX() && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
							velX = -2;
						else if(tempEnt.getX() <= this.getX()  && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
							velX = 2;
						if(tempEnt.getY()<=this.getY())
							velY = 3;
						else if(this.getY()< tempEnt.getY() + tempEnt.getHeight())
							velY = -3;
						if(!shellHitBowser.clipIsActive())
							shellHitBowser.play();
						/*
						if(this.getX()+16 <= tempEnt.getX() && this.getY() + 14 <= tempEnt.getY() && this.getY() >= tempEnt.getY()+tempEnt.getHeight())
							velX = -5;
						else if(tempEnt.getX() + tempEnt.getWidth() <= this.getX()  && this.getY() + 14 <= tempEnt.getY() && this.getY() >= tempEnt.getY()+tempEnt.getHeight())
							velX = 5;
						if(tempEnt.getY() + tempEnt.getHeight()<=this.getY() - 14)
							velY = 6;
						else if(this.getY() + 14 <= tempEnt.getY())
							velY = -6;
							*/
					}
				}
			}
			for(int i = 0; i < game.ec.size(); i ++){
				EntityC tempEnt = game.ec.get(i);
				if(tempEnt == this || tempEnt.getEntityCDead()) {}
				else if(Physics.Collision(this, tempEnt)){
					if(tempEnt.entityName().equals("BuzzyBeetleShell") || tempEnt.entityName().equals("Thwimp") || tempEnt.entityName().equals("Mechakoopa")) {
						this.setEntityCDead(true);
						return;
					}
					if(this.getX() <= tempEnt.getX() && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
						velX = -2;
					else if(tempEnt.getX() <= this.getX()  && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
						velX = 2;
					if(tempEnt.getY()<=this.getY())
						velY = 3;
					else if(this.getY()<= tempEnt.getY())
						velY = -3;
					if(!tempEnt.entityName().equals("ZigzagLavaBubble"))
						tempEnt.setEntityCDead(true);
					shellHitShell.play();
				}
			}
		}
		else{
			Random rand = new Random();
			int i = rand.nextInt(37);
			if(i<5){
				Rectangle brick = new Rectangle((int)this.getX(),(int)this.getY(),24,24);
				for(int j = game.getBb().wall.size(); j > 0; j--){
					if(brick.contains(game.getBb().wall.get(j-1))){
						game.getBb().wall.remove(j-1);
					}
				}
			}
			else if(i>5 && i<9){
				Rectangle brick = new Rectangle((int)this.getX(),(int)this.getY(),32,32);
				for(int j = game.getBb().wall.size(); j > 0; j--){
					if(brick.contains(game.getBb().wall.get(j-1))){
						game.getBb().wall.remove(j-1);
					}
				}
			}
			x += velX/2;
			y += velY/2;
		}
		if(pauseSoundLoop) {
			if(shellHit.getSoundLoopBoolean() == true && !Game.isPaused()){
				if(!shellHit.clipIsActive())
					shellHit.continuePlaying();
			}
			if(shellHitShell.getSoundLoopBoolean() == true && !Game.isPaused()){
				if(!shellHitShell.clipIsActive())
					shellHitShell.continuePlaying();
			}
			if(shellHitBowser.getSoundLoopBoolean() == true && !Game.isPaused()){
				if(!shellHitBowser.clipIsActive())
					shellHitBowser.continuePlaying();
			}
		}
		if (y-getBounds().height >= Game.HEIGHT * Game.SCALE){
			game.getController().removeEntity(this);
		}
		else if(y+getBounds().height < 0)
			game.getController().removeEntity(this);
		upL.runAnimation();
		upR.runAnimation();
		downL.runAnimation();
		downR.runAnimation();
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
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
		if(velX < 0 && 0 < velY)
			downL.drawAnimation(g, x, y, 0);
		else if(0 < velX && 0 < velY)
			downR.drawAnimation(g, x, y, 0);
		else if(velX < 0 && velY < 0)
			upL.drawAnimation(g, x, y, 0);
		else
			upR.drawAnimation(g, x, y, 0);
		if(Game.isPaused()){
			if(shellHit.getSoundLoopBoolean() == false){
					if(shellHit.clipIsActive())
						shellHit.stop();
				shellHit.setSoundLoopBoolean(true);
				pauseSoundLoop = true;
			}
			if(shellHitShell.getSoundLoopBoolean() == false){
				if(shellHitShell.clipIsActive())
					shellHitShell.stop();
				shellHitShell.setSoundLoopBoolean(true);
				pauseSoundLoop = true;
			}
			if(shellHitBowser.getSoundLoopBoolean() == false){
				if(shellHitBowser.clipIsActive())
					shellHitBowser.stop();
				shellHitBowser.setSoundLoopBoolean(true);
				pauseSoundLoop = true;
			}
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean getEntityCDead() {
		return false;
	}

	public String entityName() {
		return entityName;
	}

	public int getWidth() {
		return getBounds().width;
	}

	public int getHeight() {
		return getBounds().height;
	}

	public void setEntityCDead(boolean dead) {
		if(dead) {
			shellHit.play();
			game.getHUD().setScore(200);
			game.getController().addEntity(new ChompFX(game,x+4,y+2,"ZigzagLavaBubble"));
			game.getController().removeEntity(this);
		}
	}

	public void renderFlicker() {
		flicker = true;
	}
	
	public void close() {
		shellHit.close();
		shellHitShell.close();
		shellHitBowser.close();
	}
}
