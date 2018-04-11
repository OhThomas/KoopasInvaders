package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class ChainChomp extends GameObject implements EntityE {

	private Textures tex;
	private Game game;
	private int xDestination = 10000;
	private int ecMarked = -1;
	Animation anim;
	
	public ChainChomp(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.game = game;
		this.tex = tex;
		
		anim = new Animation(6,tex.chainChomp[0], tex.chainChomp[0],tex.chainChomp[1]);
		anim.nextFrame();
		// TODO Auto-generated constructor stub
	}

	public void tick(){
		if(!game.ec.isEmpty()){
			if(ecMarked != -1){
				if(game.ec.get(ecMarked).getX() < this.getX())
					x--;
				else
					x++;
			}
			for(int i = 0; i < game.ec.size(); i++){

				EntityC tempEnt = game.ec.get(i);
				if((Math.abs((int)this.getX() - (int)game.ec.get(i).getX()) < xDestination)){
						xDestination = Math.abs((int)this.getX() - (int)game.ec.get(i).getX());
						ecMarked = i;
				}
				if(Physics.Collision(this, tempEnt)){
					game.ec.remove(i);
					game.setEnemyHitPauseTimer(System.currentTimeMillis() + 500);
					game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 300);
					ecMarked = -1;
				}
			}
		}
		y--;
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
