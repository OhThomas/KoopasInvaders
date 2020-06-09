package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class BuzzyBeetleShell extends GameObject implements EntityC{

	
	private Textures tex;
	private Game game;
	private boolean topHitOnce = false;
	private boolean xDirectionSet = false;
	private boolean yDirectionSet = false;
	private boolean buzzyBeetleShellisDead = false;
	private boolean pauseSoundLoop = false;
	private boolean flicker = false;
	private double velX = 0;
	private double velY = 0;
	private long flickerTimer1 = 0;
	private long flickerTimer2 = 0;
	private String entityName = "BuzzyBeetleShell";
	Animation anim;
	Animation animDead;
	SoundLoops shellHit;
	SoundLoops shellHitShell;
	SoundLoops shellHitBowser;
	
	public BuzzyBeetleShell(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(4,tex.buzzyBeetleShell[0],tex.buzzyBeetleShell[1],tex.buzzyBeetleShell[2],tex.buzzyBeetleShell[3]);
		animDead = new Animation(4,tex.buzzyBeetleShellDead[0],tex.buzzyBeetleShellDead[1],tex.buzzyBeetleShellDead[2],
				tex.buzzyBeetleShellDead[3],tex.buzzyBeetleShellDead[4],tex.buzzyBeetleShellDead[5],tex.buzzyBeetleShellDead[6]);
		animDead.nextFrame();
		animDead.setCount(0);
		String shellHitFile = "res/Sounds/SFX/ssbm_shell.wav";
		String shellHitShellFile = "res/Sounds/SFX/nsmbwiiBump.wav";
		String shellHitBowserFile = "res/Sounds/SFX/nsmbwiiClassicBump.wav";
		SoundLoops shellHitSoundLoop = new SoundLoops(shellHitFile);
		SoundLoops shellHitShellSoundLoop = new SoundLoops(shellHitShellFile);
		SoundLoops shellHitBowserSoundLoop = new SoundLoops(shellHitBowserFile);
		VolumeSlider.adjustSFX(shellHitSoundLoop);
		VolumeSlider.adjustSFX(shellHitShellSoundLoop);
		VolumeSlider.adjustSFX(shellHitBowserSoundLoop);
		this.shellHit = shellHitSoundLoop;
		this.shellHitShell = shellHitShellSoundLoop;
		this.shellHitBowser = shellHitBowserSoundLoop;
		
		Random rand = new Random();
		int i = rand.nextInt(2);
		if(i == 0) 
			velX = -5;
		else
			velX = 5;
	}
	
	public void tick(){
		if(buzzyBeetleShellisDead) {
			if(velX < 0)
				x-=0.75;
			else
				x+=0.75;
			if(velY < 0)
				y-=0.75;
			else 
				y+=0.75;
			animDead.runAnimation();
		}
		else {
		if(!topHitOnce) {
			//if(!xDirectionSet) {
			//	xDirectionSet = true;
			//}
			if(y <= 0)
				topHitOnce = true;
			if(Game.WIDTH * Game.SCALE <= x+14)
				velX = -5;
			if(x <= 0)
				velX = 5;
			
			velY = -1;
			y += velY;
			x += velX;
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt) && !buzzyBeetleShellisDead){
					if(Game.currentlySelectedFireball == 3) {
						buzzyBeetleShellisDead = true;
						game.getHUD().setScore(500);
						shellHit.play();
					}
					else {
						game.fireballSplash(tempEnt.getX(), tempEnt.getY());
						if(!game.ea.isEmpty()) {
							game.ea.remove(game.ea.getLast());
							Game.soundFireballPop();
						}
					}
//					buzzyBeetleShellisDead = true;
//					if(!game.ea.isEmpty() && Game.currentlySelectedFireball != 3)
//						game.ea.remove(game.ea.getLast());
//					game.getHUD().setScore(500);
//					shellHit.play();
				}
			}
			for(int i = 0; i < game.eb.size(); i ++){
				EntityB tempEnt = game.eb.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if(tempEnt.enemyType().equals("Bowser") ) {
						if(this.getX() <= tempEnt.getX() && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
							velX = -5;
						else if(tempEnt.getX() <= this.getX()  && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
							velX = 5;
						if(tempEnt.getY()<=this.getY())
							velY = 6;
						else if(this.getY()<= tempEnt.getY())
							velY = -6;
						if(!shellHitBowser.clipIsActive())
							shellHitBowser.play();
					}
				}
			}
			for(int i = 0; i < game.ec.size(); i ++){
				EntityC tempEnt = game.ec.get(i);
				if(tempEnt == this) {}
				else if(Physics.Collision(this, tempEnt)){
					if(tempEnt.entityName().equals("GreenShell") || tempEnt.entityName().equals("GreenShellCircle")) {
						topHitOnce = true;
						tempEnt.setEntityCDead(true);
						shellHitShell.play();
					}
					else if(tempEnt.entityName().equals("BulletBill")) {
						topHitOnce = true;
						tempEnt.setEntityCDead(true);
					}
				}
			}
		}
		else if(!Physics.Collision(this, game.getBb())){
			if(!yDirectionSet) {
				velY = 6;
				yDirectionSet = true;
			}
			x += velX;
			y += velY;
			if(Game.WIDTH * Game.SCALE <= x+14)
				velX = -5;
			else if(x <= 0)
				velX = 5;
			if(Game.HEIGHT * Game.SCALE <= y + 14)
				velY = -6;
			else if(y + 14 <= 0)
				game.ec.remove(this);
			/*
			if(Physics.Collision(this, game.eb)){
				System.out.println("Collision detected!");
			}
			*/
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt) && !buzzyBeetleShellisDead){
					if(Game.currentlySelectedFireball == 3) {
						buzzyBeetleShellisDead = true;
						game.getHUD().setScore(500);
						shellHit.play();
					}
					else {
						game.fireballSplash(tempEnt.getX(), tempEnt.getY());
						if(!game.ea.isEmpty()) {
							game.ea.remove(game.ea.getLast());
							Game.soundFireballPop();
						}
					}
//					buzzyBeetleShellisDead = true;
//					if(!game.ea.isEmpty() && Game.currentlySelectedFireball != 3)
//						game.ea.remove(game.ea.getLast());
//					game.getHUD().setScore(500);
//					shellHit.play();
				}
			}

			for(int i = 0; i < game.eb.size(); i ++){
				EntityB tempEnt = game.eb.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if(tempEnt.enemyType().equals("Bowser") ) {
						if(this.getX() <= tempEnt.getX() && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
							velX = -5;
						else if(tempEnt.getX() <= this.getX()  && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
							velX = 5;
						if(tempEnt.getY()<=this.getY())
							velY = 6;
						else if(this.getY()< tempEnt.getY() + tempEnt.getHeight())
							velY = -6;
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
					if(this.getX() <= tempEnt.getX() && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
						velX = -5;
					else if(tempEnt.getX() <= this.getX()  && this.getY() + 14 >= tempEnt.getY() && this.getY() <= tempEnt.getY()+tempEnt.getHeight())
						velX = 5;
					if(tempEnt.getY()<=this.getY())
						velY = 6;
					else if(this.getY()<= tempEnt.getY())
						velY = -6;
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
		if(animDead.getCount() == 7)
			game.ec.remove(this);
		anim.runAnimation();
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 14);
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
		if(buzzyBeetleShellisDead)
			animDead.drawAnimation(g, x, y, 0);
		else
			anim.drawAnimation(g, x, y, 0);
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
		return buzzyBeetleShellisDead;
	}

	public String entityName() {
		return entityName;
	}

	public int getWidth() {
		return 16;
	}

	public int getHeight() {
		return 14;
	}

	public void setEntityCDead(boolean dead) {
		if(dead) {
			game.getHUD().setScore(500);
			shellHit.play();
		}
		buzzyBeetleShellisDead = dead;
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
