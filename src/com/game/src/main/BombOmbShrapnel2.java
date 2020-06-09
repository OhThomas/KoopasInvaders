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

public class BombOmbShrapnel2 extends GameObject implements EntityE  {
	Textures tex;
	Game game;
	double velX = 0;
	double velY = -3.2;
	double randomFactor = 0;
	double randomFactor2 = 0;
	boolean goingLeft = false;
	boolean noY = false;
	boolean noYUp = false;
	boolean hitIndicator = false;
	Animation shrapnel;
	Animation glowingShrapnel;
	public BombOmbShrapnel2(double x, double y, Textures tex, Game game,int setup) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		if(setup == 0 || setup == 2)
			goingLeft = true;
		else if(setup == 1 || setup == 3 || setup == 4)
			goingLeft = false;
		if(goingLeft) {
			shrapnel  = new Animation(3, tex.bombOmbShrapnel[8], tex.bombOmbShrapnel[9], tex.bombOmbShrapnel[10], tex.bombOmbShrapnel[11]);
			glowingShrapnel  = new Animation(9, tex.bombOmbShrapnel[8], tex.bombOmbShrapnel[9], tex.bombOmbShrapnel[10], tex.bombOmbShrapnel[11],
					tex.bombOmbShrapnel[16], tex.bombOmbShrapnel[17], tex.bombOmbShrapnel[18], tex.bombOmbShrapnel[19]);
			velX= -2;
		}
		else {
			shrapnel  = new Animation(3, tex.bombOmbShrapnel[12], tex.bombOmbShrapnel[13], tex.bombOmbShrapnel[14], tex.bombOmbShrapnel[15]);
			glowingShrapnel  = new Animation(3, tex.bombOmbShrapnel[12], tex.bombOmbShrapnel[13], tex.bombOmbShrapnel[14], tex.bombOmbShrapnel[15],
					tex.bombOmbShrapnel[20], tex.bombOmbShrapnel[21], tex.bombOmbShrapnel[22], tex.bombOmbShrapnel[23]);
			velX= 2;
		}
		shrapnel.nextFrame();
		shrapnel.setCount(0);
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			randomFactor = 0.56;
		else
			randomFactor = 0.07;
		randomFactor2 = rand.nextInt(1000)/1000 + 0.09;
		if(rand.nextInt(9) == 0) {
			noY = true;
			if(goingLeft)
				velX--;
			else
				velX++;
			if(rand.nextInt(2) == 0) 
				noYUp = false;
			else
				noYUp = true;
			if(rand.nextInt(4) == 0) {
				if(goingLeft)
					velX-=2;
				else
					velX+=2;
			}
		}
		if(setup == 2 || setup == 3) {
			velY*=-1;
		}
		if(setup == 4) {
			velY = 4;
			if(rand.nextInt(2) == 0) {
				velX = -0.2-rand.nextDouble();
				goingLeft = true;
			}
			else {
				velX = 0.2+rand.nextDouble();
				goingLeft = false;
			}
		}
	}

	public void tick() {
		if(!Physics.Collision(this, game.getBb())){
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
					if(!tempEnt.enemyType().equals("Bowser")) {
						game.eb.get(i).setEntityBDead(true);
					}
					else {
						HUD.HEALTH -=4;
						game.ee.remove(this);
						return;
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
//					else if(tempEnt.entityName().equals("BulletBill")) 
//						game.getHUD().setScore(200);
//					else
//						game.getHUD().setScore(200);
					game.ec.get(i).setEntityCDead(true);
				}
			}
			if(goingLeft)
				velX-=randomFactor2;//0.56;
			else
				velX+=randomFactor2;//0.56;
			if(velY < 0)
				velY-=randomFactor2;//0.56;//0.07
			else
				velY+=randomFactor2;
			x+=velX;
			if(!noY)
				y+=velY;
			else {
				if(noYUp)
					y-=velX;
				else
					y+=velX;
			}
			if(velY == 0.33 && (y+5) >= 370 && (velX == 0.33 || velX == -0.33)) {
				velY = 0.77;
				if(velX < 0)
					velX = -0.77;
				else
					velX = 0.77;
			}
			if(y > Game.HEIGHT * Game.SCALE || y < -5 || x < 0 || x+5 > Game.WIDTH * Game.SCALE) {
				game.ee.remove(this);
			}
		}
		else {
			Random rand = new Random();
			int i = rand.nextInt(37);
			if(i<5){
				Rectangle brick = new Rectangle((int)this.getX(),(int)this.getY(),5,5);
				for(int j = game.getBb().wall.size(); j > 0; j--){
					if(brick.contains(game.getBb().wall.get(j-1))){
						game.getBb().wall.remove(j-1);
					}
				}
			}
			else if(i>5 && i<9){
				Rectangle brick = new Rectangle((int)this.getX(),(int)this.getY(),15,15);
				for(int j = game.getBb().wall.size(); j > 0; j--){
					if(brick.contains(game.getBb().wall.get(j-1))){
						game.getBb().wall.remove(j-1);
					}
				}
			}/*
			else if(i == 19){
				Rectangle brick = new Rectangle((int)this.getX(),(int)this.getY(),40,40);
				for(int j = game.getBb().wall.size(); j > 0; j--){
					if(brick.contains(game.getBb().wall.get(j-1))){
						game.getBb().wall.remove(j-1);
					}
				}
			}*/
			y+=0.01;
			if(velX < -0.33)
				velX = -0.33;
			else if(velX > 0.33)
				velX = 0.33;
			velY = 0.33;
		}
		shrapnel.runAnimation();
	}

	public void render(Graphics g) {
		if(Game.Health > 0)
			shrapnel.drawAnimation(g, x, y, 0);
		else if(hitIndicator){
			glowingShrapnel.drawAnimation(g, x, y, 0);
			if(System.currentTimeMillis()%60 == 0)
				glowingShrapnel.runAnimation();
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 5, 5);
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
		return "bombOmbShrapnel2";
	}
	
	public void setHitIndicator(boolean b) {
		this.hitIndicator = b;
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
