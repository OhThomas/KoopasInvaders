package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class LakituRedShell extends GameObject implements EntityE {
	
	private Textures tex;
	private Game game;
	Animation anim;
	Animation animDead;
	SoundLoops shellHit;
	private long popOffTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private long flickerTimer1 = 0;
	private long flickerTimer2 = 0;	
	double velX = 0;
	double velY = 0;
	private int xDestination = 10000;
	private int ebMarked = -1;
	private int ecMarked = -1;
	private int xDirection = -1;
	private boolean hitIndicator = false;
	private boolean dead = false;
	public LakituRedShell(double x, double y, Textures tex, Double velX, Game game){
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
		}
		else if(velX > 0) {
			this.velX = 2.4;
		}
		if(!game.eb.isEmpty()){
			for(int i = 0; i < game.eb.size(); i++){

				EntityB tempEnt = game.eb.get(i);
				if(((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1) &&
						this.getY()+16 < game.eb.get(i).getY()){
						xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
						ebMarked = i;
				}
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead() && !dead){
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
		if(!game.ec.isEmpty()){
			for(int i = 0; i < game.ec.size(); i++){

				EntityC tempEnt = game.ec.get(i);
				if(((Math.abs((int)this.getX() - (int)game.ec.get(i).getX()) < xDestination) || game.ec.size() == 1) && 
						this.getY()+16 < game.ec.get(i).getY()){
						xDestination = Math.abs((int)this.getX() - (int)game.ec.get(i).getX());
						ecMarked = i;
				}
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead() && !dead){
					ecMarked = -1;
				}
			}
			if(ecMarked != -1 && ecMarked < game.ec.size()){
				if(game.ec.get(ecMarked).getX() < this.getX())
					xDirection = 1;//x--
				else
					xDirection = 0;
			}
		}
		anim = new Animation(5,tex.redShell[0],tex.redShell[1],tex.redShell[2],tex.redShell[3]);
		animDead = new Animation(5,tex.redShellDead[0],tex.redShellDead[1],tex.redShellDead[2],tex.redShellDead[3], tex.redShellDead[4],
				tex.redShellDead[5],tex.redShellDead[6],tex.redShellDead[7],tex.redShellDead[7]);
		anim.nextFrame();
		anim.setCount(0);
		animDead.nextFrame();
		animDead.setCount(0);

		String shellHitFile = "res/Sounds/SFX/ssbm_shell.wav";
		SoundLoops shellHitSoundLoop = new SoundLoops(shellHitFile);
		VolumeSlider.adjustSFX(shellHitSoundLoop);
		this.shellHit = shellHitSoundLoop;
		popOffTimer = System.currentTimeMillis() + 200;
	}
	
	public void tick(){
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
			if(velY < 14)
				velY+=0.1;
		}
		if(!Physics.Collision(this, game.getBb())){
			if(!dead)
				y += velY;
			else
				y += velY/10;
			if(!dead) {
				xDestination = 10000;
				if(!game.ea.isEmpty()) {
	
					for(int i = 0; i < game.ea.size(); i++){
						EntityA tempEnt = game.ea.get(i);
						if(Physics.Collision(this, tempEnt)) {
							if(Game.currentlySelectedFireball != 3) {
								game.ea.removeLast();
								this.setEntityEDead(true);
							}
							else {
								game.getController().addEntity(new ChompFX(game,x+4,y+4,"RedShell"));
								game.ee.remove(this);
							}
						}
					}
				}
				if(!game.eb.isEmpty()){
					for(int i = 0; i < game.eb.size(); i++){
		
						EntityB tempEnt = game.eb.get(i);
						if(((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1) &&
								this.getY()+16 < game.eb.get(i).getY()){
								xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
								ebMarked = i;
								ecMarked = -1;
						}
						if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead() && !dead){
							if(!tempEnt.enemyType().equals("Bowser")) {
								//if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
								//	this.itemSoundLoop.play();
								//	this.itemSoundLoop.setSoundLoopBoolean(true);
								//}
								ebMarked = -1;
								game.eb.get(i).setEntityBDead(true);
								game.ee.remove(this);
							}
							else {
								HUD.HEALTH -= 4;
								this.setEntityEDead(true);
								//chainChompisDead = true;
								//MAKE EXPLOSION SOUND
								//Death Sound
							}
						}
					}
				}
				if(!game.ec.isEmpty()){
					for(int i = 0; i < game.ec.size(); i++){
		
						EntityC tempEnt = game.ec.get(i);
						if(((Math.abs((int)this.getX() - (int)game.ec.get(i).getX()) < xDestination) || game.ec.size() == 1) &&
								this.getY()+16 < game.ec.get(i).getY()){
								xDestination = Math.abs((int)this.getX() - (int)game.ec.get(i).getX());
								ecMarked = i;
								ebMarked = -1;
						}
						if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead() && !dead){
								ecMarked = -1;
								game.ec.get(i).setEntityCDead(true);
								game.ee.remove(this);
						}
					}
				}
				else if(game.ec.isEmpty() && game.eb.isEmpty()){
					xDirection = -1;
				}
				if((ebMarked != -1 && ebMarked < game.eb.size()) || (ecMarked != -1 && ecMarked < game.ec.size())){
					if(ebMarked != -1) {
						if(xDirection == 0 && game.eb.get(ebMarked).getX() < this.getX())
							xDirection = 1;//x--
						else if(xDirection == 1 && game.eb.get(ebMarked).getX() > this.getX())
							xDirection = 0;
					}
					else if(ecMarked != -1) {
						if(xDirection == 0 && game.ec.get(ecMarked).getX() < this.getX())
							xDirection = 1;//x--
						else if(xDirection == 1 && game.ec.get(ecMarked).getX() > this.getX())
							xDirection = 0;
					}
				}
				if(!game.ee.isEmpty()){
					for(int k = 0; k < game.ee.size(); k++){
		
						EntityE tempEnt = game.ee.get(k);
						if(Physics.Collision(this, tempEnt)){
							if(!tempEnt.getEntityEDead() && !dead) {
								if(tempEnt.entityName().equals("chainChomp") || tempEnt.entityName().equals("amp")) {
									game.getController().addEntity(new ChompFX(game,x+4,y+4,"RedShell"));
									game.ee.remove(this);
								}
								else if(tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmb")) {
									game.ee.get(k).setEntityEDead(true);
									game.getController().addEntity(new ChompFX(game,x+4,y+4,"RedShell"));
									game.ee.remove(this);
								}
								else if(tempEnt.entityName().equals("cheepCheeps")) {
									game.ee.get(k).setEntityEDead(true);
									this.setEntityEDead(true);
								}
								else if(tempEnt.entityName().equals("bombOmbShrapnel1") || tempEnt.entityName().equals("bombOmbShrapnel2")) {
									game.ee.remove(k);
								}
								else if(tempEnt.entityName().equals("lakituBombOmb")) {
									game.ee.get(k).setEntityEDead(true);
									game.ee.remove(this);
								}
							}
						}
					}
				}
			}
			else if(xDirection != -1 && xDestination != 10000 && ebMarked != -1 && ecMarked != -1 && dead) {
				xDirection = -1;
				xDestination = 10000;
				ebMarked = -1;
				ecMarked = -1;
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
			if(!dead) {
				if(!game.ea.isEmpty()) {
	
					for(int sigh = 0; sigh < game.ea.size(); sigh++){
						EntityA tempEnt = game.ea.get(sigh);
						if(Physics.Collision(this, tempEnt)) {
							if(Game.currentlySelectedFireball != 3) {
								game.ea.removeLast();
								this.setEntityEDead(true);
							}
							else {
								game.getController().addEntity(new ChompFX(game,x+4,y+4,"RedShell"));
								game.ee.remove(this);
							}
						}
					}
				}
				if(!game.eb.isEmpty()){
					for(int k = 0; k < game.eb.size(); k++){
		
						EntityB tempEnt = game.eb.get(k);
						if(((Math.abs((int)this.getX() - (int)game.eb.get(k).getX()) < xDestination) || game.eb.size() == 1) &&
								this.getY()+16 < game.eb.get(k).getY()){
								xDestination = Math.abs((int)this.getX() - (int)game.eb.get(k).getX());
								ebMarked = k;
								ecMarked = -1;
						}
						if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead() && !dead){
							if(!tempEnt.enemyType().equals("Bowser")) {
								//if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
								//	this.itemSoundLoop.play();
								//	this.itemSoundLoop.setSoundLoopBoolean(true);
								//}
								ebMarked = -1;
								game.eb.get(k).setEntityBDead(true);
								game.ee.remove(this);
							}
							else {
								HUD.HEALTH -= 4;
								this.setEntityEDead(true);
								//chainChompisDead = true;
								//MAKE EXPLOSION SOUND
								//Death Sound
							}
						}
					}
				}
				if(!game.ec.isEmpty()){
					for(int k = 0; k < game.ec.size(); k++){
		
						EntityC tempEnt = game.ec.get(k);
						if(((Math.abs((int)this.getX() - (int)game.ec.get(k).getX()) < xDestination) || game.ec.size() == 1) && 
								this.getY()+16 < game.ec.get(k).getY()){
								xDestination = Math.abs((int)this.getX() - (int)game.ec.get(k).getX());
								ecMarked = k;
								ebMarked = -1;
						}
						if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead() && !dead){
								ecMarked = -1;
								game.ec.get(k).setEntityCDead(true);
								game.ee.remove(this);
						}
					}
				}
				else if(game.ec.isEmpty() && game.eb.isEmpty()){
					xDirection = -1;
				}
				if(!game.ee.isEmpty()){
					for(int k = 0; k < game.ee.size(); k++){
		
						EntityE tempEnt = game.ee.get(k);
						if(Physics.Collision(this, tempEnt)){
							if(!tempEnt.getEntityEDead() && !dead) {
								if(tempEnt.entityName().equals("chainChomp") || tempEnt.entityName().equals("amp")) {
									game.getController().addEntity(new ChompFX(game,x+4,y+4,"RedShell"));
									game.ee.remove(this);
								}
								else if(tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmb")) {
									game.ee.get(k).setEntityEDead(true);
									game.getController().addEntity(new ChompFX(game,x+4,y+4,"RedShell"));
									game.ee.remove(this);
								}
								else if(tempEnt.entityName().equals("cheepCheeps")) {
									game.ee.get(k).setEntityEDead(true);
									this.setEntityEDead(true);
								}
								else if(tempEnt.entityName().equals("bombOmbShrapnel1") || tempEnt.entityName().equals("bombOmbShrapnel2")) {
									game.ee.remove(k);
								}
								else if(tempEnt.entityName().equals("lakituBombOmb")) {
									game.ee.get(k).setEntityEDead(true);
									game.ee.remove(this);
								}
							}
						}
					}
				}
			}
			else if(xDirection != -1 && xDestination != 10000 && ebMarked != -1 && ecMarked != -1 && dead) {
				xDirection = -1;
				xDestination = 10000;
				ebMarked = -1;
				ecMarked = -1;
			}
			
			y+=velY/10;
		}
		if((ebMarked != -1 && ebMarked < game.eb.size()) || (ecMarked != -1 && ecMarked < game.ec.size())){
			if(ebMarked != -1) {
				if(xDirection == 0 && game.eb.get(ebMarked).getX() < this.getX())
					xDirection = 1;//x--
				else if(xDirection == 1 && game.eb.get(ebMarked).getX() > this.getX())
					xDirection = 0;
				if(xDestination > 1 &&  this.y < game.eb.get(ebMarked).getY() -30) {
					if(game.eb.get(ebMarked).getX() < this.x) {
//						if(velX > 0)
//							velX = velX * -1;
						velX-=0.1;
						if(this.y > game.eb.get(ebMarked).getY()-20)
							x-=0.003;
						else
							x-=0.0003;
					}
					else {
//						if(velX < 0)
//							velX = velX * -1;
						velX+=0.1;
						if(this.y > game.eb.get(ebMarked).getY() -20)
							x+=0.003;
						else
							x+=0.0003;
					}
				}
//				if(game.enemyHitRightBarrier == false)
//					x+=game.enemySpeedIncrease;
//				else
//					x-=game.enemySpeedIncrease;
			}
			else if(ecMarked != -1) {
				if(xDirection == 0 && game.ec.get(ecMarked).getX() < this.getX())
					xDirection = 1;//x--
				else if(xDirection == 1 && game.ec.get(ecMarked).getX() > this.getX())
					xDirection = 0;
			}
		}

		if(velX != 0 && (x+16 >= Game.WIDTH * Game.SCALE || x <= 0))
			velX = velX * -1;
		if(xDirection == 1) {
			if(velX> -1.8)
				velX = velX - 0.05;
		}else if(xDirection == 0) {
			if(velX<1.8)
				velX = velX + 0.05;
		}
		if(velX > 2)
			velX = 2;
		if(velX < -2)
			velX = -2;
		if(xDestination != 10000) {
			if(xDestination > 10) {
	//			if(xDestination < 15 && y < 370)
	//				x+=velX*1.5;
				x+=velX;
			}
			if(xDestination > 40)
				x+=(velX/6);
		}
		else if(velX != 0) {
			if(velX <= 0.002 && -0.002 <= velX)
				velX = 0;
			else if(velX < 0.002)
				velX+=0.001;
			else if(-0.002 < velX)
				velX-=0.001;
		}
		Rectangle rect = new Rectangle((int)x-45, (int)y-45, 90, 90);
		if(rect.intersects(game.getPlayer().getBounds())) {
			if(this.x + (getBounds().width/2) <= game.getPlayer().getX() + (game.getPlayer().getBounds().width/2)) {
				if(xDestination != 10000 && 0 < velX) {
					if(xDestination > 10) 
						x-=velX;
					if(xDestination > 40)
						x-=(velX/6);
				}
				else if(0 < velX)
					x-=velX;
				else
					x+=velX;
				x-=0.5;
			}
			else {
				if(xDestination != 10000 && 0 < velX) {
					if(xDestination > 10) 
						x+=velX;
					if(xDestination > 40)
						x+=(velX/6);
				}
				else if(0 < velX)
					x+=velX;
				else
					x-=velX;
				x+=0.5;
			}
//			if(!Physics.Collision(this, game.getBb()))
//				y-=velY/5;
//			else
//				y-=velY/20;
		}
		if ((Game.HEIGHT * Game.SCALE) + 30 < this.getY())
			game.ee.remove(this);
		anim.runAnimation();
		if(dead) {
			animDead.runAnimation();
			if(animDead.getCount() == 9)
				game.ee.remove(this);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void render(Graphics g){
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
		if(dead)
			animDead.drawAnimation(g, x, y, 0);
		else
			anim.drawAnimation(g, x, y, 0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
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

	public String entityName() {
		return "lakituRedShell";
	}

	public void setHitIndicator(boolean b) {
		this.hitIndicator = b;
	}

	public boolean getHitIndicator() {
		return hitIndicator;
	}

	public boolean getEntityEDead() {
		return dead;
	}

	public void setEntityEDead(boolean dead) {
		if(dead)
			game.getController().addEntity(new ChompFX(game,x+4,y+4,"RedShell"));
		if(this.shellHit.getSoundLoopBoolean() == false) {
			shellHit.play();
			this.shellHit.setSoundLoopBoolean(true);
		}
		this.dead = dead;
	}

	public void close() {
		
	}
}
