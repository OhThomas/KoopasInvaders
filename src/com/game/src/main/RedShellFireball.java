package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class RedShellFireball extends GameObject implements EntityA {
	
	private Textures tex;
	private Game game;
	Animation anim;
	double velX = 0;
	int ySpeed = 0;
	private int xDestination = 10000;
	private int ebMarked = -1;
	private int xDirection = -1;
	public RedShellFireball(double x, double y, Textures tex, Double velX, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		if(Game.WIDTH * Game.SCALE <= this.x+16) {
			this.x-=11;
			if(velX > 0)
				velX = 0.0;
		}
		else if(x <= 0) {
			this.x+=1;
			if(velX < 0)
				velX = 0.0;
		}
		if(velX < 0) {
			this.velX = -2.4;
			ySpeed = 6;//8
		}
		else if(velX > 0) {
			this.velX = 2.4;
			ySpeed = 6;//8
		}
		else if(velX == 0)
			ySpeed = 6;
		if(!game.eb.isEmpty()){
			for(int i = 0; i < game.eb.size(); i++){

				EntityB tempEnt = game.eb.get(i);
				if((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1){
						xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
						ebMarked = i;
				}
				if(Physics.Collision(this, tempEnt)){
					if(!tempEnt.enemyType().equals("Bowser")) {
						//if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
						//	this.itemSoundLoop.play();
						//	this.itemSoundLoop.setSoundLoopBoolean(true);
						//}
						ebMarked = -1;
					}
					else {
						HUD.HEALTH -= 4;
						//chainChompisDead = true;
						//MAKE EXPLOSION SOUND
						//Death Sound
					}
				}
			}
			if(ebMarked != -1 && ebMarked < game.eb.size()){
				if(game.eb.get(ebMarked).getX() < this.getX())
					xDirection = 1;//x--
				else
					xDirection = 0;
			}
		}
		anim = new Animation(5,tex.redShell[0],tex.redShell[1],tex.redShell[2],tex.redShell[3]);
	}
	
	public void tick(){
		if(!Physics.Collision(this, game.getBb())){
			y -= ySpeed;
			if(xDestination < 15 && y < 370)
				x+=velX*1.5;
			x+=velX;
			if (this.getY() <= -8)
				game.ea.remove(this);
			if(velX != 0 && (x+16 >= Game.WIDTH * Game.SCALE || x <= 0))
				velX = velX * -1;
			if(xDirection == 1) {
				if(velX> -1.8)
					velX = velX - 0.05;
			}else if(xDirection == 0) {
				if(velX<1.8)
					velX = velX + 0.05;
			}
			if(!game.eb.isEmpty()){
				for(int i = 0; i < game.eb.size(); i++){
	
					EntityB tempEnt = game.eb.get(i);
					if((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1){
							xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
							ebMarked = i;
					}
					if(Physics.Collision(this, tempEnt)){
						if(!tempEnt.enemyType().equals("Bowser")) {
							//if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
							//	this.itemSoundLoop.play();
							//	this.itemSoundLoop.setSoundLoopBoolean(true);
							//}
							ebMarked = -1;
						}
						else {
							HUD.HEALTH -= 4;
							//chainChompisDead = true;
							//MAKE EXPLOSION SOUND
							//Death Sound
						}
					}
				}
			}
			else if(game.eb.isEmpty()){
				xDirection = -1;
			}
			if(ebMarked != -1 && ebMarked < game.eb.size()){
				if(xDirection == 0 && game.eb.get(ebMarked).getX() < this.getX())
					xDirection = 1;//x--
				else if(xDirection == 1 && game.eb.get(ebMarked).getX() > this.getX())
					xDirection = 0;
			}
			
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
						if(game.getBb().wall.get(j-1).getY() <= this.y && 
							((velX < 0 && game.getBb().wall.get(j-1).getX() < this.x) ||
							(velX > 0 && game.getBb().wall.get(j-1).getX() > this.x+8)))
							velX = velX * -1;
						game.getBb().wall.remove(j-1);
					}
				}
			}
			else if(i>5 && i<9){
				Rectangle brick = new Rectangle((int)this.getX(),(int)this.getY(),32,32);
				for(int j = game.getBb().wall.size(); j > 0; j--){
					if(brick.contains(game.getBb().wall.get(j-1))){
						if(game.getBb().wall.get(j-1).getY() <= this.y && 
							((velX < 0 && game.getBb().wall.get(j-1).getX() < this.x) ||
							(velX > 0 && game.getBb().wall.get(j-1).getX() > this.x+8)))
							velX = velX * -1;
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
			if(!game.eb.isEmpty()){
				if(ebMarked != -1 && ebMarked < game.eb.size()){
					if(game.eb.get(ebMarked).getX() < this.getX())
						xDirection = 0;//x--
					else
						xDirection = 1;
				}
				for(int j = 0; j < game.eb.size(); j++){
	
					EntityB tempEnt = game.eb.get(j);
					if((Math.abs((int)this.getX() - (int)game.eb.get(j).getX()) < xDestination) || game.eb.size() == 1){
							xDestination = Math.abs((int)this.getX() - (int)game.eb.get(j).getX());
							ebMarked = j;
					}
					if(Physics.Collision(this, tempEnt)){
						if(!tempEnt.enemyType().equals("Bowser")) {
							//if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
							//	this.itemSoundLoop.play();
							//	this.itemSoundLoop.setSoundLoopBoolean(true);
							//}
							ebMarked = -1;
						}
						else {
							HUD.HEALTH -= 4;
							//chainChompisDead = true;
							//MAKE EXPLOSION SOUND
							//Death Sound
						}
					}
				}
			}
			else if(game.eb.isEmpty()){
				xDirection = -1;
			}
			if(ebMarked != -1 && ebMarked < game.eb.size()){
				if(xDirection == 0 && game.eb.get(ebMarked).getX() < this.getX())
					xDirection = 1;//x--
				else if(xDirection == 1 && game.eb.get(ebMarked).getX() > this.getX())
					xDirection = 0;
			}
			y-=0.5;
			x+=(velX/6);
		}
		anim.runAnimation();
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
