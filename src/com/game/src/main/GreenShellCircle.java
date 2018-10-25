package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class GreenShellCircle extends GameObject implements EntityC{

	
	private Textures tex;
	private Game game;
	private boolean circleSpawn = false;
	private boolean circling = false;
	private boolean greenShellisDead = false;
	private boolean pauseSoundLoop = false;
	private int startAtTop = 377;
	private String entityName = "GreenShellCircle";
	Animation anim;
	Animation animDead;
	SoundLoops shellHit;
	SoundLoops shellSpawn;
	SoundLoops shellSentOut;
	
	public GreenShellCircle(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(4,tex.greenShell[0],tex.greenShell[1],tex.greenShell[2],tex.greenShell[3]);
		animDead = new Animation(4,tex.greenShellDead[0],tex.greenShellDead[1],tex.greenShellDead[2],tex.greenShellDead[3],
				tex.greenShellDead[4],tex.greenShellDead[5],tex.greenShellDead[6],tex.greenShellDead[7]);
		animDead.nextFrame();
		animDead.setCount(0);
		String shellHitFile = "res/Sounds/SFX/ssbm_shell.wav";
		String shellSpawnFile = "res/Sounds/SFX/nsmbwiiFlip.wav";
		String shellSentOutFile = "res/Sounds/SFX/nsmbwiiMenuCancel.wav";
		SoundLoops shellHitSoundLoop = new SoundLoops(shellHitFile);
		SoundLoops shellSpawnSoundLoop = new SoundLoops(shellSpawnFile);
		SoundLoops shellSentOutSoundLoop = new SoundLoops(shellSentOutFile);
		VolumeSlider.adjustSFX(shellHitSoundLoop);
		VolumeSlider.adjustSFX(shellSpawnSoundLoop);
		VolumeSlider.adjustSFX(shellSentOutSoundLoop);
		this.shellHit = shellHitSoundLoop;
		this.shellSpawn = shellSpawnSoundLoop;
		this.shellSentOut = shellSentOutSoundLoop;
		shellSpawn.play();
	}
	
	public void tick(){
		if(greenShellisDead) {
			if(circling) {
				if(startAtTop < 183)
					y -= 0.35;
				else
					y+= 0.35;
				if(startAtTop < 183 && startAtTop > 100)
					x-=0.35;
				else if(startAtTop < 100)
					x+=0.35;
				if(startAtTop < 377 && startAtTop > 294)
					x+=0.35;
				else if(startAtTop < 294 && startAtTop > 183)
					x-=0.35;
			}
			else
				y+=0.75;
			animDead.runAnimation();
		}
		else {
		if(game.eb.isEmpty() || game.eb.getLast().getEntityBDead()) {
			circleSpawn = true;
			circling = false;
			if(this.shellSentOut.getSoundLoopBoolean() == false) {
				shellSentOut.play();
				this.shellSentOut.setSoundLoopBoolean(true);
			}
		}
		if(!circleSpawn) {
			if(y < game.eb.getLast().getY() - 17) {
				circleSpawn = true;
				circling = true;
			}
			else {
				y-=3;
				x = game.eb.getLast().getX() + 18;
			}
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					greenShellisDead = true;
					if(!game.ea.isEmpty())
						game.ea.remove(game.ea.getLast());
				}
			}
		}
		else if(circling) {
			circleBowser();
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					greenShellisDead = true;
					if(!game.ea.isEmpty())
						game.ea.remove(game.ea.getLast());
					shellHit.play();
				}
			}
		}
		else if(!Physics.Collision(this, game.getBb())){
			y += 6;
			/*
			if(Physics.Collision(this, game.eb)){
				System.out.println("Collision detected!");
			}
			*/
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					greenShellisDead = true;
					if(!game.ea.isEmpty())
						game.ea.remove(game.ea.getLast());
					shellHit.play();
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
			y+=0.3;
		}
		}
		if(pauseSoundLoop) {
			if(shellHit.getSoundLoopBoolean() == true && !Game.isPaused()){
				if(!shellHit.clipIsActive())
					shellHit.continuePlaying();
			}
			if(shellSpawn.getSoundLoopBoolean() == true && !Game.isPaused()){
				if(!shellSpawn.clipIsActive())
					shellSpawn.continuePlaying();
			}
			if(shellSentOut.getSoundLoopBoolean() == true && !Game.isPaused()){
				if(!shellSentOut.clipIsActive())
					shellSentOut.continuePlaying();
			}
		}
		if(animDead.getCount() == 8)
			game.ec.remove(this);
		anim.runAnimation();
		
	}
	
	public void circleBowser() {
	    // 1 - Math.acos()
		double r = 75 ; // radius (it might radius of your circle, but consider dimensions of rectangle to make sure you are drawing inside of circle, e.g. circleRadius - rectangeDimesion / 2.0)
		//System.out.println((int)game.eb.getLast().getX());
		for (int f = 540; f < startAtTop +360 + 540; f++) {
			//if(Math.cos(Math.toRadians((double)f)) * r + (int)game.eb.getLast().getY() + 96/2 == 39) {
			//System.out.println(Math.cos(Math.toRadians((double)f)) * r + (int)game.eb.getLast().getY() + 96/2);
			//System.out.println((double)f);//}
		    double x = Math.sin(Math.toRadians((double)f)) * r + (int)game.eb.getLast().getX() + (int)game.eb.getLast().getWidth()/2;
		    double y = Math.cos(Math.toRadians((double)f)) * r + (int)game.eb.getLast().getY() + 96/2;
		    // draw rectangle on [x, y] coordinates
			this.x = x;
			this.y = y;
		}
		if(startAtTop == 183) {//197 
			Random rand = new Random();
			int i = rand.nextInt(3);
			if(i == 0) {
				circling = false;
				shellSentOut.play();
			}
		}
		if(startAtTop == 17)
			startAtTop = 377;
		else
			startAtTop--;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void render(Graphics g){
		if(greenShellisDead)
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
			if(shellSpawn.getSoundLoopBoolean() == false){
				if(shellSpawn.clipIsActive())
					shellSpawn.stop();
				shellSpawn.setSoundLoopBoolean(true);
				pauseSoundLoop = true;
			}
			if(shellSentOut.getSoundLoopBoolean() == false){
				if(shellSentOut.clipIsActive())
					shellSentOut.stop();
				shellSentOut.setSoundLoopBoolean(true);
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

	public int getWidth() {
		return 16;
	}

	public int getHeight() {
		return 16;
	}
	
	public boolean getEntityCDead() {
		return false;
	}

	public String entityName() {
		return entityName;
	}

	public void setEntityCDead(boolean dead) {
		greenShellisDead = dead;
	}
}
