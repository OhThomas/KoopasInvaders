package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class GloveFireball extends GameObject implements EntityA {
	
	private Textures tex;
	private Game game;
	double starVel = 1;
	
	Animation anim;
	
	public GloveFireball(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		if(game.getPlayer().getMarioInvincible())
			starVel = 1.5;
		else if(game.characterSkinPosition == 4)
			starVel = 1.2;
		anim = new Animation(5,tex.fireballMikeTyson[0],tex.fireballMikeTyson[1],tex.fireballMikeTyson[2],tex.fireballMikeTyson[3]);
	}
	
	public void tick(){
		if(!Physics.Collision(this, game.getBb())){
			y -= 8*starVel;
			if (this.getY() <= -8)
				game.ea.remove(this);
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
		}
		anim.runAnimation();
	}
	
	public Rectangle getBounds(){
		if(anim.getCount() == 1)
			return new Rectangle((int)x, (int)y, 12, 15);
		else if(anim.getCount() == 2)
			return new Rectangle((int)x, (int)y, 14, 12);
		else if(anim.getCount() == 3)
			return new Rectangle((int)x, (int)y, 12, 14);
		else if(anim.getCount() == 4)
			return new Rectangle((int)x, (int)y, 15, 12);
		else
			return new Rectangle((int)x, (int)y, 12, 15);
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
