package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class Bowser extends GameObject implements EntityB{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private Controller c;
	private double timer1 = 100;
	private double timer2 = 0;
	private double hitTimer = 0;
	public double speedIncrease = 0.1;
	protected double velX, velY;
	Random r = new Random();
	
	Animation anim;
	Animation animEntrance;
	Animation animHit;
	
	public Bowser(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		velX = 0;
		velY = 2;
		
		animEntrance = new Animation(20, tex.bowserEntrance[0],tex.bowserEntrance[1],
				tex.bowserEntrance[2],tex.bowserEntrance[3],
				tex.bowserEntrance[4],tex.bowserEntrance[5],
				tex.bowserEntrance[6],tex.bowserEntrance[7]);
		
		animHit = new Animation(6,tex.bowserHit[0], tex.bowserHit[1]);
		anim = new Animation(38, tex.bowser[0],tex.bowser[1]);
	}
	
	public void tick(){
		if(timer1 > 0){
			x += 1;
			//y += velY;
			timer1 = timer1 - 1;
			animEntrance.runAnimation();
		}
		if(timer2 > 0){
			//y += velY;
			timer2--;
			animEntrance.runAnimation();
		}
		if(hitTimer > 0){
			hitTimer--;
			animHit.runAnimation();
		}
		if (game.enemyHitRightBarrier == false){
			x+=game.enemySpeedIncrease; //x+=1;
		}
		
		if (x >= (Game.WIDTH * Game.SCALE)- 55 || game.enemyHitRightBarrier == true){
			int i = r.nextInt(10);
			if (barrier == false && i == 0 && y < ((Game.HEIGHT * Game.SCALE)/2) - 16)
				y += 16;
			barrier = true;
			game.enemyHitRightBarrier = true;
			//y +=16;
		}
		
		if (game.enemyHitRightBarrier == true){
			x-=game.enemySpeedIncrease; //x-= 1;
		}
		
		if (x <= 0 || game.enemyHitRightBarrier == false){
			int i = r.nextInt(10);
			if (barrier == true && i == 0 && y < ((Game.HEIGHT * Game.SCALE)/2) - 16)
				y +=16;
			barrier = false;
			game.enemyHitRightBarrier = false;
		}
		
		if (y >= game.getHeight()){
			game.gameOverBoolean = true;
		}
		
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				if (game.eb.size() == 2)
					game.enemySpeedIncrease+= 0.9;
				game.enemySpeedIncrease+= 0.2; //0.7
				HUD.HEALTH -= 2;
				hitTimer = 80;
				if(game.ea.size() >= 1)
					game.ea.removeLast();
				//c.removeEntity(tempEnt);
				//c.removeEntity(this);
			}
		}
		
		anim.runAnimation();
		animHit.runAnimation();
		animEntrance.runAnimation();
	}
	
	public void render(Graphics g){
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				timer2 = 50;
			}
		}
		if(timer1 > 0)
			animEntrance.drawAnimation(g, x, y, 0);
		else if(hitTimer > 0){
			animHit.drawAnimation(g, x, y, 0);
		}
		else
			anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 64, 50);
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setSpeed(double speed){
		this.speedIncrease = speed;
	}
	
	public double getX() {
		return x;
	}

	public double getY(){
		return y;
	}
	
	public double getTimer1(){
		return timer1;
	}

}
