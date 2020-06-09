package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class LakituBombOmb extends GameObject implements EntityE {
	Textures tex;
	Game game;
	Animation walking;
	Animation glowing1;
	Animation glowing2;
	Animation glowing3;
	Animation glowing4;
	Animation exploding;
	SoundLoops explosionSound;
	private double velX = 0;
	private double velY = 0;
	private long popOffTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private long flickerTimer1 = 0;									
	private long flickerTimer2 = 0;			
	private int glowingTimer = 0;
	private int explodingCount = 0;
	private boolean glowingBoolean = false;
	private boolean explodingBoolean = false;
	private boolean shrapnel1Spawn = false;
	private boolean shrapnel2Spawn = false;
	private boolean shrapnel3Spawn = false;
	private boolean explodingAdjustment = false;
	private boolean bowserHurt = false;
	private boolean hitIndicator = false;
	public LakituBombOmb(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
//		walking  = new Animation(6, tex.bombOmbItem[0], tex.bombOmbItem[1], tex.bombOmbItem[0], tex.bombOmbItem[2]);
//		glowing  = new Animation(6, tex.bombOmbItem[0], tex.bombOmbItem[3], tex.bombOmbItem[4], tex.bombOmbItem[5],
//				tex.bombOmbItem[6],tex.bombOmbItem[7],tex.bombOmbItem[6],tex.bombOmbItem[5],tex.bombOmbItem[4],
//				tex.bombOmbItem[3],tex.bombOmbItem[0],tex.bombOmbItem[3],tex.bombOmbItem[4],tex.bombOmbItem[5],
//				tex.bombOmbItem[6],tex.bombOmbItem[7],tex.bombOmbItem[6],tex.bombOmbItem[5],tex.bombOmbItem[4],
//				tex.bombOmbItem[3],tex.bombOmbItem[0],tex.bombOmbItem[3],tex.bombOmbItem[4],tex.bombOmbItem[5],
//				tex.bombOmbItem[6],tex.bombOmbItem[7]);
		exploding  = new Animation(6, tex.bombOmbItem[8], tex.bombOmbItem[9], tex.bombOmbItem[10], tex.bombOmbItem[11],
				tex.bombOmbItem[12],tex.bombOmbItem[13],tex.bombOmbItem[14],tex.bombOmbItem[15]);
		
		walking = new Animation(6, tex.lakituBombOmb[0],tex.lakituBombOmb[1],tex.lakituBombOmb[2],tex.lakituBombOmb[3],
				tex.lakituBombOmb[4],tex.lakituBombOmb[3],tex.lakituBombOmb[2],tex.lakituBombOmb[1]);
		glowing1 = new Animation(6, tex.lakituBombOmb[0],tex.lakituBombOmb[1],tex.lakituBombOmb[2],tex.lakituBombOmb[3],
				tex.lakituBombOmb[4],tex.lakituBombOmb[3],tex.lakituBombOmb[2],tex.lakituBombOmb[1]);
		glowing2 = new Animation(6, tex.lakituBombOmb[5],tex.lakituBombOmb[6],tex.lakituBombOmb[7],tex.lakituBombOmb[8],
				tex.lakituBombOmb[9],tex.lakituBombOmb[8],tex.lakituBombOmb[7],tex.lakituBombOmb[6]);
		glowing3 = new Animation(6, tex.lakituBombOmb[10],tex.lakituBombOmb[11],tex.lakituBombOmb[12],tex.lakituBombOmb[13],
				tex.lakituBombOmb[14],tex.lakituBombOmb[13],tex.lakituBombOmb[12],tex.lakituBombOmb[11]);
		glowing4 = new Animation(6, tex.lakituBombOmb[15],tex.lakituBombOmb[16],tex.lakituBombOmb[17],tex.lakituBombOmb[18],
				tex.lakituBombOmb[19],tex.lakituBombOmb[18],tex.lakituBombOmb[17],tex.lakituBombOmb[16]);
//		exploding = new Animation(6, tex.lakituBombOmb[0],tex.lakituBombOmb[1],tex.lakituBombOmb[2],tex.lakituBombOmb[3],
//				tex.lakituBombOmb[4]);

		walking.nextFrame();
		walking.setCount(0);
		glowing1.nextFrame();
		glowing1.setCount(0);
		glowing2.nextFrame();
		glowing2.setCount(0);
		glowing3.nextFrame();
		glowing3.setCount(0);
		glowing4.nextFrame();
		glowing4.setCount(0);
		exploding.nextFrame();
		exploding.setCount(0);
		String deathFile = "res/Sounds/SFX/mariobombombexplosion.wav";
		SoundLoops deathSoundLoop = new SoundLoops(deathFile);
		VolumeSlider.adjustSFX(deathSoundLoop);
		deathSoundLoop.increaseSound();
		this.explosionSound = deathSoundLoop;
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
			if(velY < 1)
				velY+=0.1;
		}
		switch(glowing1.getCount()) {
		case 0:
			velX = 0.2;
			break;
		case 1:
			velX = 0.5;
			break;
		case 2:
			velX = 1;
			break;
		case 3:
			velX = 0.5;
			break;
		case 4:
			velX = -0.2;
			break;
		case 5:
			velX = -0.5;
			break;
		case 6:
			velX = -1;
			break;
		case 7:
			velX = -0.5;
			break;
		default:
			velX=0;
			break;
		}
		if(this.getBombOmbBounds().intersects(game.getPlayer().getBounds())) {
			if(game.isMarioInvincible()) {
				game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
				game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
				game.ee.remove(this);
			
			}
			else {
				hitIndicator = true;
				Game.Health -= 100;
			}
		}if(this.getParachuteBounds().intersects(game.getPlayer().getBounds())) {
			game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
		}
		if(glowingBoolean == false && explodingBoolean == false) {
			if(y < -48)
				game.ee.remove(this);
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					if(this.getParachuteBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
					}
					if(this.getBombOmbBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
						game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
						glowingBoolean = true;
						explodingBoolean = true;
						if(Game.currentlySelectedFireball != 3)
							game.ea.remove(tempEnt);
						return;
					}
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				Rectangle rect = new Rectangle((int)x-35, (int)y-35, 70, 70);
				if(Physics.Collision(rect, tempEnt) && !glowingBoolean){
//					glowing1.setCount(walking.getCount());
//					glowing2.setCount(walking.getCount());
//					glowing3.setCount(walking.getCount());
//					glowing4.setCount(walking.getCount());
					glowingBoolean = true;
				}
			}
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					if(this.getParachuteBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
					}
					if(this.getBombOmbBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
						game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
						if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
							//game.getHUD().setScore(500);
							glowingBoolean = true;
							explodingBoolean = true;
							return;
						}
						else if(tempEnt.entityName().equals("BulletBill")) 
							game.getHUD().setScore(200);
						else
							game.getHUD().setScore(200);
						game.ec.remove(tempEnt);
						glowingBoolean = true;
						explodingBoolean = true;
					}
				}
			}
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && this != tempEnt) {
					if(this.getParachuteBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
					}
					if(this.getBombOmbBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
						game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
						if(!tempEnt.entityName().equals("lakitu") && !tempEnt.entityName().equals("lakituBombOmb")) {
							glowingBoolean = true;
							explodingBoolean = true;
							if((tempEnt.entityName().equals("bombOmbShrapnel2") ||tempEnt.entityName().equals("bombOmbShrapnel1")))
								game.ee.remove(tempEnt);
							else if((tempEnt.entityName().equals("lakituFish") && popOffTimer < System.currentTimeMillis()) || tempEnt.entityName().equals("cheepCheeps")){
								game.getController().addEntity(new ChompFX(game,tempEnt.getX()+(tempEnt.getBounds().width/2),tempEnt.getY()+(tempEnt.getBounds().height/2),"Fish"));
								game.ee.remove(tempEnt);
							}
							else if(!tempEnt.getEntityEDead() && (tempEnt.entityName().equals("wiggler") || tempEnt.entityName().equals("wigglerBody") || 
									tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmb"))) {
								game.ee.get(i).setEntityEDead(true);
							}
						}
					}
				}
			}
			walking.runAnimation();
			glowing1.runAnimation();
			glowing2.runAnimation();
			glowing3.runAnimation();
			glowing4.runAnimation();
			y+=velY;
			x+=velX;
			if(Physics.Collision(this, game.getBb())){
				y -= velY/1.4;
				x-=velX/1.1;
			}
		}
		else if(glowingBoolean == true && explodingBoolean == false) {
			//System.out.println(glowing1.getCount());
			if(explodingCount >= 2 && (glowing1.getCount() == 2 || glowing1.getCount() == 6) && (glowingTimer >= 45 && glowingTimer < 75)) {
				explodingBoolean = true;
			}
//			glowing.runAnimation();
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					if(this.getParachuteBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
					}
					if(this.getBombOmbBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
						game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
						explodingBoolean = true;
						if(Game.currentlySelectedFireball != 3)
							game.ea.remove(tempEnt);
						return;
					}
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){
					if(this.getParachuteBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
					}
					if(this.getBombOmbBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
						game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
						//game.eb.get(i).setEntityBDead(true);
						if(!tempEnt.enemyType().equals("Bowser")) {
							game.eb.remove(tempEnt);
							if(!game.eb.isEmpty() && game.getSpawnDone2() == false) {
								if (game.eb.size() == 2)
									game.enemySpeedIncrease+= 0.3;
								game.enemySpeedIncrease+= 0.06;
							}else if(!game.eb.isEmpty() && game.getSpawnDone3() == false) {
								if (game.eb.size() == 2)
									game.enemySpeedIncrease+= 0.4;
								game.enemySpeedIncrease+= 0.06; //0.7
							}else if(!game.eb.isEmpty() && game.getSpawnDone4() == false) {
								if (game.eb.size() == 2)
									game.enemySpeedIncrease+= 0.9;
								game.enemySpeedIncrease+= 0.08; //0.7
							}
							game.getHUD().setScore(200);
						}
						explodingBoolean = true;
					}
				}
			}
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					if(this.getParachuteBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
					}
					if(this.getBombOmbBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
						game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
						if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
							//game.getHUD().setScore(500);
							explodingBoolean = true;
							return;
						}
						else if(tempEnt.entityName().equals("BulletBill")) 
							game.getHUD().setScore(200);
						else
							game.getHUD().setScore(200);
						game.ec.remove(tempEnt);
						explodingBoolean = true;
					}
				}
			}
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && this != tempEnt) {
					if(this.getParachuteBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
					}
					if(this.getBombOmbBounds().intersects(tempEnt.getBounds())) {
						game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
						game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
						if(!tempEnt.entityName().equals("lakitu") && !tempEnt.entityName().equals("lakituBombOmb")) {
							explodingBoolean = true;
							if((tempEnt.entityName().equals("bombOmbShrapnel2") ||tempEnt.entityName().equals("bombOmbShrapnel1")))
								game.ee.remove(tempEnt);
							else if((tempEnt.entityName().equals("lakituFish") && popOffTimer < System.currentTimeMillis()) || tempEnt.entityName().equals("cheepCheeps")){
								game.getController().addEntity(new ChompFX(game,tempEnt.getX()+(tempEnt.getBounds().width/2),tempEnt.getY()+(tempEnt.getBounds().height/2),"Fish"));
								game.ee.remove(tempEnt);
							}
							else if(!tempEnt.getEntityEDead() && (tempEnt.entityName().equals("wiggler") || tempEnt.entityName().equals("wigglerBody") || 
									tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmb"))) {
								game.ee.get(i).setEntityEDead(true);
							}
						}
					}
				}
			}
			glowingTimer++;
			if(glowingTimer >= 120) {
				glowingTimer = 0;
				explodingCount++;
			}
			glowing1.runAnimation();
			glowing2.runAnimation();
			glowing3.runAnimation();
			glowing4.runAnimation();
			y+=velY;
			x+=velX;
			if(Physics.Collision(this, game.getBb())){
				y -= velY/1.4;
				x-=velX/1.1;
			}
		}
		else {
			if(!explosionSound.getSoundLoopBoolean()) {
				if(!explosionSound.clipIsActive())
					explosionSound.play();
				explosionSound.setSoundLoopBoolean(true);
			}
			if(!explodingAdjustment) {
				switch(glowing1.getCount()) {
				case 0:
					y+=15;
					break;
				case 1:
					y+=16;
					break;
				case 2:
					x+=2;
					y+=16;
					break;
				case 3:
					x+=5;
					y+=16;
					break;
				case 4:
					x+=6;
					y+=16;
					break;
				default:
					break;
				}
				y+=velY;
				explodingAdjustment = true;
			}
			if(exploding.getCount() == 2) {
				y+=0.04;
				x-=0.1;
				Random rand2 = new Random();
				if(rand2.nextInt(70) == 0)
					shrapnel2Spawn = false;
				if(shrapnel2Spawn == false) {
					Random rand = new Random();
					int randy = rand.nextInt(1);
					if(randy == 4) 
						game.getController().addEntity(new BombOmbShrapnel2(x-16,y, tex, game,0));
					else if(randy == 3) 
						game.getController().addEntity(new BombOmbShrapnel2(x+16,y, tex, game,1));
					else if(randy == 2) 
						game.getController().addEntity(new BombOmbShrapnel2(x-16,y+8, tex, game,2));
					else if(randy == 1) 
						game.getController().addEntity(new BombOmbShrapnel2(x+16,y+8, tex, game,3));
					else 
						game.getController().addEntity(new BombOmbShrapnel2(x,y+8, tex, game,4));
					
					shrapnel2Spawn = true;
				}

				if(shrapnel1Spawn == false) {
					boolean leftShrapnelSpawn = false;
					boolean rightShrapnelSpawn = false;
					Random rand = new Random();
					if(rand.nextInt(2) == 0) {
						leftShrapnelSpawn = true;
						game.getController().addEntity(new BombOmbShrapnel(x-8,y-8, tex, game,true));
						//spawnleft
					}
					else if(rand.nextInt(2) == 0) {
						rightShrapnelSpawn = true;
						game.getController().addEntity(new BombOmbShrapnel(x+8,y-8, tex, game,false));
						//spawnright
					}
					if(leftShrapnelSpawn == false && rightShrapnelSpawn == false) {
						if(rand.nextInt(2) == 0) {
							//spawnleft
							game.getController().addEntity(new BombOmbShrapnel(x-8,y-8, tex, game,true));
						}
						else {
							//spawnright
							game.getController().addEntity(new BombOmbShrapnel(x+8,y-8, tex, game,false));
						}
					}
					shrapnel1Spawn = true;
				}
				if(shrapnel3Spawn == false) {
					game.getController().addEntity(new BombOmbShrapnel2(x-16,y, tex, game,0));
					game.getController().addEntity(new BombOmbShrapnel2(x+22,y, tex, game,1));
					shrapnel3Spawn = true;
				}
			}
			else if(exploding.getCount() == 8) {
				game.ee.remove(this);
			}
			exploding.runAnimation();
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){

					if(!tempEnt.enemyType().equals("Bowser")) {
						//game.eb.get(i).setEntityBDead(true);
						game.eb.remove(tempEnt);
						if(!game.eb.isEmpty() && game.getSpawnDone2() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.3;
							game.enemySpeedIncrease+= 0.06;
						}else if(!game.eb.isEmpty() && game.getSpawnDone3() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.4;
							game.enemySpeedIncrease+= 0.06; //0.7
						}else if(!game.eb.isEmpty() && game.getSpawnDone4() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.9;
							game.enemySpeedIncrease+= 0.08; //0.7
						}
						game.getHUD().setScore(200);
					}
					else if(!bowserHurt) {
						HUD.HEALTH -= 4;
						bowserHurt = true;
					}
				}
			}
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
						//game.getHUD().setScore(500);
						return;
					}
					else if(tempEnt.entityName().equals("BulletBill")) 
						game.getHUD().setScore(200);
					else
						game.getHUD().setScore(200);
					game.ec.remove(tempEnt);
				}
			}
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && this != tempEnt){
					if(tempEnt.entityName().equals("bombOmbShrapnel2") || tempEnt.entityName().equals("bombOmbShrapnel1"))
						game.ee.remove(tempEnt);
					else if((tempEnt.entityName().equals("lakituFish") && popOffTimer < System.currentTimeMillis()) || tempEnt.entityName().equals("cheepCheeps")){
						game.getController().addEntity(new ChompFX(game,tempEnt.getX()+(tempEnt.getBounds().width/2),tempEnt.getY()+(tempEnt.getBounds().height/2),"Fish"));
						game.ee.remove(tempEnt);
					}
					else if(!tempEnt.getEntityEDead() && (tempEnt.entityName().equals("wiggler") || tempEnt.entityName().equals("wigglerBody") || 
							tempEnt.entityName().equals("bulletBill") || tempEnt.entityName().equals("bombOmb"))) {
						game.ee.get(i).setEntityEDead(true);
					}
				}
			}
			y+=0.04;
			x-=0.07;
		}
		if(Game.WIDTH * Game.SCALE < x+getBounds().width)
			x++;
		if(x < 0)
			x--;
		if(Game.WIDTH * Game.SCALE < x || x+getBounds().width < 0 || Game.HEIGHT * Game.SCALE + 50 < y)
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
		if(glowingBoolean == false && explodingBoolean == false) {
			walking.drawAnimation(g, x, y, 0);
		}
		else if(glowingBoolean == true && explodingBoolean == false) {
			if((glowingTimer < 15) || (glowingTimer >= 105))
				glowing1.drawAnimation(g, x, y, 0);
			if((glowingTimer >= 15 && glowingTimer < 30) || (glowingTimer >= 90 && glowingTimer < 105))
				glowing2.drawAnimation(g, x, y, 0);
			if((glowingTimer >= 30 && glowingTimer < 45) || (glowingTimer >= 75 && glowingTimer < 90))
				glowing3.drawAnimation(g, x, y, 0);
			if((glowingTimer >= 45 && glowingTimer < 60) || (glowingTimer >= 60 && glowingTimer < 75))
				glowing4.drawAnimation(g, x, y, 0);
//			glowing.drawAnimation(g, x, y, 0);
		}
		else {
			exploding.drawAnimation(g, x, y, 0);
		}
	}
	
	public Rectangle getBombOmbBounds() {//BombOmb itself
		if(glowingBoolean == false || explodingBoolean == false) {
			switch(glowing1.getCount()) {
			case 0:
				return new Rectangle((int)x, (int)y+15, 15, 16);
			case 1: case 7:
				return new Rectangle((int)x, (int)y+16, 13, 15);
			case 2: case 6:
				return new Rectangle((int)x+2, (int)y+16, 12, 15);
			case 3: case 5:
				return new Rectangle((int)x+5, (int)y+16, 13, 15);
			case 4:
				return new Rectangle((int)x+6, (int)y+15, 15, 16);
			}
			return new Rectangle((int)x, (int)y+16, 13, 15);
		}
		else {
			switch(exploding.getCount()) {
			case 0:
				return new Rectangle((int)x, (int)y, 12, 18);
			case 1:
				return new Rectangle((int)x, (int)y, 14, 16);
			case 2:
				return new Rectangle((int)x, (int)y, 21, 18);
			case 3:
				return new Rectangle((int)x+1, (int)y, 25, 25);//27, 25
			case 4:
				return new Rectangle((int)x, (int)y, 25, 22);
			case 5:
				return new Rectangle((int)x, (int)y, 19, 21);
			case 6:
				return new Rectangle((int)x, (int)y, 17, 14);
			case 7:
				return new Rectangle((int)x, (int)y, 11, 11);
			default:
				break;
			}
		}
		return new Rectangle((int)x+2, (int)y+16, 12, 15);
	}
	
	public Rectangle getParachuteBounds() {//BombOmb itself
		if(glowingBoolean == false || explodingBoolean == false) {
			switch(glowing1.getCount()) {
			case 0:
				return new Rectangle((int)x+6, (int)y, 15, 15);
			case 1: case 7:
				return new Rectangle((int)x+2, (int)y, 16, 16);
			case 2: case 6:
				return new Rectangle((int)x, (int)y, 16, 16);
			case 3: case 5:
				return new Rectangle((int)x, (int)y, 16, 16);
			case 4:
				return new Rectangle((int)x, (int)y, 15, 15);
			}
			return new Rectangle((int)x, (int)y, 16, 16);
		}
		else {
			switch(exploding.getCount()) {
			case 0:
				return new Rectangle((int)x, (int)y, 12, 18);
			case 1:
				return new Rectangle((int)x, (int)y, 14, 16);
			case 2:
				return new Rectangle((int)x, (int)y, 21, 18);
			case 3:
				return new Rectangle((int)x+1, (int)y, 25, 25);//27, 25
			case 4:
				return new Rectangle((int)x, (int)y, 25, 22);
			case 5:
				return new Rectangle((int)x, (int)y, 19, 21);
			case 6:
				return new Rectangle((int)x, (int)y, 17, 14);
			case 7:
				return new Rectangle((int)x, (int)y, 11, 11);
			default:
				break;
			}
		}
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		if(glowingBoolean == false || explodingBoolean == false) {
			switch(glowing1.getCount()) {
			case 0:
				return new Rectangle((int)x, (int)y, 21, 31);
			case 1: case 7:
				return new Rectangle((int)x, (int)y, 18, 31);
			case 2: case 6:
				return new Rectangle((int)x, (int)y, 16, 31);
			case 3: case 5:
				return new Rectangle((int)x, (int)y, 18, 31);
			case 4:
				return new Rectangle((int)x, (int)y, 21, 31);
			}
			return new Rectangle((int)x, (int)y, 16, 31);
		}
		else {
			switch(exploding.getCount()) {
			case 0:
				return new Rectangle((int)x, (int)y, 12, 18);
			case 1:
				return new Rectangle((int)x, (int)y, 14, 16);
			case 2:
				return new Rectangle((int)x, (int)y, 21, 18);
			case 3:
				return new Rectangle((int)x+1, (int)y, 25, 25);//27, 25
			case 4:
				return new Rectangle((int)x, (int)y, 25, 22);
			case 5:
				return new Rectangle((int)x, (int)y, 19, 21);
			case 6:
				return new Rectangle((int)x, (int)y, 17, 14);
			case 7:
				return new Rectangle((int)x, (int)y, 11, 11);
			default:
				break;
			}
		}
		return new Rectangle((int)x, (int)y, 16, 31);
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
		return "lakituBombOmb";
	}
	
	public void setHitIndicator(boolean b) {
		this.hitIndicator = b;
	}
	
	public boolean getHitIndicator() {
		return hitIndicator;
	}
	
	public void setEntityEDead(boolean dead) {
		game.getController().addEntity(new ChompFX(game,x+4,y,"Parachute"));
		game.getController().addEntity(new ChompFX(game,x+4,y+16,"BombOmb"));
		this.glowingBoolean = dead;
		this.explodingBoolean = dead;
	}
	
	public boolean getEntityEDead() {
		return explodingBoolean;
	}
	
	public void close() {
		explosionSound.close();
	}
	
}
