package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class BuzzyBeetleShellFireball extends GameObject implements EntityA {
	
	private Textures tex;
	private Game game;
	Animation anim;
	double velX = 0;
	double starVel = 1;
	public BuzzyBeetleShellFireball(double x, double y, Textures tex, Double velX, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		if(game.getPlayer().getMarioInvincible())
			starVel = 1.5;
		if(velX!=0) {
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
			if(velX < 0)
				this.velX = -5;
			else if(velX > 0)
				this.velX = 5;
		}
		anim = new Animation(5,tex.buzzyBeetleShell[0],tex.buzzyBeetleShell[1],tex.buzzyBeetleShell[2],tex.buzzyBeetleShell[3]);
	}
	
	public void tick(){
		if(!Physics.Collision(this, game.getBb())){
			y -= 8*starVel;
			x+=velX*starVel;
			if (this.getY() <= -8)
				game.ea.remove(this);
			if(velX != 0 && (x+16 >= Game.WIDTH * Game.SCALE || x <= 0))
				velX = velX * -1;
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
			}/*
			else if(i == 19){
				Rectangle brick = new Rectangle((int)this.getX(),(int)this.getY(),40,40);
				for(int j = game.getBb().wall.size(); j > 0; j--){
					if(brick.contains(game.getBb().wall.get(j-1))){
						game.getBb().wall.remove(j-1);
					}
				}
			}*/
			y-=0.5*starVel;
			x+=(velX/12)*starVel;
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
