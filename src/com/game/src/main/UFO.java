package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class UFO extends GameObject implements EntityB{

	private Game game;
	private Controller c;
	private double x;
	private double y;
	private double velX;
	private double velY;
	private int width = 40;
	private int height = 18;
	private boolean ufoisDead = false;
	private double imageRotate = 0;
	private int yImpact = -1;//-1 - velY=0; 0 - velY=-5; 1 - velY=5;
	private long traverseTime = 0;
	private String enemyType = "ufo";
	Animation anim;
	Animation animExplosion;
	
	public UFO(double x, double y, Textures tex, Controller c, Game game) {
		super(x, y);
		this.game = game;
		this.c = c;
		this.x = x;
		this.y = y;
		anim = new Animation(6, tex.ufo[0], tex.ufo[1], tex.ufo[2], tex.ufo[3], tex.ufo[4], tex.ufo[5],
				 tex.ufo[6], tex.ufo[7], tex.ufo[8], tex.ufo[9]);
		animExplosion = new Animation(2, tex.ufoExplosion[0], tex.ufoExplosion[1], tex.ufoExplosion[2], tex.ufoExplosion[3],
				 tex.ufoExplosion[4], tex.ufoExplosion[5], tex.ufoExplosion[6], tex.ufoExplosion[7], tex.ufoExplosion[8],
				 tex.ufoExplosion[9]);
		this.anim.nextFrame();
		this.anim.setCount(0);
		this.animExplosion.nextFrame();
		this.animExplosion.setCount(0);
		if(x >= Game.WIDTH * Game.SCALE)
			velX = -10;
		else
			velX = 10;
	}

	public void tick() {
		if(ufoisDead) {
			switch(yImpact) {
			case -1:
				velY=0;
				break;
			case 0:
				velY=-0.5;
				break;
			case 1:
				velY=0.5;
				break;
			default:
				velY=0;
				break;
			}
			if(animExplosion.getCount() == 9)
				c.removeEntity(this);
			animExplosion.runAnimation();
			x+=velX;
			y+=velY;
		}
		else {
			x+=velX;
			anim.runAnimation();
			if(traverseTime != System.currentTimeMillis() && System.currentTimeMillis() % 2 == 0) {
				anim.setCurrentImage(anim.rotateImage(anim.getCurrentImage(),imageRotate));
				traverseTime = System.currentTimeMillis();
			}
			if(imageRotate == 2.85) {
				imageRotate = -2.85;
			}
			else
				imageRotate = 2.85;
			for(int i = 0; i < StarExplosion.p.size(); i++){
				Rectangle tempEnt = StarExplosion.p.get(i).getBounds();
				if(Physics.Collision(this, tempEnt)){
					if(this.getY()+this.getHeight()+45 <= Game.my)
						yImpact = 0;
					else if(this.getY()+this.getHeight() > Game.my && this.getY()+this.getHeight() < Game.my)
						yImpact = -1;
					else if(this.getY()+this.getHeight()-45 >= Game.my)
						yImpact = 1;
					if(velX > 0) {
						if(this.getX()+this.getWidth() <= Game.mx)
							velX = 5;//2
						else if(this.getX()+this.getWidth() > Game.mx && this.getX()+this.getWidth() < Game.mx)
							velX = 0;
						else
							velX = 8;
					}
					else if(velX < 0) {
						if(this.getX()+this.getWidth() <= Game.mx)
							velX = -8;
						else if(this.getX()+this.getWidth() > Game.mx && this.getX()+this.getWidth() < Game.mx)
							velX = 0;
						else
							velX = -5;//-2
					}
					//playdeathsound
					ufoisDead = true;
					Game.alienisDead = true;
				}
			}
			if(x < -41 || x > Game.WIDTH*Game.SCALE +41)
				c.removeEntity(this);
		}
	}

	public void render(Graphics g) {
		if(ufoisDead) {
			animExplosion.drawAnimation(g, x, y, 0);
		}
		else {
			anim.drawAnimation(g, x, y, 0);
			
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean getEntityBDead() {
		return ufoisDead;
	}
	
	public void setEntityBDead(boolean dead) {
		this.ufoisDead = dead;
	}

	public String enemyType() {
		return enemyType;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void renderFlicker() {
		
	}
	
	public void close() {
		return;
	}
}
