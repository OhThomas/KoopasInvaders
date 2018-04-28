/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

// TODO: Auto-generated Javadoc
/**
 * The Class Fireball.
 */
public class Fireball extends GameObject implements EntityA {
	
	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The anim. */
	Animation anim;
	
	/**
	 * Instantiates a new fireball.
	 *
	 * @param x the x
	 * @param y the y
	 * @param tex the tex
	 * @param game the game
	 */
	public Fireball(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(5,tex.fireball[0],tex.fireball[1],tex.fireball[2],tex.fireball[3]);
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#tick()
	 */
	public void tick(){
		if(!Physics.Collision(this, game.getBb())){
			y -= 8;
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
			y-=0.5;
		}
		anim.runAnimation();
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#getBounds()
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#render(java.awt.Graphics)
	 */
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#getX()
	 */
	public double getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#getY()
	 */
	public double getY() {
		return y;
	}
}
