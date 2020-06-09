package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;
import com.game.src.main.libs.Animation;

public class ChainChompChainFX  extends GameObject implements EntityF {
	private Game game;
	private Textures tex;
	private Animation chain;
	private Animation death;
	private EntityE chainChompTrack;
	private EntityF chainChompBallToFollow;
	private long physicsTimer = 0;
	private long physicsTimer2 = 0;
	private double velX = 0;
	private double velY = 0;
	private double velYTracker = 0;
	private double xSwaying = 0;
	private int chainChompBall = 0;
	private boolean velXBoolean = false;
	private boolean swaying = false;
	private boolean dead = false;
	public ChainChompChainFX(double x, double y, Textures tex, Game game, EntityE chainChompNumber) {//, int chainChompBall) {
		super(x, y);
		this.game = game;
		this.tex = tex;
//		chain = new Animation(6,tex.chainChompChainBigger[0],tex.chainChompChainBigger[1],tex.chainChompChainBigger[2],
//				tex.chainChompChainBigger[3],tex.chainChompChainBigger[4]);
		chain = new Animation(6,tex.chainChompChainBigger[0],tex.chainChompChainBigger[1],tex.chainChompChainBigger[2],
				tex.chainChompChainBigger[3],tex.chainChompChainBigger[4],tex.chainChompChainBigger[4],tex.chainChompChainBigger[3],
				tex.chainChompChainBigger[2],tex.chainChompChainBigger[1]);
		death = new Animation(6, tex.chainChompDisintegrate[4], tex.chainChompDisintegrate[5], tex.chainChompDisintegrate[6],
				 tex.chainChompDisintegrate[7]);
//		int tracker = 0;
//		if(game.ef != null && !game.ef.isEmpty()) {
//			for(int i = 0; i < game.ef.size(); i++){
//				EntityF tempEnt = game.ef.get(i);
//				System.out.println("HERE");
//				if(tempEnt == this) {
//					chainChompBall = tracker;
//					break;
//				}
//				if(tempEnt.entityName().equals("chainChompChainFX")) {
//					tracker++;
//				}
//				if(tracker > 2) {
//					tracker = 0;
//					chainChompTrack++;
//				}
//			}
//		}
		this.chainChompTrack = chainChompNumber;
		this.chainChompBall = chainChompBall;
		swaying = true;
		xSwaying = x;
		chain.nextFrame();
		chain.setCount(0);
		death.nextFrame();
		death.setCount(0);
	}
	public ChainChompChainFX(double x, double y, Textures tex, Game game, EntityE chainChompNumber, EntityF chainChompBallToFollow, int ballNum) {//, int chainChompBall) {
		super(x, y);
		this.game = game;
		this.tex = tex;
//		if(ballNum == 0)
//			chain = new Animation(6,tex.chainChompChainBigger[4],tex.chainChompChainBigger[3],tex.chainChompChainBigger[2],
//					tex.chainChompChainBigger[1],tex.chainChompChainBigger[0]);
//		else
//			chain = new Animation(6,tex.chainChompChainBigger[0],tex.chainChompChainBigger[1],tex.chainChompChainBigger[2],
//					tex.chainChompChainBigger[3],tex.chainChompChainBigger[4]);
		chain = new Animation(6,tex.chainChompChainBigger[0],tex.chainChompChainBigger[1],tex.chainChompChainBigger[2],
				tex.chainChompChainBigger[3],tex.chainChompChainBigger[4],tex.chainChompChainBigger[4],tex.chainChompChainBigger[3],
				tex.chainChompChainBigger[2],tex.chainChompChainBigger[1]);
		death = new Animation(6, tex.chainChompDisintegrate[4], tex.chainChompDisintegrate[5], tex.chainChompDisintegrate[6],
				 tex.chainChompDisintegrate[7],tex.chainChompDisintegrate[7]);
		this.chainChompTrack = chainChompNumber;
		this.chainChompBallToFollow = chainChompBallToFollow;
		swaying = true;
		xSwaying = x;
		chain.nextFrame();
		chain.setCount(0);
		death.nextFrame();
		death.setCount(0);
	}
	
