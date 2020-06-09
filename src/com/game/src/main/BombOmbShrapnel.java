package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class BombOmbShrapnel extends GameObject implements EntityE  {
	Textures tex;
	Game game;
	long flickerTimer1 = 0;
	long flickerTimer2 = 0;
	double velX = 0;
	double velY = -2;
	boolean goingLeft = false;
	boolean noX = false;
	boolean hitIndicator = false;
	Animation shrapnel;
	public BombOmbShrapnel(double x, double y, Textures tex, Game game,boolean left) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		goingLeft = left;
		Random rand = new Random();
		if(rand.nextInt(9) == 0)
			noX = true;
		if(goingLeft) {
			shrapnel  = new Animation(3, tex.bombOmbShrapnel[0], tex.bombOmbShrapnel[1], tex.bombOmbShrapnel[2], tex.bombOmbShrapnel[3]);
			if(noX)
				velX=-0.2;
			else
				velX= -3.2;
		}
		else {
			shrapnel  = new Animation(3, tex.bombOmbShrapnel[4], tex.bombOmbShrapnel[5], tex.bombOmbShrapnel[6], tex.bombOmbShrapnel[7]);
			if(noX)
				velX=0.2;
			else
				velX= 3.2;
		}
		shrapnel.nextFrame();
		shrapnel.setCount(0);
	}

	public void tick() {
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				game.ee.remove(this);
				return;
			}
		}
		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
			if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){
				if( !tempEnt.enemyType().equals("Bowser"))
					game.eb.get(i).setEntityBDead(true);
				else {
					HUD.HEALTH -= 4;
					game.ee.remove(this);
				}
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
				if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
					game.ee.remove(this);
					return;
				}
//				else if(tempEnt.entityName().equals("BulletBill")) 
//					game.getHUD().setScore(200);
//				else
//					game.getHUD().setScore(200);
				game.ec.get(i).setEntityCDead(true);
			}
		}
		if(goingLeft && !noX)
			velX-=0.56;//0.07
		else if(!noX)
			velX+=0.56;//0.07
		velY-=0.56;
		x+=velX;
		y+=velY;
		if(y < -18 || x < 0 || x+8 > Game.WIDTH * Game.SCALE)
			game.ee.remove(this);

		shrapnel.runAnimation();
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
		shrapnel.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 10);
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
	}

	public String entityName() {
		return "bombOmbShrapnel1";
	}
	
	public void setHitIndicator(boolean b) {
		this.hitIndicator = b;
		return;
	}
	
	public boolean getHitIndicator() {
		return hitIndicator;
	}
	
	public void setEntityEDead(boolean dead) {
		close();
		game.ee.remove(this);
	}
	
	public boolean getEntityEDead() {
		return false;
	}
	
	public void close() {
		
	}
}
