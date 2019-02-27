package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class CheepCheepsItem extends GameObject implements EntityE   {
	
	Textures tex;
	Game game;
	Animation floppy;
	Animation floppyDead;
	private int setup = 0;
	private double velX = 0;
	private double velY = 0;
	private double velYRandom = 0;
	private boolean dead = false;
	
	public CheepCheepsItem(double x, double y, Textures tex, Game game, int setup) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.setup = setup;
		Random rand = new Random();
		if(rand.nextInt(2) == 0) {
			this.y-=rand.nextInt(40);
		}
		if(rand.nextInt(3) == 0) {
			velYRandom = rand.nextInt(1000)/1000;
		}
		velY = -1.2;
		if(setup == 0) {
			floppy = new Animation(6, tex.cheepCheepsItem[6],tex.cheepCheepsItem[5],tex.cheepCheepsItem[7],tex.cheepCheepsItem[4]);
			floppyDead = new Animation(6, tex.cheepCheepsItem[14], tex.cheepCheepsItem[13], tex.cheepCheepsItem[15], tex.cheepCheepsItem[12]);
			velX = 10;
		}
		else if(setup == 1) {
			floppy = new Animation(6, tex.cheepCheepsItem[1],tex.cheepCheepsItem[2],tex.cheepCheepsItem[0],tex.cheepCheepsItem[3]);
			floppyDead = new Animation(6, tex.cheepCheepsItem[9], tex.cheepCheepsItem[10], tex.cheepCheepsItem[8], tex.cheepCheepsItem[11]);
			velX = -10;
		}
		floppy.nextFrame();
		floppy.setCount(0);
		floppyDead.nextFrame();
		floppyDead.setCount(0);
	}

	public void tick() {
		if(dead) {
			y+=4;
			if(y > Game.HEIGHT * Game.SCALE || y < -16 || x+16 < 0 || x > Game.WIDTH * Game.SCALE)
				game.ee.remove(this);
		}
		else {
			if(setup == 0) {
				if(x+8 <= Game.WIDTH && velX > 4) {
					if(velY+velYRandom < -0.01)
						velY+=(0.007+velYRandom);
					velX-=0.12;
					if(x < 45 && y > 340)
						y-=5;
				}
				else if(velX < 10) {
					if(velY+velYRandom < 1.08)
						velY+=(0.07+velYRandom);
					velX+=0.12;
				}
				else if(velY+velYRandom < 1.08) {
					velY+=(0.07+velYRandom);
				}
				if(x > 597)
					y+=3;
			}
			else if(setup == 1) {
				if(x-8 >= Game.WIDTH && velX < -4) {
					if(velY+velYRandom < -0.01)
						velY+=(0.007+velYRandom);
					velX+=0.12;
					if(x > 597 && y > 340)
						y-=5;
				}
				else if(velX > -10) {
					if(velY+velYRandom < 1.08)
						velY+=(0.07+velYRandom);
					velX-=0.12;
				}
				else if(velY+velYRandom < 1.08) {
					velY+=(0.07+velYRandom);
				}
				if(x < 45)
					y+=3;
			}
			if(x+8 >= Game.WIDTH && setup == 0 && velY < 0) {
				velY = 0.3+velYRandom;
			}
			else if(x-8 <= Game.WIDTH && setup == 1 && velY < 0) {
				velY = 0.3+velYRandom;
			}
			
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					dead = true;
					floppyDead.setCount(floppy.getCount());
					if(Game.currentlySelectedFireball != 3)
						game.ea.remove(tempEnt);
				}
			}
			
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityBDead() == false){
					if(!tempEnt.enemyType().equals("Bowser")) {
						game.eb.get(i).setEntityBDead(true);
					}
					else {
						HUD.HEALTH -=4;
						dead = true;
						floppyDead.setCount(floppy.getCount());
					}
				}
			}
			
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityCDead() == false){
					if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
						dead = true;
						floppyDead.setCount(floppy.getCount());
					}
					else {
						game.ec.get(i).setEntityCDead(true);
						dead = true;
						floppyDead.setCount(floppy.getCount());
					}
				}
			}
			
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityEDead()) { 
					if((tempEnt.entityName().equals("bombOmbShrapnel2") || tempEnt.entityName().equals("bombOmbShrapnel1"))){
						dead = true;
						floppyDead.setCount(floppy.getCount());
						game.ee.remove(tempEnt);
					}
					else if(tempEnt.entityName().equals("chainChomp")) {
						dead = true;
						floppyDead.setCount(floppy.getCount());
					}
					else if(tempEnt.entityName().equals("bulletBill")) {
						dead = true;
						floppyDead.setCount(floppy.getCount());
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("bombOmb")) {
						game.ee.get(i).setEntityEDead(true);
						game.ee.remove(this);
					}
				}
			}
			floppy.runAnimation();
			x+=velX;
			y+=velY;
			if(y > Game.HEIGHT * Game.SCALE || y < -16 || x+16 < 0 || x > Game.WIDTH * Game.SCALE)
				game.ee.remove(this);
		}
	}

	public void render(Graphics g) {
		if(dead) 
			floppyDead.drawAnimation(g, x, y, 0);
		else
			floppy.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		if(floppy.getCount() == 1)
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

	public String entityName() {
		return "cheepCheeps";
	}

	public void setHitIndicator(boolean b) {
		return;
	}

	public boolean getHitIndicator() {
		return false;
	}
	
	public void setEntityEDead(boolean dead) {
		this.dead = dead;
		if(dead)
			floppyDead.setCount(floppy.getCount());
	}
	
	public boolean getEntityEDead() {
		return dead;
	}
	
	public void close() {
		
	}

}
