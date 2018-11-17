package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class GreenShell extends GameObject implements EntityC{

	
	private Textures tex;
	private Game game;
	private String entityName = "GreenShell";
	private boolean greenShellisDead = false;
	Animation anim;
	Animation animDead;
	SoundLoops shellHit;
	
	public GreenShell(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(4,tex.greenShell[0],tex.greenShell[1],tex.greenShell[2],tex.greenShell[3]);
		animDead = new Animation(4,tex.greenShellDead[0],tex.greenShellDead[1],tex.greenShellDead[2],tex.greenShellDead[3],
				tex.greenShellDead[4],tex.greenShellDead[5],tex.greenShellDead[6],tex.greenShellDead[7]);
		animDead.nextFrame();
		animDead.setCount(0);
		String shellHitFile = "res/Sounds/SFX/ssbm_shell.wav";
		SoundLoops shellHitSoundLoop = new SoundLoops(shellHitFile);
		VolumeSlider.adjustSFX(shellHitSoundLoop);
		if(shellHitSoundLoop.getVolume() - 13f >= shellHitSoundLoop.minimumVolume())
			shellHitSoundLoop.reduceSound(13f);
		this.shellHit = shellHitSoundLoop;
	}
	
	public void tick(){
		if(!Physics.Collision(this, game.getBb())){
			if(!greenShellisDead) {
				y += 6;
				for(int i = 0; i < game.ea.size(); i ++){
					EntityA tempEnt = game.ea.get(i);
					
					if(Physics.Collision(this, tempEnt)){
						greenShellisDead = true;
						if(!game.ea.isEmpty() && Game.currentlySelectedFireball != 3)
							game.ea.remove(game.ea.getLast());
						shellHit.play();
					}
				}
			}
			else
				y += 0.75;
			if (y+this.getHeight() >= game.getHeight())
				game.ec.remove(this);
			/*
			if(Physics.Collision(this, game.eb)){
				System.out.println("Collision detected!");
			}
			*/
			
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
		if(greenShellisDead) {
			animDead.runAnimation();
			if(shellHit.getSoundLoopBoolean() == true && !game.isPaused()){
				if(!shellHit.clipIsActive())
					shellHit.continuePlaying();
			}
		}
		if(animDead.getCount() == 8)
			game.ec.remove(this);
		anim.runAnimation();
		
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
		return greenShellisDead;
	}

	public String entityName() {
		return entityName;
	}
	
	public void setEntityCDead(boolean dead) {
		greenShellisDead = dead;
	}

	
}
