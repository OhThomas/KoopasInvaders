package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class BulletBill extends GameObject implements EntityC{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private float timer1 = 100;										//timer for chasing sequence
	private int i = 0;												//replace with random value
	public double speedIncrease = 0.1;								//way to set speed
	
	Animation animD;
	Animation animDL;
	Animation animDR;
	
	public BulletBill(double x, double y, Textures tex, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		
		animD = new Animation(6, tex.bulletBillD[0], tex.bulletBillD[1], tex.bulletBillD[2], tex.bulletBillD[3],
				 tex.bulletBillD[4], tex.bulletBillD[5], tex.bulletBillD[6], tex.bulletBillD[7], tex.bulletBillD[8],
				 tex.bulletBillD[9], tex.bulletBillD[10], tex.bulletBillD[11], tex.bulletBillD[12], tex.bulletBillD[13],
				 tex.bulletBillD[14], tex.bulletBillD[15], tex.bulletBillD[16]);
		
		animDL = new Animation(6, tex.bulletBillDL[0], tex.bulletBillDL[1], tex.bulletBillDL[2], tex.bulletBillDL[3],
				 tex.bulletBillDL[4], tex.bulletBillDL[5], tex.bulletBillDL[6], tex.bulletBillDL[7],
				 tex.bulletBillDL[8], tex.bulletBillDL[9]);
		
		animDR = new Animation(6, tex.bulletBillDR[0], tex.bulletBillDR[1], tex.bulletBillDR[2], tex.bulletBillDR[3],
				 tex.bulletBillDR[4], tex.bulletBillDR[5], tex.bulletBillDR[6], tex.bulletBillDR[7],
				 tex.bulletBillDR[8], tex.bulletBillDR[9]);
	}
	
	public void tick(){
		if((int)timer1 > 0){									//Chases Player
			if(this.getX() > game.playerX())
				x--;
			else
				x++;
			if(this.getY() > game.playerY())
				y++;
			else
				y++;
			timer1--;
		}
		else{
			Random rand = new Random();
			int i = rand.nextInt(130);
			y++;
			if (i == 18){										//Resets timer1 so he chases Player
				timer1 = 100;
			}
		}
		if (this.getX() >= (Game.WIDTH * Game.SCALE)){			//Removing BulletBill from Game
			game.ec.remove(this);
		}
		
		if (x <= 0){
			game.ec.remove(this);
		}
		
		if (y >= game.getHeight()){
			game.ec.remove(this);
		}
		
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			/*
			if(Physics.Collision(this, tempEnt)){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
			}
			*/
			if(Physics.Collision(this, tempEnt)){
				game.ec.remove(this);
				if(!game.ea.isEmpty())
					game.ea.remove(game.ea.getLast());
			}
		}
		
		
		animD.runAnimation();
		animDL.runAnimation();
		animDR.runAnimation();
	}
	
	public void render(Graphics g){
		if(this.getX() > game.playerX()+6.4 && (int)this.timer1 > 0)
			animDL.drawAnimation(g, x, y, 0);
		else if(this.getX() < game.playerX()-6.4 && (int)this.timer1 > 0)
			animDR.drawAnimation(g, x, y, 0);
		else// if(this.getX() == game.playerX() || (int)this.timer1 <= 0)
			animD.drawAnimation(g, x, y, 0);
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
