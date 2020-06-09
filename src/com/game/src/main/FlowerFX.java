package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;

public class FlowerFX  extends GameObject implements EntityF{
	private Textures tex;
	private Game game;
	private BufferedImage flower;
	private double velX = 0;
	private double velY = 0;
	private int width = 8;
	private int height = 8;
	private long popOffTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private boolean velXBoolean = false;
	public FlowerFX(double x, double y,Textures tex, Game game, int setup) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		if(setup == 0)
			flower = tex.wigglerItemFlower[0];
		else
			flower = tex.wigglerItemFlower[1];
		popOffTimer = System.currentTimeMillis() + 200;
			
	}

	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(popOffTimer != 0)
					popOffTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(System.currentTimeMillis() < popOffTimer) {
			if(velY > -2.5) {
				velY-=0.15;
			}
		}
		else {
			if(velY < 2)
				velY+=0.1;
		}
		
		if(velXBoolean == false) {
			velX-=0.05;
			if(velX <= -0.4)
				velXBoolean = true;
		}
		else {
			velX+=0.05;
			if(velX >= 0.4)
				velXBoolean = false;
		}
		if(game.getPlayer().getBounds().intersects(this.getBounds())) {
			//ADD SFX
			game.getController().addEntity(new ChompFX(game,x+4,y,"Flower"));
			game.getController().removeEntity(this);
		}
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(game.ea.get(i).getBounds().intersects(this.getBounds())) {
				//ADD SFX
				game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Flower"));
				game.getController().removeEntity(this);
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(game.ec.get(i).getBounds().intersects(this.getBounds())) {
				//ADD SFX
				game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Flower"));
				game.getController().removeEntity(this);
			}
		}
		for(int i = 0; i < game.ee.size(); i++) {
			EntityE tempEnt = game.ee.get(i);
			if(game.ee.get(i).getBounds().intersects(this.getBounds()) && !tempEnt.getEntityEDead() && !tempEnt.entityName().equals("wiggler") && !tempEnt.entityName().equals("wigglerBody")){
				game.getController().addEntity(new ChompFX(game,x+4,y,"Flower"));
				game.getController().removeEntity(this);
			}
		}
		
		y+=velY;
		x+=velX;
	}

	public void render(Graphics g) {
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < popOffTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		g.drawImage(flower,(int)x,(int)y,null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getVelX() {
		return 0;
	}

	public double getVelY() {
		return 0;
	}

	public String entityName() {
		return "flowerFX";
	}

	public void close() {
		//g2d.dispose();
	}

}
