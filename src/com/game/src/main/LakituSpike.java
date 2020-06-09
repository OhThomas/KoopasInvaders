package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;
import com.game.src.main.libs.Animation;

public class LakituSpike  extends GameObject implements EntityE{
	private Textures tex;
	private Game game;
	private Animation spike;
	private double velX = 0;
	private double velY = 0;
	private long popOffTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private long flickerTimer1 = 0;									
	private long flickerTimer2 = 0;				
	private boolean velXBoolean = false;
	private boolean hitIndicator = false;
	public LakituSpike(double x, double y,Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		spike = new Animation(6, tex.lakituSpike[0], tex.lakituSpike[1]);
		spike.nextFrame();
		spike.setCount(0);
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
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(game.ea.get(i).getBounds().intersects(this.getBounds())) {
				//ADD SFX
				boolean remove = false;
				if(Game.currentlySelectedFireball == 0) {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Fireball"));
				}
				else if(Game.currentlySelectedFireball == 1) {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"GreenShell"));
				}
				else if(Game.currentlySelectedFireball == 2) {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"RedShell"));
				}
				else if(Game.currentlySelectedFireball == 3) {
					game.getController().addEntity(new ChompFX(game,x+4,y+4,"Spike"));
					remove = true;
					//game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"BuzzyBeetleShell"));
				}
				else if(Game.currentlySelectedFireball == 4) {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"GloveFireball"));
				}
				else if(Game.currentlySelectedFireball == 5) {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"ContraFireball"));
				}
				if(game.ea.size() >= 1 && Game.currentlySelectedFireball != 3) {
					game.ea.removeLast();
					Game.soundFireballPop();
				}
				if(remove)
					game.getController().removeEntity(this);
			}
		}
		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
			if(Physics.Collision(this,tempEnt) && !tempEnt.getEntityBDead()) {
				if(!tempEnt.enemyType().equals("Bowser")) {
					game.eb.get(i).setEntityBDead(true);
				}
				else {
					HUD.HEALTH -= 8;
					game.getController().addEntity(new ChompFX(game,x+4,y+4,"Spike"));
					game.getController().removeEntity(this);
				}
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(Physics.Collision(this,tempEnt) && !tempEnt.getEntityCDead()) {
				//ADD SFX
				if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
					game.getController().addEntity(new ChompFX(game,x+4,y+4,"Spike"));
					game.getController().removeEntity(this);
				}
				else {
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,tempEnt.entityName()));
					game.ec.get(i).setEntityCDead(true);
				}
			}
		}
		for(int i = 0; i < game.ee.size(); i++) {
			EntityE tempEnt = game.ee.get(i);
			if(Physics.Collision(this,tempEnt) && !tempEnt.entityName().equals("lakitu")){
				if(tempEnt.getEntityEDead() ) {
					
				}
				else {
					if(tempEnt.entityName().equals("chainChomp") || tempEnt.entityName().equals("amp")) {
						game.getController().addEntity(new ChompFX(game,x+4,y+4,"Spike"));
						game.getController().removeEntity(this);
					}
					else if(tempEnt.entityName().equals("bombOmb") || tempEnt.entityName().equals("bulletBill")) {
						game.ee.get(i).setEntityEDead(true);
						game.getController().addEntity(new ChompFX(game,x+4,y+4,"Spike"));
						game.getController().removeEntity(this);
					}
					else if(tempEnt.entityName().equals("bombOmbShrapnel1") || tempEnt.entityName().equals("bombOmbShrapnel2")) {
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,tempEnt.entityName()));
						game.ee.get(i).setEntityEDead(true);
					}
					else if(tempEnt.entityName().equals("cheepCheeps") || (tempEnt.entityName().equals("lakituRedShell") && popOffTimer < System.currentTimeMillis())) {
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,tempEnt.entityName()));
						game.ee.remove(i);
					}
				}
			}
		}
		spike.runAnimation();
		y+=velY;
		x+=velX;
		if(Physics.Collision(this, game.getBb())){
			y -= velY/1.4;
			x-=velX/1.1;
		}
		if(Game.WIDTH * Game.SCALE < x+getBounds().width)
			x++;
		if(x < 0)
			x--;
		if(Game.WIDTH * Game.SCALE < x || x+getBounds().width < 0  || (Game.HEIGHT * Game.SCALE)+20 < y)
			game.ee.remove(this);
	}

	public void render(Graphics g) {
		if(hitIndicator) {
			if(flickerTimer1 == 0 && flickerTimer2 == 0)
				flickerTimer1 = System.currentTimeMillis() + 250;
			if(flickerTimer1 < System.currentTimeMillis() && flickerTimer2 == 0) {
				flickerTimer1 = 0;
				flickerTimer2 = System.currentTimeMillis() + 250;
			}
			if(flickerTimer2 < System.currentTimeMillis() && flickerTimer1 == 0) {
				flickerTimer2 = 0;
				flickerTimer1 = System.currentTimeMillis() + 250;
			}
			if(flickerTimer1 < flickerTimer2) 
				return;
		}
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < popOffTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		spike.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		if(spike.getCount() == 0)
			return new Rectangle((int)x, (int)y, 16, 16);
		else
			return new Rectangle((int)x, (int)y, 14, 14);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String entityName() {
		return "lakituSpike";
	}

	public void close() {
		//g2d.dispose();
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void setScoreFollowMe(boolean b) {
		
	}

	public void setHitIndicator(boolean b) {
		this.hitIndicator = b;
	}

	public boolean getHitIndicator() {
		return hitIndicator;
	}

	public boolean getEntityEDead() {
		return false;
	}

	public void setEntityEDead(boolean dead) {
		if(dead) {
			game.getController().addEntity(new ChompFX(game,x+4,y+4,"Spike"));
			game.ee.remove(this);
		}
	}

}
