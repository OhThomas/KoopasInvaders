package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class GreenShell extends GameObject implements EntityC{

	
	private Textures tex;
	private Game game;
	
	Animation anim;
	
	public GreenShell(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(4,tex.greenShell[0],tex.greenShell[1],tex.greenShell[2],tex.greenShell[3]);
	}
	
	public void tick(){
		if(!Physics.Collision(this, game.getBb())){
			y += 6;
			if (y >= game.getHeight())
				game.ec.remove(this);
			/*
			if(Physics.Collision(this, game.eb)){
				System.out.println("Collision detected!");
			}
			*/
			for(int i = 0; i < game.ea.size(); i ++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					game.ec.remove(this);
					if(!game.ea.isEmpty())
						game.ea.remove(game.ea.getLast());
				}
			}
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
			}
			y+=0.3;
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

	public boolean getEntityCDead() {
		return false;
	}
}
