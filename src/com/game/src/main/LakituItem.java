package com.game.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class LakituItem extends GameObject implements EntityE   {

	private Game game;
	private Textures tex;
	ChompFX chomped;
	NakedLakituFX nakedLakitu;
	Animation goingLeft;
	Animation goingRight;
	Animation throwingLeft;
	Animation throwingRight;
	Animation hit;
	Animation cloud;
	SoundLoops entranceSound;
	SoundLoops itemThrowSound;
	SoundLoops lakituHitSound;
	private long timer = 0;
	private long firstTimer = 0;
	private long spawnTimer = 0;
	private long startItemThrowTimer = 0;
	private long throwingItemsTimer = 0;
	private long throwingItemsAnimationTimer = 0;
	private long bowserSpawnTimer = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private double velX = 0;
	private double velY = 0;
	private double distanceBetween = 40;
	private int ebMarked = -1;
	private int xDestination = 10000;
	private int totalEnemiesInB = 0;
	private int totalEnemiesInBChecker = 0;
	private boolean totalEnemiesInBBoolean = false;
	private boolean flyAway = false;
	private boolean flyUp = false;
	private boolean downBelow = false;
	private boolean scoreFollowMe = false;
	private boolean initialStartDirection = false;
	private boolean initialStartDirectionSet = false;
	private boolean throwingItem = false;
	private boolean throwingMoreItems = false;
	private boolean throwingItemsAnimator = false;
	private boolean bowserSpawned = false;
	private boolean fishSpawn = false;
	private boolean spikeSpawn = false;
	private boolean bombOmbSpawn = false;
	private boolean redShellSpawn = false;
	private boolean starSpawn = false;
	private boolean lakituHit = false;
	private boolean dead = false;
	public LakituItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		timer = System.currentTimeMillis() + 15000;
		firstTimer = System.currentTimeMillis() + 750;
		goingLeft = new Animation(12,tex.lakituItem[0],tex.lakituItem[1]);
		goingRight = new Animation(12,tex.lakituItem[2],tex.lakituItem[3]);
		throwingLeft = new Animation(6,tex.lakituItem[4],tex.lakituItem[4]);
		throwingRight = new Animation(6,tex.lakituItem[5],tex.lakituItem[5]);
		hit = new Animation(20,tex.lakituItem[6],tex.lakituItem[6]);
		cloud = new Animation(6,tex.lakituItem[9],tex.lakituItem[10],tex.lakituItem[11],tex.lakituItem[12]);
		if(!game.eb.isEmpty()) {
			totalEnemiesInB = game.eb.size()-1;
			totalEnemiesInBChecker = game.eb.size()-1;
			totalEnemiesInBBoolean = true;
			if(game.eb.get(0).enemyType().equals("Bowser"))
				bowserSpawned = true;
		}
		goingLeft.nextFrame();
		goingLeft.setCount(0);
		goingRight.nextFrame();
		goingRight.setCount(0);
		throwingLeft.nextFrame();
		throwingLeft.setCount(0);
		throwingRight.nextFrame();
		throwingRight.setCount(0);
		hit.nextFrame();
		hit.setCount(0);
		cloud.nextFrame();
		cloud.setCount(0);


		String entranceFile = "res/Sounds/SFX/lakituholysoundeffect.wav";
		SoundLoops entranceSoundLoops = new SoundLoops(entranceFile);
		VolumeSlider.adjustSFX(entranceSoundLoops);
		this.entranceSound = entranceSoundLoops;
		
		String itemEnemyFile = "res/Sounds/SFX/conkerbanksfx.wav";
		SoundLoops itemEnemySoundLoops = new SoundLoops(itemEnemyFile);
		VolumeSlider.adjustSFX(itemEnemySoundLoops);
		itemEnemySoundLoops.reduceSound(35f);
		this.itemThrowSound = itemEnemySoundLoops;
		

		String shellHitFile = "res/Sounds/SFX/smw_kick.wav";
		SoundLoops shellHitSoundLoops = new SoundLoops(shellHitFile);
		VolumeSlider.adjustSFX(shellHitSoundLoops);
		this.lakituHitSound = shellHitSoundLoops;
		
		entranceSound.play();
//		goingLeft = new Animation(6,tex.)
	}

	public void tick() {
//		System.out.println("x = " + x);
//		System.out.println("velX = " + velX);
//		System.out.println("y = " + y);
//		System.out.println("velY = " + velY);
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(timer != 0)
					timer+=pausedTimerAddition2;
				if(spawnTimer != 0)
					spawnTimer+=pausedTimerAddition2;
				if(startItemThrowTimer != 0)
					startItemThrowTimer+=pausedTimerAddition2;
				if(throwingItemsAnimationTimer != 0)
					throwingItemsAnimationTimer+=pausedTimerAddition2;
				if(throwingItemsTimer != 0)
					throwingItemsTimer+=pausedTimerAddition2;
				if(bowserSpawnTimer != 0)
					bowserSpawnTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(flyAway) {
			if(y > Game.HEIGHT * Game.SCALE || y < -38 || x+30 < 0 || x > Game.WIDTH * Game.SCALE) {
				if(scoreFollowMe) {
					game.getHUD().setEnemyHitPauseTimer(0);
					scoreFollowMe = false;
					Game.scoreFollowingBoolean = false;
				}
				if(!lakituHitSound.clipIsActive())
					game.ee.remove(this);
			}
			if(velY > -5)
				velY-=0.2;
			if(!dead) {
				if(velX < 0)
					goingLeft.runAnimation();
				else
					goingRight.runAnimation();
			}
			else
				cloud.runAnimation();
		}
		else if(!flyUp) {
			if(downBelow) {
				for(int i = 0; i < game.ea.size(); i++){
					EntityA tempEnt = game.ea.get(i);
					if(Physics.Collision(this, tempEnt)){
						chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
						game.getController().addEntity(chomped);
						//dead
						if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
							game.getController().addEntity(chomped);
							lakituHit = true;
							flyUp = true;
						}
					}
					if(!dead && !lakituHit) {
						double distanceTemp = PythagoreanDistanceFinder(x,game.ea.get(i).getX(),y,game.ea.get(i).getY());
						if(distanceTemp < distanceBetween) {
							//AVOID
							if(game.ea.get(i).getX() + (8) < x+(getBounds().width/2))
								x+=2;
							else
								x-=2;
							if(game.ea.get(i).getY() + (8) > y)
								y-=2;
							else
								y+=2;
							distanceBetween = distanceTemp;
						}
					}
				}
				for(int i = 0; i < game.ec.size(); i++){
					EntityC tempEnt = game.ec.get(i);
					if(Physics.Collision(this, tempEnt) && tempEnt.getEntityCDead() == false){
						if(!tempEnt.entityName().equals("BuzzyBeetleShell")) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
							game.getController().addEntity(chomped);
						}
						else {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
							game.getController().addEntity(chomped);
							//dead
							if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
								chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
								game.getController().addEntity(chomped);
								lakituHit = true;
								flyUp = true;
							}
						}
					}
					if(!dead && !lakituHit) {
						double distanceTemp = PythagoreanDistanceFinder(x,game.ec.get(i).getX(),y,game.ec.get(i).getY());
						if(distanceTemp < distanceBetween) {
							//AVOID
							if(game.ec.get(i).getX() + (game.ec.get(i).getWidth()/2) < x+(getBounds().width/2))
								x+=2;
							else
								x-=2;
							if(game.ec.get(i).getY() + (game.ec.get(i).getWidth()/2) > y)
								y-=2;
							else
								y+=2;
							distanceBetween = distanceTemp;
						}
					}
				}
				if(!dead) {
					if(velX < 0)
						goingLeft.runAnimation();
					else
						goingRight.runAnimation();
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(xDestination == 10000) {
					if(!tempEnt.getEntityBDead() && ((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1)){
							xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
							ebMarked = i;
					}
				}
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityBDead() == false){
					chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
					game.getController().addEntity(chomped);
					if(downBelow) {
						if(!tempEnt.enemyType().equals("Bowser")) {
							if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
								chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
								game.getController().addEntity(chomped);
								lakituHit = true;
								flyUp = true;
							}
						}
						else {
							if(lakituHit == false)
								HUD.HEALTH -=8;
							if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
								chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
								game.getController().addEntity(chomped);
								lakituHit = true;
								flyUp = true;
							}
	//						HUD.HEALTH -=8;
							//dead
						}
					}
				}
			}
			if(xDestination != 10000 && !initialStartDirectionSet) {
				//velXDestination(game.eb.get(ebMarked).getX());
				zoomX(game.eb.get(ebMarked).getX());
				if(game.eb.get(ebMarked).getX() < x)
					initialStartDirection = true;
				initialStartDirectionSet = true;
			}
			else if (!initialStartDirectionSet) {
				//velXDestination(50);
				initialStartDirectionSet = true;
			}
			if(initialStartDirection) {
				if(x-28 < Game.WIDTH * Game.SCALE)
					x+=10;
				else if(x > (Game.WIDTH * Game.SCALE) + 80)
					x=(Game.WIDTH * Game.SCALE) + 80;
				zoomX((Game.WIDTH * Game.SCALE) + 80);
			}
			else {
				if(x + 48 > 0)
					x-=10;
				else if(x < -80)
					x=-80;
				zoomX(-80);
			}
			if(velY > -20)
				velY-=0.4;
			if(y < 40) {
				if(x+48 < 0)
					x+=20;
				if(x-20 > Game.WIDTH * Game.SCALE)
					x-=20;
				if(velX < -10)
					velX = -10;
				if(velX > 10)
					velX = 10;
				if(velY < -10)
					velY = -2;
				if(downBelow) {
					if(velY>5)
						velY=2;
					if(velY < -5)
						velY = -2;
				}
				flyUp = true;
				xDestination = 10000;
				ebMarked = -1;
			}
			cloud.runAnimation();
		}
		else {
//			if(velY == -2)
//				velY *= -1;
			if(velX < -5)
				velX += 1;
			if(velX > 5)
				velX-= 1;
			if(downBelow) {
				if(velY < -5)
					velY += 1;
				if(velY > 5)
					velY-= 1;
			}
			if(totalEnemiesInBChecker <= totalEnemiesInB && totalEnemiesInBBoolean == false) {
				totalEnemiesInBChecker = totalEnemiesInB;
				totalEnemiesInBBoolean = true;
				flyUp = false;
				if(downBelow == false)
					downBelow = true;
			}
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
					game.getController().addEntity(chomped);
					//dead
					if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
						chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
						game.getController().addEntity(chomped);
						lakituHit = true;
					}
				}
				if(!dead && !lakituHit) {
					double distanceTemp = PythagoreanDistanceFinder(x,game.ea.get(i).getX(),y,game.ea.get(i).getY());
					if(distanceTemp < distanceBetween) {
						//AVOID
						if(game.ea.get(i).getX() + (8) < x+(getBounds().width/2))
							x+=2;
						else
							x-=2;
						if(game.ea.get(i).getY() + (8) > y)
							y-=2;
						else
							y+=2;
						distanceBetween = distanceTemp;
					}
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(xDestination == 10000) {
					if(!tempEnt.getEntityBDead() && ((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1)){
							xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
							ebMarked = i;
					}
				}
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityBDead() == false){
					if(!tempEnt.enemyType().equals("Bowser")) {
						chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
						game.getController().addEntity(chomped);
						//dead
						if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
							game.getController().addEntity(chomped);
							lakituHit = true;
						}
					}
					else {
						chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
						game.getController().addEntity(chomped);
						//dead
						if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
							game.getController().addEntity(chomped);
							if(lakituHit == false)
								HUD.HEALTH -=8;
							lakituHit = true;
						}
						//dead
					}
				}
				if(bowserSpawned == false && tempEnt.enemyType().equals("Bowser")) {
					flyUp = false;
					if(downBelow == false)
						downBelow = true;
					bowserSpawned = true;
					bowserSpawnTimer = System.currentTimeMillis() + 4500;
				}
				if(!dead && !lakituHit && bowserSpawned == false) {
					double distanceTemp = PythagoreanDistanceFinder(x,game.eb.get(i).getX(),y,game.eb.get(i).getY());
					if(distanceTemp < distanceBetween) {
						//AVOID
						if(game.eb.get(i).getX() + (game.eb.get(i).getWidth()/2) < x+(getBounds().width/2))
							x+=2;
						else
							x-=2;
						if(game.eb.get(i).getY() + (game.eb.get(i).getHeight()/2) > y)
							y-=2;
						else
							y+=2;
						distanceBetween = distanceTemp;
					}
				}
				if(totalEnemiesInB < totalEnemiesInBChecker && totalEnemiesInBBoolean == true)
					totalEnemiesInBBoolean = false;
				if(i == game.eb.size()-1)
					totalEnemiesInB = i;
			}
			if(ebMarked >= game.eb.size() || (downBelow && (velY < -5 || velY > 5))) {
				ebMarked = -1;
				xDestination = 10000;
			}
			if(xDestination != 10000) {
				velXDestination(game.eb.get(ebMarked).getX());
				if(game.eb.get(ebMarked).enemyType().equals("Bowser"))
					velYDestination(game.eb.get(ebMarked).getY()-40);
				else
					velYDestination(game.eb.get(ebMarked).getY()-38);
				if(y-20 < 0) {
					//y+=1;
//					if(game.eb.get(ebMarked).getY()-51> this.y)
//						y+=2;
//					else if(game.eb.get(ebMarked).getY()-34> this.y)
//						y-=1;
				}
					
			}
			else {
				Random rand = new Random();
				if(rand.nextInt(2) == 0) {
					velXDestination(50);
					velYDestination(50);
				}
				else {
					velXDestination(Game.WIDTH * Game.SCALE - 50);
					velYDestination(50);
				}
			}

			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityCDead() == false){
					if(!tempEnt.entityName().equals("BuzzyBeetleShell")) {
						chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
						game.getController().addEntity(chomped);
//						if(x > tempEnt.getX() + (tempEnt.getWidth() /2))
//							x = tempEnt.getX() + tempEnt.getWidth();
//						else
//							x = tempEnt.getX()-1;
//
//						if(y > tempEnt.getY() + (tempEnt.getHeight() /2))
//							y = tempEnt.getY() + tempEnt.getHeight();
//						else
//							y = tempEnt.getY()-1-this.getBounds().height;
						
						//bounce
					}
					else {
						chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
						game.getController().addEntity(chomped);
						//dead
						if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
							game.getController().addEntity(chomped);
							lakituHit = true;
						}
					}
				}
				if(!dead && !lakituHit) {
					double distanceTemp = PythagoreanDistanceFinder(x,game.ec.get(i).getX(),y,game.ec.get(i).getY());
					if(distanceTemp < distanceBetween) {
						//AVOID
						if(game.ec.get(i).getX() + (game.ec.get(i).getWidth()/2) < x+(getBounds().width/2))
							x+=2;
						else
							x-=2;
						if(game.ec.get(i).getY() + (game.ec.get(i).getHeight()/2) > y)
							y-=2;
						else
							y+=2;
						distanceBetween = distanceTemp;
					}
				}
			}

			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && tempEnt.getEntityEDead() == false && !tempEnt.entityName().equals("lakitu")){
					if(tempEnt.entityName().equals("lakituFish")) {
						if(tempEnt.getBounds().intersects(this.cloudHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
							game.getController().addEntity(chomped);
						}
						if(!dead && !lakituHit && tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Fish");
							game.getController().addEntity(chomped);
						}
//						if(!dead && !lakituHit && tempEnt.getBounds().intersects(this.lakituHitBox())) {
//							if(x+(getBounds().width/2) < tempEnt.getX())
//								tempEnt.setX(tempEnt.getX()+2);//getBounds().width);
//							else
//								tempEnt.setX(tempEnt.getX()-2);//tempEnt.getBounds().width);
//						}
					}
					else if(tempEnt.entityName().equals("lakituSpike")) {
						if(tempEnt.getBounds().intersects(this.cloudHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
							game.getController().addEntity(chomped);
						}
						if(!dead && !lakituHit && tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Spike");
							game.getController().addEntity(chomped);
						}
					}else if(tempEnt.entityName().equals("lakituRedShell")) {
						if(tempEnt.getBounds().intersects(this.cloudHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
							game.getController().addEntity(chomped);
						}
						if(!dead && !lakituHit && tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"RedShell");
							game.getController().addEntity(chomped);
						}
					}
					if(tempEnt.entityName().equals("bombOmb") || tempEnt.entityName().equals("bombOmbShrapnel1") || 
							tempEnt.entityName().equals("bombOmbShrapnel2") || tempEnt.entityName().equals("amp")) {
						if(tempEnt.getBounds().intersects(this.cloudHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Cloud");
							game.getController().addEntity(chomped);
						}
						//dead
						if(tempEnt.getBounds().intersects(this.lakituHitBox())) {
							chomped = new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu");
							game.getController().addEntity(chomped);
							lakituHit = true;
						}
					}
				}
				if(!tempEnt.entityName().equals("lakitu") && !tempEnt.entityName().equals("lakituFish") && !tempEnt.entityName().equals("lakituRedShell")) {
					if(!dead && !lakituHit) {
						double distanceTemp = PythagoreanDistanceFinder(x,game.ee.get(i).getX(),y,game.ee.get(i).getY());
						if(distanceTemp < distanceBetween) {
							//AVOID
							if(game.ee.get(i).getX() + (game.ee.get(i).getBounds().width/2) < x+(getBounds().width/2))
								x+=2;
							else
								x-=2;
							if(game.ee.get(i).getY() + (game.ee.get(i).getBounds().height/2) > y)
								y-=2;
							else
								y+=2;
							distanceBetween = distanceTemp;
						}
					}
				}
			}
			if(!dead && !lakituHit) {
				if(velX < 0)
					goingLeft.runAnimation();
				else
					goingRight.runAnimation();
				if(spikeSpawn) {
					game.addEntity(new LakituSpike(x+(getBounds().width/2),y + (getBounds().height/2),tex, game));
					spikeSpawn = false;
					throwingItem = false;
//					startItemThrowTimer = System.currentTimeMillis();
				}else if(fishSpawn) {
					game.addEntity(new LakituFish(x+(getBounds().width/2),y + (getBounds().height/5),tex, game,velX));
					fishSpawn = false;
					throwingItem = false;
				}else if(bombOmbSpawn) {
					game.addEntity(new LakituBombOmb(x+(getBounds().width/2),y + (getBounds().height/5),tex, game));
					bombOmbSpawn = false;
					throwingItem = false;
				}else if(redShellSpawn) {
					game.addEntity(new LakituRedShell(x+(getBounds().width/2),y + (getBounds().height/5),tex,velX, game));
					redShellSpawn = false;
					throwingItem = false;
				}else if(starSpawn) {
					if(game.ed.size() < 1 && !game.isMarioInvincible())
						game.addEntity(new LakituStar(x+(getBounds().width/2),y + (getBounds().height/5),tex, game,velX));
					else {
						Random rand = new Random();
						int i = rand.nextInt(4);
						switch(i) {
						case 0:
							game.addEntity(new LakituSpike(x+(getBounds().width/2),y + (getBounds().height/2),tex, game));
							break;
						case 1:
							game.addEntity(new LakituFish(x+(getBounds().width/2),y + (getBounds().height/5),tex, game,velX));
							break;
						case 2:
							game.addEntity(new LakituBombOmb(x+(getBounds().width/2),y + (getBounds().height/5),tex, game));
							break;
						case 3:
							game.addEntity(new LakituRedShell(x+(getBounds().width/2),y + (getBounds().height/5),tex,velX, game));
							break;
						default:
							game.addEntity(new LakituSpike(x+(getBounds().width/2),y + (getBounds().height/2),tex, game));
							break;
						}
					}
					starSpawn = false;
					throwingItem = false;
				}
				if(10000 < (System.currentTimeMillis() - startItemThrowTimer) && !throwingItem && throwingItemsTimer < System.currentTimeMillis()) {
					Random rand = new Random();
					int i = rand.nextInt(5);
					switch(i) {
					case 0:
						spikeSpawn = true;
//						redShellSpawn = true;
						break;
					case 1:
						fishSpawn = true;
//						redShellSpawn = true;
						break;
					case 2:
						bombOmbSpawn = true;
//						redShellSpawn = true;
						break;
					case 3:
						redShellSpawn = true;
						break;
					case 4:
						starSpawn = true;
//						redShellSpawn = true;
						break;
					default:
						break;
					}
					throwingItem = true;
					throwingMoreItems = true;
					throwingItemsTimer = System.currentTimeMillis() + 500;
					throwingItemsAnimationTimer = System.currentTimeMillis() + 150;
					int j = rand.nextInt(3);
					if(j == 0) {
						throwingMoreItems = false;
						throwingItemsTimer = 0;
						startItemThrowTimer = System.currentTimeMillis();
					}
					else {
						if(!itemThrowSound.clipIsActive())
							itemThrowSound.play();
					}
				}
				if(throwingItemsAnimator == false && System.currentTimeMillis() < throwingItemsAnimationTimer)
					throwingItemsAnimator = true;
				else if(throwingItemsAnimator == true && throwingItemsAnimationTimer < System.currentTimeMillis())
					throwingItemsAnimator = false;
				if(throwingItemsAnimator) {
					if(velX < 0)
						throwingLeft.runAnimation();
					else
						throwingRight.runAnimation();
				}
			}
			else
				cloud.runAnimation();
		}
		x+=velX;
		y+=velY;
		if(lakituHit) {
			if(!lakituHitSound.getSoundLoopBoolean()) {
				if(!lakituHitSound.clipIsActive())
					lakituHitSound.play();
				lakituHitSound.setSoundLoopBoolean(true);
			}
			if(hit.getCount() == 1 && dead == false) {
				setEntityEDead(true);
				nakedLakitu = new NakedLakituFX(x,y,tex,game,velX);
				game.getController().addEntity(nakedLakitu);
			}
			hit.runAnimation();
		}
		if(distanceBetween != 40)
			distanceBetween = 40;
		if(timer < System.currentTimeMillis() && flyAway == false)
			flyAway = true;
	}

	public void render(Graphics g) {
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < timer || System.currentTimeMillis() < spawnTimer || System.currentTimeMillis() < startItemThrowTimer || System.currentTimeMillis() < throwingItemsAnimationTimer || System.currentTimeMillis() < throwingItemsTimer || System.currentTimeMillis() < bowserSpawnTimer)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
		if((!flyUp && !downBelow) || dead) {
			if(dead)
				cloud.drawAnimation(g, x, y+16, 0);
			else
				cloud.drawAnimation(g, x, y, 0);
		}
		else if(throwingItemsAnimator) {
			if(velX < 0)
				throwingLeft.drawAnimation(g, x, y, 0);
			else
				throwingRight.drawAnimation(g, x, y, 0);
		}
		else if(lakituHit) {
			hit.drawAnimation(g, x, y, 0);
		}
		else {
			if(velX < 0)
				goingLeft.drawAnimation(g, x, y, 0);
			else
				goingRight.drawAnimation(g, x, y, 0);
		}
		return;
	}
	
	public void zoomX(double d) {
		if(x < d && x < d + 250) {
			if(velX < 500) {
				if(velX < 300)
					velX+=9;
				else
					velX+=0.9;
			}
		}
		else if(x < d) {
			if(velX < 0)
				velX *= -1;
			if(velX > 300)
				velX += 5;
			else
				velX -=0.7;
		}
		if(x > d && x > d - 250) {
			if(velX > -500) {
				if(velX > -300)
					velX-=9;
				else
					velX-=0.9;
			}
		}
		else if(x > d) {
			if(velX > 0)
				velX *= -1;
			if(velX > -300)
				velX -= 5;
			else
				velX +=0.7;
		}
		velX = velX/3;
	}

	public void velXDestination(double d) {
		if(x < d && x < d + 250) {
			if(velX < 5) {
				if(velX < 3)
					velX+=0.09;
				else
					velX+=0.009;
			}
		}
		else if(x < d) {
			if(velX < 0)
				velX *= -1;
			if(velX > 3)
				velX += 0.05;
			else
				velX -=0.007;
		}
		if(x > d && x > d - 250) {
			if(velX > -5) {
				if(velX > -3)
					velX-=0.09;
				else
					velX-=0.009;
			}
		}
		else if(x > d) {
			if(velX > 0)
				velX *= -1;
			if(velX > -3)
				velX -= 0.05;
			else
				velX +=0.007;
		}
	}
	
	public void velYDestination(double d) {
		//MAKE y < 0 ~~ y < d-10..?
		if(bowserSpawned && bowserSpawnTimer < System.currentTimeMillis()) {
			if((y > d && velY > 0) || ((y < 0) && velY < 0)) {
				velY *= -1;
				return;
			}
		}
		else {
			if((y > d && velY > 0) || ((y < d-45) && velY < 0)) {
				velY *= -1;
				return;
			}
		}
		if((y < d && y > 0) && (velY < -1 || velY > 1)) {
			double n = 3.5;
			if(y < d && y < d + 250) {
				if(velY < 5) {
					if(velY < 3)
						velY+=0.09*n;
					else
						velY+=0.009*n;
				}
			}
			else if(y < d) {
//				if(velY < 0)
//					velY *= -1;
				if(velY > 3)
					velY += 0.05*n;
				else
					velY -=0.007*n;
			}
			if(y > d && y > d - 250) {
				if(velY > -5) {
					if(velY > -3)
						velY-=0.09*n;
					else
						velY-=0.009*n;
				}
			}
			else if(y > d) {
//				if(velY > 0)
//					velY *= -1;
				if(velY > -3)
					velY -= 0.05*n;
				else
					velY +=0.007*n;
			}
		}
		
	}
	
	public double PythagoreanDistanceFinder(double x1,double x2,double y1, double y2) {
		double x = x1-x2;
		double y = y1-y2;
		double z = 0;
		x = Math.pow(x, 2);
		y = Math.pow(y, 2);
		z = x + y;
		z = Math.sqrt(z);
		return z;
	}
	
	public Rectangle getBounds() {
		if(!flyUp || dead) {
			if(cloud.getCount() == 0)
				return new Rectangle((int)x, (int)y+16, 26, 22);
			else if(cloud.getCount() == 1)
				return new Rectangle((int)x,(int)y+16, 28, 21);
			else if(cloud.getCount() == 2)
				return new Rectangle((int)x,(int)y+16, 26, 20);
			else
				return new Rectangle((int)x,(int)y+16, 24, 21);
		}
		else if(throwingItemsAnimator) {
			return new Rectangle((int)x, (int)y+1, 28, 36);
		}
		else if(lakituHit) {
			return new Rectangle((int)x, (int)y+2, 26, 35);
		}
		else {
			if(velX < 0) {
				if(goingLeft.getCount() == 0)
					return new Rectangle((int)x, (int)y+2, 24, 35);
				else
					return new Rectangle((int)x, (int)y, 26, 37);
			}
			else {
				if(goingRight.getCount() == 0)
					return new Rectangle((int)x, (int)y+2, 24, 35);
				else
					return new Rectangle((int)x, (int)y, 26, 37);
			}
		}
	}
	
	public Rectangle lakituHitBox() {
		if(throwingItemsAnimator) {
			if(velX < 0)
				return new Rectangle((int)x+7, (int)y+1, 16, 22);
			else
				return new Rectangle((int)x+5, (int)y+1, 16, 22);
		}
		else if(lakituHit) {
			return new Rectangle((int)x+5, (int)y+2, 15, 20);
		}
		else {
			if(velX < 0) {
				if(goingLeft.getCount() == 0)
					return new Rectangle((int)x+4, (int)y+2, 15, 20);
				else
					return new Rectangle((int)x+5, (int)y, 15, 22);
			}
			else {
				if(goingRight.getCount() == 0)
					return new Rectangle((int)x+5, (int)y+2, 15, 20);
				else
					return new Rectangle((int)x+6, (int)y, 15, 22);
			}
		}
	}
	

	public Rectangle cloudHitBox() {
		if(throwingItemsAnimator) {
			if(velX < 0)
				return new Rectangle((int)x, (int)y+16, 28, 21);
			else
				return new Rectangle((int)x, (int)y+16, 28, 21);
		}
		else if(lakituHit) {
			return new Rectangle((int)x, (int)y+16, 26, 21);
		}
		else {
			if(velX < 0) {
				if(goingLeft.getCount() == 0)
					return new Rectangle((int)x, (int)y+17, 24, 20);
				else
					return new Rectangle((int)x, (int)y+16, 26, 21);
			}
			else {
				if(goingRight.getCount() == 0)
					return new Rectangle((int)x, (int)y+17, 24, 20);
				else
					return new Rectangle((int)x, (int)y+16, 26, 21);
			}
		}
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
		this.scoreFollowMe = b;
	}

	public String entityName() {
		return "lakitu";
	}

	public void setHitIndicator(boolean b) {
		return;
	}

	public boolean getHitIndicator() {
		return false;
	}

	public void setEntityEDead(boolean dead) {
//		if(!this.dead)
//			y+=16;
		this.dead = dead;
//		close();
//		game.ee.remove(this);
	}
	
	public boolean getEntityEDead() {
		return false;
	}
	
	public void close() {
		entranceSound.close();
		itemThrowSound.close();
		lakituHitSound.close();
	}

}
