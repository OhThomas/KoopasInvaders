package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class Fireball extends GameObject implements EntityA {
	
	private Textures tex;
	private Game game;
	
	Animation anim;
	
	public Fireball(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(5,tex.fireball[0],tex.fireball[1],tex.fireball[2],tex.fireball[3]);
	}
	
	public void tick(){
		y -= 8;
		if (this.getY() <= 0)
			game.ea.remove(this);
		/*
		if(Physics.Collision(this, game.eb)){
			System.out.println("Collision detected!");
		}
		*/
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