	public void tick() {
		int tracker = 0;
		//if(chainChompBall == 0 || chainChompBall % 3 == 0) {
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(game.ea.get(i).getBounds().intersects(this.getBounds())) {
				//ADD SFX
				if(!dead)
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"ChainChompChain"));
				else
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"ChainChompChainDead"));	
			}
		}
		for(int i = 0; i < game.eb.size(); i++){
			if(game.eb.get(i).getBounds().intersects(this.getBounds())) {
				//ADD SFX
				if(!dead)
					game.getController().addEntity(new ChompFX(game,x+5,y+5,"ChainChompChain"));
				else
					game.getController().addEntity(new ChompFX(game,x+5,y+5,"ChainChompChainDead"));
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(game.ec.get(i).getBounds().intersects(this.getBounds())) {
				//ADD SFX
				if(!dead)
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"ChainChompChain"));
				else
					game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"ChainChompChainDead"));
			}
		}
		for(int i = 0; i < game.ee.size(); i++){
			if(!(game.ee.get(i).entityName().equals("chainChomp"))) {
				if(game.ee.get(i).getBounds().intersects(this.getBounds())) {
					//ADD SFX
					if(!dead)
						game.getController().addEntity(new ChompFX(game,x+5,y+5,"ChainChompChain"));
					else
						game.getController().addEntity(new ChompFX(game,x+5,y+5,"ChainChompChainDead"));
				}
			}
		}
		outer:
		if(chainChompBallToFollow == null && !dead) {
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(tempEnt.entityName().equals("chainChomp")) {
					if(tempEnt == chainChompTrack) {
						//CHECK FOR DEATH
						if(tempEnt.getEntityEDead()) {
							dead = true;
						}
						if(tempEnt.getVelY() == 0 && velYTracker != tempEnt.getVelY()) {
							Random rand = new Random();
							physicsTimer = System.currentTimeMillis() + 30 + rand.nextInt(80);
							velYTracker = tempEnt.getVelY();
						}
						if(tempEnt.getVelY() != velYTracker) {
							velYTracker = tempEnt.getVelY();
						}
						if(tempEnt.getVelY() != 0) {
							if((this.x+6 < tempEnt.getX() && 0 < tempEnt.getVelX()) || 
									(this.x > tempEnt.getX()+tempEnt.getBounds().width && tempEnt.getVelX() < 0)) 
								this.velX = tempEnt.getVelX();
							else
								this.velX = 0;
							if(tempEnt.getY()+tempEnt.getBounds().height+5 < y)
								this.velY = tempEnt.getVelY();
							if(this.x+6 < tempEnt.getX()+4 && 0 < tempEnt.getVelX()) 
								this.x++;
							if(this.x > tempEnt.getX()+tempEnt.getBounds().width-4)
								this.x--;
							if(tempEnt.getY()+tempEnt.getBounds().height+5+4 < y && y > tempEnt.getY()+tempEnt.getBounds().height-5)
								y--;
							swaying = false;
//							this.y = tempEnt.getY()+tempEnt.getBounds().height+5;
						}
						else if(System.currentTimeMillis() < physicsTimer) {
							if(this.x +3-this.getBounds().width/2 < tempEnt.getX()+2) {
								if(velX < 1)
									velX = velX + 0.02;
							}
							else if(this.x+2+3-this.getBounds().width/2 > tempEnt.getX()) {
								if(velX> -1)
									velX = velX - 0.02;
							}
							else {
								if(velX < 0)
									velX += 0.01;
								else
									velX -= 0.01;
							}
							Random rand = new Random();
							if(rand.nextBoolean() == true)
								this.y--;
							swaying = false;
						}
						else {
							if(!swaying) {
							swaying = true;
							xSwaying = x;
							if(velX < 0)
								velX = -1;
							else
								velX = 1;
							}
//							velX = 0;
							velY = 0;
						}
						if(y <= tempEnt.getY()+tempEnt.getBounds().height-5)
							velY=0;
						switch(chainChompBall) {
						case 0:
							if(this.x -10 > tempEnt.getX() ) {
	//							if(velX> -2)
	//								velX = velX - 0.2;
							}
							if(this.x +10 < tempEnt.getX()) {
	//							if(velX< 2)
	//								velX = velX + 0.2;
							}
							if(this.y -10 < tempEnt.getY()) {
	//							if(velY> -3)
	//								velY--;
							}
	//						this.x = tempEnt.getX()-3+tempEnt.getBounds().width/2;
//							this.y = tempEnt.getY()+tempEnt.getBounds().height+10;
							break;
						case 1:
							if(this.x -20 > tempEnt.getX() ) {
	//							if(velX> -2)
	//								velX = velX - 0.2;
							}
							if(this.x +20 < tempEnt.getX()) {
	//							if(velX< 2)
	//							velX = velX + 0.2;
							}
							if(this.y -20 < tempEnt.getY()) {
	//							if(velY> -3)
	//							velY--;
							}
	//						this.x = tempEnt.getX()-3+tempEnt.getBounds().width/2;
//							this.y = tempEnt.getY()+tempEnt.getBounds().height+20;
							break;
						case 2:
							if(this.x -30 > tempEnt.getX() ) {
	//							if(velX> -2)
	//							velX = velX - 0.2;
							}
							if(this.x +30 < tempEnt.getX()) {
	//							if(velX< 2)
	//							velX = velX + 0.2;
							}
							if(this.y -30 < tempEnt.getY()) {
	//							if(velY> -3)
	//							velY--;
							}
	//						this.x = tempEnt.getX()-3+tempEnt.getBounds().width/2;
//							this.y = tempEnt.getY()+tempEnt.getBounds().height+30;
							break;
						default:
							break;
						}
						break;
					}
					else
						tracker++;
				}
			}
		}
		else if(!dead){
			for(int i = 0; i < game.ee.size(); i++) {
				EntityE tempEnt = game.ee.get(i);
				if(tempEnt == chainChompTrack) {
					//CHECK FOR DEATH
					if(tempEnt.getEntityEDead()) {
						dead = true;
						break outer;
					}
					if(tempEnt.getVelY() == 0 && velYTracker != tempEnt.getVelY()) {
						Random rand = new Random();
						physicsTimer = System.currentTimeMillis() + 180 + rand.nextInt(80) + (20*this.chainChompBall);
						velYTracker = tempEnt.getVelY();
					}
					if(tempEnt.getVelY() != 0 && velYTracker != tempEnt.getVelY()) {
						Random rand = new Random();
						physicsTimer2 =  System.currentTimeMillis() + 120 + (20*this.chainChompBall);
						velYTracker = tempEnt.getVelY();
						swaying = false;
					}
					if(tempEnt.getVelY() != velYTracker) {
						velYTracker = tempEnt.getVelY();
					}
				}
			}
			if(physicsTimer2 < System.currentTimeMillis()) {
				for(int i = 0; i < game.ef.size(); i++){
					EntityF tempEnt = game.ef.get(i);
					if(tempEnt.entityName().equals("chainChompChainFX")) {
						//if(chainChompBall-1 == tracker) {
						if(tempEnt == chainChompBallToFollow) {
							if(velYTracker != 0) {
								if((this.x+6 < tempEnt.getX() && 0 < tempEnt.getVelX()) || 
										(this.x > tempEnt.getX()+tempEnt.getBounds().width && tempEnt.getVelX() < 0)) 
									this.velX = tempEnt.getVelX();
								else
									this.velX = 0;
								if(tempEnt.getY()+tempEnt.getBounds().height+5 < y)
									this.velY = tempEnt.getVelY();
								if(this.x+6 < tempEnt.getX()+4 && 0 < tempEnt.getVelX()) 
									this.x++;
								if(this.x > tempEnt.getX()+tempEnt.getBounds().width-4)
									this.x--;
								if(tempEnt.getY()+tempEnt.getBounds().height+5+4 < y)
									y--;
	//							this.y = tempEnt.getY()+tempEnt.getBounds().height+5;
							}
							else if(System.currentTimeMillis() < physicsTimer) {
								if(this.x +3-this.getBounds().width/2+10 < tempEnt.getX()) {
	//								if(velX < .5)
	//									velX = velX + 0.002;
									velX = 0.05;
								}
								else if(this.x +3-this.getBounds().width/2-10 > tempEnt.getX()) {
	//								if(velX> -.5)
	//									velX = velX - 0.002;
									velX = -0.05;
								}
								else {
									if(velX < 0)
										velX += 0.001;
									else
										velX -= 0.001;
								}
								Random rand = new Random();
								if(System.currentTimeMillis() < physicsTimer-150 && rand.nextBoolean() == true && y > tempEnt.getY()+7)
									this.y--;
	//							this.y = tempEnt.getY()+tempEnt.getBounds().height+3;
							}
							else {
								if(!swaying) {
								xSwaying = x;
								swaying = true;
								if(velX < 0)
									velX = -1;
								else
									velX = 1;
//								velX = 0;
								}
								velY = 0;
							}
							if(y <= tempEnt.getY()+7)
								velY=0;
							break;
						}
						else
							tracker++;
					}
				}
				if(chainChompBallToFollow.getX()+15 < x) 
					x--;
				if(x+15 < chainChompBallToFollow.getX()) 
					x++;
				if(y-4 < chainChompBallToFollow.getY())
					y++;
				if(chainChompBallToFollow.getY()+2 < y)
					y-=0.01;
				
			}
		}
		if(dead) {
			x -= velX/1.3;
			y -= velY/1.3;
			if(death.getCount()<=4)
				death.runAnimation();
		}
		if(chainChompBallToFollow != null)
			if(y <= chainChompBallToFollow.getY()+7)
				velY=velY/1.6;
		
		if(swaying) {
			if(velX < 0) {
				if(x < xSwaying-2)
					velX = 0.17;
			}
			else {
				if(xSwaying < x-2)
					velX = -0.17;
			}
		}
		x += velX;
		y += velY;
		chain.runAnimation();
	}

	public void render(Graphics g) {
		if(!dead)
			chain.drawAnimation(g, x, y, 0);
		else
			death.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 10, 10);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public String entityName() {
		return "chainChompChainFX";
	}

	public void close() {
		
	}

	

}
