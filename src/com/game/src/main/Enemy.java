package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class Enemy extends GameObject implements EntityB{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private Controller c;
	public double speedIncrease = 0.1;
	
	Animation anim;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		
		anim = new Animation(6, tex.enemy[0],tex.enemy[1]);
	}
	
	public void tick(){
		if (game.enemyHitRightBarrier == false){
			x+=game.enemySpeedIncrease; //x+=1;
		}
		
		if (x >= (Game.WIDTH * Game.SCALE) || game.enemyHitRightBarrier == true){
			/*
			for(int i = 0; i <= game.eb.size(); i++)
			{
				//game.eb.get(i)
				//if(game.ea.isEmpty())
				//	System.out.println("EMPTY");
				System.out.println("HIGH");
				//INCREASE ALL UP y+=16
			}
			*/
			//game.enemySpeedIncrease += 0.1;
			if (barrier == false)
				y += 16;
			barrier = true;
			game.enemyHitRightBarrier = true;
			//y +=16;
		}
		
		if (game.enemyHitRightBarrier == true){
			x-=game.enemySpeedIncrease; //x-= 1;
		}
		
		if (x <= 0 || game.enemyHitRightBarrier == false){
			//game.enemySpeedIncrease += 0.1;
			if (barrier == true)
				y +=16;
			barrier = false;
			game.enemyHitRightBarrier = false;
			//y +=16;
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
				c.removeEntity(tempEnt);
				c.removeEntity(this);
			}
		}
		
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
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

}
